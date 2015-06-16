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

                if (bTexto.equals("PARAR")) {
                    // Estamos en modo escaneo - Paramos Bluetooth
                    datos.stopBTScan();
                    // Deshabilitamos botón
                    boton.setEnabled(false);
                    // Cambiamos texto a GUARDAR
                    texto.setText("Seleccione los puntos y pulse guardar");
                    boton.setText("GUARDAR");
                    // TODO Visualizamos el ListView con los puntos
                }
                else { // bTexto.equals("GUARDAR");
                    // Estamos en modo lista - Recogemos los pulsados
                    //TODO Recoger los pulsados del ListView
                    //TODO Crear JSon y enviarlo al servidor
                }
            }
        });
        // 7 - Llamamos al controller para que escanee con la lista de MACs
        datos.escanearBT(this, lBalizas);

    }
}
