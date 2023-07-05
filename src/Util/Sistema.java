package Util;

public interface Sistema {
    /**
     * Opcion utilizada para iniciar con la ejecucios del programa creando una nuevo Inicio de Sesion
     */
    void startProgram();

    /**
     * Método encargado de leer el archivo de "libros.txt".
     */
    void leerArchivoLibros();

    /**
     * Método encargado de leer el archivo de "usuarios.txt".
     */
    void leerArchivoUsuarios();
}
