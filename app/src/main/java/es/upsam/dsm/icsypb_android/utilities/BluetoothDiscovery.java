package es.upsam.dsm.icsypb_android.utilities;;


import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


/**
 *
 */
public class BluetoothDiscovery
{
    ArrayList<String> BluetoothArray = new ArrayList<String>();
    private BluetoothAdapter mBTA;
    private SingBroadcastReceiver mReceiver;
    Set<BluetoothDevice> pairedDevices;
    String ownMAC;

    public String getOwnMAC() {
        return ownMAC;
    }

    public void setOwnMAC(String ownMAC) {
        this.ownMAC = ownMAC;
    }

    /**
     *
     * @param activity
     * @param alMACs
     */
    public BluetoothDiscovery(Activity activity, ArrayList<String> alMACs)
    {
        mBTA = BluetoothAdapter.getDefaultAdapter();
        // 1 - Recogemos la propia MAC Address antes de hacer nada
        setOwnMAC(mBTA.getAddress());
        // 2 - Empezamos el discover
        BluetoothArray = alMACs;
        if(mBTA.isDiscovering())
            mBTA.cancelDiscovery();

        pairedDevices = mBTA.getBondedDevices();
        mBTA.startDiscovery();

        mReceiver = new SingBroadcastReceiver();
        IntentFilter ifilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(mReceiver, ifilter);

    }

    /**
     *
     */
    public void startBTScan () {
        if (!(mBTA.isDiscovering()))
          mBTA.startDiscovery();
    }

    /**
     *
     */
    public void stopBTScan () {
        if (mBTA.isDiscovering())
          mBTA.cancelDiscovery();
    }

    /**
     * buscarArray
     *
     * @brief Busca la MAC Address en el ArrayList
     * @param alMACs
     * @param cadena
     * @return true si encontrado, false si no encontrado
     */
    public boolean buscarArray(ArrayList<String> alMACs, String cadena) {
        boolean resultado = false;

        // 1 - Recorremos el ArrayList de cadenas
        for (String i : alMACs) {
            // 2 - Si la MAC es igual a la que tenemos
            if (i.matches(cadena)) {
                resultado = true;
                break;
            }
        }
        return (resultado);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getBTAddress()
    {
        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device : pairedDevices)
            {
                BluetoothArray.add(device.getAddress());
            }
        }
        return BluetoothArray;
    }

    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished
    private class SingBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String mac_actual;
            Boolean encontrado = false;

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Si el dispositivo no está asociado de serie
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    // Si está en el array añadimos
                    mac_actual = device.getAddress();
                    encontrado = buscarArray(BluetoothArray, mac_actual);
                    if (encontrado = true) {
                    /* Añadimos los campos al objeto Tracking que creemos
                        String mac_usuario;
                        String mac_baliza;
                        String fecha_actual;
                        int id_baliza;
                        int id_ruta; */
                        Log.d("[BluetoothDiscovery]", "Dispositivo encontrado para añadir - " + mac_actual);
                    }
                }
            }
        }
    };
}
