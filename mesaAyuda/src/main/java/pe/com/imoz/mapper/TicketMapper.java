package pe.com.imoz.mapper;

import java.util.List;

import pe.com.imoz.entity.Ticket;

public interface TicketMapper {
	
	public List<Ticket> listar();
	
	public Ticket obtenerPorId(int idTicket);
	
	public void guardar(Ticket ticket);
	
	public void actualizarFechaProgramada(Ticket ticket);
	
	public void actualizarEstado(Ticket ticket);	
	

}
