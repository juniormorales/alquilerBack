package com.back.alquiler.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Banco;
import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Pagina;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.UtilitarioService;

@RestController
@RequestMapping("/api/utilitario")
public class UtilitarioController {

	@Autowired
	private UtilitarioService service;

	Map<String, List<String>> resp_BD1 = new HashMap<>();

	Map<String, List<String>> resp_BD2 = new HashMap<>();

	@PostMapping("/llenarBD")
	public ResponseEntity<?> insertarDatos1() throws Exception {
		try {
			List<String> messages = new ArrayList<>();
			List<Modulo> tmp_modulo = new ArrayList<>();
			Modulo modGestionCuentas = this.CrearModulo(null, "tim-icons icon-key-25", false, 1,
					"/administracion/cuentas", "Gestion de cuentas", "link");
			Modulo modVerSoliAcep = this.CrearModulo(null, "tim-icons icon-email-85", false, 2,
					"/administracion/ver-sol", "Solicitudes Pendientes", "link");
			Modulo modDash = this.CrearModulo(null, "tim-icons icon-chart-pie-36", false, 1, "/arrendero/dashboard",
					"Dashboard", "link");
			Modulo modGestionProp = this.CrearModulo("prop", "mdi mdi-home-modern", true, 2, "/arrendero/",
					"Propiedades", "sub");
			Modulo modGestInqui = this.CrearModulo("inquilinos", "mdi mdi-emoticon-cool", true, 3, "/arrendero/",
					"Inquilinos", "sub");
			Modulo modCondicionPago = this.CrearModulo(null, "tim-icons icon-coins", false, 5,
					"/arrendero/condicion-pago", "Condicion Pago", "link");
			Modulo modGestSolici = this.CrearModulo(null, "mdi mdi-eye", false, 6, "/arrendero/solicitudes",
					"Solicitudes", "link");
			Modulo modGestDoc = this.CrearModulo("doc", "tim-icons icon-single-copy-04", true, 4, "/arrendero/",
					"Documentos", "sub");
			Modulo modPresentacion = this.CrearModulo(null, "mdi mdi-map-marker-radius", false, 1,
					"/arrendatario/buscar", "Buscar Propiedades", "link");
			Modulo modHistorialUsu = this.CrearModulo(null, "mdi mdi-library", true, 2, "/arrendatario/", "Historiales",
					"sub");
			Modulo modDeudas = this.CrearModulo("deudas", "tim-icons icon-paper", true, 3, "/arrendatario/",
					"Pagos / Deudas", "sub");
			
			Modulo modPropConfirmar = this.CrearModulo("propconfir", "mdi mdi-timetable", false, 3, "/administracion/propiedad-confirmar", "Confirmar Propiedades", "link");
			tmp_modulo.add(modGestionCuentas);
			tmp_modulo.add(modVerSoliAcep);
			tmp_modulo.add(modDash);
			tmp_modulo.add(modGestionProp);
			tmp_modulo.add(modCondicionPago);
			tmp_modulo.add(modGestInqui);
			tmp_modulo.add(modGestSolici);
			tmp_modulo.add(modGestDoc);
			tmp_modulo.add(modPresentacion);
			tmp_modulo.add(modHistorialUsu);
			tmp_modulo.add(modDeudas);
			tmp_modulo.add(modPropConfirmar);
			messages.add(service.insertarDatosModulo(tmp_modulo));

			List<Pagina> tmp_pagina = new ArrayList<>();
			Pagina pgprop = this.CrearPagina("misprop", "mis-propiedades", "MPROP", "Mis Propiedades", modGestionProp);
			Pagina pggesinq = this.CrearPagina("gestinq", "gestion-inquilino", "GINQ", "Gestion Inquilino",
					modGestInqui);
			Pagina pgpago = this.CrearPagina("verpago", "pagos", "PAG", "Pagos", modGestInqui);
			Pagina pgpagoporacept = this.CrearPagina("pagoporacept", "por-aceptar", "PENDI", "Pagos por Confirmar",
					modGestInqui);
			Pagina pgcontratopend = this.CrearPagina("contrpend", "contrat-pend", "CNPND", "Contratos pendientes",
					modGestInqui);
			Pagina pgrecib = this.CrearPagina("recibo", "recibos", "REC", "Recibos", modGestDoc);
			Pagina pgcontr = this.CrearPagina("contr", "contratos", "CONT", "Contratos", modGestDoc);
			Pagina pgsol = this.CrearPagina("solicitud", "sol-hechas", "SOL", "Solicitudes Hechas", modHistorialUsu);
			Pagina pgbol = this.CrearPagina("hboletas", "boletas", "BOL", "Mis Boletas", modHistorialUsu);
			Pagina pgveracep = this.CrearPagina("veracep", "sol-aceptadas", "ACEPT", "Sol. Aceptadas", modHistorialUsu);
			Pagina pgrecordpagos = this.CrearPagina("pagos", "pagos", "PAGO", "Record de Pagos", modDeudas);
			Pagina pgpagosvencer = this.CrearPagina("pagosven", "por-vencer", "PPV", "Pagos por Vencer", modDeudas);
			Pagina pgpagospend = this.CrearPagina("deudas", "deudas", "DEUDA", "Deudas pendientes", modDeudas);
			tmp_pagina.add(pgprop);
			tmp_pagina.add(pggesinq);
			tmp_pagina.add(pgpago);
			tmp_pagina.add(pgpagoporacept);
			tmp_pagina.add(pgcontratopend);
			tmp_pagina.add(pgrecib);
			tmp_pagina.add(pgcontr);
			tmp_pagina.add(pgsol);
			tmp_pagina.add(pgbol);
			tmp_pagina.add(pgveracep);
			tmp_pagina.add(pgrecordpagos);
			tmp_pagina.add(pgpagosvencer);
			tmp_pagina.add(pgpagospend);

			messages.add(service.insertarDatosPagina(tmp_pagina));

			List<Perfil> tmp_perfil = new ArrayList<>();
			Perfil admi = this.CrearPerfil(1, true, "ROLE_ADMINISTRADOR");
			Perfil arrendero = this.CrearPerfil(1, true, "ROLE_ARRENDERO");
			Perfil arrendetario = this.CrearPerfil(1, true, "ROLE_ARRENDATARIO");
			tmp_perfil.add(admi);
			tmp_perfil.add(arrendero);
			tmp_perfil.add(arrendetario);
			messages.add(service.insertarDatosPerfil(tmp_perfil));

			List<Usuario> tmp_usuarios = new ArrayList<>();
			Usuario user1 = this.crearUsuario("correo1@hotmail.com", true,
					"$2a$10$5lQfLdWrdOiZudh3cCNmbOz2TcU3DtgfjqCeFHvGS1HDBHSvlNdu6", "user1", admi, "ADMINISTRADOR",false);
			tmp_usuarios.add(user1);
			messages.add(service.insertarDatosUsuarios(tmp_usuarios));
			
			List<Banco> tmp_bancos = new ArrayList<>();
			tmp_bancos.add(new Banco("Banco de Comercio"));
			tmp_bancos.add(new Banco("Banco de Crédito del Perú"));
			tmp_bancos.add(new Banco("Banco Interamericano de Finanzas (BanBif)"));
			tmp_bancos.add(new Banco("Banco Pichincha"));
			tmp_bancos.add(new Banco("BBVA"));
			tmp_bancos.add(new Banco("Citibank Perú"));
			tmp_bancos.add(new Banco("Interbank"));
			tmp_bancos.add(new Banco("MiBanco"));
			tmp_bancos.add(new Banco("Scotiabank Perú"));
			tmp_bancos.add(new Banco("Banco GNB Perú"));
			tmp_bancos.add(new Banco("Banco Falabella"));
			tmp_bancos.add(new Banco("Banco Ripley"));
			tmp_bancos.add(new Banco("Banco Santander Perú"));
			tmp_bancos.add(new Banco("Banco Azteca"));
			tmp_bancos.add(new Banco("CRAC CAT Perú"));
			tmp_bancos.add(new Banco("ICBC PERU BANK"));
			tmp_bancos.add(new Banco("Agrobanco"));
			tmp_bancos.add(new Banco("Banco de la Nación"));
			tmp_bancos.add(new Banco("COFIDE"));
			tmp_bancos.add(new Banco("Fondo MiVivienda"));
			tmp_bancos.add(new Banco("Caja Arequipa"));
			tmp_bancos.add(new Banco("Caja Cusco"));
			tmp_bancos.add(new Banco("Caja Del Santa"));
			tmp_bancos.add(new Banco("Caja Trujillo"));
			tmp_bancos.add(new Banco("Caja Huancayo"));
			tmp_bancos.add(new Banco("Caja Ica"));
			tmp_bancos.add(new Banco("Caja Maynas"));
			tmp_bancos.add(new Banco("Caja Paita"));
			tmp_bancos.add(new Banco("Caja Piura"));
			tmp_bancos.add(new Banco("Caja Sullana"));
			tmp_bancos.add(new Banco("Caja Incasur"));
			tmp_bancos.add(new Banco("Caja Los Andes"));
			tmp_bancos.add(new Banco("Caja Prymera"));
			tmp_bancos.add(new Banco("Caja Sipán"));
			tmp_bancos.add(new Banco("Caja Del Centro"));
			tmp_bancos.add(new Banco("Caja Raíz"));
			tmp_bancos.add(new Banco("Caja Metropolitana de Lima"));
			tmp_bancos.add(new Banco("Financiera Amérika"));
			tmp_bancos.add(new Banco("Financiera Crediscotia"));
			tmp_bancos.add(new Banco("Financiera Confianza"));
			tmp_bancos.add(new Banco("Financiera Compartamos"));
			tmp_bancos.add(new Banco("Financiera Credinka"));
			tmp_bancos.add(new Banco("Financiera Efectiva"));
			tmp_bancos.add(new Banco("Financiera Proempresa"));
			tmp_bancos.add(new Banco("Financiera Mitsui Auto Finance"));
			tmp_bancos.add(new Banco("Financiera Oh!"));
			tmp_bancos.add(new Banco("Financiera Qapaq"));
			tmp_bancos.add(new Banco("Financiera TFC"));
			tmp_bancos.add(new Banco("EDPYME Acceso Crediticio"));
			tmp_bancos.add(new Banco("EDPYME Alternativa"));
			tmp_bancos.add(new Banco("EDPYME BBVA Consumer Finance"));
			tmp_bancos.add(new Banco("EDPYME Credivisión"));
			tmp_bancos.add(new Banco("EDPYME Inversiones La Cruz"));
			tmp_bancos.add(new Banco("EDPYME Mi Casita"));
			tmp_bancos.add(new Banco("EDPYME Progreso"));
			tmp_bancos.add(new Banco("EDPYME GMG Servicios Perú"));
			tmp_bancos.add(new Banco("EDPYME Santander Consumer Perú"));
			messages.add(service.insertarDatosBancos(tmp_bancos));
			
			messages.add(service.insertarDatosPerfilesModulos());
			messages.add(service.insertarDatosDepartamento());
			messages.add(service.insertarDatosProvincia());
			messages.add(service.insertarDatosDistrito());
			resp_BD1.put("mensaje", messages);

		} catch (Exception e) {

		}
		return new ResponseEntity<Map<String, List<String>>>(resp_BD1, HttpStatus.OK);

	}

