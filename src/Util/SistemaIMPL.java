package Util;

import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;
import Forms.InicioSesion;
import Forms.MenuOpciones;
import Forms.menuBuscarPorISBN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SistemaIMPL implements Sistema{
    //creacion arrays para almacenar los datos leidos desde el txt
    ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();
    ArrayList<Libro> Libros = new ArrayList<Libro>();

    // inicializacion de los menus y clases a utilizar
    private InicioSesion inicioSesion;
    private menuBuscarPorISBN menuBuscarPorISBN;
    private UsuarioInicioSesion usuarioInicioSesion;

    @Override
    public void leerArchivoLibros() {

        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String isbn = chain[0];
                String nombre = chain[1];
                String autor = chain[2];
                String categoria = chain[3];
                int stock = Integer.parseInt(chain[4]);
                int precio = Integer.parseInt(chain[5]);

                Libro libroAux = new Libro(isbn,nombre,autor,categoria,stock,precio);
                Libros.add(libroAux);

            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public void leerArchivoUsuarios() {

        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut = chain[0];
                String name = chain[1];
                String lastname = chain[2];
                String password = chain[3];

                Usuario usuarioAux = new Usuario(rut,name,lastname,password);
                ListaUsuarios.add(usuarioAux);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    @Override
    public void startProgram(){
        this.inicioSesion=new InicioSesion(ListaUsuarios,Libros,usuarioInicioSesion);

    }
}



