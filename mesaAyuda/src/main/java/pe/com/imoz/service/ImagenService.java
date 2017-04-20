package pe.com.imoz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.imoz.entity.Imagen;
import pe.com.imoz.mapper.ImagenMapper;

@ManagedBean(name="imagenService")
@ApplicationScoped
public class ImagenService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ImagenService.class);

	@ManagedProperty("#{imagenMapper}")
	private ImagenMapper imagenMapper;

	public ImagenMapper getImagenMapper() {
		return imagenMapper;
	}

	public void setImagenMapper(ImagenMapper imagenMapper) {
		this.imagenMapper = imagenMapper;
	}
	
	public List<Imagen> listar(){
		List<Imagen> lstImagen =null;
		
		try{
			lstImagen = this.imagenMapper.listar();
			LOG.info("cantidad de imagens: "+lstImagen.size());
		}catch(Exception e){
			LOG.error(e.getMessage());
			lstImagen = new ArrayList<>();
		}
		return lstImagen;
	}
	
	public Imagen obtenerPorIdTicket(int id){
		Imagen imagen =null;
		
		try{
			imagen = this.imagenMapper.obtenerPorIdTicket(id);
			LOG.info("Imagens: "+imagen);
		}catch(Exception e){
			LOG.error(e.getMessage());
			imagen = new Imagen();
		}
		return imagen;
	}
	
	public Imagen obtenerPorIdInforme(int id){
		Imagen imagen =null;
		
		try{
			imagen = this.imagenMapper.obtenerPorIdInforme(id);
			LOG.info("Imagens: "+imagen);
		}catch(Exception e){
			LOG.error(e.getMessage());
			imagen = new Imagen();
		}
		return imagen;
	}

	public boolean guardar(Imagen imagen) {
		boolean respuesta = false;
		try {
			this.imagenMapper.guardar(imagen);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
 
}