	private Modulo CrearModulo(String collapse, String icono, Boolean isCollapsed, int orden, String path, String title,
			String type) {
		Modulo modulo = new Modulo();
		modulo.setCollapse(collapse);
		modulo.setIcontype(icono);
		modulo.setIsCollapsed(isCollapsed);
		modulo.setOrden(orden);
		modulo.setPath(path);
		modulo.setTitle(title);
		modulo.setType(type);
		return modulo;
	}

	private Pagina CrearPagina(String collapse, String path, String smallTitle, String title, Modulo modulo) {
		Pagina pagina = new Pagina();
		pagina.setCollapse(collapse);
		pagina.setIsCollapsed(false);
		pagina.setPath(path);
		pagina.setSmallTitle(smallTitle);
		pagina.setTitle(title);
		pagina.setType("link");
		pagina.setModulo(modulo);
		return pagina;
	}

	private Perfil CrearPerfil(int ambito, boolean estado, String nombres) {
		Perfil p = new Perfil();
		p.setAmbito(ambito);
		p.setEstado(estado);
		p.setNombres(nombres);
		return p;
	}

	private Usuario crearUsuario(String email, Boolean estado, String pass, String username, Perfil perfil, String tipoUsuario, Boolean isDisabled) {
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setEstado(estado);
		u.setUsername(username);
		u.setPassword(pass);
		u.setPerfil(perfil);
		u.setNombres("nombre prueba");
		u.setApellidos("apellido prueba");
		u.setDni("99999999");
		u.setNroCel("951228608");
		u.setTipoUsuario(tipoUsuario);
		u.setTelefono("5555555");
		u.setEstaInhabilitado(isDisabled);
		return u;
	}

}
