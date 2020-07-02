package com.back.alquiler.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.repo.ArrendatarioRepo;
import com.back.alquiler.repo.ArrenderoRepo;
import com.back.alquiler.repo.UsuarioRepo;
import com.back.alquiler.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	
	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	private ArrenderoRepo repo_arrendero;
	
	@Autowired
	private ArrendatarioRepo repo_arrendatario;
	
	
	public UsuarioRepo getRepo() {
		return repo;
	}

	public void setRepo(UsuarioRepo repo) {
		this.repo = repo;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = repo.findByUsername(username);
	
		if(usuario==null) {
			UsernameNotFoundException ex = new UsernameNotFoundException("¡Error en el login: no existe el usuario '"+username+"' en el sistema!");
			LOG.error("¡Error en el login: no existe el usuario '"+username+"' en el sistema!", new RuntimeException(ex),"Fallo: "+ex.getStackTrace()[0].getClassName());
			throw ex;
		}
		
		GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getPerfil().getNombres());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(rol);
		
		return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEstado(), true, true, true, authorities);
	}

	@Override
	public Usuario findbyUsername(String username) {
		try {
			return repo.findByUsername(username);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" buscarPorUsername. ERROR : "+e.getMessage());
			throw e;
		}	
	}

	@Override
	public Usuario findbyPerfil(Perfil perfil) {
		try {
			return repo.findByPerfil(perfil);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" buscarPorPerfil. ERROR : "+e.getMessage());
			throw e;
		}
		
	}

	@Override
	public Usuario registrar(Usuario obj) {
		try {
			obj.setFechaCreacion(new Date());
			obj.setEstaInhabilitado(false);
			return repo.save(obj);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" registrarUsuario. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Usuario modificar(Usuario obj) {
		try {
			return repo.save(obj);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" actualizarUsuario. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Usuario leer(Integer id) {
		try{
			Optional<Usuario> op = repo.findById(id);
			return op.isPresent() ? op.get() : null;
		}catch(Exception e){
			LOG.error(this.getClass().getSimpleName()+" leerUsuario. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Usuario> listar() {
		try {
			return repo.findAll();
					
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" listarUsuarios. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean eliminar(Integer id) {
		try {
			Boolean resp = repo.existsById(id);
			if(resp) {
				repo.deleteById(id);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" eliminarUsuario. ERROR : "+e.getMessage());
			throw e;
		}
		
	}

	@Override
	public Boolean buscarSiExisteEmail(String email) {
		return repo.existsByEmail(email);
	}


	@Override
	public Boolean buscarSiExisteUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public Boolean buscarSiExisteDNI(String dni) {
		return repo.existsByDni(dni);
	}

	@Override
	public Usuario inhabilitarCuenta(Usuario usuario) {
		usuario.setEstado(true);
		usuario.setEstaInhabilitado(true);
		return repo.save(usuario);
	}

	@Override
	public List<Arrendero> listarCuentasNoActivadas() {
		List<Usuario> lsUsu = repo.findByTipoUsuarioAndEstaInhabilitadoAndEstado("ARRENDERO", false, false);
		List<Arrendero> lsArrendero = new ArrayList<>();
		lsUsu.forEach( usuario -> {
			lsArrendero.add(repo_arrendero.findByUsuario(usuario));
		});
		return lsArrendero;
	}

	@Override
	public Usuario activarCuenta(Usuario usuario) {
		usuario.setEstado(true);
		return repo.save(usuario);
	}

	@Override
	public Boolean verificarInhabilitado(Usuario usuario) {
		Usuario user = findbyUsername(usuario.getUsername());
		if(user.getEstaInhabilitado()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Arrendero retornarArrendero(Usuario usuario) {
		try {
			return repo_arrendero.findByUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Arrendatario retornarArrendatario(Usuario usuario) {
		try {
			return repo_arrendatario.findByUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
}
