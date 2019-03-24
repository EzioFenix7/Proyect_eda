/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2;

/**
 *
 * @author Ezio
 */
public class V2 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int op,op1; //opciones del menu
        Utilidad util=new Utilidad();
       do 
       {
            menu();
            op=util.entre(0,1);
           switch(op)
           {
               case 0:
               {
                    System.out.println("Hasta la vista usuario");
               }
               break;
               case 1:  //Ordenar un archivo
               {
                System.out.println("Selecciono odernamiento");
                    do
                    {
                        subMenu();
                        op1=util.entre(0,3); 
                        switch (op1) 
                        {
                            case 0:
                            {
                                System.out.println("Regresamos al menu principal");
                            } 
                            break;
                            case 1: //Distribución(Radix)
                            {
                                System.out.println("Selecciono Distribución(Radix)");

                            } 
                            break;
                            case 2: //Mezcla Equilibrada
                            {
                                System.out.println("Selecciono Equilibrada");

                            } 
                            break; 
                            case 3: //Polifase
                            {
                                System.out.println("Selecciono Polifase");

                            } 
                            break; 
                        }
                    }
                    while (op1!=0);
               }
               break;
               /*case 2:
               {
                    System.out.println("Selecciono");

               }
               break;
               case 3:
               {
                    System.out.println("Selecciono");
               }
               break;
               case 4:
               {
                    System.out.println("Selecciono");

               }
               break;*/
           } 
       }
       while(op!=0);
    }
    private static void menu()
    {
        int x=1;
        System.out.println("_________________________________________");
        System.out.println("Menu principal");
        System.out.println(x + "\tOrdenar un archivo");x++;
        System.out.println(x + "\tIntroduzca");x++;
        //System.out.println(x + "\t ");x++;
        //System.out.println(x + "\t ");x++;
        System.out.println("0 \t Salir del programa");
        /*System.out.println( " ");*/
    }
    private static void subMenu()
    {
        int x=1;
        System.out.println("_________________________________________");
        System.out.println("Sub Menu de la opcion 1 (Ordenamiento)");
        System.out.println("Introduzca el metodo ordenamiento que desear emplear");
        System.out.println(x + "\t Distribución(Radix)");x++;
        System.out.println(x + "\t Mezcla Equilibrada");x++;
        System.out.println(x + "\t Polifase");x++;
        System.out.println("0 \t Salir del Sub menu");
    }
}
