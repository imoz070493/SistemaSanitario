package pe.com.imoz.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Imagen {
	private int idImagen;
	private String descripcion;
	private File fileImagen;
	private byte[] imagen;
	private int idTicket;
	private int idInformeTicket;
	
	public int getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public byte[] getImagen(){
		try{
			 
			File archivo = new File("destino.jpg");
			System.out.println("No se encuentra el archivo"+archivo.exists());
			byte[] imgFoto = new byte[(int) archivo.length()];
			InputStream input = new FileInputStream(archivo);
			input.read(imgFoto);
			imagen = imgFoto;
		}catch (IOException e) {
			
			System.err.println(e.getMessage());
		}
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	public int getIdInformeTicket() {
		return idInformeTicket;
	}
	public void setIdInformeTicket(int idInformeTicket) {
		this.idInformeTicket = idInformeTicket;
	}
	public File getFileImagen() {
		return fileImagen;
	}
	public void setFileImagen(File fileImagen) {
		this.fileImagen = fileImagen;
	}
	
	
}
