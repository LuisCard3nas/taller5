package Forms;

import javax.swing.*;
import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class menuAgregarLibro extends JFrame {
    private JPanel menuagregarlibro;
    private JTextField ISBNaAgregar;
    private JTextField TituloaAgregar;
    private JTextField AutoraAgregar;
    private JTextField CategoriaAgregar;
    private JTextField PaginasaAgregar;
    private JTextField StockaAgregar;
    private JButton agregar;
    private JButton menuPrincipalButton;


    private List<Libro> listaLibros;
    private List<Usuario>listaUsuarios;
    private UsuarioInicioSesion usuarioInicioSesion;

    //Se crea la clase MenuAgregarLibro con sus respectivos parametros, ademas se crea la interfaz grafica para esto.
    public menuAgregarLibro(List<Libro>listaLibros,List<Usuario>listaUsuarios,UsuarioInicioSesion usuarioInicioSesion){
        this.listaLibros= listaLibros;
        this.usuarioInicioSesion=usuarioInicioSesion;
        setContentPane(menuagregarlibro);
        setTitle("Agregar Libro ");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro(listaLibros);
            }
        });
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuOpciones menuOpciones = new MenuOpciones(listaLibros,listaUsuarios,usuarioInicioSesion);
                dispose();
                menuOpciones.setVisible(true);
            }
        });

    }
    //La clase agregarLibro es usada para preguntar por todos los datos del libro que se desea usar usando la ventana.
    private void agregarLibro(List<Libro>listaLibros){
        boolean isbnEncotrado=false;
        try{
            String ISBN = ISBNaAgregar.getText();
            String Titulo = TituloaAgregar.getText();
            String Autor = AutoraAgregar.getText();
            String Categoria = CategoriaAgregar.getText();
            String Paginas = PaginasaAgregar.getText();
            String Stock = StockaAgregar.getText();
            //Se verifica que todos los espacios tengan contenido.
            if (!ISBN.isEmpty() && !Titulo.isEmpty() && !Autor.isEmpty() && !Categoria.isEmpty() && !Paginas.isEmpty() && !Stock.isEmpty()){
                //Se crea un iterador para recorrer el arreglo y comporbar si el libro que se desea agregar ya se encuentra en el sistema.
                Iterator<Libro> iterator= this.listaLibros.iterator();
                while (iterator.hasNext()){
                    Libro libroaux = iterator.next();
                    String ISBNaux = libroaux.getISBN();
                    if (!ISBN.equalsIgnoreCase(ISBNaux)){
                        isbnEncotrado=true;

                    }else{
                        JOptionPane.showMessageDialog(menuagregarlibro, "El ISBN del libro a agregar ya se encuentra registrado, Intentelo nuevamente con un ISBN distinto");
                        isbnEncotrado=false;
                        clear();
                    }
                }
                //Si la condicion es true, o sea, que el libro no esta en el sistema ae agrega el libro con sus respectivos datos.
                if(isbnEncotrado){
                    Libro libroaAgregar = new Libro(ISBN,Titulo,Autor,Categoria,Integer.parseInt(Paginas),Integer.parseInt(Stock));
                    listaLibros.add(libroaAgregar);
                    JOptionPane.showMessageDialog(menuagregarlibro,"El libro a sido agregado con exito!");
                    clear();
                }
            }else{
                JOptionPane.showMessageDialog(menuagregarlibro,"Por favor , Complete todos los campos para continuar");
                clear();
            }


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(menuagregarlibro,"Ha ocurrido un error [!]");
            clear();
        }

    }
    //Clear para dejar los campos a rellenar vacios.
    private void clear(){
        ISBNaAgregar.setText("");
        TituloaAgregar.setText("");
        AutoraAgregar.setText("");
        CategoriaAgregar.setText("");
        StockaAgregar.setText("");
        PaginasaAgregar.setText("");

    }

}
