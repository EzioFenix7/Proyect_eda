/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package equilibrabamain;
import java.io.*; // File.pathSeparator
import java.util.*;
public class Equilibrada {
int coma=44;
int dp=58;
String nombrea;
public void Equilibrada(String nombre){
    nombrea=nombre;
}
    public void lectura(String[] args) {
		
                String lec="";
                int lec2=0,temp,temp2=0,f=0,i;
                ArrayList lista=new ArrayList();
                
		try{
			FileInputStream archivo = new FileInputStream(nombrea);// creacion del flujo de lectura
                        PrintWriter wf1 = new PrintWriter("f1.txt");//escritura para archivo 1 a ruta es susceptible a cambio
                        PrintWriter wf2 = new PrintWriter("f2.txt");//escritura para archivo 2 la ruta es susceptible a cambio			
                        BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
                        while(lec2!=-1){//condicion mientras no se llegue al final del archivo seguira leyendo
                        i=1000000;
                        temp=0;
                        lec2=br.read();// avanza al siguiente caracter para evitar quedarse en una coma y entrar en un ciclo infinito
                        while(lec2!=coma&&lec2!=-1){//condicion mientras lo que lea no sea una coma o el final del archivo seguira leyendo se compara con enteros por que es el valor que devuelve read. en este caso el 44 es',' en unicode se debera cambiar para ASCII
                        temp=temp+lec2*i;
                        lec=lec+(char)lec2;// dado que read regresa un valor entero, se debe hacer un cast para transformarlo en un caracter y guardarlo en la cadena
                        lec2=br.read();//se avanza al siguiente caracter
                        i=i/1000;
                        }
                        System.out.println(lec);// muestra el dato actual
                        if(!"".equals(lec)){
                        if(temp2<=temp){//en caso de que continue en orden el numero se agrega a la lista y se actualiza el valor anterior
                        lista.add(lec);
                        temp2=temp;
                        lec="";//se borra la cadena para no interferir con el siguiente dato
                        }
                        else {// en caso de que no se encuentre ordenado se imprimira la lista en el archivo correspondiente
                            if(f%2==0){//un simple modulo con solo 2 resultados para alternar en que archivo se escribira
                                printfile(lista,wf1);
                                f++;
                                lista.add(lec);
                                temp2=temp;
                                lec="";
                            }
                            else{
                                printfile(lista,wf2);
                                f++;
                                lista.add(lec);
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
         void printfile(ArrayList lista,PrintWriter wf){//se imprime la lista en el archivo hasta quedar vacia y se mete un : al final como separador entre vloques              
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
         void printfileb(ArrayList<String> lista,ArrayList<String> lista2,PrintWriter wf){           
            int a,b,i,p;
            while (!lista.isEmpty()&&!lista2.isEmpty())//va comparando ambas listas para imprimir en el archivo de manera ordenada hasta que una de las listas quede vacia
                                {p=1000000;
                                a=0;
                                b=0;
                for(i=0;i<3;i++){//se construte un valor para los elementos que se usara para comparar y determinar cual es el mayor
                    a=a+(lista.get(0).codePointAt(i))*p;
                    p=p/1000;
                }
                p=1000000;
                for(i=0;i<3;i++){
                    b=b+(lista2.get(0).codePointAt(i))*p;
                    p=p/1000;
                }
                                    if (a<b){
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
                                    System.out.println("el elemento0 es: "+lista2.get(0));
                                wf.print(lista2.get(0));
                                lista2.remove(0);
                                if (lista2.size()>0){
                                    wf.print(",");}
                                }
            
            wf.flush();
        }
        void ordena(){
           try{
			PrintWriter wf = new PrintWriter(nombrea);
                        FileInputStream archivo1 = new FileInputStream("f1.txt");
                        FileInputStream archivo2 = new FileInputStream("f2.txt");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(archivo1));
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(archivo2));
                        ArrayList<String> lista1=new ArrayList();
                        ArrayList<String> lista2=new ArrayList();
                        int lec1=0,lec2=0;
                        String lec3;
			while(lec1!=-1&&lec2!=-1){//la validacion requiere que ambos archivos hayan sido leidos hasta el final
                            lec1=br1.read();
                            lec2=br2.read();
                            lec3="";
                            while (lec1!=dp&&lec1!=-1){//aqui se lee el archivo y se genera el elemento
                                if (lec1==coma){//cuando se llega a una coma se inserta en la lista
                                lista1.add(lec3);
                                lec3="";
                                }
                                else{
                                    lec3=lec3+(char)lec1;}//se construye la cadena caracter ah caracter 
                                lec1=br1.read();
                            }
                            if(!"".equals(lec3))// se revise que la cadena no este vacia
                            lista1.add(lec3);

                                lec3="";
                                while (lec2!=dp&&lec2!=-1){
                                if (lec2==coma){
                                lista2.add(lec3);
                                lec3="";
                                }
                                else
                                    lec3=lec3+(char)lec2;   
                                lec2=br2.read();
                            }
                                if(!"".equals(lec3))
                                lista2.add(lec3);
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
