import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedList;


/**
* Ordena los elementos de un archivo txt leyendo un determinado numero de llaves.
* Genera bloques y los guarda en archivos auxiliares.
*/
public class Polifase{
	// Inicializando propiedades del Sistema Operativo y Directorio Actual de Trabajo
	public static String os = System.getProperty("os.name").toLowerCase();
	public static String dir = System.getProperty("user.home");

	// Listas auxiliares para almacenar datos leidos desde archivo.
	public static List<String> lista1 = new LinkedList<>();
	public static List<String> lista2 = new LinkedList<>();

	public static int tamarch1 = 0;
	public static int tamarch2 = 0;

	public static String archivoEntrada;

	public Polifase(String archivoEntrada){
		this.archivoEntrada = archivoEntrada;
	}

	/** 
	* Funcion principal del programa
	* @exception FileNotFoundException Archivo no encontrado.
	* @exception IOException Error de entrada/salida.
	*/
	public static void polifase(){
		// Definiendo Sistema Operativo
		if(os.indexOf("win")>=0){
			dir = dir + "\\Desktop\\";
		} else if(os.indexOf("mac")>=0){
			dir = dir + "/Desktop/";
		}
		System.out.println(dir);

		String ruta = dir;

		// Definiendo Sistema Operativo
		if(os.indexOf("win")>=0){
			ruta = ruta + archivoEntrada + ".txt";
		} else if(os.indexOf("mac")>=0){
			ruta = ruta + archivoEntrada + ".txt";
		}
		System.out.println(ruta);

		BufferedReader br;
		int llaves = 2; // Numero de llaves a leer
		int arch = 2;

		try{
			FileInputStream archivo = new FileInputStream(ruta);
			br = new BufferedReader(new InputStreamReader(archivo));
			char[] buf = new char[archivo.available()]; // Número de caracteres en la línea del archivo
			br.read(buf,0,archivo.available());

			StringBuilder sb = new StringBuilder(); // Convierte el arreglo de caracteres a un String
			int mod = 2; // Determina el archivo en donde se colocarán las claves
			int act = 0; // Número de clave actual

			// Añadiendo elementos a las listas
			for(int i=0; i<buf.length; i++){
				if(buf[i] != ','){
					sb.append(buf[i]);
				} else {
					// Si encuentra una coma, se genera el elemento a añadir
					if(act >=llaves){
						// Ordenando por bloques
						if(mod % 2 == 0){
							bubbleSort(lista1);
							escribirArchivo(lista1,arch-1);
							lista1.clear();
						} else if (mod % 2 == 1){
							bubbleSort(lista2);
							escribirArchivo(lista2,arch);
							lista2.clear();
						}
						act=0;
						mod=mod+1;
					}

					String elem = sb.toString();
					if(mod % 2 == 0){
						lista1.add(elem);
						tamarch1++;
					} else if(mod % 2 == 1){
						lista2.add(elem);
						tamarch2++;
					}
					act++;
					sb.delete(0,3);
				}
			}

			// Ordenando último bloque generado
			if(mod % 2 == 0){
				bubbleSort(lista1);
				escribirArchivo(lista1,arch-1);
				lista1.clear();
			} else if (mod % 2 == 1){
				bubbleSort(lista2);
				escribirArchivo(lista2,arch);
				lista2.clear();
			}

			// Intercalación de archivos auxiliares
			String ruta1 = null;
			String ruta2 = null;

			tamarch1 = tamarch1/llaves;
			tamarch2 = tamarch2/llaves;

			// Repite el proceso de intercalación
			while(tamarch2 > 0){
				act = 0;
				mod = 0;
				ruta1 = dir;
				ruta2 = dir;

				// Definiendo rutas de archivos auxililares
				if(os.indexOf("win")>=0){
					ruta1 = ruta1 + "Polifase" + (arch-1) + ".txt";
					ruta2 = ruta2 + "Polifase" + arch + ".txt";
				} else if(os.indexOf("mac")>=0){
					ruta1 = ruta1 + "Polifase" + (arch-1) + ".txt";
					ruta2 = ruta2 + "Polifase" + arch + ".txt";
				}

				arch = arch+2;
				intercalarArchivos(ruta1, ruta2, llaves, arch);

				llaves = llaves*2;
				tamarch1 = tamarch1/llaves;
				tamarch2 = tamarch2/llaves;
			}

			// Intercalando los dos últimos bloques restantes
			ruta1 = dir;
			ruta2 = dir;

			// Definiendo rutas de los últimos dos archivos auxililares
			if(os.indexOf("win")>=0){
				ruta1 = ruta1 + "Polifase" + (arch-1) + ".txt";
				ruta2 = ruta2 + "Polifase" + arch + ".txt";
			} else if(os.indexOf("mac")>=0){
				ruta1 = ruta1 + "Polifase" + (arch-1) + ".txt";
				ruta2 = ruta2 + "Polifase" + arch + ".txt";
			}

			FileInputStream archivo1 = new FileInputStream(ruta1);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(archivo1));
			char[] buf1 = new char[archivo1.available()]; // Número de caracteres en la línea del archivo 1
			br1.read(buf1,0,archivo1.available());
			StringBuilder sb1 = new StringBuilder();

			FileInputStream archivo2 = new FileInputStream(ruta2);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(archivo2));
			char[] buf2 = new char[archivo2.available()]; // Número de caracteres en la línea del archivo 2
			br2.read(buf2,0,archivo2.available());
			StringBuilder sb2 = new StringBuilder();

