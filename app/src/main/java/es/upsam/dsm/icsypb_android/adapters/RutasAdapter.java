package es.upsam.dsm.icsypb_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.entities.Ruta;

/**
 * RutasAdapter
 *
 * @brief Clase Adapter para las Rutas en la vista activity_rutas
 *
 * Referencias : https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 *
 */
public class RutasAdapter extends ArrayAdapter<Ruta>{

    public RutasAdapter(Context context, int resource, List<Ruta> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer iRuta;
        String sDescripcion;

        // 1 - Recoger el dato de esta posicion
        Ruta ruta = getItem(position);
        // 2 - Comprobar que la vista está inflada, si no crearla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rutas, parent, false);
        }
        // 3 - Recogemos los campos de la vista para tratarlos
        TextView tvRutaId = (TextView) convertView.findViewById(R.id.ruta_id);
        TextView tvRutaDesc = (TextView) convertView.findViewById(R.id.ruta_desc);
        // 4 - Recogemos los valores del objeto actual
        iRuta = ruta.getId();
        sDescripcion = ruta.getDescripcion();
        // 5 - Pasamos los parámetros al XML como cadenas
        tvRutaId.setText(iRuta.toString());
        tvRutaDesc.setText(sDescripcion);
        // 6 - Devolvemos la vista completa con los datos ya puestos
        return convertView;
    }
}
