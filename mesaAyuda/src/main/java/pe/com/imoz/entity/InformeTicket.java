package pe.com.imoz.entity;

public class InformeTicket {

	private int idInformeTicket;
	private String nombre;
	private String descripcion;
	private int idTicket;
	
	public int getIdInformeTicket() {
		return idInformeTicket;
	}
	public void setIdInformeTicket(int idInformeTicket) {
		this.idInformeTicket = idInformeTicket;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
}
