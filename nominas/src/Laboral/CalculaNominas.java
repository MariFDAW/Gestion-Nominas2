package Laboral;

public class CalculaNominas {

    /**
     * @param args
     *  Maria del carmen Farfán Gavilán
 	 *  Esto es para generar un javadoc
     */
    public static void main(String[] args) {

        try {
            Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
            Empleado e2 = new Empleado("Ada LoveLace", "32000031R", 'F');
            escribe(e1, e2);
            e2.incrAnyo();
            e1.setCategoria(9);
            escribe(e1, e2);
        } catch (Exception e) {
            System.out.println("Datos no correctos");
        }

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
