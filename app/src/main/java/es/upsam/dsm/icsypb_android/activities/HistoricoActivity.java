package es.upsam.dsm.icsypb_android.activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        icsyph = new ICSYPBSQLiteHelper(this, "ICSYPBDB", null, 1);
        db = icsyph.getReadableDatabase();

        // Inicializamos los objetos
        lHistorico = new ArrayList<>();
        lHistorico.clear();

        // Lanzamos query
        Cursor c = db.rawQuery("SELECT * FROM Tracking", null);

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
