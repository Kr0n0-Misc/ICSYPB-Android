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
    int id;
    int id_usuario;
    int id_ruta;
    int id_disp;
    String id_baliza;
    String fecha;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Tracking() {
        this.id = 0;
        this.id_usuario = 0;
        this.id_ruta = 0;
        this.id_disp = 0;
        this.id_baliza = "";
        this.fecha = "";
    }

    /**
     * Tracking - Constructor con parámetros
     * @param id    Identificador de tracking
     * @param id_usuario    Identificador de usuario
     * @param id_ruta   Identificador de ruta
     * @param id_disp   Identificador de dispositivo
     * @param id_baliza Identificador de baliza
     * @param fecha Fecha de grabación
     */
    public Tracking(int id, int id_usuario, int id_ruta, int id_disp, String id_baliza, String fecha) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_ruta = id_ruta;
        this.id_disp = id_disp;
        this.id_baliza = id_baliza;
        this.fecha = fecha;
    }

    /**
     * getId()
     * @brief   Recupera el atributo Tracking.id
     * @return  id
     */
    public int getId() {
        return id;
    }

    /**
     * setId()
     * @brief   Establece el atributo Tracking.id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getId_usuario()
     * @brief   Recupera el atributo Tracking.id_usuario
     * @return  id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * setId_usuario()
     * @brief   Establece el atributo Tracking.id_usuario
     * @param id_usuario
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * getId_ruta()
     * @brief   Recupera el atributo Tracking.id_ruta
     * @return  id_ruta
     */
    public int getId_ruta() {
        return id_ruta;
    }

    /**
     * setId_ruta()
     * @brief   Establece el atributo Tracking.id_ruta
     * @param id_ruta
     */
    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    /**
     * getId_disp()
     * @brief   Recupera el atributo Tracking.id_disp
     * @return  id_disp
     */
    public int getId_disp() {
        return id_disp;
    }

    /**
     * setId_disp()
     * @brief   Establece el atributo Tracking.id_disp
     * @param id_disp
     */
    public void setId_disp(int id_disp) {
        this.id_disp = id_disp;
    }

    /**
     * getId_baliza()
     * @brief   Recupera el atributo Tracking.id_baliza
     * @return  id_baliza
     */
    public String getId_baliza() {
        return id_baliza;
    }

    /**
     * setId_baliza()
     * @brief   Establece el atributo Tracking.id_baliza
     * @param id_baliza
     */
    public void setId_baliza(String id_baliza) {
        this.id_baliza = id_baliza;
    }

    /**
     * getFecha()
     * @brief   Recupera el atributo Tracking.fecha
     * @return  fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * setFecha()
     * @brief   Establece el atributo Tracking.fecha
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
