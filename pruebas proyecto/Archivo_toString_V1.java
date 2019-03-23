/// la diferencias es que este lee y lo convierte a una lista  de strings
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File; // File.pathSeparator
import java.util.*;

public class Archivo_toString_V1{

	public static void main(String[] args){
		String usuario = "Ezio";
		String ruta = "C:\\Users\\" + usuario + "\\Desktop\\texto.txt";
		BufferedReader br;
		LinkedList<String> list = new LinkedList<>();
		try
		{
			FileInputStream archivo = new FileInputStream(ruta);
			br = new BufferedReader(new InputStreamReader(archivo));
			//char[] buf = new char[archivo.available()];
			//br.read(buf,0,archivo.available());
			String lectura=br.readLine();
			String tmp="";
			/*for(char c:buf)
			{
				if(c == ',')
				{
					list.add(tmp);
					System.out.print("\n");
					tmp="";
				}
				else
				{
					tmp=tmp+c;
				System.out.print(c);	
				}
				
			}*/
			//System.out.print(list);
			char c;
			for(int i=0;i<lectura.length();i++)
			{
				c=lectura.charAt(i);
				if( c== ',')
				{
					list.add(tmp);
					System.out.print("\n");
					tmp="";
				}
				else
				{
					tmp=tmp+c;
				System.out.print(c);	
				}

			}
			System.out.print(lectura);
			System.out.println("hola");
			System.out.print(list);
			System.out.println("." +list.remove(1) + ".");
			//System.out.print(lectura.indexOf(",",0));

		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado");
		} catch (IOException e){
			System.out.println("Error de entrada");
		}
	}

}