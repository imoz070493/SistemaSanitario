package pe.com.imoz.managedBean;

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

import pe.com.imoz.entity.Categoria;
import pe.com.imoz.entity.Producto;
import pe.com.imoz.service.CategoriaService;
import pe.com.imoz.service.ProductoService;


@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoMB implements Serializable {

	private static final long serialVersionUID = 8263292830109228228L;
	private static final Logger LOG = LoggerFactory.getLogger(ProductoService.class);

	@ManagedProperty("#{productoService}")
	private ProductoService productoService;

	private List<Producto> lstProducto;
	
	@ManagedProperty("#{categoriaService}")
	private CategoriaService categoriaService;
	private List<Categoria> lstCategoria;
	private List<Integer> lstCategoriaNombre; 
	private Producto producto;

	@PostConstruct
	void init() {
		LOG.info("init de producto MB");
		lstProducto = this.productoService.listar();
		producto = new Producto();
	}
	
	public void iniciarId(){
		System.out.println("Probando..");
		lstCategoria = this.categoriaService.listar();
		
	}

	public void buscar(ActionEvent actionEvent) {

		lstProducto = this.productoService.listar();
		agregarMensaje("Se encontraron " + lstProducto.size() + " productos.");
	}
	
	public void registrar() {

		try {
			LOG.info("registrar");
			LOG.info(producto.toString());
			if (this.productoService.registrar(producto)) {
				LOG.info(producto.toString());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró exitosamente."));
				producto = new Producto();
				lstProducto = productoService.listar();
				iniciarId();

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La producto no fue registrada."));

			}
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("../../paginas/producto/listar.xhtml?faces-redirect=true");
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	public void agregarMensaje(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public List<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Categoria> getLstCategoria() {
		iniciarId();
		return lstCategoria;
	}

	public void setLstCategoria(List<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}

	public CategoriaService getCategoriaService() {
		
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<Integer> getLstCategoriaNombre() {
		iniciarId();
		return lstCategoriaNombre;
	}

	public void setLstCategoriaNombre(List<Integer> lstCategoriaNombre) {
		this.lstCategoriaNombre = lstCategoriaNombre;
	}
	
	

}
