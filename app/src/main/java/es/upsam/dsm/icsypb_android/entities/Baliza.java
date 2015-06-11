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
    // CONSTRUCTOR - CON PARAMETROS
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

    /**
     * getId()
     *
     * @brief  Recupera el atributo Baliza.id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setId()
     * @brief Establece el atributo Baliza.id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getTexto()
     * @brief Recupera el atributo Baliza.texto
     * @return texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * setTexto()
     * @brief Establece el atributo Baliza.texto
     * @param texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * getMac()
     * @brief Recupera el atributo Baliza.mac
     * @return mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * setMac()
     * @brief Establece el atributo Baliza.mac
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * getPosicion()
     * @brief Recupera el atributo Baliza.posicion
     * @return posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * setPosicion()
     * @brief Establece el atributo Baliza.posicion
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * getId_contacto()
     * @brief Recupera el atributo Baliza.id_contacto
     * @return id_contacto
     */
    public int getId_contacto() {
        return id_contacto;
    }

    /**
     * setId_contacto()
     * @brief Establece el atributo Baliza.id_contacto
     * @param id_contacto
     */
    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    /**
     * getEstropeado()
     * @brief Recupera el atributo Baliza.estropeado
     * @return estropeado
     */
    public Boolean getEstropeado() {
        return estropeado;
    }

    /**
     * setEstropeado()
     * @brief Establece el atributo Baliza.estropeado
     * @param estropeado
     */
    public void setEstropeado(Boolean estropeado) {
        this.estropeado = estropeado;
    }

    /**
     * getMail()
     * @brief Recupera el atributo Baliza.mail
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * setMail()
     * @brief Establece el atributo Baliza.mail
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}