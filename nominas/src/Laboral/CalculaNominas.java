package Laboral;

import java.util.List;

import GestorFIcheros.GestorFicheroEmpleados;

public class CalculaNominas {

    /**
     * @param args
     *             Maria del carmen Farfán Gavilán
     *             Esto es para generar un javadoc
     */
    public static void main(String[] args) {
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
}
