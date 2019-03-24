/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2;
import java.util.*;
public class Utilidad
{
    /**
     * [Pregunta al usuario entre dos opciones (si o no) y regresa la respueesta]
     * @param  pregunta [Pregunta que sera preguntada]
     * @return          [Respuesta a la pregunta] 
     */
    public boolean siNo(String pregunta)
    {
        Scanner teclado = new Scanner(System.in);
        int op;
        boolean exito=false;
        do
        {
            System.out.println("_________________________");
            System.out.println("Desea " + pregunta);
            System.out.println("Introduzca el numero de la opcion");
            System.out.println("0==No\t 1==Si");
            op=teclado.nextInt();
            switch(op)
            {
                case 0:
                    exito=false;
                break;
                case 1:
                    exito=true;
                break;
                default:
                    System.out.println("Intentelo de nuevo, su valor es incorrecto");
                break;
            }
        }
        while(op!=0 && op!=1);
        return exito;
    }  
    /**
     * [Pide valores y devuelve el numero hasta que el numero este entre (x-y)]
     * @param  x [rango inferior]
     * @param  y [rango superior]
     * @return   [valor que esta entre (X-Y) introducido por el usuario]
     */
    public int entre(int x,int y)
    {
        Scanner teclado = new Scanner(System.in);
        int aux;
        do
        {
            System.out.println("Introduzca el numero de la opción entre (" + x + "-" + y +")");
            aux=teclado.nextInt();

            if(aux<x || y<aux)
                System.out.println("Intenta de nuevo, la opcion no es valida");
        }
        while(aux<x || y<aux);
        return aux;
    }
    /**
     * [Lectura de un String]
     * @return [String leido]
     */
    public String rcadena() // read cadena
    {
        Scanner teclado = new Scanner(System.in);
        String aux=teclado.nextLine();
        return aux;
    }
    /**
     * [Lectura de un int]
     * @return [entero leido]
     */
    public int rentero() // read entero
    {
        Scanner teclado = new Scanner(System.in);
        int aux=teclado.nextInt();
        return aux;
    }
}