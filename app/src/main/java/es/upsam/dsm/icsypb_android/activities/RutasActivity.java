package es.upsam.dsm.icsypb_android.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
 *                  http://stackoverflow.com/questions/21295328/android-listview-with-onclick-items
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
        final Singleton datos = Singleton.getInstance(this);

        // 3 - Instanciamos el adaptador que vamos a usar para la lista de rutas
        RutasAdapter adapter = new RutasAdapter(this, R.layout.activity_rutas, datos.getlRutas());

        // 4 - Recogemos el listview donde vamos a publicar la lista. Como lo hemos llamado @android:id/list podemos recogerlo con getListView() directamente.
        ListView listView = getListView();

        // 5 - Asociamos el adapter con el listview
        listView.setAdapter(adapter);

        // 6 - Creamos listener para pulsación de cada Ruta
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //datos.Trazas("El ID es "+ datos.getRuta(position).getID());
                //datos.Trazas("La descripcion es "+ datos.getRuta(position).getDescripcion());
            }
        });


    }

}
