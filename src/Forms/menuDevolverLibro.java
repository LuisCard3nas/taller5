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

public class menuDevolverLibro extends JFrame {
    private JPanel menudevolverlibro;
    private JTextField ISBNlibroadevolver;
    private JButton devolverButton;
    private JButton menuPrincipalButton;

    private List<Libro> listaLibro;
    private List<Usuario> listaUsuario;
    private UsuarioInicioSesion usuarioInicioSesion;

    public menuDevolverLibro(List<Libro>listaLibro,List<Usuario>listaUsuario,UsuarioInicioSesion usuarioInicioSesion){
        this.listaLibro=listaLibro;
        this.listaUsuario=listaUsuario;
        this.usuarioInicioSesion=usuarioInicioSesion;
        setContentPane(menudevolverlibro);
        setTitle("Menu de Biblioteca");
        setSize(500,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro(listaLibro);
            }
        });
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuOpciones menuOpciones = new MenuOpciones(listaLibro,listaUsuario,usuarioInicioSesion);
                dispose();
                menuOpciones.setVisible(true);
            }
        });

    }
    private void devolverLibro(List<Libro>listaLibro){
        try{
            boolean condicion = true;
            String ISBNadevoler= ISBNlibroadevolver.getText();
            if (!ISBNadevoler.isEmpty()){
                Iterator<Libro>iterator=listaLibro.iterator();
                while (iterator.hasNext()){
                    Libro libroaux=iterator.next();
                    String NombreAutor= libroaux.getAutor();
                    String Titulo =libroaux.getNombre();
                    String Categoria = libroaux.getCategoria();
                    int NumeroCopias= libroaux.getStock();
                    String ISBNlibro=libroaux.getISBN();
                    if (ISBNlibro.equalsIgnoreCase(ISBNadevoler)){
                        condicion=false;
                        int StockNuevo=libroaux.getStock()+1;
                        libroaux.setStock(StockNuevo);
                        JOptionPane.showMessageDialog(menudevolverlibro,"Libro devuelto con exito!");
                        AgregarDatosDevolucionLibroTxt(libroaux,usuarioInicioSesion);
                        dispose();
                        MenuOpciones menuOpciones = new MenuOpciones(listaLibro,listaUsuario,usuarioInicioSesion);
                    }
                }
                if (condicion){
                    JOptionPane.showMessageDialog(menudevolverlibro,"ISBN no encontrado , por favor intente con otro");
                    clear();
                }

            }else{
                JOptionPane.showMessageDialog(menudevolverlibro,"Por favor , ingrese el ISBN del libro a devoler");
            }

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(menudevolverlibro,"Ha ocurrido un error [!]");
            clear();
        }


    }
    private void clear(){
        ISBNlibroadevolver.setText("");
    }

    private void AgregarDatosDevolucionLibroTxt (Libro libroAux,UsuarioInicioSesion usuarioInicioSesion){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Reservas.txt",true));

            String linea = usuarioInicioSesion.getRut() + "," + usuarioInicioSesion.getNombre()+ "," + usuarioInicioSesion.getApellido() + "," + libroAux.getISBN() + "," + libroAux.getNombre() + "," + "Devolucion";
            writer.write(linea);
            writer.newLine();

            writer.close();
        }catch (IOException e){
            System.out.println("[!] Ha ocurrido un error [!]");
            e.printStackTrace();
        }
    }
}
