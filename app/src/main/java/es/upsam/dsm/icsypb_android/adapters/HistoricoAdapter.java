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

public class HistoricoAdapter extends ArrayAdapter<Tracking> {

    public HistoricoAdapter(Context context, int resource, List<Tracking> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String mac_usuario, desc_ruta, mac_baliza, desc_baliza, posicion, fecha;
        int id_ruta, id_baliza;

        // 1 - Recoger el dato de esta posicion
        Tracking tracking = getItem(position);
        // 2 - Comprobar que la vista está inflada, si no crearla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_historico, parent, false);
        }
        // 3 - Recogemos los campos de la vista para tratarlos
        //TextView tvhMAC_Usuario = (TextView) convertView.findViewById(R.id.tvhMAC_USUARIO);
        //TextView tvhID_Ruta = (TextView) convertView.findViewById(R.id.tvhID_RUTA);
        TextView tvhDESC_Ruta = (TextView) convertView.findViewById(R.id.tvhDESC_RUTA);
        //TextView tvhID_Baliza = (TextView) convertView.findViewById(R.id.tvhID_BALIZA);
        //TextView tvhMAC_Baliza = (TextView) convertView.findViewById(R.id.tvhMAC_BALIZA);
        TextView tvhDESC_Baliza = (TextView) convertView.findViewById(R.id.tvhDESC_BALIZA);
        TextView tvhPosicion = (TextView) convertView.findViewById(R.id.tvhPOSICION);
        TextView tvhFecha = (TextView) convertView.findViewById(R.id.tvhFECHA);
        // 4 - Recogemos los valores del objeto actual
        mac_usuario = tracking.getMac_baliza();
        id_ruta = tracking.getId_ruta();
        desc_ruta = tracking.getDesc_ruta();
        id_baliza = tracking.getId_baliza();
        mac_baliza = tracking.getMac_baliza();
        desc_baliza = tracking.getDesc_baliza();
        posicion = tracking.getPosicion();
        fecha = tracking.getFecha();
        // 5 - Pasamos los parámetros al XML como cadenas
        //tvhMAC_Usuario.setText(mac_usuario);
        //tvhID_Ruta.setText(id_ruta);
        tvhDESC_Ruta.setText(desc_ruta);
        //tvhID_Baliza.setText(id_baliza);
        //tvhMAC_Baliza.setText(mac_baliza);
        tvhDESC_Baliza.setText(desc_baliza);
        tvhPosicion.setText(posicion);
        tvhFecha.setText(fecha);
        // 6 - Devolvemos la vista completa con los datos ya puestos
        return convertView;
    }
}
