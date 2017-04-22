package pe.com.imoz.managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import pe.com.imoz.entity.Imagen;
import pe.com.imoz.service.ImagenService;


@ManagedBean(name = "imagenMB")
@SessionScoped
public class ImagenMB implements Serializable {

	private static final long serialVersionUID = 8263292830109228228L;
	private static final Logger LOG = LoggerFactory.getLogger(ImagenService.class);

	@ManagedProperty("#{imagenService}")
	private ImagenService imagenService;

	private List<Imagen> lstImagen;
	
	private Imagen imagen;
	
	private File fileImagen;

	@PostConstruct
	void init() {
		LOG.info("init de imagen MB");
		lstImagen = this.imagenService.listar();
		imagen = new Imagen();
	}

	public void buscar(ActionEvent actionEvent) {

		lstImagen = this.imagenService.listar();
		agregarMensaje("Se encontraron " + lstImagen.size() + " imagens.");
	}
	
	/*public void buscarPorId(ActionEvent actionEvent) {
		lstImagen = this.imagenService.obtenerPorId(imagen.getIdImagen());
		agregarMensaje("Se encontraron " + lstImagen.size() + " imagens.");
		System.err.println("");
	}*/
	
	public void guardar() {

		try {
			
			LOG.info("registrar");
			LOG.info(imagen.toString());
			if (this.imagenService.guardar(imagen)) {
				LOG.info(imagen.toString());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró exitosamente."));
				imagen = new Imagen();
				lstImagen = imagenService.listar();

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La imagen no fue registrada."));
				

			}
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("../../paginas/imagen/listar.xhtml?faces-redirect=true");
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
	
	public void actualizar(Imagen imagen) throws IOException{
		this.imagen=imagen;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/imagen/actualizar.xhtml?faces-redirect=true");
	}

	/*public void update() throws IOException{
		this.imagenService.modificar(imagen);
		lstImagen = imagenService.listar();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("../../paginas/imagen/listar.xhtml?faces-redirect=true");
	}*/
	
	public void agregarMensaje(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ImagenService getImagenService() {
		return imagenService;
	}

	public void setImagenService(ImagenService imagenService) {
		this.imagenService = imagenService;
	}

	public List<Imagen> getLstImagen() {
		return lstImagen;
	}

	public void setLstImagen(List<Imagen> lstImagen) {
		this.lstImagen = lstImagen;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public File getFileImagen() {
		return fileImagen;
	}

	public void setFileImagen(File fileImagen) {
		this.fileImagen = fileImagen;
	}
}
