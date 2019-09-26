package com.lk.cursomc.services;

import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);


    // === HTML ===

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage msg);


    // === JWT ===

    void sendNewPasswordEmail(Cliente cliente, String newPass);

}
