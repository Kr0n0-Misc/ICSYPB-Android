package es.upsam.dsm.icsypb_android.entities;

import java.util.ArrayList;
import java.util.List;

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
    List<Baliza> balizas;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Ruta() {
        this.id = 0;
        this.descripcion = "";
        this.balizas = new ArrayList<>();
    }

    /**
     * Ruta - Constructor con parámetros
     * @param id    Identificador de la ruta
     * @param descripcion   Descripción de la ruta
     * @param balizas Lista de balizas
     */
    public Ruta(int id, String descripcion, List<Baliza> balizas) {
        this.id = id;
        this.descripcion = descripcion;
        this.balizas = balizas;
    }

     /****************************************
          Getters y Setters de los campos
     ****************************************/

    /**
     * getBalizas()
     * @brief Recupera la lista de Balizas
     * @return balizas
     */
    public List<Baliza> getBalizas() {
        return balizas;
    }

    /**
     * setBalizas()
     * @brief Establece la lista de Balizas
     * @param balizas
     */
    public void setBalizas(List<Baliza> balizas) {
        this.balizas = balizas;
    }

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

