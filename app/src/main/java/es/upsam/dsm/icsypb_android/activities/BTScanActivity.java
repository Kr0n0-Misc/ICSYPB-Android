package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Baliza;

public class BTScanActivity extends Activity {
    List<Baliza> lBalizas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int posicion;
        final Button boton;
        final TextView texto;

        // 1 - Visualizamos el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btscan);
        // 2 - Recogemos Singleton con el contexto de aplicación
        final Singleton datos = Singleton.getInstance(this);
        // 3 - Recogemos los parámetros desde la otra Activity
        //     RutasActivity(posicion) -> Posicion del array
        Bundle parametros = getIntent().getExtras();
        posicion = parametros.getInt("posicion");
        // 4 - Recogemos la lista de Balizas de esa ruta
        lBalizas = datos.getRuta(posicion).getBalizas();
        // 5 - Recogemos el boton y el texto
        boton = (Button) findViewById(R.id.button);
        texto = (TextView) findViewById(R.id.textView2);
        // 6 - Asociamos el método al botón
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

                    // Cambiamos el botón a PARAR
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
                    datos.stopBTScan();
                    boton.setEnabled(false);
                    texto.setText("Seleccione los puntos y pulse guardar");
                    boton.setText("GUARDAR");
                    //TODO
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
}
