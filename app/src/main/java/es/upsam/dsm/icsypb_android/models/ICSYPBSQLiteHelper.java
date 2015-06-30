package es.upsam.dsm.icsypb_android.models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 *
 *
 * Referencias :        https://www.sqlite.org/datatype3.html
 *                      http://www.sgoliver.net/blog/bases-de-datos-en-android-i-primeros-pasos/
 *
 */
public class ICSYPBSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Tracking (" +
            "IDTRACK INTEGER PRIMARY KEY, " +           // IDTRACK INT(6) NOT NULL
            "MAC_USUARIO TEXT, " +      // MAC_USUARIO VARCHAR(20) NOT NULL
            "ID_RUTA INT, " +           // ID_RUTA INT(11) NOT NULL
            "ID_BALIZA INT, " +         // ID_BALIZA INT(11) NOT NULL
            "MAC_BALIZA TEXT, " +       // MAC_BALIZA VARCHAR(20) NOT NULL
            "DESC_BALIZA TEXT," +       // TEXTO_ID
            "POSICION," +               // POSICION
            "FECHA TEXT, " +            // FECHA VARCHAR(30) NOT NULL
            "IDTRACKPUB TEXT " +        // IDTRACKPUB VARCHAR(100)
            ")";
    String sqlDrop = "DROP TABLE IF EXISTS Tracking";



    public ICSYPBSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            // Creamos la tabla si no está creada
            sqLiteDatabase.execSQL(sqlCreate);
        } catch (SQLException e) {
            Log.v("[ICSYPBSQLiteHelper]", "Error en la creación de la base de datos.");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            // Borramos la tabla anterior
            sqLiteDatabase.execSQL(sqlDrop);
            // Creamos la tabla de nuevo
            sqLiteDatabase.execSQL(sqlCreate);
        } catch (SQLException e) {
            Log.v("[ICSYPBSQLiteHelper]", "Error en el update de la base de datos.");
        }
    }
}
