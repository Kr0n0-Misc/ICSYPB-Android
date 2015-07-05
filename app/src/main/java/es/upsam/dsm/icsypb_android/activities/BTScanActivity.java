package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.adapters.TrackingAdapter;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Baliza;
import es.upsam.dsm.icsypb_android.entities.Tracking;
import es.upsam.dsm.icsypb_android.models.ICSYPBSQLiteHelper;
import es.upsam.dsm.icsypb_android.utilities.GSONUtil;


/**
 * BTScanActivity
 *
 *
 *
 * Referencias :    http://stackoverflow.com/questions/29122031/how-can-i-show-the-scanned-bluetooth-devices-in-the-listview
 *                  http://stackoverflow.com/questions/3285580/how-to-periodically-scan-for-bluetooth-devices-on-android?answertab=oldest#tab-top
 *                  http://www.programcreek.com/java-api-examples/index.php?api=android.bluetooth.BluetoothAdapter
 *
 *
 */
public class BTScanActivity extends ListActivity {
    List<Baliza> lBalizas;
    private BluetoothAdapter mBtAdapter;
    private static final int REQUEST_ENABLE_BT = 1; // Necesario para activación
    private static String mac_dispositivo;
    SimpleDateFormat sdf;
    Singleton datos = Singleton.getInstance(this);
    int posicion;
    Tracking tracking = new Tracking();
    SQLiteDatabase db;
    ICSYPBSQLiteHelper icsyph;
    ArrayList<String> macs_registradas = new ArrayList<>();
    List<Tracking> lTracking_envio = new ArrayList<>();
    List<Tracking> lTracking_registro = new ArrayList<>();
    GSONUtil gsonUtil = new GSONUtil();
    String json_envio;
    private static String URL_BACKEND = "http://ctcloud.sytes.net/backend/Jsontobbdd";
    String clave_hash_pub;
    int numero_registros = 0;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button boton, historico;
        final TextView texto;

