package Controlador;

import java.util.ArrayList;
import Controlador.control_metodos;
import javax.swing.JOptionPane;

public class control_principal {

    public static void main(String[] args) {
        control_metodos c = new control_metodos();
        int num_restricciones = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de Restricciones"));
        int num_variables = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de Variables"));
        String[] funciones = {"Maximizar", "Minimizar"};
        String[] opc = { "<=", ">=", "="};
        String funcion = (String) JOptionPane.showInputDialog(null, "Seleccione que desea realizar", "Función", JOptionPane.DEFAULT_OPTION, null, funciones, funciones[0]);
        ArrayList<ArrayList> ejercicio = new ArrayList<>();
        ArrayList<String> aux1 = new ArrayList();
        for (int i = 0; i <= num_restricciones; i++) {
            for (int j = 0; j <= num_variables; j++) {
                if (j == num_variables) {
                    if (i != 0) {
                        aux1.add((String) JOptionPane.showInputDialog(null, "Seleccione operando", "Función", JOptionPane.DEFAULT_OPTION, null, opc, funciones[0]));
                        aux1.add(JOptionPane.showInputDialog("Ingrese igualdad"));
                    }
                } else {
                    aux1.add(JOptionPane.showInputDialog("Ingrese x" + (j + 1)));
                }
            }
            ejercicio.add((ArrayList) aux1.clone());
            aux1.clear();
        }
        for (int i = 0; i < ejercicio.size(); i++) {
            aux1=ejercicio.get(i);
            if(i==0){
                System.out.print(funcion+" = ");
            }else if(i==1){
                System.out.println("Restricciones");
            }
            
            for (int j = 0; j < aux1.size(); j++) {
                if (j >= aux1.size() - 2 & i>0) {
                    System.out.print(" "+aux1.get(j)+" ");
                } else if(i==0){
                    System.out.print(" "+aux1.get(j) + "x" + (j + 1)+" ");
                }else{
                    System.out.print(" "+aux1.get(j) + "x" + (j + 1)+" ");
                }
             
            }
            System.out.println("");
            if(i==num_restricciones){
                for (int j = 0; j < num_variables; j++) {
                    System.out.print(" x"+(j+1)+" >= "+"0");
                }
            }
        }
        System.out.println("");
        ejercicio=c.estandarizar(ejercicio,num_restricciones);
        
        //En esta parte se verifica se se debe maximizar en este caso se invierten los signos de la funcion objetivo y al minimizar no se realiza nada a la funion objetivo
        if (funcion.equals("Maximizar")) {
            ejercicio.set(0, c.invertir(ejercicio.get(0)));
        }
        for (int i = 0; i < ejercicio.size(); i++) {
            System.out.println(ejercicio.get(i));
        }
        System.out.println("");
        while (c.continuar(ejercicio.get(0))) {
            //elegimos la columna del pivote
            int columna = c.elegir_pivote(ejercicio.get(0));
            //realizamos un arraylist para guardar todas las razones de la columna pivote y elegir la menor o la positiva
            ArrayList<Double> razon = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                razon.add(c.razon(ejercicio.get(i), columna));
            }
            ArrayList<Double> fila = new ArrayList<>();
            fila = ejercicio.get(c.elegir_razon(razon) + 1);
            double div = fila.get(columna);
            if (div != 1) {
                for (int i = 0; i < fila.size(); i++) {
                    fila.set(i, fila.get(i) / div);
                }
            }
            ArrayList<Double> aux = new ArrayList<>();
            for (int i = 0; i < ejercicio.size(); i++) {
                if (fila != ejercicio.get(i)) {
                    aux = ejercicio.get(i);
                    double op = aux.get(columna) * -1;
                    for (int j = 0; j < fila.size(); j++) {
                        if (fila.get(j) != 0) {
                            aux.set(j, (aux.get(j)) + (fila.get(j) * op));
                        } else {
                            fila.set(j, 0.0);
                        }
                    }
                }
            }
            for (int i = 0; i < ejercicio.size(); i++) {
                System.out.println(ejercicio.get(i));
            }
            System.out.println("");
        }

    }
}
