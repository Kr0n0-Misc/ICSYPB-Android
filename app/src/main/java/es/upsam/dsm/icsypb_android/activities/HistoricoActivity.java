package es.upsam.dsm.icsypb_android.activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.adapters.HistoricoAdapter;
import es.upsam.dsm.icsypb_android.controller.Singleton;
import es.upsam.dsm.icsypb_android.entities.Tracking;
import es.upsam.dsm.icsypb_android.models.ICSYPBSQLiteHelper;

public class HistoricoActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Singleton datos = Singleton.getInstance(this);
        SQLiteDatabase db;
        ICSYPBSQLiteHelper icsyph;
        List<Tracking> lHistorico;
        Tracking historico;
        TextView texto;
        int posicion;
        int id_ruta;
        String nombre_ruta;

        // Recogemos los parámetros desde la otra Activity
        Bundle parametros = getIntent().getExtras();
        posicion = parametros.getInt("posicion");

        // Asociamos el layout activity_historico
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        // Cambiamos el texto para reflejar la ruta
        nombre_ruta = datos.getRuta(posicion).getDescripcion();
        texto = (TextView) findViewById(R.id.textView3);
        texto.setText("Historico - Ruta "+ nombre_ruta);

        // Iniciamos la BBDD en R/O
        icsyph = new ICSYPBSQLiteHelper(this, "ICSYPBDB", null, 1);
        db = icsyph.getReadableDatabase();

        // Inicializamos los objetos
        lHistorico = new ArrayList<>();
        lHistorico.clear();

        // Recogemos el id de la ruta actual
        id_ruta = datos.getRuta(posicion).getId();
        // Lanzamos query
        Cursor c = db.rawQuery("SELECT * FROM Tracking WHERE ID_RUTA="+ id_ruta +" ORDER BY IDTRACK DESC", null);

        if (c.moveToFirst()) {
            // Existen registros
            do {
                historico = new Tracking();
                // Añadimos los campos de la BBDD al objeto historico
                historico.setMac_usuario(c.getString(1));
                historico.setId_ruta(c.getInt(2));
                historico.setDesc_ruta(c.getString(3));
                historico.setId_baliza(c.getInt(4));
                historico.setMac_baliza(c.getString(5));
                historico.setDesc_baliza(c.getString(6));
                historico.setPosicion(c.getString(7));
                historico.setFecha(c.getString(8));
                // Añadimos el objeto histórico a la lista
                lHistorico.add(historico);
            } while (c.moveToNext());
            HistoricoAdapter adapter = new HistoricoAdapter(datos.getmContext(), R.layout.activity_historico, lHistorico);
            ListView lv = getListView();
            // Asociamos el adapter con el listview
            lv.setAdapter(adapter);
        }
        // Cerramos cursor y acceso a BBDD
        c.close();
        db.close();
    }
}
