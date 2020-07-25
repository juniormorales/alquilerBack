package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Banco;
import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Pagina;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;


public interface UtilitarioService {
	
	public String insertarDatosPerfilesModulos();
	public String insertarDatosDepartamento();
	public String insertarDatosProvincia();
	public String insertarDatosDistrito();
	public String insertarDatosModulo(List<Modulo> modulos);
	public String insertarDatosPagina(List<Pagina> paginas);
	public String insertarDatosPerfil(List<Perfil> perfiles);
	public String insertarDatosUsuarios(List<Usuario> usuarios);
	public String insertarDatosBancos(List<Banco> bancos);
	
}
