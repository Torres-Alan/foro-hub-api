package com.alura.forohub.forohubapi.domain.users;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public AutenticacionService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = usuarioRepository.findByLogin(username);
        if (u == null) throw new UsernameNotFoundException("Usuario no encontrado");
        return u;
    }
}
