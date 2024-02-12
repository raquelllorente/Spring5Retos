package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario obtener(int id) throws SQLException {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    @Transactional
    public Usuario crear(Usuario usuario) {
        if(usuario.valido()){
            entityManager.persist(usuario);
            return usuario;
        }
        return null;
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
    public boolean borrar(Usuario usuario) {
        Usuario currUser = entityManager.find(Usuario.class, usuario.getId());
        if (currUser != null) {
            entityManager.remove(currUser);
            return true;
        } else {
            throw new UsuarioException();
        }
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) {
        Usuario currUser = entityManager.find(Usuario.class, id);
        Set<Usuario> usuarios = null;
        if(currUser != null && currUser.valido()){
            TypedQuery<Usuario> q = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id != :id AND u.activo = true", Usuario.class).setMaxResults(max);
            q.setParameter("id", id);
            usuarios = new HashSet<>(q.getResultList());
        } else{
            throw new UsuarioException();
        }
        return usuarios;
    }

}
