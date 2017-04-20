package pe.com.imoz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.imoz.entity.InformeTicket;
import pe.com.imoz.mapper.InformeTicketMapper;

@ManagedBean(name="informeTicketService")
@ApplicationScoped
public class InformeTicketService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(InformeTicket.class);

	@ManagedProperty("#{informeTicketMapper}")
	private InformeTicketMapper informeTicketMapper;

	public InformeTicketMapper getInformeTicketMapper() {
		return informeTicketMapper;
	}

	public void setInformeTicketMapper(InformeTicketMapper informeTicketMapper) {
		this.informeTicketMapper = informeTicketMapper;
	}
	
	public List<InformeTicket> listar(){
		List<InformeTicket> lstInformeTicket =null;
		
		try{
			lstInformeTicket = this.informeTicketMapper.listar();
			LOG.info("cantidad de informeTickets: "+lstInformeTicket.size());
		}catch(Exception e){
			LOG.error(e.getMessage());
			lstInformeTicket = new ArrayList<>();
		}
		return lstInformeTicket;
	}
	
	public InformeTicket obtenerPorId(int id){
		InformeTicket informeTicket =null;
		
		try{
			informeTicket = this.informeTicketMapper.obtenerPorId(id);
			LOG.info("InformeTickets: "+informeTicket);
		}catch(Exception e){
			LOG.error(e.getMessage());
			informeTicket = new InformeTicket();
		}
		return informeTicket;
	}

	public boolean guardar(InformeTicket informeTicket) {
		boolean respuesta = false;
		try {
			this.informeTicketMapper.guardar(informeTicket);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
 
}
