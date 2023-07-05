package Forms;

import Entities.Libro;
import Entities.Usuario;
import Entities.UsuarioInicioSesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class InicioSesion extends JFrame {
    private JTextField RUTIngresado;
    private JButton iniciarSesionButton;
    private JPanel menu;
    private JPasswordField ContraseniaIngresada;
    private JButton salirDelProgramaButton;

    private List<Usuario> listaUsuarios;
    private List<Libro> listaLibros;
    private UsuarioInicioSesion usuarioInicioSesion;

    //Se crea la clase IniciarSesion con sus respectivos parametros, ademas se crea la interfaz grafica para esto.
    public InicioSesion(List<Usuario> listaUsuarios,List<Libro>listaLibros,UsuarioInicioSesion usuarioInicioSesion){
        this.listaUsuarios=listaUsuarios;
        this.listaLibros=listaLibros;
        this.usuarioInicioSesion=usuarioInicioSesion;
        setContentPane(menu);
        setTitle("Iniciar sesion");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Boton IniciarSesion
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEntrada(listaUsuarios);

            }
        });

        //Boton SalirDelPrograma.
        salirDelProgramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menu,"Saliendo del programa");
                dispose();
            }
        });

    }

    //En esta clase se recorre el arreglo en cual tiene los usuarios regitrados para hacer un inicion de sesion valido.
    private void verificarEntrada(List<Usuario> ListaUsuarios){
        boolean condicion = true;
        try {
            //Pregunta los datos para iniciar sesion.
            String rutIngresado = RUTIngresado.getText();
            String contraseniaIngresada = String.valueOf(ContraseniaIngresada.getPassword());
            //If para comprobar que se llene las casillas.
            if (!rutIngresado.isEmpty() && !contraseniaIngresada.isEmpty()){
                //Se crea un iterador para recorrer el arreglo y se busca que los datos ingresados se encuentran en el sistema.
                Iterator<Usuario> iterator = this.listaUsuarios.listIterator();
                while (iterator.hasNext()){
                    Usuario usuario = iterator.next();
                    String rutAux = usuario.getRut();
                    String contraseniaAux = usuario.getContrasenia();
                    if(rutIngresado.equalsIgnoreCase(rutAux) && contraseniaIngresada.equalsIgnoreCase(contraseniaAux)) {
                        JOptionPane.showMessageDialog(menu, "Ingresaste con exito");
                        UsuarioInicioSesion usuarioInicioSesion = new UsuarioInicioSesion(usuario.getRut(),usuario.getNombre(),usuario.getApellido(),usuario.getContrasenia());
                        condicion = false;
                        dispose();
                        //Una vez tod0 validado se invoca otra clase que tiene las opciones del menu y se envia los parametros a usar.
                        MenuOpciones menuOpciones = new MenuOpciones(listaLibros,listaUsuarios,usuarioInicioSesion);
                        menuOpciones.setVisible(true);

                    }
                }
                if(condicion){
                    JOptionPane.showMessageDialog(menu, "Credenciales invalidas, por favor vuelva a intentarlo");
                    clear();
                }
            }
            else{
                JOptionPane.showMessageDialog(menu,"Por favor , ingrese datos en todos los campos");
                clear();
            }
        }
        catch (NumberFormatException e){
           JOptionPane.showMessageDialog(menu," Ha ocurrido un error [!]");
           clear();

        }
    }

    private void clear(){
        RUTIngresado.setText("");
        ContraseniaIngresada.setText("");
    }

}
