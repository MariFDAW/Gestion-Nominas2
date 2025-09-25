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
                    System.out.println("Dime el dni del empleado a actualizar");
                    String dni = sc.nextLine();
                    System.out.println("Dime el nombre nuevo del empleado a actualizar");
                    String nombre = sc.nextLine();
                    actualizaNombre(dni, nombre);
                    break;
                case 2:
                    System.out.println("Dime el dni del empleado a actualizar");
                    String dniAnt = sc.nextLine();
                    System.out.println("Dime el dni nuevo del empleado a actualizar");
                    String dniNuv = sc.nextLine();
                    actualizaDni(dniAnt, dniNuv);
                    break;
                case 3:
                    dni = "";
                    System.out.println("Dime el dni del empleado a actualizar");
                    dni = sc.nextLine();
                    System.out.println("Dime el sexo nuevo del empleado a actualizar");
                    String sexo = sc.nextLine();
                    actualizaSexo(dni, sexo);
                    break;
                case 4:
                    dni = "";
                    Scanner scNum = new Scanner(System.in);
                    System.out.println("Dime el dni del empleado a actualizar");
                    dni = sc.nextLine();
                    System.out.println("Dime la categoria nueva del empleado a actualizar");
                    int categoria = scNum.nextInt();
                    actualizaCategoria(dni, categoria);
                    break;
                case 5:
                    dni = "";
                    Scanner scNum2 = new Scanner(System.in);
                    System.out.println("Dime el dni del empleado a actualizar");
                    dni = sc.nextLine();
                    System.out.println("Dime los años trabajados nuevos del empleado a actualizar");
                    int anyos = scNum2.nextInt();
                    actualizaAnyos(dni, anyos);
                    break;

                default:
                    break;
            }
        } while (opcion != 0);
        sc.close();
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

    public void actualizaNombre(String dni, String nombre){     
        String update ="UPDATE empleados SET Nombre = ? WHERE DNI = ?";
        try (Connection conn = ConexionBD.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(update)) {

        ps.setString(1, nombre);
        ps.setString(2, dni);
         
        ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    public void actualizaDni(String dni, String dniNuevo){
        String update ="UPDATE empleados SET DNI = ? WHERE DNI = ?";
        try (Connection conn = ConexionBD.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(update)) {

        ps.setString(1, dniNuevo);
        ps.setString(2, dni);
         
        ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    public void actualizaSexo(String dni, String sexo){
        String update ="UPDATE empleados SET SEXO = ? WHERE DNI = ?";
        try (Connection conn = ConexionBD.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(update)) {

        ps.setString(1, sexo);
        ps.setString(2, dni);
         
        ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    public void actualizaCategoria(String dni, int categoria){
        String update ="UPDATE empleados SET categoria = ? WHERE DNI = ?";
        try (Connection conn = ConexionBD.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(update)) {

        ps.setInt(1, categoria);
        ps.setString(2, dni);
         
        ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void actualizaAnyos(String dni, int anyos){
        String update ="UPDATE empleados SET años = ? WHERE DNI = ?";
        try (Connection conn = ConexionBD.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(update)) {

        ps.setInt(1, anyos);
        ps.setString(2, dni);
         
        ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
}
