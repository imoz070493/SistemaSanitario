package pe.com.imoz.mapper;

import java.util.List;

import pe.com.imoz.entity.InformeTicket;

public interface InformeTicketMapper {
	
	public List<InformeTicket> listar();
	
	public InformeTicket obtenerPorId(int idInformeTicket);
	
	public void guardar(InformeTicket ticket);

}
