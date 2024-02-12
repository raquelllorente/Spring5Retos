package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Set;

@Service
public class ServicioUsuariosImpl implements IServicioUsuarios{

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Override
    public Usuario obtener(int id) throws UsuarioException {
        try {
            return iUsuarioRepository.obtener(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        try {
            return iUsuarioRepository.crear(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        try {
            return iUsuarioRepository.borrar(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        try {
            return iUsuarioRepository.actualizar(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        try {
            return iUsuarioRepository.obtenerPosiblesDestinatarios(usuario.getId(), max);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
