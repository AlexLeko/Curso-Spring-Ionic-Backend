package com.lk.cursomc.resource;

import com.lk.cursomc.dto.EmailDTO;
import com.lk.cursomc.security.JWTUtil;
import com.lk.cursomc.security.UserSS;
import com.lk.cursomc.services.AuthService;
import com.lk.cursomc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generationToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDto) {
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }

}
