package Forms;

import Entities.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class menuBuscarPorISBN extends JFrame {
    private JPanel busquedaporISBN;
    private JTextField ISBNTextField;
    private JButton buscarButton;
    private JLabel TituloRespuesta;
    private JLabel Nombreautor;
    private JLabel CategoriaRespuesta;
    private JLabel NumeroCopiasRespuesta;

    private List<Libro> listaLibros;

    public menuBuscarPorISBN(List<Libro>listaLibros){
        this.listaLibros=listaLibros;
        setContentPane(busquedaporISBN);
        setTitle("Menu de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                despliegaDatos(listaLibros);
            }
        });
    }
    private void despliegaDatos(List<Libro>listaLibros){
        boolean condicion = true;
        try{
            String ISBNaBuscar = ISBNTextField.getText();
            if (!ISBNaBuscar.isEmpty()){
                Iterator<Libro>iterator = listaLibros.iterator();
                while (iterator.hasNext()){
                    Libro libroaux = iterator.next();
                    String NombreAutor= libroaux.getAutor();
                    String Titulo =libroaux.getNombre();
                    String Categoria = libroaux.getCategoria();
                    int NumeroCopias= libroaux.getStock();
                    String ISBNlibro=libroaux.getISBN();
                    if (ISBNlibro.equalsIgnoreCase(ISBNaBuscar)){
                        TituloRespuesta.setText(Titulo);
                        Nombreautor.setText(NombreAutor);
                        CategoriaRespuesta.setText(Categoria);
                        NumeroCopiasRespuesta.setText(String.valueOf(NumeroCopias));
                        JOptionPane.showMessageDialog(busquedaporISBN, "Libro Encontrado con Exito !");
                        condicion=false;
                    }
                }
                if (condicion){
                    JOptionPane.showMessageDialog(busquedaporISBN, "ISBN no encontrado , intente nuevamente");
                    TituloRespuesta.setText("");
                    Nombreautor.setText("");
                    CategoriaRespuesta.setText("");
                    NumeroCopiasRespuesta.setText(String.valueOf(""));
                    clear();

                }

            }else{
                JOptionPane.showMessageDialog(busquedaporISBN,"Por favor , ingrese un ISBN para buscar");
            }

        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(busquedaporISBN,"Ha ocurrido un error [!]");
            clear();
        }


    }
    private void clear(){
        ISBNTextField.setText("");
    }

}