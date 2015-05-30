package es.upsam.dsm.icsypb_android.controller;

/**
 * Singleton
 *
 * Basado en https://gist.github.com/Akayh/5566992
 *
 * @brief Patrón singleton para gestionar los datos entre activities
 * @author Kr0n0
 */
public class Singleton {
    // Atributo de clase - Instancia
    private static Singleton mInstance = null;

    // Lista de atributos de clase a gestionar



    private Singleton() {
        // Inicializamos los atributos de clase

    }

    /**
     * getInstance() - Devuelve la instancia del singleton
     * @return mInstance
     */
    public static Singleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }
}
