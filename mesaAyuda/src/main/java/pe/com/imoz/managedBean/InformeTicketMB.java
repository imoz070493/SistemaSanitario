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

import pe.com.imoz.entity.InformeTicket;
import pe.com.imoz.service.InformeTicketService;


@ManagedBean(name = "informeTicketMB")
@SessionScoped
public class InformeTicketMB implements Serializable {

	private static final long serialVersionUID = 8263292830109228228L;
	private static final Logger LOG = LoggerFactory.getLogger(InformeTicketService.class);

	@ManagedProperty("#{informeTicketService}")
	private InformeTicketService informeTicketService;

	private List<InformeTicket> lstInformeTicket;
	
	private InformeTicket informeTicket;

	@PostConstruct
	void init() {
		LOG.info("init de informeTicket MB");
		lstInformeTicket = this.informeTicketService.listar();
		informeTicket = new InformeTicket();
	}

	public void buscar(ActionEvent actionEvent) {

		lstInformeTicket = this.informeTicketService.listar();
		agregarMensaje("Se encontraron " + lstInformeTicket.size() + " informeTickets.");
	}
	
	public void buscarPorId(ActionEvent actionEvent) {

		lstInformeTicket = this.informeTicketService.obtenerPorId(informeTicket.getIdInformeTicket());
		agregarMensaje("Se encontraron " + lstInformeTicket.size() + " informeTickets.");
		System.err.println("");
	}
	
	public void guardar() {

		try {
			LOG.info("registrar");
			LOG.info(informeTicket.toString());
			if (this.informeTicketService.guardar(informeTicket)) {
				LOG.info(informeTicket.toString());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró exitosamente."));
				informeTicket = new InformeTicket();
				lstInformeTicket = informeTicketService.listar();

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La informeTicket no fue registrada."));

			}
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("../../paginas/informeTicket/listar.xhtml?faces-redirect=true");
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
	
	public void actualizar(InformeTicket informeTicket) throws IOException{
		this.informeTicket=informeTicket;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/informeTicket/actualizar.xhtml?faces-redirect=true");
	}

	/*public void update() throws IOException{
		this.informeTicketService.modificar(informeTicket);
		lstInformeTicket = informeTicketService.listar();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/informeTicket/listar.xhtml?faces-redirect=true");
	}*/
	
	public void agregarMensaje(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public InformeTicketService getInformeTicketService() {
		return informeTicketService;
	}

	public void setInformeTicketService(InformeTicketService informeTicketService) {
		this.informeTicketService = informeTicketService;
	}

	public List<InformeTicket> getLstInformeTicket() {
		return lstInformeTicket;
	}

	public void setLstInformeTicket(List<InformeTicket> lstInformeTicket) {
		this.lstInformeTicket = lstInformeTicket;
	}

	public InformeTicket getInformeTicket() {
		return informeTicket;
	}

	public void setInformeTicket(InformeTicket informeTicket) {
		this.informeTicket = informeTicket;
	}
	
	

}
