package Forms;

import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;

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
    private JButton volverAlMenuPrincipalButton;

    private List<Libro> listaLibros;
    private List<Usuario>listaUsuarios;
    private UsuarioInicioSesion usuarioInicioSesion;

    //Se crea la clase MenuBuscarPorISBN con sus respectivos parametros, ademas se crea la interfaz grafica para esto.
    public menuBuscarPorISBN(List<Libro>listaLibros, List<Usuario>listaUsuarios,UsuarioInicioSesion usuarioInicioSesion){
        this.listaLibros=listaLibros;
        this.listaUsuarios=listaUsuarios;
        this.usuarioInicioSesion=usuarioInicioSesion;
        setContentPane(busquedaporISBN);
        setTitle("Menu de Biblioteca");
        setSize(500,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                despliegaDatos(listaLibros);
            }
        });
        volverAlMenuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuOpciones menuOpciones = new MenuOpciones(listaLibros,listaUsuarios,usuarioInicioSesion);
                dispose();
                menuOpciones.setVisible(true);
            }
        });
    }
    //La clase DespliegaDatos es usadad para mostrar todos los parametros del libro que se buscó por el ISBN.
    private void despliegaDatos(List<Libro>listaLibros){
        boolean condicion = true;
        try{
            //Se pregunta por el ISBN a buscar y se verifica que la casilla no etse vacia.
            String ISBNaBuscar = ISBNTextField.getText();
            if (!ISBNaBuscar.isEmpty()){
                //Se crea un iterador para recorrer los libros que hay disponibles, y se almacenan los datos en variables.
                Iterator<Libro>iterator = listaLibros.iterator();
                while (iterator.hasNext()){
                    Libro libroaux = iterator.next();
                    String NombreAutor= libroaux.getAutor();
                    String Titulo =libroaux.getNombre();
                    String Categoria = libroaux.getCategoria();
                    int NumeroCopias= libroaux.getStock();
                    String ISBNlibro=libroaux.getISBN();
                    //Se compara el ISBN a buscar con el ISBN del sistema.
                    if (ISBNlibro.equalsIgnoreCase(ISBNaBuscar)){
                        //Si el ISBN a buscar se encuentra en la lista, se despliegan los datos del libro en las casillas de la ventana.
                        TituloRespuesta.setText(Titulo);
                        Nombreautor.setText(NombreAutor);
                        CategoriaRespuesta.setText(Categoria);
                        NumeroCopiasRespuesta.setText(String.valueOf(NumeroCopias));
                        JOptionPane.showMessageDialog(busquedaporISBN, "Libro Encontrado con Exito !");
                        condicion=false;
                    }
                }
                //No esta el libro y se vacia la casilla para volver a rellenarlas con otro ISBN.
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
