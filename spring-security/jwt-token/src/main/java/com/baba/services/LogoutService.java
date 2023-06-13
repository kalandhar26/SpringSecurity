package com.baba.services;

import com.baba.repos.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtService jwtService;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalStateException("Unauthorized, Token Invalid.!");
        }

        final String jwtToken;

        jwtToken = authHeader.substring(7);

        var storedToken = tokenRepository.findByToken(jwtToken).orElse(null);

        if (storedToken != null) {
            boolean isExpiredOrRevoked = storedToken.isExpired() || storedToken.isRevoked();
            storedToken.setExpired(!isExpiredOrRevoked);
            storedToken.setRevoked(!isExpiredOrRevoked);
            tokenRepository.save(storedToken);
            if (isExpiredOrRevoked) {
                throw new IllegalStateException("Token is Expired or Revoked");
            }
        } else {
            throw new IllegalStateException("Invalid Token");
        }
    }

}
