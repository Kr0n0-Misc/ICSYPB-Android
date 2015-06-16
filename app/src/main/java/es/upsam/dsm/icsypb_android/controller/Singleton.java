package es.upsam.dsm.icsypb_android.controller;

// Importamos las clases de aplicación que vamos a utilizar desde el singleton
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Importamos las clases Entity que vamos a utilizar desde el singleton
import es.upsam.dsm.icsypb_android.entities.*;

// Importamos las clases de conector y utilidades
import es.upsam.dsm.icsypb_android.connections.CommMgr;
import es.upsam.dsm.icsypb_android.utilities.GSONUtil;
import es.upsam.dsm.icsypb_android.utilities.BluetoothDiscovery;

/**
 * Singleton
 *
 * @brief Patrón singleton para gestionar los datos entre activities
 * @author Kr0n0
 *
 * Referencias :    https://gist.github.com/Akayh/5566992
 *                  http://possiblemobile.com/2013/06/context/
 *                  http://stackoverflow.com/questions/5991417/how-to-get-response-from-any-url-in-json-for-android-and-than-after-reponse-i-wa
 *                  http://developer.android.com/guide/topics/ui/notifiers/toasts.html
 */
public class Singleton {
    // ATRIBUTOS DE CLASE
    private static Singleton mInstance = null;
    //private static String URL_RUTAS = "http://ctcloud.sytes.net/icsypb/rutas.php";
    private static String URL_RUTAS = "http://192.168.0.241/ejemplo.json";
    private Context mContext;
    private CommMgr cManager;
    List<Ruta> lRutas;
    List<Tracking> lTracking;
    BluetoothDiscovery BTD;


    private Singleton(Context context) {
        // Inicializamos los atributos de clase
        mContext = context;
        cManager = new CommMgr();
        lRutas = new ArrayList<>();
        lTracking = new ArrayList<>();
    }

    /**
     * getInstance()
     *
     * @brief Devuelve la instancia del singleton
     * @return mInstance
     */
    public static Singleton getInstance(Context context) {
        if(mInstance == null)
        {
            mInstance = new Singleton(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * comprobarConexion()
     *
     * @brief Método que comprueba si hay conectividad o no
     * @return boolean true si hay conexion false si no la hay
     */
    public boolean comprobarConexion() {
        if (CommMgr.isNoNetworkAvailable(mContext)) return false;
        else return true;
    }

    /**
     * recibirRutas()
     *
     * @brief Método que descarga el JSON de las rutas, lo deserializa y lo instancia en ¿ARRAYLIST?
     * @return boolean true si se han recibido false si hay error
     */
    public boolean recibirRutas() throws ExecutionException, InterruptedException {
        String sRutas;

        // 1 - Descargar el JSON
        sRutas = cManager.getJSON(mContext, URL_RUTAS);
        if (sRutas != null)
        {
            // 2 - Instanciamos objeto GSON para conversión
            GSONUtil objGSON = new GSONUtil();
            // 3 - Convertimos rutas JSON a objetos
            lRutas = objGSON.json2obj(sRutas, Ruta.class);
            if (lRutas != null) {
                // 4 - Devolvemos true y terminamos el método
                return(true);
            }
            else {
                Log.d("[Singleton]", "Tratamiento de JSON2OBJ" + sRutas + "erróneo.");
                return false;
            }
        }
        else {
            Log.d("[Singleton]", "Descarga de " + URL_RUTAS + " fallida.");
            return false;
        }
    }

    /**
     * escanearBT
     *
     * @param activity Actividad
     * @param lBalizas
     * @return
     */
    public void escanearBT (Activity activity, List<Baliza> lBalizas) {
        ArrayList<String> alMACs = null;

        // 1 - Recogemos las MAC de la lista de balizas
        alMACs = this.recuperarMACs(lBalizas);

        // 2 - Instanciamos el Discovery de Bluetooth con la lista de MACs
        BTD = new BluetoothDiscovery(activity, alMACs);

        // 3 - TODO
    }

    /**
     * recuperarMACs
     *
     * @brief Recupera las MAC Address del array de balizas
     *
     * @param lBalizas
     * @return ArrayList<String>
     */
    private  ArrayList<String> recuperarMACs (List<Baliza> lBalizas) {
        int i;
        ArrayList<String> alMACs = null;

        // Recorremos el array de Balizas para ir añadiendo las MAC al array
        for (i=0;i<lBalizas.size();i++) {
            alMACs.add(lBalizas.get(i).getMac());
        }
        return (alMACs);
    }


    /**
     * Trazas
     *
     * @brief Deja un mensaje Toast en la aplicación de duración corta
     * @param mensaje Mensaje de tipo String
     * @return void
     */
     public void Trazas (String mensaje) {
         Toast toast = Toast.makeText(mContext, mensaje, Toast.LENGTH_SHORT);
         toast.show();
     }


    /**************************************
     *              BYPASS                *
     **************************************/

    public void stopBTScan () {
        BTD.stopBTScan();
    }

    /**************************************
     *        GETTERS Y SETTERS           *
     **************************************/

    public List<Ruta> getlRutas() {
        return lRutas;
    }

    public Ruta getRuta(int i) {
        return lRutas.get(i);
    }

    public List<Tracking> getlTracking() {
        return lTracking;
    }

    public void setlTracking(List<Tracking> lTracking) {
        this.lTracking = lTracking;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

}
