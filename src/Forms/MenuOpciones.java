package Forms;

import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuOpciones extends JFrame {
    private JPanel eleccionopciones;
    private JButton BuscarLibro;
    private JButton DevolverLibroButton;
    private JButton AgregarNuevoLibroButton;
    private JButton PrestarLibroButton;
    private JButton cerrarSesionButton;

    private List<Libro>listaLibro;
    private List<Usuario>listaUsuarios;
    private UsuarioInicioSesion usuarioInicioSesion;

    //Se crea la clase MenuOpciones con sus respectivos parametros, ademas se crea la interfaz grafica para esto.
    public MenuOpciones(List<Libro>listaLibro, List<Usuario>listaUsuarios,UsuarioInicioSesion usuarioInicioSesion){
        this.listaLibro=listaLibro;
        this.listaUsuarios=listaUsuarios;
        this.usuarioInicioSesion=usuarioInicioSesion;
        setContentPane(eleccionopciones);
        setTitle("Menu De Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        // establecemos los listeners para cada uno de los botones del menu enviandolo a sus respectivos forms
        BuscarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarlibro();
            }
        });
        DevolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverlibro();
            }
        });
        AgregarNuevoLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarnuevolibro();
            }
        });
        PrestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarlibro();
            }
        });
        // para con la ejecucion del programa
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesion inicioSesion = new InicioSesion(listaUsuarios,listaLibro,usuarioInicioSesion);
                dispose();
                inicioSesion.setVisible(true);
            }
        });

    }
    // cierra la ventana de menu opciones y despliega en pantalla el menu de buscarlibro
    private void buscarlibro (){
        dispose();
        menuBuscarPorISBN busquedaISBN = new menuBuscarPorISBN(listaLibro,listaUsuarios,usuarioInicioSesion);
        busquedaISBN.setVisible(true);


    }
    // cierra la ventana de menu opciones y despliega en pantalla el menu de devolverLibro
    private void devolverlibro(){
        dispose();
        menuDevolverLibro menuDevolverLibro = new menuDevolverLibro(listaLibro,listaUsuarios,usuarioInicioSesion);
        menuDevolverLibro.setVisible(true);

    }
    // cierra la ventana de menu opciones y despliega en pantalla el menu de agregarNuevoLibro
    private void agregarnuevolibro(){
        dispose();
        menuAgregarLibro menuAgregarLibro = new menuAgregarLibro(listaLibro,listaUsuarios,usuarioInicioSesion);
        menuAgregarLibro.setVisible(true);

    }
    // cierra la ventana de menu opciones y despliega en pantalla el menu de presta libro
    private void prestarlibro(){
        dispose();
        menuArrendarLibro menuArrendarLibro= new menuArrendarLibro(listaLibro,listaUsuarios,usuarioInicioSesion);
        menuArrendarLibro.setVisible(true);

    }
}
