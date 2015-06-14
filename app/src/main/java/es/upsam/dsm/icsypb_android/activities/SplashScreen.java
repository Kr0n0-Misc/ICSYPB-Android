package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;

/**
 * SplashScreen
 *
 * @brief Activity inicial tipo SplashScreen que instancia el Singleton
 * @author Kr0n0
 *
 * Referencias :    http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 *                  http://devtroce.com/2012/01/19/mensajes-de-dialogo-popup-alertdialog/
 */
public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 1 - Visualizamos el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 2 - Creamos un handler que se ejecute durante SPLASH_TIME_OUT
        new Handler().postDelayed(new Runnable() {

            // Esto se ejecutará una vez cumplido SPLASH_TIME_OUT
            @Override
            public void run() {
                boolean bOK = false;
                Intent i;
                // 2 - Instanciamos Singleton con el contexto de aplicación
                Singleton datos = Singleton.getInstance(SplashScreen.this);

                // 3 - Comprobamos la conexión a Internet
                bOK = datos.comprobarConexion();
                Log.d("[SplashScreen]", "comprobarConexion() - Devuelve " + bOK);
                //TODO Si false Mensaje de error con boton OK y salida de aplicación

                // 4 - Recibimos las rutas
                try {
                    bOK = datos.recibirRutas();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[SplashScreen]", "recibirRutas() - Devuelve " + bOK);

                //TODO Si false Mensaje de error con boton OK y salida de aplicación

                // 5 - Cargar el siguiente activity, lanzarlo y cerrar este
                i = new Intent(SplashScreen.this, RutasActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

