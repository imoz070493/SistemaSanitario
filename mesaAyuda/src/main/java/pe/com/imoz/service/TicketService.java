package pe.com.imoz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.imoz.entity.Ticket;
import pe.com.imoz.mapper.TicketMapper;

@ManagedBean(name="ticketService")
@ApplicationScoped
public class TicketService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(TicketService.class);

	@ManagedProperty("#{ticketMapper}")
	private TicketMapper ticketMapper;

	public TicketMapper getTicketMapper() {
		return ticketMapper;
	}

	public void setTicketMapper(TicketMapper ticketMapper) {
		this.ticketMapper = ticketMapper;
	}
	
	public List<Ticket> listar(){
		List<Ticket> lstTicket =null;
		
		try{
			lstTicket = this.ticketMapper.listar();
			LOG.info("cantidad de tickets: "+lstTicket.size());
		}catch(Exception e){
			LOG.error(e.getMessage());
			lstTicket = new ArrayList<>();
		}
		return lstTicket;
	}
	
	public Ticket obtenerPorId(int id){
		Ticket ticket =null;
		
		try{
			ticket = this.ticketMapper.obtenerPorId(id);
			LOG.info("Ticket: "+ticket);
		}catch(Exception e){
			LOG.error(e.getMessage());
			ticket = new Ticket();
		}
		return ticket;
	}

	public boolean guardar(Ticket ticket) {
		boolean respuesta = false;
		try {
			this.ticketMapper.guardar(ticket);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
	
	public boolean actualizarFecha(Ticket ticket) {
		boolean respuesta = false;
		try {
			this.ticketMapper.actualizarFecha(ticket);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
	
	public boolean actualizarEstado(Ticket ticket) {
		boolean respuesta = false;
		try {
			this.ticketMapper.actualizarEstado(ticket);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
 
}
