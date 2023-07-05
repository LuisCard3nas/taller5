package Forms;

import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class menuArrendarLibro extends JFrame {
    private JPanel menuarrendarlibro;
    private JButton buscarButton;
    private JButton menuPrincipalButton;
    private JTextField ISBNTextField;
    private List<Libro> listaLibro;
    private List<Usuario>listaUsuarios;
    private UsuarioInicioSesion usuarioInicioSesion;

    public menuArrendarLibro(List<Libro>listaLibro,List<Usuario>listaUsuarios,UsuarioInicioSesion usuarioInicioSesion){
        this.listaLibro=listaLibro;
        this.listaUsuarios=listaUsuarios;
        this.usuarioInicioSesion=usuarioInicioSesion;
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
                MenuOpciones menuOpciones = new MenuOpciones(listaLibro,listaUsuarios,usuarioInicioSesion);
                dispose();
                menuOpciones.setVisible(true);
            }
        });

    }
    private void buscarparaArrendas(List<Libro>listaLibro){

        try{
            boolean condicion = true;
            String ISBNaBuscar = ISBNTextField.getText();
            if (!ISBNaBuscar.isEmpty()){
                Iterator<Libro> iterator = listaLibro.iterator();
                while (iterator.hasNext()){
                    Libro libroaux = iterator.next();
                    String NombreAutor= libroaux.getAutor();
                    String Titulo =libroaux.getNombre();
                    String Categoria = libroaux.getCategoria();
                    int NumeroCopias= libroaux.getStock();
                    String ISBNlibro=libroaux.getISBN();
                    if (ISBNlibro.equalsIgnoreCase(ISBNaBuscar)){
                        condicion = false;
                        int StockAux = libroaux.getStock();
                        if (StockAux != 0){
                            int auxNuevoVarlosStock = StockAux-1;
                            libroaux.setStock(auxNuevoVarlosStock);
                            JOptionPane.showMessageDialog(menuarrendarlibro,"Libro arrendado con exito.");
                            String rutAux = usuarioInicioSesion.getRut();
                            System.out.println(rutAux);
                            AgregarDatosPrestamoLibroTxt(libroaux,usuarioInicioSesion);
                            dispose();
                            MenuOpciones menuOpciones = new MenuOpciones(listaLibro,listaUsuarios,usuarioInicioSesion);
                        }else{
                            JOptionPane.showMessageDialog(menuarrendarlibro,"Lo sentimos el libro solicitado no se encuentra con stock disponibles");
                            clear();
                        }

                    }
                }
                if (condicion){
                    JOptionPane.showMessageDialog(menuarrendarlibro,"ISBN no encontrado, por favor intente con otro");
                    clear();
                }
            }else{
                JOptionPane.showMessageDialog(menuarrendarlibro,"Por favor , ingrese un ISBN para buscar");
            }

        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(menuarrendarlibro,"Ha ocurrido un error [!]");
            clear();
        }
    }
    private void clear(){
        ISBNTextField.setText("");
    }
    private void AgregarDatosPrestamoLibroTxt (Libro libroAux,UsuarioInicioSesion usuarioInicioSesion){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Reservas.txt",true));

            String linea = usuarioInicioSesion.getRut() + "," + usuarioInicioSesion.getNombre()+ "," + usuarioInicioSesion.getApellido() + "," + libroAux.getISBN() + "," + libroAux.getNombre() + "," + "Prestamo";
            writer.write(linea);
            writer.newLine();

            writer.close();
        }catch (IOException e){
            System.out.println("[!] Ha ocurrido un error [!]");
            e.printStackTrace();
        }
    }
}
