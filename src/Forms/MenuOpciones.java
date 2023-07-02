package Forms;

import Entities.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuOpciones extends JFrame {
    private JPanel eleccionopciones;
    private JButton BuscarLibro;
    private JButton DevolverLibroButton;
    private JButton AgregarNuevoLibroButton;
    private JButton PrestarLibroButton;

    private List<Libro>listaLibro;

    public MenuOpciones(List<Libro>listaLibro){
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

    }
    private void buscarlibro (){
        dispose();
        menuBuscarPorISBN busquedaISBN = new menuBuscarPorISBN(listaLibro);
        busquedaISBN.setVisible(true);


    }
    private void devolverlibro(){
        JOptionPane.showMessageDialog(eleccionopciones,"DevolverLibro");

    }
    private void agregarnuevolibro(){
        JOptionPane.showMessageDialog(eleccionopciones,"agregar Libro ");

    }
    private void prestarlibro(){
        JOptionPane.showMessageDialog(eleccionopciones,"prestar libro");

    }
}
