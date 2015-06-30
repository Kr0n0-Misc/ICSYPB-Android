package es.upsam.dsm.icsypb_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.entities.Tracking;


/**
 *
 */
public class TrackingAdapter extends ArrayAdapter<Tracking> {

    public TrackingAdapter(Context context, int resource, List<Tracking> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String mac_baliza;
        String desc_baliza;
        int cuenta;
        String posicion;

        // 1 - Recoger el dato de esta posicion
        Tracking tracking = getItem(position);
        // 2 - Comprobar que la vista está inflada, si no crearla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tracking, parent, false);
        }
        // 3 - Recogemos los campos de la vista para tratarlos
        TextView tvMAC_Baliza = (TextView) convertView.findViewById(R.id.tvMAC_BALIZA);
        TextView tvDESC_Baliza = (TextView) convertView.findViewById(R.id.tvDESC_BALIZA);
        TextView tvCuenta = (TextView) convertView.findViewById(R.id.tvCUENTA);
        TextView tvPosicion = (TextView) convertView.findViewById(R.id.tvPOSICION);
        // 4 - Recogemos los valores del objeto actual
        mac_baliza = tracking.getMac_baliza();
        desc_baliza = tracking.getDesc_baliza();
        //TODO cuenta = RECOGERLO DEL SINGLETON CON UN COUNT O ALGO ASI
        posicion = tracking.getPosicion();
        // 5 - Pasamos los parámetros al XML como cadenas
        tvMAC_Baliza.setText(mac_baliza);
        tvDESC_Baliza.setText(desc_baliza);
        tvPosicion.setText(posicion);
        // 6 - Devolvemos la vista completa con los datos ya puestos
        return convertView;
    }
}