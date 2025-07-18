package estructuras;

import java.awt.Image;
import javax.swing.*;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JLabel lblMensaje;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Sistema de Trámite Documentario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 650);
        setLocationRelativeTo(null);
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("logo_ulimaa.png"));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);

        // Crear el nuevo icono con la imagen redimensionada
        ImageIcon iconoRedimensionado = new ImageIcon(imagenEscalada);

        // Crear JLabel con el nuevo icono
        JLabel lblLogo = new JLabel(iconoRedimensionado);
        lblLogo.setBounds(50, 20, 400, 200); // posición x=50, y=10. Ajusta si quieres centrar
        add(lblLogo);


        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 240, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 240, 180, 25);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 280, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 280, 180, 25);
        add(txtPassword);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(140, 330, 100, 30);
        add(btnIngresar);

        lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 370, 300, 25);
        add(lblMensaje);

        btnIngresar.addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtPassword.getPassword());

        if (usuario.equals("admin") && clave.equals("admin")) {
            new MenuPrincipal().setVisible(true);
            this.dispose(); // Cierra la ventana actual
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
    
}
