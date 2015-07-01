package es.upsam.dsm.icsypb_android.utilities;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;

import es.upsam.dsm.icsypb_android.entities.Ruta;
import es.upsam.dsm.icsypb_android.entities.Tracking;

/**
 * GSONUtil
 *
 * @brief Clase GSONUtil para gestionar el tratamiento de los fichero JSON
 * @author Kr0n0
 *
 * Referencias :    http://www.javacodegeeks.com/2013/12/storing-objects-in-android.html
 *                  https://geekytheory.com/trabajando-con-json-en-android-gson/
 *                  http://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
 *                  http://stackoverflow.com/questions/3763937/gson-and-deserializing-an-array-of-objects-with-arrays-in-it
 */
public class GSONUtil {
    // Atributos de clase
    Gson gson;

    /**
     *
     */
    public GSONUtil() {
        this.gson = new Gson();
    }

    /**
     * @param gson
     */
    public GSONUtil(Gson gson) {
        this.gson = gson;
    }

    /**
     * @param sRutas
     * @param Ruta
     * @return
     */
    public List<Ruta> json2obj(String sRutas, Object Ruta) {
        List<Ruta> lRuta;

        // 1 - Convertimos la cadena JSON sRutas a la lista de objetos Rutas
        Ruta[] aRuta = gson.fromJson(sRutas, Ruta[].class);
        // 2 - Pasamos el Array a objeto List<Ruta>
        lRuta = new ArrayList<Ruta>(Arrays.asList(aRuta));
        // 3 - Devolvemos la lista de Rutas
        return(lRuta);
    }

    public String ob2json(List<Tracking> lTracking) {
        String json_envio = "";

        try {
            json_envio = gson.toJson(lTracking);
        } catch (JsonParseException e) {
            Log.v("[GSon]", "Error en parseo");
        }
        return(json_envio);
    }


}
