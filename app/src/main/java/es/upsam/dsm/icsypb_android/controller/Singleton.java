package es.upsam.dsm.icsypb_android.controller;

// Importamos las clases de aplicación que vamos a utilizar desde el singleton
import android.content.Context;
import android.util.Log;

import java.util.List;

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
 */
public class Singleton {
    // Atributo de clase
    private static Singleton mInstance = null;
    private Context mContext;
    private static String URL_RUTAS = "http://ctcloud.sytes.net/icsypb/rutas.php";
    private CommMgr cManager;
    List<Ruta> lRutas;

    // Lista de atributos de clase a gestionar


    private Singleton(Context context) {
        // Inicializamos los atributos de clase
        mContext = context;
        cManager = new CommMgr();
        lRutas = null;
    }

    /**
     * getInstance() - Devuelve la instancia del singleton
     * @return mInstance
     */
    public static Singleton getInstance(Context context) {
        if(mInstance == null)
        {
            mInstance = new Singleton(context.getApplicationContext());
        }
        return mInstance;
    }

    /* METODOS DE VERIFICACION DE CONECTIVIDAD DE RED */

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
    public boolean recibirRutas() {
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

    public List<Ruta> getlRutas() {
        return lRutas;
    }

}
