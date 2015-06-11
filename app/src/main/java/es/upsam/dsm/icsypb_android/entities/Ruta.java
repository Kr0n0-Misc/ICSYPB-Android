package es.upsam.dsm.icsypb_android.entities;

/**
 * Ruta
 *
 * @brief Clase Entity para gestionar la tabla rutas
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `rutas` (
 * `IDRUTA` int(11) NOT NULL,
 *`DESCRIPCION` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL)
 */
public class Ruta {

    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    int id;
    String descripcion;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Ruta() {
        this.id = 0;
        this.descripcion = "";
    }

    /**
     * Ruta - Constructor con parámetros
     * @param id    Identificador de la ruta
     * @param descripcion   Descripción de la ruta
     */
    public Ruta(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /****************************************
     Getters y Setters de los campos
     ****************************************/

    /**
     * getId()
     * @brief   Recupera el atributo Ruta.id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setId()
     * @brief   Establece el atributo Ruta.id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getDescripcion()
     * @brief   Recupera el atributo Ruta.descripcion
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * setDescripcion()
     * @brief   Establece el atributo Ruta.descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
