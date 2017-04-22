package pe.com.imoz.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.imoz.entity.Ticket;
import pe.com.imoz.service.TicketService;


@ManagedBean(name = "ticketMB")
@SessionScoped
public class TicketMB implements Serializable {

	private static final long serialVersionUID = 8263292830109228228L;
	private static final Logger LOG = LoggerFactory.getLogger(TicketService.class);

	@ManagedProperty("#{ticketService}")
	private TicketService ticketService;

	private List<Ticket> lstTicket;
	
	private Ticket ticket;

	@PostConstruct
	void init() {
		LOG.info("init de ticket MB");
		lstTicket = this.ticketService.listar();
		ticket = new Ticket();
	}

	public void buscar(ActionEvent actionEvent) {

		lstTicket = this.ticketService.listar();
		agregarMensaje("Se encontraron " + lstTicket.size() + " tickets.");
	}
	
	public void guardar() {

		try {
			LOG.info("registrar");
			LOG.info(ticket.toString());
			if (this.ticketService.guardar(ticket)) {
				LOG.info(ticket.toString());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró exitosamente."));
				ticket = new Ticket();
				lstTicket = ticketService.listar();

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La ticket no fue registrada."));

			}
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("../../paginas/ticket/listar.xhtml?faces-redirect=true");
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
	
	public void verDetalle(Ticket ticket) throws IOException{
		this.ticket=ticket;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/ticket/verDetalle.xhtml?faces-redirect=true");
	}
	
	public void actualizarFechaP(Ticket ticket) throws IOException{
		this.ticket=ticket;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/ticket/actualizarFecha.xhtml?faces-redirect=true");
	}
	
	public void cerrarTicket(Ticket ticket) throws IOException{
		this.ticket=ticket;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/ticket/cerrarTicket.xhtml?faces-redirect=true");
	}

	public void updateFecha() throws IOException{
		this.ticketService.actualizarFechaProgramada(ticket);
		lstTicket = ticketService.listar();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/ticket/listar.xhtml?faces-redirect=true");
	}
	
	public void updateEstado() throws IOException{
		this.ticketService.actualizarEstado(ticket);
		lstTicket = ticketService.listar();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/ticket/listar.xhtml?faces-redirect=true");
	}
	
	public void agregarMensaje(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public List<Ticket> getLstTicket() {
		return lstTicket;
	}

	public void setLstTicket(List<Ticket> lstTicket) {
		this.lstTicket = lstTicket;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	

}
