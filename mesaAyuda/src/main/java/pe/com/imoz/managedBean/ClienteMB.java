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

import pe.com.imoz.entity.Cliente;
import pe.com.imoz.service.ClienteService;


@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 8263292830109228228L;
	private static final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	@ManagedProperty("#{clienteService}")
	private ClienteService clienteService;

	private List<Cliente> lstCliente;
	
	private Cliente cliente;

	@PostConstruct
	void init() {
		LOG.info("init de cliente MB");
		lstCliente = this.clienteService.listar();
		cliente = new Cliente();
	}

	public void buscar(ActionEvent actionEvent) {

		lstCliente = this.clienteService.listar();
		agregarMensaje("Se encontraron " + lstCliente.size() + " clientes.");
	}
	
	public void buscarPorNombre(ActionEvent actionEvent) {

		lstCliente = this.clienteService.obtenerPorNombre(cliente.getNombre());
		agregarMensaje("Se encontraron " + lstCliente.size() + " clientes.");
		System.err.println("");
	}
	
	public void guardar() {

		try {
			LOG.info("registrar");
			LOG.info(cliente.toString());
			if (this.clienteService.guardar(cliente)) {
				LOG.info(cliente.toString());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró exitosamente."));
				cliente = new Cliente();
				lstCliente = clienteService.listar();

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La cliente no fue registrada."));

			}
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("../../paginas/cliente/listar.xhtml?faces-redirect=true");
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
	
	public void actualizar(Cliente cliente) throws IOException{
		this.cliente=cliente;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/cliente/actualizar.xhtml?faces-redirect=true");
	}

	public void update() throws IOException{
		this.clienteService.modificar(cliente);
		lstCliente = clienteService.listar();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/cliente/listar.xhtml?faces-redirect=true");
	}
	
	public void agregarMensaje(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
