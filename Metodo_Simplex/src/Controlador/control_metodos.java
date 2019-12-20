package Controlador;

import java.util.ArrayList;

public class control_metodos {

    public double razon(ArrayList<Double> array, int indice) {
        double resultado = 0;
        if (array.get(indice) == 0) {
            resultado = -1;
        } else {
            resultado = array.get(array.size()-1)/array.get(indice);
        }
        return resultado;
    }

    public int elegir_razon(ArrayList<Double> array) {
        double menor = 99999;
        int indice = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) >= 1) {
                if (array.get(i)< menor) {
                    menor = array.get(i);
                    indice=i;
                }
            }
        }

        return indice;
    }

    public ArrayList<Double> invertir(ArrayList<Double> array) {
        ArrayList<Double> lista = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != 0) {
                lista.add(array.get(i) * new Double(-1));
            }else{
                lista.add(array.get(i));
            }
        }
        return lista;
    }

    public int elegir_pivote(ArrayList<Double> array) {
        double menor = 99999;
        int indice = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != 0) {
                if (array.get(i) < menor) {
                    menor = array.get(i);
                    indice = i;
                }
            }
        }
        return indice;
    }
    
    public boolean continuar(ArrayList<Double> array){
        Boolean cont=true;
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i)>=0){
                cont=false;
            }else{
               return cont=true;
            }
        }
        return cont;
    }

    public ArrayList<ArrayList> estandarizar(ArrayList<ArrayList> array, int num_restricciones) {
        ArrayList<ArrayList> f = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>();
        ArrayList<Double> fin = new ArrayList<>();
        int cont = 1;
        for (int i = 0; i< array.size(); i++) {
            aux = array.get(i);
            String comp = aux.get(aux.size() - 2);
            if (comp.equals("<=")||comp.equals("=")) {
                for (int j = 0; j < aux.size(); j++) {
                    if (aux.get(j) == comp) {
                        for (int k = 1; k <= num_restricciones; k++) {
                            if (cont == k) {
                                fin.add(1.0);
                            } else {
                                fin.add(0.0);
                            }
                        }
                        cont++;
                    } else {
                        fin.add(Double.parseDouble(aux.get(j)));
                    }
                }
                f.add((ArrayList) fin.clone());
                fin.clear();
            } else if(comp.equals(">=")){
                for (int j = 0; j < aux.size(); j++) {
                    if (aux.get(j) == comp) {
                        for (int k = 1; k <= num_restricciones; k++) {
                            if (cont == k) {
                                fin.add(-1.0);
                            } else {
                                fin.add(0.0);
                            }
                        }
                        cont++;
                    } else {
                        fin.add(Double.parseDouble(aux.get(j)));
                    }
                }
                f.add((ArrayList) fin.clone());
                fin.clear();
            } else {
                for (int j = 0; j < aux.size()+1; j++) {
                    if (j == aux.size()) {
                        for (int k = 0; k <= num_restricciones; k++) {
                            fin.add(0.0);
                        }
                    } else {
                        fin.add(Double.parseDouble(aux.get(j)));
                    }
                }
                f.add((ArrayList) fin.clone());
                fin.clear();
            }
        }
        return f;
    }


}
