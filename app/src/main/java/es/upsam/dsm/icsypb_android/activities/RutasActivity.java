package es.upsam.dsm.icsypb_android.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.adapters.RutasAdapter;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Ruta;

/**
 * RutasActivity
 *
 * @brief Activity que gestiona el listado de las zonas
 * @author Kr0n0
 *
 * Referencias :    http://www.compiletimeerror.com/2013/08/android-listview-example-using.html#.VXvzuef7m20
 *                  http://www.hermosaprogramacion.com/2014/10/android-listas-adaptadores/
 *                  http://www.framentos.com/en/android-tutorial/2012/07/16/listview-in-android-using-custom-listadapter-and-viewcache/
 *                  https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */
public class RutasActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean bOK = false;
        Intent i;

        // 1 - Visualizamos el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        // 2 - Recogemos Singleton con el contexto de aplicación
        Singleton datos = Singleton.getInstance(this);

        // 3 - Instanciamos el adaptador que vamos a usar para la lista de rutas
        RutasAdapter adapter = new RutasAdapter(this, R.layout.activity_rutas, datos.getlRutas());

        // 4 - Recogemos el listview donde vamos a publicar la lista. Como lo hemos llamado @android:id/list podemos recogerlo con getListView() directamente.
        ListView listView = getListView();

        // 5 - Asociamos el adapter con el listview
        listView.setAdapter(adapter);

    }

}
