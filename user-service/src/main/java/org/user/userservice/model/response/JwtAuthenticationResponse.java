package org.user.userservice.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtAuthenticationResponse {
    private String token;
}