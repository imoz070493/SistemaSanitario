package pe.com.imoz.managedBean;

import java.io.File;

public class Prueba {

	public static void main(String[] args) {
		File archivo = new File("destino.jpg");
		System.out.println(archivo.exists());

	}

}
