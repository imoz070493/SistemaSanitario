package pe.com.imoz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.imoz.entity.Cliente;
import pe.com.imoz.mapper.ClienteMapper;

@ManagedBean(name="clienteService")
@ApplicationScoped
public class ClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	@ManagedProperty("#{clienteMapper}")
	private ClienteMapper clienteMapper;

	
	
	public ClienteMapper getClienteMapper() {
		return clienteMapper;
	}

	public void setClienteMapper(ClienteMapper clienteMapper) {
		this.clienteMapper = clienteMapper;
	}
	
	public List<Cliente> listar(){
		List<Cliente> lstCliente =null;
		
		try{
			lstCliente = this.clienteMapper.listar();
			LOG.info("cantidad de clientes: "+lstCliente.size());
		}catch(Exception e){
			LOG.error(e.getMessage());
			lstCliente = new ArrayList<>();
		}
		return lstCliente;
	}
	
	public List<Cliente> obtenerPorNombre(String nombre){
		List<Cliente> lstCliente =null;
		
		try{
			lstCliente = this.clienteMapper.obtenerPorNombre(nombre);
			LOG.info("Clientes: "+lstCliente.size());
		}catch(Exception e){
			LOG.error(e.getMessage());
			lstCliente = new ArrayList<>();
		}
		return lstCliente;
	}

	public boolean guardar(Cliente cliente) {
		boolean respuesta = false;
		try {
			this.clienteMapper.guardar(cliente);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
	
	public boolean modificar(Cliente cliente) {
		boolean respuesta = false;
		try {
			this.clienteMapper.actualizar(cliente);
			respuesta = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return respuesta;
	}
}
