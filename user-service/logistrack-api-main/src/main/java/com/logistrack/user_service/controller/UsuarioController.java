package com.logistrack.user_service.controller;

import com.logistrack.user_service.dto.UsuarioRequestDTO;
import com.logistrack.user_service.dto.UsuarioResponseDTO;
import com.logistrack.user_service.model.Usuario;
import com.logistrack.user_service.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorUsername(@PathVariable String username){
        return ResponseEntity.ok(usuarioService.obtenerPorUsername(username));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerPorRol(
            @PathVariable Usuario.RolUsuario rol){
        return ResponseEntity.ok(usuarioService.obtenerPorRol(rol));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerPorEstado(
            @PathVariable Usuario.EstadoUsuario estado){
        return ResponseEntity.ok(usuarioService.obtenerPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
            @Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<UsuarioResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Usuario.EstadoUsuario nuevoEstado){
        return ResponseEntity.ok(usuarioService.cambiarEstado(id, nuevoEstado));
    }
}
