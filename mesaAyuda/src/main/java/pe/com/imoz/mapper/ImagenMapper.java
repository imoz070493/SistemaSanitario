package pe.com.imoz.mapper;

import java.util.List;

import pe.com.imoz.entity.Imagen;

public interface ImagenMapper {
	
	public List<Imagen> listar();
	
	public Imagen obtenerPorIdTicket(int idImagen);
	
	public Imagen obtenerPorIdInforme(int idImagen);
	
	public void guardar(Imagen imagen);
	
}
