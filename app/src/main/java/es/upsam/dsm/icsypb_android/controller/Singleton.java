package es.upsam.dsm.icsypb_android.controller;

// Importamos las clases de aplicación que vamos a utilizar desde el singleton
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
    //private static String URL_RUTAS = "http://ctcloud.sytes.net/backend/Jsonbase";
    private static String URL_RUTAS = "http://192.168.0.241/backend/Jsonbase";
    private Context mContext;
    private CommMgr cManager;
    public List<Ruta> lRutas;
    public List<Tracking> lTracking;
    public Tracking tracking;

    private Singleton(Context context) {
        // Inicializamos los atributos de clase
        mContext = context;
        cManager = new CommMgr();
        lRutas = new ArrayList<>();
        lTracking = new ArrayList<>();
        tracking = new Tracking();
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
     *        GETTERS Y SETTERS           *
     **************************************/

    public List<Tracking> getlTracking() {
        return lTracking;
    }

    public void setlTracking(List<Tracking> lTracking) {
        this.lTracking = lTracking;
    }

    public List<Ruta> getlRutas() {
        return lRutas;
    }

    public Ruta getRuta(int i) {
        return lRutas.get(i);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

}
