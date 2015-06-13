package es.upsam.dsm.icsypb_android.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import es.upsam.dsm.icsypb_android.entities.Ruta;

/**
 * GSONUtil
 *
 * @brief Clase GSONUtil para gestionar el tratamiento de los fichero JSON
 * @author Kr0n0
 *
 * Referencias :    http://www.javacodegeeks.com/2013/12/storing-objects-in-android.html
 *                  https://geekytheory.com/trabajando-con-json-en-android-gson/
 *                  http://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
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
     *
     * @param gson
     */
    public GSONUtil(Gson gson) {
        this.gson = gson;
    }

    /**
     *
     * @param sRutas
     * @param Ruta
     * @return
     */
    public List<Ruta> json2obj (String sRutas, Object Ruta) {
        List<Ruta> lRutas;

        // 1 - Recogemos el tipo de clase que es ArrayList<Ruta>
        Type listType = new TypeToken<ArrayList<Ruta>>() {}.getType();
        // 2 - Convertimos la cadena JSON sRutas a la lista de objetos Rutas
        try {
            lRutas = this.gson.fromJson(sRutas, listType);
            // 3 - Devolvemos la lista de objetos Rutas
            return (lRutas);
        } catch (Exception e) {
            return (null);
        }
    }
}
