package es.upsam.dsm.icsypb_android.controller;

// Importamos las clases de aplicación que vamos a utilizar desde el singleton
import android.content.Context;
import es.upsam.dsm.icsypb_android.connections.InternetConnection;

/**
 * Singleton
 *
 * Basado en https://gist.github.com/Akayh/5566992
 *
 * @brief Patrón singleton para gestionar los datos entre activities
 * @author Kr0n0
 */
public class Singleton {
    // Atributo de clase - Instancia y contexto
    private static Singleton mInstance = null;
    private Context mContext;

    // Lista de atributos de clase a gestionar


    private Singleton(Context context) {
        // Inicializamos los atributos de clase
        mContext = context;
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
     * @brief Clase que comprueba si hay conectividad o no
     * @param
     * @return boolean true si hay conexion false si no la hay
     */
    public boolean comprobarConexion() {
        if (InternetConnection.isNoNetworkAvailable(mContext)) return false;
        else return true;
    }

}