        // 1 - Registramos el intent para bluetooth
        IntentFilter intentFilter_reset = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(this.mReceiver_reset, intentFilter_reset);
        IntentFilter intentFilter_scan = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(this.mReceiver_scan, intentFilter_scan);
        // 2 - Inicializamos el dispositivo Bluetooth y recogemos la MAC
        mBtAdapter= BluetoothAdapter.getDefaultAdapter();
        mac_dispositivo = mBtAdapter.getAddress();
        // 3 - Habilitamos bluetooth si no está habilitado
        if (!(mBtAdapter.isEnabled())){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        // 3 - Visualizamos el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btscan);
        // 4 - Instanciamos la base de datos en modo escritura
        icsyph = new ICSYPBSQLiteHelper(this, "ICSYPBDB", null, 1);
        db = icsyph.getWritableDatabase();
        // 5 - Recogemos los parámetros desde la otra Activity
        Bundle parametros = getIntent().getExtras();
        posicion = parametros.getInt("posicion");
        // 6 - Recogemos la lista de Balizas de esa ruta
        lBalizas = datos.getRuta(posicion).getBalizas();
        lTracking_registro = datos.getlTracking();
        // 7 - Recogemos el boton y el texto
        boton = (Button) findViewById(R.id.button);
        texto = (TextView) findViewById(R.id.textView2);
        historico = (Button) findViewById(R.id.button2);
        historico.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(BTScanActivity.this, HistoricoActivity.class);
                 i.putExtra("posicion", posicion);
                 startActivity(i);
             }
         }
        );

        // 8 - Asociamos el método al botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bTexto = boton.getText().toString();

                /* 1 - EMPEZAR
                   -----------
                - Comprobamos el bluetooth
                - Iniciamos el escaneo
                - Registramos el broadcast receiver
                - Cambiamos el texto del botón a PARAR
                */
                if (bTexto.equals("EMPEZAR")) {
                    mBtAdapter.startDiscovery();
                    // Cambiamos el botón a PARAR
                    texto.setText("Pulse el botón PARAR cuando termine");
                    boton.setText("PARAR");
                    // Creamos la tarea de borrado del array y del numero de registros
                    TimerTask tBorradoMacs = new TimerTask() {
                        @Override
                        public void run() {
                            /*
                            for (int i=0;i<macs_registradas.size();i++) {
                                borrarMacTracking(lTracking_registro, macs_registradas.get(i));
                            }
                            numero_registros = 0;
                            */
                            macs_registradas.clear();
                        }
                    };
                    // Lanzamos la tarea cada 3 minutos (180000 milisegundos)
                    timer = new Timer();
                    //timer.scheduleAtFixedRate(tBorradoMacs, 0, 10000);
                    timer.scheduleAtFixedRate(tBorradoMacs, 0, 180000);
                }

                /* 2 - PARAR
                   ---------
                - Paramos el bluetooth
                - Desregistramos el broadcast receiver
                - Registramos los datos en la BBDD -> ESTO LO HACE EL BROADCAST RECEIVER YA!!
                - Rellenamos el ListView con los datos de la BBDD (nuevos y antiguos)
                - Cambiamos el texto principal y el del botón a GUARDAR
                - Deshabilitamos el botón hasta que el onClick del ListView lo active (si hay alguno seleccionado)
                 */
                if (bTexto.equals("PARAR")) {
                    // Paramos el timer
                    timer.cancel();
                    // Deshabilitamos la búsqueda bluetooth
                    if (mBtAdapter.isDiscovering())
                        mBtAdapter.cancelDiscovery();
                    // Desregistramos el BroadcastReceiver bluetooth
                    getBaseContext().unregisterReceiver(mReceiver_reset);
                    getBaseContext().unregisterReceiver(mReceiver_scan);
                    //boton.setEnabled(false);
                    texto.setText("Seleccione los puntos y pulse guardar");
                    boton.setText("GUARDAR");
                    historico.setVisibility(View.VISIBLE);
                    historico.setClickable(true);

                    final TrackingAdapter trackingAdapter = new TrackingAdapter(getBaseContext(), R.layout.activity_btscan, lTracking_registro);
                    final ListView lv = getListView();
                    // 5 - Asociamos el adapter con el listview
                    lv.setAdapter(trackingAdapter);
                    lv.setClickable(true);
                    // Listado clickeable
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            boolean selected;
                            // Recogemos los TextView
                            TextView tvMAC_BALIZA = (TextView) view.findViewById(R.id.tvMAC_BALIZA);
                            TextView tvDESC_Baliza = (TextView) view.findViewById(R.id.tvDESC_BALIZA);
                            TextView tvCuenta = (TextView) view.findViewById(R.id.tvCUENTA);
                            TextView tvPosicion = (TextView) view.findViewById(R.id.tvPOSICION);

                            if (tvMAC_BALIZA.getCurrentTextColor() == Color.BLACK) {
                                // SELECCIONADO -> NO SELECCIONADO
                                tvMAC_BALIZA.setTextColor(Color.GRAY);
                                tvDESC_Baliza.setTextColor(Color.GRAY);
                                tvCuenta.setTextColor(Color.GRAY);
                                tvPosicion.setTextColor(Color.GRAY);
                                lv.deferNotifyDataSetChanged();
                                // Eliminamos de la lista
                                lTracking_envio.remove(lTracking_registro.get(i));
                            }
                            else {
                                // NO SELECCIONADO -> SELECCIONADO
                                tvMAC_BALIZA.setTextColor(Color.BLACK);
                                tvDESC_Baliza.setTextColor(Color.BLACK);
                                tvCuenta.setTextColor(Color.BLACK);
                                tvPosicion.setTextColor(Color.BLACK);
                                lv.deferNotifyDataSetChanged();
                                lTracking_envio.add(lTracking_registro.get(i));
                                // Insertamos en la lista
                            }
                        }
                    });
                    db.close();
                }

                /* 3 - GUARDAR
                   -----------
                - Recorremos el array de ListView para ver cuales son los elegidos
                - Creamos el HASH y el JSon para hacer push al server
                - Enviamos los datos al server
                - Si ok, mensaje y vamos al activity de rutas (RutasActivity)
                 */
                if (bTexto.equals("GUARDAR")) {
                    // Generamos el hash
                    clave_hash_pub = "";
                    Calendar cal = Calendar.getInstance();
                    String fecha_actual;
                    fecha_actual = cal.getTime().toString();
                    clave_hash_pub = datos.md5(fecha_actual);
                    // Copiamos al portapapeles la clave
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("RUTA", clave_hash_pub);
                    clipboard.setPrimaryClip(clip);
                    // Añadimos la clave a la lista de Tracking
                    actualizarHash(lTracking_envio, clave_hash_pub);
                    // Enviamos el JSON
                    json_envio = gsonUtil.ob2json(lTracking_envio);
                    sendJson(datos.URL_RUTAS, json_envio);
                    // Mensaje muestra
                    datos.Trazas("Registros enviados - Clave pública "+ clave_hash_pub +" copiada al portapapeles.");
                }
            }
        });
    }

    /**
     * buscarArray
     *
     * @param lBalizas Lista de balizas
     * @param cadena Cadena de texto a buscar
     * @return posicion
     */
    public int buscarArray(List<Baliza> lBalizas, String cadena) {
        String mac;

        // 1 - Recorremos el ArrayList de lBalizas
        for (int i=0;i<lBalizas.size();i++) {
            mac = lBalizas.get(i).getMac();
            if (mac.equalsIgnoreCase(cadena)) {
                // Devuelve la posición de la baliza encontrada
                return (i);
            }
        }
        // Devuelve Integer.MAX_VALUE si no la ha encontrado
        return(Integer.MAX_VALUE);
    }

    public void borrarMacTracking(List<Tracking> lTracking, String mac_baliza) {
        String mac;

        // 1 - Recorremos el ArrayList de lTracking
        for (int i=0;i<lTracking.size();i++) {
            mac = lTracking.get(i).getMac_baliza();
            if (mac.equalsIgnoreCase(mac_baliza)) {
                // Borra del tracking la baliza
                lTracking.remove(i);
            }
        }
    }




    /**
     *
     * @param lTracking_envio
     * @param clave_hash_pub
     * @return
     */
    public void actualizarHash(List<Tracking> lTracking_envio, String clave_hash_pub) {

        // Recorremos el ArrayList de lTracking_envio actualizando clave_hash_pub
        for (int i=0;i<lTracking_envio.size();i++) {
            lTracking_envio.get(i).setIdtrackpub(clave_hash_pub);
        }
    }




    /**
     *
     * @param macs_registradas
     * @param cadena
     * @return
     */
    public boolean buscarMACRegistrada(ArrayList<String> macs_registradas, String cadena) {
        String mac;

        // 1 - Recorremos el ArrayList de macs_registradas
        for (int i = 0; i < macs_registradas.size(); i++) {
            mac = macs_registradas.get(i);
            if (mac.equalsIgnoreCase(cadena)) {
                // Es igual la MAC, por lo que la filtramos y devolvemos FALSE
                return false;
            }
        }
        // No está filtrada, devolvemos true
        return true;
    }


    /**
     *
     */
    public final BroadcastReceiver mReceiver_reset = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.v("[BTScan]", "Reiniciando discover");
                mBtAdapter.startDiscovery();
                }
            }
    };

    /**
     *
     */
    public final BroadcastReceiver mReceiver_scan = new BroadcastReceiver() {
        int id_baliza;
        int pos_array_baliza;
        String mac_actual;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Calendar cal = Calendar.getInstance();
            sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            // Si se detecta un dispositivo
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mac_actual = device.getAddress().toUpperCase();
                pos_array_baliza = buscarArray(lBalizas, mac_actual);

                // Si la MAC pertenece a la ruta seleccionada
                if (pos_array_baliza != Integer.MAX_VALUE)
                {
                    //TRUE solo si la MAC no está registrada
                    if (buscarMACRegistrada(macs_registradas, mac_actual)) {

                        // BALIZA ENCONTRADA - RECUPERAMOS LOS DATOS
                        numero_registros++;
                        tracking = new Tracking();
                        datos.Trazas("BALIZA " + lBalizas.get(pos_array_baliza).getTexto() + " ENCONTRADA (" + numero_registros + ")");
                        Log.v("[BTScan]", "MAC ECONTRADA " + mac_actual);
                        // Rellenamos el objeto tracking
                        tracking.setMac_usuario(mac_dispositivo);
                        tracking.setId_ruta(datos.getRuta(posicion).getId());
                        tracking.setDesc_ruta(datos.getRuta(posicion).getDescripcion());
                        tracking.setId_baliza(lBalizas.get(pos_array_baliza).getId());
                        tracking.setDesc_baliza(lBalizas.get(pos_array_baliza).getTexto());
                        tracking.setMac_baliza(mac_actual.toUpperCase());
                        tracking.setFecha(sdf.format(cal.getTime()));
                        tracking.setPosicion(lBalizas.get(pos_array_baliza).getPosicion());

                        // Añadimos la MAC al array de existentes para filtrar en posteriores busquedas
                        macs_registradas.add(mac_actual.toUpperCase());

                        // Añadimos el objeto al singleton como primer registro para ver el orden luego bien
                        lTracking_registro.add(0, tracking);

                        // Insertamos los datos en la BBDD
                        try {
                            db.execSQL("INSERT INTO Tracking (" +
                                            "MAC_USUARIO, " +
                                            "ID_RUTA, " +
                                            "DESC_RUTA, " +
                                            "ID_BALIZA, " +
                                            "MAC_BALIZA, " +
                                            "DESC_BALIZA, " +
                                            "POSICION, " +
                                            "FECHA, " +
                                            "IDTRACKPUB" +
                                            ") " +
                                            "VALUES ('" +
                                            "" + tracking.getMac_usuario().toUpperCase() + "'," +
                                            "'" + tracking.getId_ruta() + "'," +
                                            "'" + tracking.getDesc_ruta() + "'," +
                                            "'" + tracking.getId_baliza() + "'," +
                                            "'" + tracking.getMac_baliza().toUpperCase() + "'," +
                                            "'" + tracking.getDesc_baliza() + "'," +
                                            "'" + tracking.getPosicion() + "'," +
                                            "'" + tracking.getFecha() + "'," +
                                            "" + null + ")"
                            );
                        } catch (SQLException e) {
                            Log.v ("[BTScanActivity]", "Error en SQLite");
                        }

                    }

                }
            }
        }
    };

    protected void sendJson(final String url, final String json_envio) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare(); //For Preparing Message Pool for the child Thread
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;

                try{
                    HttpPost post = new HttpPost(URL_BACKEND);
                    StringEntity se = new StringEntity(json_envio);
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);

                    if(response!=null){
                        InputStream in = response.getEntity().getContent(); //Get the data in the entity
                    }

                }catch(Exception e) {
                e.printStackTrace();
                Log.v("Error", "Cannot Estabilish Connection");
            }
            Looper.loop(); //Loop in the message queue
        }
    };
    t.start();
}
}