			for (int i=0; i<buf1.length; i++){
				if(buf1[i] != ','){
					sb1.append(buf1[i]);
				} else {
					// Si encuentra una coma, se genera el elemento a añadir
					String elem = sb1.toString();
					lista1.add(elem);
					sb1.delete(0,3);
				}
			}

			for (int i=0; i<buf2.length; i++){
				if(buf2[i] != ','){
					sb2.append(buf2[i]);
				} else {
					// Si encuentra una coma, se genera el elemento a añadir
					String elem = sb2.toString();
					lista1.add(elem);
					sb2.delete(0,3);
				}
			}

			bubbleSort(lista1);
			escribirArchivo(lista1,arch+1);
			lista1.clear();

		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado: " + e);
		} catch (IOException e){
			System.out.println("Error de entrada/salida: " + e);
		}
		System.out.println(" ");
	}

   /**
   * Lee los bloques nones y pares generados de dos archivos y los mezcla en otro archivo, respectivamente.
   * @param ruta1 Ruta en donde se encuentra el primer archivo.
   * @param ruta2 Ruta en donde se encuentra el segundo archivo.
   * @param llaves Define el numero de llaves de cada bloque.
   * @param arch Determina el numero de archivo a generar.
   * @exception FileNotFoundException Archivo no encontrado
   * @exception IOException Error de entrada/salida
   */
	public static void intercalarArchivos(String ruta1, String ruta2, int llaves, int arch){
		try{
			FileInputStream archivo1 = new FileInputStream(ruta1);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(archivo1));
			char[] buf1 = new char[archivo1.available()]; // Número de caracteres en la línea del archivo 1
			br1.read(buf1,0,archivo1.available());
			StringBuilder sb1 = new StringBuilder();

			FileInputStream archivo2 = new FileInputStream(ruta2);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(archivo2));
			char[] buf2 = new char[archivo2.available()]; // Número de caracteres en la línea del archivo 2
			br2.read(buf2,0,archivo2.available());
			StringBuilder sb2 = new StringBuilder();

			int tam=0;
			if(tamarch2<tamarch1){
				tam=tamarch2;
			} else if (tamarch2==tamarch1) {
				tam=tamarch1;
			}

			int mod = 2;
			int act = 0;
			int t1 = 0;
			int t2 = 0;

			// Intercalando bloques
			for (int i=0; i<llaves*tam*4; i++){
				if(buf1[i] != ',' && buf2[i] != ','){
					sb1.append(buf1[i]);
					sb2.append(buf2[i]);
				} else {
					// Si encuentra una coma, se genera el elemento a añadir
					act++;

					String elem1 = sb1.toString();
					String elem2 = sb2.toString();

					if(mod % 2 == 0){
						lista1.add(elem1);
						lista1.add(elem2);
					} else if(mod % 2 == 1){
						lista2.add(elem1);
						lista2.add(elem2);
					}

					if(act >=llaves){
						// Ordenando por bloques
						if(mod % 2 == 0){
							bubbleSort(lista1);
							escribirArchivo(lista1,arch-1);
							t1 = t1 + lista1.size();
							lista1.clear();
						} else if (mod % 2 == 1){
							bubbleSort(lista2);
							escribirArchivo(lista2,arch);
							t2 = t2 + lista2.size();
							lista2.clear();
						}
						act=0;
						mod=mod+1;
					}

					tamarch1 = t1; // Modifica al tamaño del archivo impar
					tamarch2 = t2; // Modifica al tamaño del archivo par

					sb1.delete(0,3);
					sb2.delete(0,3);
				}
			}

			// Intercalando último bloque
			for(int i=llaves*tam*4; i<buf1.length; i++){
				if(buf1[i] != ','){
					sb1.append(buf1[i]);
				} else {
					String elem = sb1.toString();
					lista1.add(elem);
					sb1.delete(0,3);
				}
			}

			for(int i=llaves*tam*4; i<buf2.length; i++){
				if(buf2[i] != ','){
					sb2.append(buf2[i]);
				} else {
					String elem = sb2.toString();
					lista1.add(elem);
					sb2.delete(0,3);
				}
			}

			if (mod % 2 == 0){
				bubbleSort(lista1);
				escribirArchivo(lista1,arch-1);
			} else if (mod % 2 == 1){
				bubbleSort(lista1);
				escribirArchivo(lista1, arch);
			}
			lista1.clear();

		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado: " + e);
		} catch (IOException e){
			System.out.println("Error de entrada/salida: " + e);
		}
	}

	/**
	* Sobreescribe un archivo con contenido previemente guardado. Si no existe el archivo, se crea. 
	* Guarda las llaves generadas para dicho archivo.
	* @param lista Lista auxiliar donde están almacenadas las llaves.
	* @param arch Determina el número de archivo a generar.
	* @exception FileNotFoundException Archivo no encontrado.
	* @exception IOException Error de entrada/salida.
	*/
	public static void escribirArchivo(List<String> lista, int arch){
		try{
			String ruta = dir;

			// Identificando Sistema Operativo
			if(os.indexOf("win")>=0){
				ruta = ruta + "Polifase" + arch + ".txt";
			} else if(os.indexOf("mac")>=0){
				ruta = ruta + "Polifase" + arch + ".txt";
			}
			File f = new File(ruta);
			PrintWriter pw = null;

			// Si el archivo no existe, se crea.
			if (!f.exists()){
				pw = new PrintWriter("Polifase" + arch + ".txt");
				pw.print("");
				String ln = "";

				for(int i=0; i<lista.size(); i++){
					ln = ln + lista.get(i) + ",";
				}
				pw.println(ln);
			} else {
				FileInputStream archivo = new FileInputStream(ruta);
				BufferedReader br = new BufferedReader(new InputStreamReader(archivo));

				String ln = br.readLine();
				pw = new PrintWriter("Polifase" + arch + ".txt");

				for(int i=0; i<lista.size(); i++){
					ln = ln + lista.get(i) + ",";
				}
				pw.println(ln);
			}
			pw.close();
		} catch (FileNotFoundException e){
			System.out.println("Archivo no encontrado: " + e);
		} catch (IOException e){
			System.out.println("Error de entrada/salida: " + e);
		}
	}

	/**
	* Ordena las llaves generadas y guardadas en una lista auxiliar de manera interna.
	* @param A Lista a ordenar.
	*/
	public static void bubbleSort(List<String> A){
		for(int i=A.size()-1; i>=0; i--){
			int k=0;
			for(int j=0; j<=i-1; j++){
				if(A.get(j).compareToIgnoreCase(A.get(j+1))>0){
					String tmp = A.get(j);
					A.set(j, A.get(j+1));
					A.set(j+1, tmp);
				} else {
					k=k+1;
				}
			}
			if(k==i){
				break;
			}
		}
	}

}