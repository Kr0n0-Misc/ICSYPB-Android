package es.upsam.dsm.icsypb_android.connections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * InternetConnection
 *
 * @brief Clase que gestiona todas las conexiones a Internet del programa
 * @author Kr0n0
 */
public class InternetConnection {

    /** isNotNetworkAvailable
     *
     * @brief Método para comprobar si hay conexión de red
     * @param c Contexto
     * @return null si no hay conectividad, isConnected() si hay conectividad
     */
    public static boolean isNoNetworkAvailable(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }

}
