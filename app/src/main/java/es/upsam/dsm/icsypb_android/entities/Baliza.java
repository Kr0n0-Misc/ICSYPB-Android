package es.upsam.dsm.icsypb_android.entities;

/**
 * Baliza
 *
 * @brief Clase Entity para gestionar la tabla Baliza
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `balizas` (
 * `IDBALIZA` int(11) NOT NULL,
 * `MAC` varchar(17) COLLATE utf8_spanish_ci NOT NULL,
 * `POSICION` int(11) NOT NULL,
 * `TEXTO_ID` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
 * `ID_CONTACTO` int(11) NOT NULL,
 * `ESTROPEADO` int(1) NOT NULL,
 * `MAIL` varchar(50) COLLATE utf8_spanish_ci NOT NULL)
 */
public class Baliza {
    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    int id;
    String texto;
    String mac;
    int posicion;
    int id_contacto;
    Boolean estropeado;
    String mail;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Baliza() {
        this.id = 0;
        this.texto = "";
        this.mac = "";
        this.posicion = 0;
        this.id_contacto = 0;
        this.estropeado = false;
        this.mail = "";
    }

    /**
     * Baliza
     *
     * @brief Constructor con parámetros
     * @param id    Identificador de Baliza
     * @param texto Texto de Baliza
     * @param mac   MAC Address de Baliza
     * @param posicion  Posición de Baliza
     * @param id_contacto   Identificador del contacto de Baliza
     * @param estropeado    Flag si estropeado o no
     * @param mail  Correo de la baliza
     */
    public Baliza(int id, String texto, String mac, int posicion, int id_contacto, Boolean estropeado, String mail) {
        this.id = id;
        this.texto = texto;
        this.mac = mac;
        this.posicion = posicion;
        this.id_contacto = id_contacto;
        this.estropeado = estropeado;
        this.mail = mail;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Boolean getEstropeado() {
        return estropeado;
    }

    public void setEstropeado(Boolean estropeado) {
        this.estropeado = estropeado;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
