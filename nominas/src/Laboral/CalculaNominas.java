package Laboral;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import GestorFicheros.GestorFicheroEmpleados;
import MetodosBD.AltaEmpleado;
import MetodosBD.ConexionBD;

public class CalculaNominas {

    /**
     * @param args
     *             Maria del carmen Farfán Gavilán
     *             Esto es para generar un javadoc
     */
    public static void main(String[] args) {

        AltaEmpleado altaBD = new AltaEmpleado();
        altaBD.altaEmpleados();

        List<Empleado> listaEmpleado = new GestorFicheroEmpleados().leerFichero();

        Empleado e1 = listaEmpleado.get(0);
        Empleado e2 = listaEmpleado.get(1);

        escribe(e1, e2);

        GestorFicheroEmpleados gt = new GestorFicheroEmpleados();
        gt.escribeFichero(listaEmpleado);
    }

    /**
     * @param e1 parametro del empleado 1
     * @param e2 parametro del empleado 2
     *           Para usar este metodo en el main, es necesario hacerlo static para
     *           permitir
     *           su uso en otros sitios
     *           Creo un objeto nomina para poder usar su metodo privado y asi
     *           obtener el sueldo
     */
    private static void escribe(Empleado e1, Empleado e2) {
        Nomina n = new Nomina();
        System.out.println("Empleado 1: Nombre: " + e1.nombre + " dni: " + e1.dni + " Sexo:" + e1.sexo
                + " Categoria: " + e1.getCategoria() + " Años: " + e1.anyos + " Sueldo: " + n.sueldo(e1));
        System.out.println("Empleado 2: Nombre: " + e2.nombre + " dni: " + e2.dni + " Sexo:" + e2.sexo
                + " Categoria: " + e2.getCategoria() + " Años: " + e2.anyos + " Sueldo: " + n.sueldo(e2));
    }

    private static void mostrarEmpleados(){
        try (Connection conn = ConexionBD.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM empleados");) {
            
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString(1)+", DNI: "+rs.getString(2)+", Sexo: "+rs.getString(3)
                +" ,Categoria: "+rs.getInt(4)+" ,Años: "+rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarUnSoloEmpleado(String dni){
        try (Connection conn = ConexionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM empleados where dni = ?");) {
            
            ps.setString(1, dni);   
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("Nombre: "+rs.getString(1)+", DNI: "+rs.getString(2)+", Sexo: "+rs.getString(3)
                +" ,Categoria: "+rs.getInt(4)+" ,Años: "+rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Revisar
    private static void actualizarEmpleados(Empleado emp){
        String update = "UPDATE empleados SET Nombre = ?, SET DNI = ?, SET SEXO = ?,SET Categoria = ?,SET Años = ? WHERE DNI = ?";
        try(Connection conn = ConexionBD.getConnection();
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(update)) {
            
            ps.setString(1, emp.nombre);
            ps.setString(2, emp.dni);
            ps.setString(3, String.valueOf(emp.sexo));
            ps.setInt(4, emp.getCategoria());
            ps.setInt(5, emp.anyos);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void actualizarSueldoUnEmpleado(Empleado emp){
        String update = "UPDATE nominas SET sueldo = ? WHERE empleado = ?";
        Nomina n = new Nomina();
        try(Connection conn = ConexionBD.getConnection();
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(update)) {
            
            ps.setDouble(1, n.sueldo(emp));
            ps.setString(2, emp.dni);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void actualizarSueldosTodosEmpleados(){
        
    }

    private static void copiaBDEnFichero(){
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
