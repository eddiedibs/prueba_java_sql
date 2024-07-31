import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class StudentOperations {
    public void addStudent(String nombre, String apellido, String direccion, String email, String fechaNacimiento, String estado, String municipio) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO ALUMNO (Nombre, Apellido, Dirección, Email, Fecha_Nacimiento, Estado, Municipio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, direccion);
            ps.setString(4, email);
            ps.setString(5, fechaNacimiento);
            ps.setString(6, estado);
            ps.setString(7, municipio);
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante registrado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String nombre, String apellido, String direccion, String email, String fechaNacimiento, String estado, String municipio) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE ALUMNO SET Nombre = ?, Apellido = ?, Dirección = ?, Email = ?, Fecha_Nacimiento = ?, Estado = ?, Municipio = ? WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, direccion);
            ps.setString(4, email);
            ps.setString(5, fechaNacimiento);
            ps.setString(6, estado);
            ps.setString(7, municipio);
            ps.setInt(8, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante modificado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM ALUMNO WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findStudent(int id) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM ALUMNO WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String direccion = rs.getString("Dirección");
                String email = rs.getString("Email");
                String fechaNacimiento = rs.getString("Fecha_Nacimiento");
                String estado = rs.getString("Estado");
                String municipio = rs.getString("Municipio");

                // Mostrar los datos en los campos de texto o área de texto
                JOptionPane.showMessageDialog(null, "ID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido +
                        "\nDirección: " + direccion + "\nEmail: " + email + "\nFecha de Nacimiento: " + fechaNacimiento +
                        "\nEstado: " + estado + "\nMunicipio: " + municipio);
            } else {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

