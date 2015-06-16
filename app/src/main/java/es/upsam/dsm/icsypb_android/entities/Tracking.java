package es.upsam.dsm.icsypb_android.entities;

/**
 * Tracking
 *
 * @brief Clase Entity para gestionar la tabla tracking
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `tracking` (
 * `IDTRACK` int(6) NOT NULL,
 * `IDUSUARIO` int(11) NOT NULL,
 * `IDRUTA` int(11) NOT NULL,
 * `IDDISP` int(11) NOT NULL,
 * `IDBALIZA` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
 * `DIA` datetime DEFAULT NULL)
 */
public class Tracking {

    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    String mac_usuario;
    String mac_baliza;
    String fecha_actual;
    int id_baliza;
    int id_ruta;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Tracking() {
        this.mac_usuario = "";
        this.mac_baliza = "";
        this.fecha_actual = "";
        this.id_baliza = 0;
        this.id_ruta = 0;
    }

    /**
     * Tracking
     *
     * @param mac_usuario
     * @param mac_baliza
     * @param fecha_actual
     * @param id_baliza
     * @param id_ruta
     */
    public Tracking(String mac_usuario, String mac_baliza, String fecha_actual, int id_baliza, int id_ruta) {
        this.mac_usuario = mac_usuario;
        this.mac_baliza = mac_baliza;
        this.fecha_actual = fecha_actual;
        this.id_baliza = id_baliza;
        this.id_ruta = id_ruta;
    }

    public String getMac_usuario() {
        return mac_usuario;
    }

    public void setMac_usuario(String mac_usuario) {
        this.mac_usuario = mac_usuario;
    }

    public String getMac_baliza() {
        return mac_baliza;
    }

    public void setMac_baliza(String mac_baliza) {
        this.mac_baliza = mac_baliza;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public int getId_baliza() {
        return id_baliza;
    }

    public void setId_baliza(int id_baliza) {
        this.id_baliza = id_baliza;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }
}
