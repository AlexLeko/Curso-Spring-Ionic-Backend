package com.lk.cursomc.services;

import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();


    public void sendNewPassword(String email) {

        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null) {
            throw new ObjectNotFoundException("E-mail não encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(encoder.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];

        for (int i = 0; i<10; i++) {
            vet[i] = ramdomChar();
        }

        return new String(vet);
    }

    private char ramdomChar() {

        int opt = random.nextInt(3);

        if (opt == 0) {
            // gera um numero de 0 a 10 ( 48 é o index de '0' na tabela unicode)
            return (char) (random.nextInt(10) + 48);
        }
        else if (opt == 1) {
            // gera letra maiuscula ( 65 é o index de 'A' na tabela unicode)
            return (char) (random.nextInt(26) + 65);
        }
        else {
            // gera letra minuscula ( 97 é o index de 'a' na tabela unicode)
            return (char) (random.nextInt(26) + 97);
        }
    }


}
