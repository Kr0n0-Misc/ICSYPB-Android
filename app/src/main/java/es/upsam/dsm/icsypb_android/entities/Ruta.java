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
     * Ruta
     *
     * @brief Constructor con parámetros
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

    public List<Baliza> getBalizas() {
        return balizas;
    }

    public void setBalizas(List<Baliza> balizas) {
        this.balizas = balizas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}


