package pe.com.imoz.entity;

public class Imagen {
	private int idImagen;
	private String descripcion;
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
	public byte[] getImagen() {
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
}
