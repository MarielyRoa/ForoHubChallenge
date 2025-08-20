package com.example.ForoHub;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Crear tópico
    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestBody @Valid TopicoDTO dto) {
        try {
            Topico nuevo = topicoService.crearTopico(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Listar todos
    @GetMapping
    public List<Topico> listarTodos() {
        return topicoService.listarTodos();
    }

    // Listar primeros 10
    @GetMapping("/ultimos10")
    public List<Topico> listarUltimos10() {
        return topicoService.listarPrimeros10();
    }

    // Buscar por curso
    @GetMapping("/buscar")
    public List<Topico> buscarPorCurso(@RequestParam String curso) {
        return topicoService.buscarPorCurso(curso);
    }

    // Detalle de tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetalle(@PathVariable Long id) {
        try {
            Topico topico = topicoService.obtenerPorId(id);
            return ResponseEntity.ok(topico);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Actualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDTO dto) {
        try {
            Topico actualizado = topicoService.actualizarTopico(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Eliminar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        try {
            topicoService.eliminarTopico(id);
            return ResponseEntity.ok("Tópico eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
