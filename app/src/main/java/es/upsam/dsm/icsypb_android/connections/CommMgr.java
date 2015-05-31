package es.upsam.dsm.icsypb_android.connections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * CommMgr
 *
 * @brief Clase que gestiona todas las conexiones a Internet del programa
 * @author Kr0n0
 *
 * Referencias :    https://github.com/learning-layers/android-openid-connect/blob/master/app/src/main/java/com/lnikkila/oidcsample/APIUtility.java
 *
 */
public class CommMgr {


    /** isNotNetworkAvailable
     *
     * @brief Método para comprobar si hay conexión de red
     * @param context Contexto de la aplicación
     * @return null si no hay conectividad, isConnected() si hay conectividad
     */
    public static boolean isNoNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }

    /** getJSON
     *
     * @param context Contexto de la aplicación
     * @param direccion URL para descargar el JSON
     * @return TODO
     */
    public boolean getJSON (Context context, String direccion) {

        // 1 - Descarga del fichero JSON
        try {
            String JSON = this.descargaFichero(direccion);
        }
        catch (Exception e){
            Log.v("[CommMgr]", "Fallo en descarga de fichero " + direccion);
        }

        // 2 - Tratamiento de JSON

    }

    /**
     * descargaFichero
     *
     * @brief Funcion auxiliar que descarga el fichero y lo pasa a formato cadena
     * @param direccion URL en texto del fichero a descargar
     * @return Cadena de texto del fichero
     * @throws IOException
     */
    private String descargaFichero (String direccion) throws IOException {

        StringBuilder response  = new StringBuilder();

        URL url = new URL(direccion);
        HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
        if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8192);
            String strLine = null;
            while ((strLine = input.readLine()) != null)
            {
                response.append(strLine);
            }
            input.close();
        }
        return response.toString();
    }

}
