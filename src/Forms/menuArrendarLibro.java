package Forms;

import Entities.Libro;
import Entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class menuArrendarLibro extends JFrame {
    private JPanel menuarrendarlibro;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton menuPrincipalButton;
    private List<Libro> listaLibro;
    private List<Usuario>listaUsuarios;

    public menuArrendarLibro(List<Libro>listaLibro,List<Usuario>listaUsuarios){
        this.listaLibro=listaLibro;
        this.listaUsuarios=listaUsuarios;
        setContentPane(menuarrendarlibro);
        setTitle("Menu de Biblioteca");
        setSize(500,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarparaArrendas(listaLibro);

            }
        });
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuOpciones menuOpciones = new MenuOpciones(listaLibro,listaUsuarios);
                dispose();
                menuOpciones.setVisible(true);
            }
        });

    }
    private void buscarparaArrendas(List<Libro>listaLibro){


    }
}
