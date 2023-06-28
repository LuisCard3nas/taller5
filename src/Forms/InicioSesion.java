package Forms;

import Entities.Usuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InicioSesion extends JFrame {
    private JTextField RUTIngresado;
    private JButton iniciarSesionButton;
    private JPanel menu;
    private JPasswordField ContraseniaIngresada;

    private List<Usuario> listaUsuarios;

    public InicioSesion(List<Usuario> listaUsuarios){
        this.listaUsuarios=listaUsuarios;
        setContentPane(menu);
        setTitle("Iniciar sesion");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEntrada(listaUsuarios);

            }
        });

    }

    private void verificarEntrada(List<Usuario> ListaUsuarios){
        boolean condicion = true;

        try {
            String rutIngresado = RUTIngresado.getText();
            String contraseniaIngresada = String.valueOf(ContraseniaIngresada.getPassword());

            if (!rutIngresado.isEmpty() && !contraseniaIngresada.isEmpty()){
                Iterator<Usuario> iterator = ListaUsuarios.iterator();
                while (iterator.hasNext()){
                    Usuario usuario = iterator.next();
                    String rutAux = usuario.getRut();
                    String contraseniaAux = usuario.getContrasenia();
                    if(rutIngresado.equalsIgnoreCase(rutAux) && contraseniaIngresada.equalsIgnoreCase(contraseniaAux)) {
                        JOptionPane.showMessageDialog(menu, "Ingresaste con exito");
                        condicion = false;
                        setVisible(false);

                    }
                }
                if(condicion){
                    JOptionPane.showMessageDialog(menu, "Credenciales invalidas, por favor vuelva a intentarlo");
                    clear();
                }





            }
            else{
                JOptionPane.showMessageDialog(menu,"Por favor , ingrese datos en todos los campos");
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