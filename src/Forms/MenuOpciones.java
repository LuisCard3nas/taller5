package Forms;

import Entities.Libro;
import Entities.Usuario;

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

    public MenuOpciones(List<Libro>listaLibro, List<Usuario>listaUsuarios){
        this.listaLibro=listaLibro;
        setContentPane(eleccionopciones);
        setTitle("Menu De Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesion inicioSesion = new InicioSesion(listaUsuarios, listaLibro);
                dispose();
                inicioSesion.setVisible(true);
            }
        });

    }
    private void buscarlibro (){
        dispose();
        menuBuscarPorISBN busquedaISBN = new menuBuscarPorISBN(listaLibro,listaUsuarios);
        busquedaISBN.setVisible(true);


    }
    private void devolverlibro(){
        JOptionPane.showMessageDialog(eleccionopciones,"DevolverLibro");

    }
    private void agregarnuevolibro(){
        dispose();
        menuAgregarLibro menuAgregarLibro = new menuAgregarLibro(listaLibro,listaUsuarios);
        menuAgregarLibro.setVisible(true);

    }
    private void prestarlibro(){
        JOptionPane.showMessageDialog(eleccionopciones,"prestar libro");

    }
}
