package com.banana.bananawhatsapp.modelos;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private LocalDate alta;
    private boolean activo;
    @OneToMany(mappedBy = "remitente", cascade={CascadeType.ALL})
    private List<Mensaje> mensajesEnviados;
    @OneToMany(mappedBy = "destinatario", cascade={CascadeType.ALL})
    private List<Mensaje> mensajesRecibidos;

    private boolean validarNombre() {
        return this.nombre != null && this.nombre.length() >= 3;
    }

    private boolean validarEmail() {
        return this.email != null && this.email.indexOf("@") > 0 && this.email.indexOf(".") > 0;
    }

    private boolean validarAlta() {
        return this.alta != null && this.alta.compareTo(LocalDate.now()) <= 0;
    }

    public boolean valido() throws UsuarioException {
        if ((id != null && id > 0 || id == null)
                && activo
                && validarNombre()
                && validarEmail()
                && validarAlta()
        ) return true;
        else throw new UsuarioException("Usuario no valido");
    }

    public Usuario(Integer id, String nombre, String email, LocalDate alta, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.alta = alta;
        this.activo = activo;
    }
}
