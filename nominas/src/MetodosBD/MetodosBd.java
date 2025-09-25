package MetodosBD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Laboral.Empleado;
import Laboral.Nomina;

public class MetodosBd {

    public void mostrarEmpleados() {
        try (Connection conn = ConexionBD.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM empleados");) {

            while (rs.next()) {
                System.out.println(
                        "Nombre: " + rs.getString(1) + ", DNI: " + rs.getString(2) + ", Sexo: " + rs.getString(3)
                                + " ,Categoria: " + rs.getInt(4) + " ,Años: " + rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarSalarioEmpleado(String dni) {
        try (Connection conn = ConexionBD.getConnection();
                PreparedStatement ps = conn
                        .prepareStatement("SELECT sueldo,empleado FROM nominas where empleado = ?");) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Sueldo: " + rs.getDouble(2) + " ,Empleado: " + rs.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Revisar
    public void actualizarEmpleados(Empleado emp) {

        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENÚ");
            System.out.println("0. Volver al inicio");
            System.out.println("1. Actualizar nombre");
            System.out.println("2. Actualizar dni");
            System.out.println("3. Actualizar sexo");
            System.out.println("4. Actualizar categoria");
            System.out.println("5. Actualizar años");

            System.out.println("------------------------------------------------------------------");
            System.out.println("Inserta un numero para elegir su opcion");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:

                    break;

                default:
                    break;
            }
        } while (opcion != 0);
        /*
         * String update =
         * "UPDATE empleados SET Nombre = ?, SET DNI = ?, SET SEXO = ?,SET Categoria = ?,SET Años = ? WHERE DNI = ?"
         * ;
         * try (Connection conn = ConexionBD.getConnection();
         * Statement st = conn.createStatement();
         * PreparedStatement ps = conn.prepareStatement(update)) {
         * 
         * ps.setString(1, emp.nombre);
         * ps.setString(2, emp.dni);
         * ps.setString(3, String.valueOf(emp.sexo));
         * ps.setInt(4, emp.getCategoria());
         * ps.setInt(5, emp.anyos);
         * 
         * ps.executeUpdate();
         * 
         * } catch (SQLException e) {
         * System.out.println(e.getMessage());
         * 
         * }
         */
    }

    public void actualizarSueldoUnEmpleado(Empleado emp) {
        String update = "UPDATE nominas SET sueldo = ? WHERE empleado = ?";
        Nomina n = new Nomina();
        try (Connection conn = ConexionBD.getConnection();
                Statement st = conn.createStatement();
                PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setDouble(1, n.sueldo(emp));
            ps.setString(2, emp.dni);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void actualizarSueldosTodosEmpleados() {

    }

    public void copiaBDEnFichero() {
        String ruta = "data\\copiaBD";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
                Connection conn = ConexionBD.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM empleados");) {

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
