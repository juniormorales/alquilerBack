package com.back.alquiler.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
	private UsuarioRepo repoUsuario;

	@Autowired
	private ArrenderoRepo repoArrendero;

	@Autowired
	private ArrendatarioRepo repoArrendatario;

	public UsuarioRepo getRepoUsuario() {
		return repoUsuario;
	}

	public void setRepoUsuario(UsuarioRepo repo) {
		this.repoUsuario = repo;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username){

		Usuario usuario = repoUsuario.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(
					"¡Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}

		GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getPerfil().getNombres());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(rol);

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEstado(), true, true, true,
				authorities);
	}

	@Override
	public Usuario findbyUsername(String username) {
		return repoUsuario.findByUsername(username);

	}

	@Override
	public Usuario findbyPerfil(Perfil perfil) {
		return repoUsuario.findByPerfil(perfil);
	}

	@Override
	public Usuario registrar(Usuario obj) {
		obj.setFechaCreacion(new Date());
		obj.setEstaInhabilitado(false);
		return repoUsuario.save(obj);

	}

	@Override
	public Usuario modificar(Usuario obj) {
		return repoUsuario.save(obj);

	}

	@Override
	public List<Usuario> listar() {
		return repoUsuario.findAll().stream()
				.filter(usuario -> !usuario.getTipoUsuario().equalsIgnoreCase("ADMINISTRADOR")).map(usuario -> {
					usuario.setPassword(null);
					return usuario;
				}).collect(Collectors.toList());
	}

	@Override
	public Boolean eliminar(Integer id) {
		Boolean resp = repoUsuario.existsById(id);
		if (Boolean.TRUE.equals(resp)) {
			repoUsuario.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Boolean buscarSiExisteEmail(String email) {
		return repoUsuario.existsByEmail(email);
	}

	@Override
	public Boolean buscarSiExisteUsername(String username) {
		return repoUsuario.existsByUsername(username);
	}

	@Override
	public Boolean buscarSiExisteDNI(String dni) {
		return repoUsuario.existsByDni(dni);
	}

	@Override
	public Usuario inhabilitarCuenta(Usuario usuario) {
		usuario.setEstado(true);
		usuario.setEstaInhabilitado(true);
		return repoUsuario.save(usuario);
	}

	@Override
	public List<Arrendero> listarCuentasNoActivadas() {
		List<Usuario> lsUsu = repoUsuario.findByTipoUsuarioAndEstaInhabilitadoAndEstado("ARRENDERO", false, false);
		List<Arrendero> lsArrendero = new ArrayList<>();
		lsUsu.forEach(usuario -> lsArrendero.add(repoArrendero.findByUsuario(usuario)));
		return lsArrendero;
	}

	@Override
	public Usuario activarCuenta(Usuario usuario) {
		usuario.setEstado(true);
		return repoUsuario.save(usuario);
	}

	@Override
	public Boolean verificarInhabilitado(Usuario usuario) {
		Usuario user = findbyUsername(usuario.getUsername());
		return user.getEstaInhabilitado();
	}

	@Override
	public Arrendero retornarArrendero(Usuario usuario) {
		return repoArrendero.findByUsuario(usuario);

	}

	@Override
	public Arrendatario retornarArrendatario(Usuario usuario) {
		return repoArrendatario.findByUsuario(usuario);

	}
}
