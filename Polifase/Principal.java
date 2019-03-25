import java.util.Scanner;

public class Principal{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el nombre el archivo: ");
		String archivo = sc.next();

		Polifase polifase = new Polifase(archivo);
		polifase.polifase();
	}

}