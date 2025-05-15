package com.github.jinn9.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        // TODO: replace random id with session id / spring security login info
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}
