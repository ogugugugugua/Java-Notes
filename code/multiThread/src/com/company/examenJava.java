package com.company;
import javax.crypto.spec.PSource;

public class examenJava {
    private static int i ;
    private static int nonStatic;

    class A_nonStatic{
        int get_nonStatic(){
            return nonStatic;
        }
    }
    static class A{
        int get(){
            return (new examenJava()).i;
        }
    }
    public static void main(String args[]) {
//        agent[] tab = new agent[3];
//        tab[0] = new agent();
//        tab[1] = new agent(){
//            public String toString(){
//                return "agent secret";
//            }
//        };
//        tab[2] = new agent();
//        for(agent a : tab){
//            System.out.println(a);
//        }
        examenJava examen = new examenJava();
        int temp = (examen.new A_nonStatic()).get_nonStatic();
        String name = null;
        assert (name != null):"hell no";
        System.out.println(temp);
//        System.out.println(examenJava.A.get());
        int res = new examenJava.A().get();
    }
}
class agent{
    static int cpt = 0;
    int numero;
    agent() {
        numero = cpt;
        cpt++;
    }
    public String toString(){
        return "numero "+numero;
    }
}