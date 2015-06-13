package es.upsam.dsm.icsypb_android.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.upsam.dsm.icsypb_android.R;
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
 */
public class RutasActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 - Recogemos Singleton con el contexto de aplicación
        Singleton datos = Singleton.getInstance(this);

        // 2 - Recogemos el ListView del XML - El que tenga id @android:id/list
        ListView listView = getListView();

        // 3 - Usamos un ArrayAdapter para recoger los datos de las rutas del singleton
        ArrayAdapter<Ruta> adapter = new ArrayAdapter<Ruta>(this, R.layout.activity_rutas, datos.getlRutas());

        // 4 - Asociamos el ArrayAdapter al ListView
        listView.setAdapter(adapter);
    }

}
