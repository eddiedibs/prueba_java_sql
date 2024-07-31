import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtEmail;
    private JTextField txtFechaNacimiento;
    private JTextField txtEstado;
    private JTextField txtMunicipio;
    private JTextField txtBuscarID;

    public StudentGUI() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 80, 25);
        add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 10, 200, 25);
        add(txtNombre);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 40, 80, 25);
        add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setBounds(100, 40, 200, 25);
        add(txtApellido);

        // Agrega otros campos de texto aquí de manera similar

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(10, 250, 90, 25);
        add(btnGuardar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(110, 250, 90, 25);
        add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(210, 250, 90, 25);
        add(btnEliminar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(310, 250, 90, 25);
        add(btnSalir);

        JLabel lblBuscarID = new JLabel("Buscar ID:");
        lblBuscarID.setBounds(10, 290, 80, 25);
        add(lblBuscarID);

        txtBuscarID = new JTextField();
        txtBuscarID.setBounds(100, 290, 200, 25);
        add(txtBuscarID);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(310, 290, 90, 25);
        add(btnBuscar);

        // Acciones de los botones
        StudentOperations operations = new StudentOperations();

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validaciones y llamada al método addStudent
                operations.addStudent(txtNombre.getText(), txtApellido.getText(), txtDireccion.getText(), txtEmail.getText(), txtFechaNacimiento.getText(), txtEstado.getText(), txtMunicipio.getText());
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validaciones y llamada al método updateStudent
                // Se asume que el ID es un campo adicional que debe ser ingresado
                int id = Integer.parseInt(txtBuscarID.getText());
                operations.updateStudent(id, txtNombre.getText(), txtApellido.getText(), txtDireccion.getText(), txtEmail.getText(), txtFechaNacimiento.getText(), txtEstado.getText(), txtMunicipio.getText());
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este estudiante?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(txtBuscarID.getText());
                    operations.deleteStudent(id);
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtBuscarID.getText());
                operations.findStudent(id);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGUI().setVisible(true);
            }
        });
    }
}

