package com.example.ForoHub;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private static ITopicoRepo topicoRepo;

    // Crear tópico
    public static Topico crearTopico(TopicoDTO dto) {
        if (topicoRepo.existsByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setCurso(dto.getCurso());
        topico.setAutorId(dto.getAutorId());

        return topicoRepo.save(topico);
    }

    // Listar todos
    public List<Topico> listarTodos() {
        return topicoRepo.findAll();
    }

    // Listar primeros 10
    public List<Topico> listarPrimeros10() {
        return topicoRepo.findTop10ByOrderByFechaCreacionAsc();
    }

    // Buscar por curso
    public List<Topico> buscarPorCurso(String curso) {
        return topicoRepo.findByCursoIgnoreCase(curso);
    }

    // Obtener detalle por ID
    public Topico obtenerPorId(Long id) {
        Optional<Topico> topicoOpt = topicoRepo.findById(id);
        if (topicoOpt.isEmpty()) {
            throw new RuntimeException("Tópico no encontrado con ID: " + id);
        }
        return topicoOpt.get();
    }

    // Actualizar tópico
    public Topico actualizarTopico(Long id, TopicoDTO dto) {
        Topico topico = obtenerPorId(id); // verifica existencia

        if (!topico.getTitulo().equals(dto.getTitulo()) || !topico.getMensaje().equals(dto.getMensaje())) {
            if (topicoRepo.existsByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())) {
                throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
            }
        }

        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setCurso(dto.getCurso());
        topico.setAutorId(dto.getAutorId());

        return topicoRepo.save(topico);
    }

    // Eliminar tópico
    public void eliminarTopico(Long id) {
        if (!topicoRepo.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado con ID: " + id);
        }
        topicoRepo.deleteById(id);
    }
}
