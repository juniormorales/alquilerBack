package com.back.alquiler.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Pagina;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.repo.ModuloRepo;
import com.back.alquiler.repo.PaginaRepo;
import com.back.alquiler.repo.PerfilRepo;
import com.back.alquiler.repo.UsuarioRepo;
import com.back.alquiler.repo.UtilitarioRepo;
import com.back.alquiler.service.UtilitarioService;

@Service
public class UtilitarioServiceImpl implements UtilitarioService {

	@Autowired
	UtilitarioRepo repoUtilitario;

	@Autowired
	ModuloRepo repoModulo;

	@Autowired
	PaginaRepo repoPagina;

	@Autowired
	PerfilRepo repoPerfil;

	@Autowired
	UsuarioRepo repoUsuario;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	public String insertarDatosModulo(List<Modulo> obj) {
		try {
			repoModulo.saveAll(obj);
			return "Se insertó correctamente los módulos";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarListaModulos. ERROR : " + e.getMessage());
			throw e;
		}
	}

	public String insertarDatosPagina(List<Pagina> obj) {
		try {
			repoPagina.saveAll(obj);
			return "Se insertó correctamente las Paginas";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarPaginas. ERROR : " + e.getMessage());
			throw e;
		}
	}

	public String insertarDatosPerfil(List<Perfil> obj) {
		try {
			repoPerfil.saveAll(obj);
			return "Se insertó correctamente los perfiles";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarPerfiles. ERROR : " + e.getMessage());
			throw e;
		}
	}

	public String insertarDatosPerfilesModulos() {

		try {
			repoUtilitario.insertarPerfilesModulo();
			return "Se insertó correctamente los Perfiles-Modulos";

		} catch (Exception e) {
			return "Se insertó correctamente los Perfiles-Modulos";
		}

	}

	public String insertarDatosDepartamento() {
		try {
			repoUtilitario.insertarDepartamento();
			return "Se insertó correctamente los Departamentos";
		} catch (Exception e) {
			return "Se insertó correctamente los Departamentos";

		}
	}

	public String insertarDatosProvincia() {
		try {
			repoUtilitario.insertarProvincia();
			return "Se insertó correctamente las Provincias";
		} catch (Exception e) {
			return "Se insertó correctamente las Provincias";

		}
	}

	public String insertarDatosDistrito() {
		try {
			repoUtilitario.insertarDistrito();
			return "Se insertó correctamente los Distritos";
		} catch (Exception e) {
			return "Se insertó correctamente los Distritos";
		}
	}

	@Override
	public String insertarDatosUsuarios(List<Usuario> usuarios) {
		try {
			repoUsuario.saveAll(usuarios);
			return "Se insertó correctamente los usuarios";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarDatosUsuarios. ERROR : " + e.getMessage());
			throw e;
		}

	}

}
