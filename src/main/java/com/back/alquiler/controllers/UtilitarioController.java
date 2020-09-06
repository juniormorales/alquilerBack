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

	Map<String, List<String>> respBD1 = new HashMap<>();

	Map<String, List<String>> respBD2 = new HashMap<>();

	@PostMapping("/llenarBD")
	public ResponseEntity<Map<String, List<String>>> insertarDatos1(){
		
			List<String> messages = new ArrayList<>();
			List<Modulo> tmpModulo = new ArrayList<>();
			Modulo modGestionCuentas = this.crearModulo(null, "tim-icons icon-key-25", false, 1,
					"/administracion/cuentas", "Gestion de cuentas", "link");
			Modulo modVerSoliAcep = this.crearModulo(null, "tim-icons icon-email-85", false, 2,
					"/administracion/ver-sol", "Solicitudes Pendientes", "link");
			Modulo modDash = this.crearModulo(null, "tim-icons icon-chart-pie-36", false, 1, "/arrendero/dashboard",
					"Dashboard", "link");
			Modulo modGestionProp = this.crearModulo("prop", "mdi mdi-home-modern", true, 2, "/arrendero/",
					"Propiedades", "sub");
			Modulo modGestInqui = this.crearModulo("inquilinos", "mdi mdi-emoticon-cool", true, 3, "/arrendero/",
					"Inquilinos", "sub");
			Modulo modCondicionPago = this.crearModulo(null, "tim-icons icon-coins", false, 5,
					"/arrendero/condicion-pago", "Condicion Pago", "link");
			Modulo modGestSolici = this.crearModulo(null, "mdi mdi-eye", false, 6, "/arrendero/solicitudes",
					"Solicitudes", "link");
			Modulo modGestDoc = this.crearModulo("doc", "tim-icons icon-single-copy-04", true, 4, "/arrendero/",
					"Contratos", "sub");
			Modulo modPresentacion = this.crearModulo(null, "mdi mdi-map-marker-radius", false, 1,
					"/arrendatario/buscar", "Buscar Propiedades", "link");
			Modulo modHistorialUsu = this.crearModulo(null, "mdi mdi-library", true, 2, "/arrendatario/", "Solicitudes",
					"sub");
			Modulo modDeudas = this.crearModulo("deudas", "tim-icons icon-paper", true, 3, "/arrendatario/",
					"Pagos / Deudas", "sub");
			
			Modulo modPropConfirmar = this.crearModulo("propconfir", "mdi mdi-timetable", false, 3, "/administracion/propiedad-confirmar", "Confirmar Propiedades", "link");
			tmpModulo.add(modGestionCuentas);
			tmpModulo.add(modVerSoliAcep);
			tmpModulo.add(modDash);
			tmpModulo.add(modGestionProp);
			tmpModulo.add(modCondicionPago);
			tmpModulo.add(modGestInqui);
			tmpModulo.add(modGestSolici);
			tmpModulo.add(modGestDoc);
			tmpModulo.add(modPresentacion);
			tmpModulo.add(modHistorialUsu);
			tmpModulo.add(modDeudas);
			tmpModulo.add(modPropConfirmar);
			messages.add(service.insertarDatosModulo(tmpModulo));

			List<Pagina> tmpPagina = new ArrayList<>();
			Pagina pgprop = this.crearPagina("misprop", "mis-propiedades", "MPROP", "Mis Propiedades", modGestionProp);
			Pagina pggesinq = this.crearPagina("gestinq", "gestion-inquilino", "GINQ", "Gestion Inquilino",
					modGestInqui);
			Pagina pgpago = this.crearPagina("verpago", "pagos", "PAG", "Pagos", modGestInqui);
			Pagina pgpagoporacept = this.crearPagina("pagoporacept", "por-aceptar", "PENDI", "Pagos por Confirmar",
					modGestInqui);
			Pagina pgcontratopend = this.crearPagina("contrpend", "contrat-pend", "CNPND", "Contratos pendientes",
					modGestDoc);
			Pagina pgcontr = this.crearPagina("contr", "contratos", "CONT", "Contratos", modGestDoc);
			Pagina pgsol = this.crearPagina("solicitud", "sol-hechas", "SOL", "Hechas", modHistorialUsu);
			Pagina pgveracep = this.crearPagina("veracep", "sol-aceptadas", "ACEPT", "Aceptadas", modHistorialUsu);
			Pagina pgrecordpagos = this.crearPagina("pagos", "pagos", "PAGO", "Record de Pagos", modDeudas);
			Pagina pgpagosvencer = this.crearPagina("pagosven", "por-vencer", "PPV", "Pagos por Vencer", modDeudas);
			Pagina pgpagospend = this.crearPagina("deudas", "deudas", "DEUDA", "Deudas pendientes", modDeudas);
			tmpPagina.add(pgprop);
			tmpPagina.add(pggesinq);
			tmpPagina.add(pgpago);
			tmpPagina.add(pgpagoporacept);
			tmpPagina.add(pgcontratopend);
			tmpPagina.add(pgcontr);
			tmpPagina.add(pgsol);
			tmpPagina.add(pgveracep);
			tmpPagina.add(pgrecordpagos);
			tmpPagina.add(pgpagosvencer);
			tmpPagina.add(pgpagospend);

			messages.add(service.insertarDatosPagina(tmpPagina));

			List<Perfil> tmpPerfil = new ArrayList<>();
			Perfil admi = this.crearPerfil(1, true, "ROLE_ADMINISTRADOR");
			Perfil arrendero = this.crearPerfil(1, true, "ROLE_ARRENDERO");
			Perfil arrendetario = this.crearPerfil(1, true, "ROLE_ARRENDATARIO");
			tmpPerfil.add(admi);
			tmpPerfil.add(arrendero);
			tmpPerfil.add(arrendetario);
			messages.add(service.insertarDatosPerfil(tmpPerfil));

			List<Usuario> tmpUsuarios = new ArrayList<>();
			Usuario user1 = this.crearUsuario("correo1@hotmail.com", true,
					"$2a$10$5lQfLdWrdOiZudh3cCNmbOz2TcU3DtgfjqCeFHvGS1HDBHSvlNdu6", "user1", admi, "ADMINISTRADOR",false);
			tmpUsuarios.add(user1);
			messages.add(service.insertarDatosUsuarios(tmpUsuarios));
			
			List<Banco> tmpBancos = new ArrayList<>();
			tmpBancos.add(new Banco("Banco de Comercio"));
			tmpBancos.add(new Banco("Banco de Crédito del Perú"));
			tmpBancos.add(new Banco("Banco Interamericano de Finanzas (BanBif)"));
			tmpBancos.add(new Banco("Banco Pichincha"));
			tmpBancos.add(new Banco("BBVA"));
			tmpBancos.add(new Banco("Citibank Perú"));
			tmpBancos.add(new Banco("Interbank"));
			tmpBancos.add(new Banco("MiBanco"));
			tmpBancos.add(new Banco("Scotiabank Perú"));
			tmpBancos.add(new Banco("Banco GNB Perú"));
			tmpBancos.add(new Banco("Banco Falabella"));
			tmpBancos.add(new Banco("Banco Ripley"));
			tmpBancos.add(new Banco("Banco Santander Perú"));
			tmpBancos.add(new Banco("Banco Azteca"));
			tmpBancos.add(new Banco("CRAC CAT Perú"));
			tmpBancos.add(new Banco("ICBC PERU BANK"));
			tmpBancos.add(new Banco("Agrobanco"));
			tmpBancos.add(new Banco("Banco de la Nación"));
			tmpBancos.add(new Banco("COFIDE"));
			tmpBancos.add(new Banco("Fondo MiVivienda"));
			tmpBancos.add(new Banco("Caja Arequipa"));
			tmpBancos.add(new Banco("Caja Cusco"));
			tmpBancos.add(new Banco("Caja Del Santa"));
			tmpBancos.add(new Banco("Caja Trujillo"));
			tmpBancos.add(new Banco("Caja Huancayo"));
			tmpBancos.add(new Banco("Caja Ica"));
			tmpBancos.add(new Banco("Caja Maynas"));
			tmpBancos.add(new Banco("Caja Paita"));
			tmpBancos.add(new Banco("Caja Piura"));
			tmpBancos.add(new Banco("Caja Sullana"));
			tmpBancos.add(new Banco("Caja Incasur"));
			tmpBancos.add(new Banco("Caja Los Andes"));
			tmpBancos.add(new Banco("Caja Prymera"));
			tmpBancos.add(new Banco("Caja Sipán"));
			tmpBancos.add(new Banco("Caja Del Centro"));
			tmpBancos.add(new Banco("Caja Raíz"));
			tmpBancos.add(new Banco("Caja Metropolitana de Lima"));
			tmpBancos.add(new Banco("Financiera Amérika"));
			tmpBancos.add(new Banco("Financiera Crediscotia"));
			tmpBancos.add(new Banco("Financiera Confianza"));
			tmpBancos.add(new Banco("Financiera Compartamos"));
			tmpBancos.add(new Banco("Financiera Credinka"));
			tmpBancos.add(new Banco("Financiera Efectiva"));
			tmpBancos.add(new Banco("Financiera Proempresa"));
			tmpBancos.add(new Banco("Financiera Mitsui Auto Finance"));
			tmpBancos.add(new Banco("Financiera Oh!"));
			tmpBancos.add(new Banco("Financiera Qapaq"));
			tmpBancos.add(new Banco("Financiera TFC"));
			tmpBancos.add(new Banco("EDPYME Acceso Crediticio"));
			tmpBancos.add(new Banco("EDPYME Alternativa"));
			tmpBancos.add(new Banco("EDPYME BBVA Consumer Finance"));
			tmpBancos.add(new Banco("EDPYME Credivisión"));
			tmpBancos.add(new Banco("EDPYME Inversiones La Cruz"));
			tmpBancos.add(new Banco("EDPYME Mi Casita"));
			tmpBancos.add(new Banco("EDPYME Progreso"));
			tmpBancos.add(new Banco("EDPYME GMG Servicios Perú"));
			tmpBancos.add(new Banco("EDPYME Santander Consumer Perú"));
			messages.add(service.insertarDatosBancos(tmpBancos));
			
			messages.add(service.insertarDatosPerfilesModulos());
			messages.add(service.insertarDatosDepartamento());
			messages.add(service.insertarDatosProvincia());
			messages.add(service.insertarDatosDistrito());
			respBD1.put("mensaje", messages);

		return new ResponseEntity<>(respBD1, HttpStatus.OK);

	}

	private Modulo crearModulo(String collapse, String icono, Boolean isCollapsed, int orden, String path, String title,
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

	private Pagina crearPagina(String collapse, String path, String smallTitle, String title, Modulo modulo) {
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

	private Perfil crearPerfil(int ambito, boolean estado, String nombres) {
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
