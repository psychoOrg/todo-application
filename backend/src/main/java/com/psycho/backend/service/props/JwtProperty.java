package com.psycho.backend.service.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperty {
    @Value(value = "${spring.security.secret}")
    private String secret;

    @Value(value = "${spring.security.lifetime}")
    private Long lifetime;
}
