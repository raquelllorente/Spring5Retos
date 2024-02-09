package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;

public interface CustomUsuarioRepository {

    void crear(Usuario usuario);
    Usuario actualizar(Usuario usuario);

    Boolean borrar(Usuario usuario);
}
