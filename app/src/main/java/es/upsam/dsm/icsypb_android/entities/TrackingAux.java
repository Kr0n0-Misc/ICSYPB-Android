package es.upsam.dsm.icsypb_android.entities;

import java.util.ArrayList;
import java.util.List;

public class TrackingAux {

    String mac_usuario;
    int id_ruta;
    int id_baliza;
    String mac_baliza;
    List<String> fecha;
    String idtrackpub;
    String posicion;
    String desc_baliza;

    public TrackingAux() {
        this.mac_usuario = null;
        this.id_ruta = 0;
        this.id_baliza = 0;
        this.mac_baliza = null;
        this.fecha = new ArrayList<>();
        this.idtrackpub = null;
        this.posicion = null;
        this.desc_baliza = null;
    }

    public TrackingAux(String mac_usuario, int id_ruta, int id_baliza, String mac_baliza, List<String> fecha, String idtrackpub, String posicion, String desc_baliza) {
        this.mac_usuario = mac_usuario;
        this.id_ruta = id_ruta;
        this.id_baliza = id_baliza;
        this.mac_baliza = mac_baliza;
        this.fecha = fecha;
        this.idtrackpub = idtrackpub;
        this.posicion = posicion;
        this.desc_baliza = desc_baliza;
    }


    public String getMac_usuario() {
        return mac_usuario;
    }

    public void setMac_usuario(String mac_usuario) {
        this.mac_usuario = mac_usuario;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public int getId_baliza() {
        return id_baliza;
    }

    public void setId_baliza(int id_baliza) {
        this.id_baliza = id_baliza;
    }

    public String getMac_baliza() {
        return mac_baliza;
    }

    public void setMac_baliza(String mac_baliza) {
        this.mac_baliza = mac_baliza;
    }

    public List<String> getFecha() {
        return fecha;
    }

    public void setFecha(List<String> fecha) {
        this.fecha = fecha;
    }

    public String getIdtrackpub() {
        return idtrackpub;
    }

    public void setIdtrackpub(String idtrackpub) {
        this.idtrackpub = idtrackpub;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDesc_baliza() {
        return desc_baliza;
    }

    public void setDesc_baliza(String desc_baliza) {
        this.desc_baliza = desc_baliza;
    }
}
