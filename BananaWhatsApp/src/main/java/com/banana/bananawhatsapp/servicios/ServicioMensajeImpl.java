package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioMensajeImpl implements IServicioMensajeria{

    @Autowired
    IMensajeRepository iMensajeRepository;

    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
        Mensaje mensaje = new Mensaje(null, remitente, destinatario, texto, LocalDate.now());
        try {
            iMensajeRepository.crear(mensaje);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mensaje;
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
         try {
            return iMensajeRepository.obtenerEntre(remitente, destinatario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        try {
            return iMensajeRepository.borrarEntre(remitente, destinatario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
