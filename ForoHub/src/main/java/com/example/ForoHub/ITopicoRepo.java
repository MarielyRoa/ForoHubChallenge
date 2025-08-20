package com.example.ForoHub;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITopicoRepo extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findTop10ByOrderByFechaCreacionAsc();

    List<Topico> findByCursoIgnoreCase(String curso);
}
