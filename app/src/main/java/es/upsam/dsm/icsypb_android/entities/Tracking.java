package es.upsam.dsm.icsypb_android.entities;

import java.util.Date;

/**
 * Tracking
 *
 * @brief Clase Entity para gestionar la tabla tracking
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `tracking` (
    ID_TRACK -> Automático
    MAC_USUARIO : mac_dispositivo
    ID_RUTA -> datos.getRuta(posicion).getId();
    ID_BALIZA -> Modificar buscarArray para que devuelva el ID de la baliza (salida si NULL)
    MAC_BALIZA -> mac_actual
    FECHA -> private sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    IDTRACKPUB -> generado a posteriori segun seleccion del usuario
 */
public class Tracking {

    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    String mac_usuario;
    int id_ruta;
    String desc_ruta;
    int id_baliza;
    String mac_baliza;
    String desc_baliza;
    String posicion;
    String fecha;
    String idtrackpub;

     public Tracking() {
         this.mac_usuario = null;
         this.id_ruta = 0;
         this.desc_ruta = null;
         this.id_baliza = 0;
         this.mac_baliza = null;
         this.desc_baliza = null;
         this.posicion = null;
         this.fecha = null;
         this.idtrackpub = null;
    }

    public Tracking(String mac_usuario, int id_ruta, String desc_ruta, int id_baliza, String mac_baliza, String desc_baliza, String fecha, String idtrackpub, String posicion) {
        this.mac_usuario = mac_usuario;
        this.id_ruta = id_ruta;
        this.desc_ruta = desc_ruta;
        this.id_baliza = id_baliza;
        this.mac_baliza = mac_baliza;
        this.desc_baliza = desc_baliza;
        this.posicion = posicion;
        this.fecha = fecha;
        this.idtrackpub = idtrackpub;
    }

    /****************************************
     Getters y Setters de los campos
     ****************************************/

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

    public String getDesc_ruta() {
        return desc_ruta;
    }

    public void setDesc_ruta(String desc_ruta) {
        this.desc_ruta = desc_ruta;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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