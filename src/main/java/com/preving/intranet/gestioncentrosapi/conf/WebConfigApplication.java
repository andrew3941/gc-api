package com.preving.intranet.gestioncentrosapi.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by javier-montesinos on 27/03/17.
 */
@Configuration
// Para subir la aplicación para swagger se debe quitar o comentar la anotación @EnableWebMvc
@EnableWebMvc
//@Profile("preprod")
public class WebConfigApplication implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // necesario para aceptar CORS OPTIONS
        registry.addMapping("/**").allowedMethods("*");
    }
}
