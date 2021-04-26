package com.preving.intranet.gestioncentrosapi.model.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rogeliogragera on 1/6/17.
 */
@Component
@ConfigurationProperties(prefix = "users")
public class Users {
    private List<User> usuariosList = new ArrayList<>();

    public Users(){
      //empty ctor
    }

    public List<User> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<User> usuariosList) {
        this.usuariosList = usuariosList;
    }
}
