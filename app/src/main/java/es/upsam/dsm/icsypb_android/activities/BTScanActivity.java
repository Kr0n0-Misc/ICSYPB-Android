package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Baliza;

public class BTScanActivity extends Activity {
    List<Baliza> lBalizas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int posicion;

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

        // 5 - Llamamos al controller para que escanee con la lista de MACs
        datos.escanearBT(this, lBalizas);



    }
}
