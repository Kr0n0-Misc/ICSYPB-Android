package es.upsam.dsm.icsypb_android.models;

// https://www.sqlite.org/datatype3.html


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ICSYPBSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Tracking (" +
            "IDTRACK INT, " +           // IDTRACK INT(6) NOT NULL
            "MAC_USUARIO TEXT, " +      // MAC_USUARIO VARCHAR(20) NOT NULL
            "ID_RUTA INT, " +           // ID_RUTA INT(11) NOT NULL
            "ID_BALIZA INT, " +         // ID_BALIZA INT(11) NOT NULL
            "MAC_BALIZA TEXT, " +       // MAC_BALIZA VARCHAR(20) NOT NULL
            "FECHA TEXT, " +            // FECHA VARCHAR(30) NOT NULL
            "IDTRACKPUB TEXT " +        // IDTRACKPUB VARCHAR(100)
            ")";
    String sqlDrop = "DROP TABLE IF EXISTS Tracking";



    public ICSYPBSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creamos la tabla si no está creada
        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Borramos la tabla anterior
        sqLiteDatabase.execSQL(sqlDrop);
        // Creamos la tabla de nuevo
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
