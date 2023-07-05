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

    //Se crea la clase MenuArrendarLibro con sus respectivos parametros, ademas se crea la interfaz grafica para esto.
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
    //La clase buscarParaArrendar es usada para buscar el libro a arrendar y que tenga stock.
    private void buscarparaArrendas(List<Libro>listaLibro){

        try{
            boolean condicion = true;
            String ISBNaBuscar = ISBNTextField.getText();
            //Se comprueba que la casilla no este vacia.
            if (!ISBNaBuscar.isEmpty()){
                //Se crea el iterador para buscar dicho ISBN.
                Iterator<Libro> iterator = listaLibro.iterator();
                while (iterator.hasNext()){
                    Libro libroaux = iterator.next();
                    String ISBNlibro=libroaux.getISBN();
                    //Se almacena el ISBN de los libros que hay dispobiles para luego compararlo con el ISBN ingresado por el usuario.
                    if (ISBNlibro.equalsIgnoreCase(ISBNaBuscar)){
                        condicion = false;
                        int StockAux = libroaux.getStock();
                        //Si son iguales se busca que el libro tenga stock.
                        if (StockAux != 0){
                            //Si tiene stock disponible, se le resta 1 al stock porque se arrendo una copia.
                            int auxNuevoVarlosStock = StockAux-1;
                            libroaux.setStock(auxNuevoVarlosStock);
                            JOptionPane.showMessageDialog(menuarrendarlibro,"Libro arrendado con exito.");
                            //Ahora se invoca el procedimiento crea una linea de texto con los datos del usuario que arrendo el libro, los datos de dicho libro y su estado (Prestamos/Devolucion).
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
    //Procedimiento encargado de crear un txt y agregar las operaciones realizadas (Prestamo/Devolucion), en este caso se agrega un prestamo.
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
