package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Baliza;
import es.upsam.dsm.icsypb_android.entities.Tracking;


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
public class BTScanActivity extends Activity {
    List<Baliza> lBalizas;
    private BluetoothAdapter mBtAdapter;
    private static final int REQUEST_ENABLE_BT = 1; // Necesario para activación
    private static String mac_dispositivo;
    SimpleDateFormat sdf;
    Singleton datos = Singleton.getInstance(this);
    Tracking tracking;
    int posicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button boton;
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
        // 4 - Recogemos Singleton con el contexto de aplicación
        //final Singleton datos = Singleton.getInstance(this);
        // 5 - Recogemos los parámetros desde la otra Activity
        //     RutasActivity(posicion) -> Posicion del array
        Bundle parametros = getIntent().getExtras();
        posicion = parametros.getInt("posicion");
        // 6 - Recogemos la lista de Balizas de esa ruta
        lBalizas = datos.getRuta(posicion).getBalizas();
        // 7 - Recogemos el boton y el texto
        boton = (Button) findViewById(R.id.button);
        texto = (TextView) findViewById(R.id.textView2);
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
                    //datos.escanearBT(this, lBalizas);
                    // TODO
                    mBtAdapter.startDiscovery();
                    // Cambiamos el botón a PARAR
                    texto.setText("Pulse el botón PARAR cuando termine");
                    boton.setText("PARAR");
                }

                /* 2 - PARAR
                   ---------
                - Paramos el bluetooth
                - Desregistramos el broadcast receiver
                - Registramos los datos en la BBDD
                - Rellenamos el ListView con los datos de la BBDD (nuevos y antiguos)
                - Cambiamos el texto principal y el del botón a GUARDAR
                - Deshabilitamos el botón hasta que el onClick del ListView lo active (si hay alguno seleccionado)
                 */
                if (bTexto.equals("PARAR")) {
                    // 1 - Deshabilitamos la búsqueda bluetooth
                    if (mBtAdapter.isDiscovering())
                        mBtAdapter.cancelDiscovery();
                    // 2 - Desregistramos el BroadcastReceiver bluetooth
                    getBaseContext().unregisterReceiver(mReceiver_reset);
                    getBaseContext().unregisterReceiver(mReceiver_scan);
                    boton.setEnabled(false);
                    texto.setText("Seleccione los puntos y pulse guardar");
                    boton.setText("GUARDAR");
                    //TODO PROPAGAR MAC
                }

                /* 3 - GUARDAR
                   -----------
                - Recorremos el array de ListView para ver cuales son los elegidos
                - Creamos el HASH y el JSon para hacer push al server
                - Enviamos los datos al server
                - Si ok, mensaje y vamos al activity de rutas (RutasActivity)
                 */
                if (bTexto.equals("GUARDAR")) {
                    //TODO
                }
            }
        });
    }

    /**
     *
     * @param lBalizas
     * @param cadena
     * @return
     */
    public boolean buscarArray(List<Baliza> lBalizas, String cadena) {
        boolean resultado = false;
        String mac;

        // 1 - Recorremos el ArrayList de lBalizas
        for (int i=0;i<lBalizas.size();i++) {
            mac = lBalizas.get(i).getMac();
            if (mac.equalsIgnoreCase(cadena)) {
                return (true);
            }
        }
        return(false);
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
        Boolean encontrado = false;
        String mac_actual;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Calendar cal = Calendar.getInstance();

            // Si se detecta un dispositivo
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mac_actual = device.getAddress();
                encontrado = buscarArray(lBalizas, mac_actual);
                if (encontrado) {
                    Log.v("[BTScan]", "MAC ECONTRADA " + mac_actual);
                    Log.v("[BTScan]", "MAC EQUIPO" + mac_dispositivo);
                    // Instanciamos nuevo objeto Tracking
                    tracking = new Tracking();
                    tracking.setMac_usuario(mac_dispositivo);
                    tracking.setId_ruta(datos.getRuta(posicion).getId());
                    //tracking.setId_baliza();
                    tracking.setMac_baliza(mac_actual);
                    sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String strDate = sdf.format(cal.getTime());
                    tracking.setFecha(strDate.toString());
                    //tracking.setIdtrackpub();
                    //TODO Meter en BBDD
                }
            }
        }
    };

}


