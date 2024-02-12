package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MensajeReposirotyImpl implements IMensajeRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        if(mensaje.valido()){
            entityManager.persist(mensaje);
        }
        return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        if(usuario.valido()) {
            TypedQuery<Mensaje> q = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.remitente = :usuario OR m.destinatario = :usuario", Mensaje.class);
            q.setParameter("usuario", usuario);
            return q.getResultList();
        } else {
            throw new UsuarioException();
        }
    }

    @Override
    public List<Mensaje> obtenerEntre(Usuario remitente, Usuario destinatario) throws SQLException {
         if(remitente.valido() && destinatario.valido()) {
            Query q = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.remitente = :remitente AND m.destinatario = :destinatario");
            q.setParameter("remitente", remitente);
            q.setParameter("destinatario", destinatario);
            return q.getResultList();
        } else {
            throw new UsuarioException();
        }
    }

    @Override
    @Transactional
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        if(remitente.valido() && destinatario.valido()) {
            Query q = entityManager.createQuery("DELETE FROM Mensaje m WHERE m.remitente = :remitente AND m.destinatario = :destinatario");
            q.setParameter("remitente", remitente);
            q.setParameter("destinatario", destinatario);
            return q.executeUpdate() > 0;
        } else {
            throw new UsuarioException();
        }
    }

    @Override
    @Transactional
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        if(usuario.valido()) {
            Query q = entityManager.createQuery("DELETE FROM Mensaje m WHERE m.remitente = :usuario OR m.destinatario = :usuario");
            q.setParameter("usuario", usuario);
            return q.executeUpdate() > 0;
        } else {
            throw new UsuarioException();
        }
    }
}
