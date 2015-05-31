package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;

public class SplashScreen extends Activity {

    // Timeout para que desaparezca el Splash Screen
    private static int SPLASH_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean conexion = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 1 - Instanciamos Singleton con el contexto de aplicación
        Singleton constructor = Singleton.getInstance(this);

        // 2 - Comprobamos la conexión a Internet
        conexion = constructor.comprobarConexion();
        Log.d("[SplashScreen]", "comprobarConexion() - Devuelve " + conexion);

    }

}
