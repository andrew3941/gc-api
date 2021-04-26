package com.preving.intranet.gestioncentrosapi.conf;

import com.preving.security.JwtUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 * Creado por David el dia 16/04/2018.
 */
@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<Integer> createAuditorProvider() {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<Integer> {
        @Override
        public Optional<Integer> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUser user = authentication == null || !authentication.isAuthenticated() ?
                    null :
                    (JwtUser) authentication.getPrincipal();

            return Optional.of(Objects.requireNonNull(user).getId().intValue());
        }
    }
}
