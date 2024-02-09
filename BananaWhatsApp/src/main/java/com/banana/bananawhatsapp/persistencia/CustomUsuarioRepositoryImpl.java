package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void crear(Usuario usuario) {
        if(usuario.valido()){
            entityManager.persist(usuario);
        }
    }

    @Override
    @Transactional
    public Usuario actualizar(Usuario usuario) {
        Usuario currUser = null;
        if(usuario.valido()){
            currUser = entityManager.find(Usuario.class, usuario.getId());
            currUser.setNombre(usuario.getNombre());
            currUser.setEmail(usuario.getEmail());
            currUser.setAlta(usuario.getAlta());
            currUser.setActivo(usuario.isActivo());
            entityManager.persist(currUser);
        }
        return currUser;
    }

    @Override
    @Transactional
    public Boolean borrar(Usuario usuario) {

        Usuario currUser = entityManager.find(Usuario.class, usuario.getId());
        entityManager.remove(currUser);

        return true;
    }
}
