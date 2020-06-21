package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.back.alquiler.models.Modulo;

public interface ModuloRepo extends JpaRepository<Modulo, Integer> {
	
	@Query(value="select * from modulo m inner join perfiles_modulo pm "
			+ "on m.id_modulo = pm.id_modulo "
			+ "left join pagina p on m.id_modulo = p.id_modulo where pm.id_perfil =:idperfil "
			+ "order by m.orden", nativeQuery = true)
	List<Modulo> listarModuloPorPerfil(@Param("idperfil") Integer idperfil);

}
