package es.upsam.dsm.icsypb_android.entities;

/**
 * Dispositivo
 *
 * @brief Clase Entity para gestionar la tabla dispositivo
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `dispositivo` (
 * `IDDISP` int(11) NOT NULL,
 * `MAC` varchar(17) COLLATE utf8_spanish_ci NOT NULL,
 * `DESCRIPCION` varchar(50) COLLATE utf8_spanish_ci NOT NULL)
 */
public class Dispositivo {
    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    int id;
    String mac;
    String descripcion;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Dispositivo() {
        this.id = 0;
        this.mac = "";
        this.descripcion = "";
    }

    /**
     * Dispositivo
     * @brief Constructor con parámetros
     * @param id    Identificador del dispositivo
     * @param mac   MAC Address del dispositivo
     * @param descripcion   Descripciónd el dispositivo
     */
    public Dispositivo(int id, String mac, String descripcion) {
        this.id = id;
        this.mac = mac;
        this.descripcion = descripcion;
    }

    /****************************************
     Getters y Setters de los campos
     ****************************************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
