/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package equilibrada;
import java.io.*; // File.pathSeparator
import java.util.*;
public class Equilibrada {
    String ruta;
    int coma=44,dp=58;
    public void Equilibrada(String archivo){
    ruta=archivo;
    }
	public void lectura(String[] args){
		
                String lec="";
                int lec2=0,temp,temp2=0,f=0,i;
                ArrayList lista=new ArrayList();
                
		try{
			FileInputStream archivo = new FileInputStream(ruta);// creacion del flujo de lectura
                        PrintWriter wf1 = new PrintWriter("f1.txt");//escritura para archivo 1 a ruta es susceptible a cambio
                        PrintWriter wf2 = new PrintWriter("f2.txt");//escritura para archivo 2 la ruta es susceptible a cambio			
                        BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
                                            while(lec2!=-1){//condicion mientras no se llegue al final del archivo seguira leyendo
                        lec2=br.read();// avanza al siguiente caracter para evitar quedarse en una coma y entrar en un ciclo infinito
                        while(lec2!=coma&&lec2!=-1){//condicion mientras lo que lea no sea una coma o el final del archivo seguira leyendo se compara con enteros por que es el valor que devuelve read. en este caso el 44 es',' en unicode se debera cambiar para ASCII
                        lec=lec+(char)lec2;// dado que read regresa un valor entero, se debe hacer un cast para transformarlo en un caracter y guardarlo en la cadena
                        lec2=br.read();//se avanza al siguiente caracter
                        }
                        System.out.println(lec);// muestra el dato actual
                        if(!"".equals(lec)){
                        temp=Integer.parseInt(lec);//la cadena se vuelve un numero para despues comparar su valor
                        if(temp2<=temp){//en caso de que continue en orden el numero se agrega a la lista y se actualiza el valor anterior
                        lista.add(temp);
                        temp2=temp;
                        lec="";//se borra la cadena para no interferir con el siguiente dato
                        }
                        else {// en caso de que no se encuentre ordenado se imprimira la lista en el archivo correspondiente
                            if(f%2==0){//un simple modulo con solo 2 resultados para alternar en que archivo se escribira
                                printfile(lista,wf1);
                                f++;
                                lista.add(temp);
                                temp2=temp;
                                lec="";
                            }
                            else{
                                printfile(lista,wf2);
                                f++;
                                lista.add(temp);
                                temp2=temp;
                                lec="";
                            }
                            
                        }
                         }}  
                           if(f%2==0)
                            printfile(lista,wf1);
                           else
                            printfile(lista,wf2); 
                           ordena();
                        

		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado");
		} catch (IOException e){
			System.out.println("Error de entrada");
		}
	}
         void printfile(ArrayList lista,PrintWriter wf){//se imprime la lista en el archivo hasta quedar vacia y se mete un : al final como separador entre bloques                      
            while (!lista.isEmpty())
                                {
                                wf.print(lista.get(0));
                                lista.remove(0);
                                
                                if (lista.size()>0)
                                    wf.print(",");
                                }
            wf.print(":");
            wf.flush();
        }
         void printfileb(ArrayList<Integer> lista,ArrayList<Integer> lista2,PrintWriter wf){                   
            while (!lista.isEmpty()&&!lista2.isEmpty())//va comparando ambas listas para imprimir en el archivo de manera ordenada hasta que una de las listas quede vacia
                                {
                                    if (lista.get(0)<lista2.get(0)){
                                wf.print(lista.get(0));
                                lista.remove(0);}
                                else{
                                wf.print(lista2.get(0));
                                lista2.remove(0);
                                        }
                                    wf.print(",");
                                }
                        while (!lista.isEmpty())//cuando una lista se vacia la otra se termina de imprimir
                                {
                                wf.print(lista.get(0));
                                lista.remove(0);
                                if (lista.size()>0){
                                    wf.print(",");
                               }
                                }
           while (!lista2.isEmpty())
                                {
                                wf.print(lista2.get(0));
                                lista2.remove(0);
                                if (lista2.size()>0){
                                    wf.print(",");}
                                }
            
            wf.flush();
        }
         void ordena(){
           try{
			PrintWriter wf = new PrintWriter(ruta);
                        FileInputStream archivo1 = new FileInputStream("f1.txt");
                        FileInputStream archivo2 = new FileInputStream("f2.txt");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(archivo1));
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(archivo2));
                        ArrayList<Integer> lista1=new ArrayList();
                        ArrayList<Integer> lista2=new ArrayList();
                        int lec1=0,lec2=0;
                        String lec3;
			while(lec1!=-1&&lec2!=-1){//la validacion requiere que ambos archivos hayan sido leidos hasta el final
                            lec1=br1.read();
                            lec2=br2.read();
                            lec3="";
                            while (lec1!=dp&&lec1!=-1){//aqui se lee el archivo y se genera el elemento
                                if (lec1==coma){//cuando se llega a una coma se inserta en la lista
                                lista1.add(Integer.parseInt(lec3));
                                lec3="";
                                }
                                else{
                                    lec3=lec3+(char)lec1;        } 
                                lec1=br1.read();
                            }
                            if(!"".equals(lec3))// se revise que la cadena no este vacia
                            lista1.add(Integer.parseInt(lec3));

                                lec3="";
                                while (lec2!=dp&&lec2!=-1){
                                if (lec2==coma){
                                lista2.add(Integer.parseInt(lec3));
                                lec3="";
                                }
                                else
                                    lec3=lec3+(char)lec2;   
                                lec2=br2.read();
                            }
                                if(!"".equals(lec3))
                                lista2.add(Integer.parseInt(lec3));
                            printfileb(lista1,lista2,wf);
                                wf.print(",");
                        }
		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado");
		}
           catch (IOException e){
			System.out.println("Error de entrada");
		}
        }

}
