package com.lk.cursomc.services;

import com.lk.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine _templateEngine;

    @Autowired
    private JavaMailSender _javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido){
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado!\n ===> Código: " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());

        return sm;
    }

    // ==========
    // == HTML ==
    // ==========

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);

        return _templateEngine.process("confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido){
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = _javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido), true);

        return mimeMessage;
    }

}
