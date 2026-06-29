package com.logistrack.user_service.repository;

import com.logistrack.user_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByEstado(Usuario.EstadoUsuario estado);
    List<Usuario> findByRol(Usuario.RolUsuario rol);


}
