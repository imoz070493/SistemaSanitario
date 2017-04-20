package pe.com.imoz.mapper;

import java.util.List;

import pe.com.imoz.entity.Cliente;

public interface ClienteMapper {
	
	public List<Cliente> listar();
	
	public Cliente obtenerPorNombre(String nombre);
	
	public void guardar(Cliente cliente);
	
	public void actualizar(Cliente cliente);

}
