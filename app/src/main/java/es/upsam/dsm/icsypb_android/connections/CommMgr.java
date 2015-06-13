package es.upsam.dsm.icsypb_android.connections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * CommMgr
 *
 * @brief Clase que gestiona todas las conexiones a Internet del programa
 * @author Kr0n0
 *
 * Referencias :    https://github.com/learning-layers/android-openid-connect/blob/master/app/src/main/java/com/lnikkila/oidcsample/APIUtility.java
 *                  http://stackoverflow.com/questions/5991417/how-to-get-response-from-any-url-in-json-for-android-and-than-after-reponse-i-wa
 *                  http://stackoverflow.com/questions/24850787/use-httpurlconnection-asynctask-on-android
 *                  http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
 *
 */
public class CommMgr {
    public static String js_descargado;

    /**
     * isNotNetworkAvailable
     *
     * @param context Contexto de la aplicación
     * @return null si no hay conectividad, isConnected() si hay conectividad
     * @brief Método para comprobar si hay conexión de red
     */
    public static boolean isNoNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }

    /**
     * getJSON
     *
     * @param context   Contexto de la aplicación
     * @param direccion URL para descargar el JSON
     * @return String js_descargado
     */
    public String getJSON(Context context, String direccion) throws ExecutionException, InterruptedException {
        String [] params;
        descargaFicheroTask descarga;

        // 1 - Inicializamos el array de parámetros para el AsyncTask con la direccion
        params = new String[1];
        params[0] = direccion;

        // 2 - Lanzamos el task de descarga con el parámetro de dirección
        descarga = new descargaFicheroTask();

        // 3 - Bloqueamos el UI Thread hasta que esté descargado el JSON
        js_descargado = descarga.execute(params).get();

        // 4 - Devolvemos el JSON en formato String
        return(js_descargado);
    }

    /**
     * descargaFicheroTask
     *
     * @brief Tarea AsyncTask para descargar el fichero correspondiente
     * Parámetros
     *  String -> Array de urls
     *  Void   -> No gestionado
     *  String -> Cadena JSON desde fichero
     *
     */
    private class descargaFicheroTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder response = new StringBuilder();
            HttpURLConnection httpconn;
            BufferedReader input;
            String strLine, cadenaJson = "";
            int httpResponse;
            URL url;

            try {
                // 1 - Convertimos la cadena en URL
                url = new URL(urls[0]);
                // 2 - Establecemos la conexión
                httpconn = (HttpURLConnection) url.openConnection();
                // 3 - Leemos el archivo en un buffer
                input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()), 8192);
                strLine = null;
                while ((strLine = input.readLine()) != null) {
                    response.append(strLine);
                }
                input.close();
            }
            catch (MalformedURLException e) {
                //TODO Gestión MalformedURLExecption
                e.printStackTrace();
                return (null);
            }
            catch (IOException e) {
                //TODO Gestión IOException
                e.printStackTrace();
                return (null);
            }
            // 4 - Devolvemos la cadena en formato string
            cadenaJson = response.toString();
            return cadenaJson;
        }
    }



}
