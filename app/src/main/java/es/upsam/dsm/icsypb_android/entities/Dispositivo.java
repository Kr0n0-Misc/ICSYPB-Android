package es.upsam.dsm.icsypb_android.entities;

/**
 * Baliza
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
     * Dispositivo - Constructor con parámetros
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

    /**
     * getId()
     * @brief   Recupera el atributo Dispositivo.id
     * @return  id
     */
    public int getId() {
        return id;
    }

    /**
     * setId()
     * @brief   Establece el atributo Dispositivo.id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getMac()
     * @brief   Recupera el atributo Dispositivo.mac
     * @return  mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * setMac()
     * @brief   Establece el atributo Dispositivo.mac
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * getDescripcion()
     * @brief   Recupera el atributo Dispositivo.descripcion
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * setDescripcion()
     * @brief   Establece el atributo Dispositivo.descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
