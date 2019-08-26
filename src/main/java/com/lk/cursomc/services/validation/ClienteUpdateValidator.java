package com.lk.cursomc.services.validation;

import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.dto.ClienteDTO;
import com.lk.cursomc.dto.ClienteNewDTO;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.resource.exception.FieldMessage;
import com.lk.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository _clienteRepository;


    @Override
    public void initialize(ClienteUpdate ann){
    }

    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context){

        // recupera o valor do ID da URI da requisição.
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        // === VALIDAÇÕES ===

       // Valida E-mail já existente.
        Cliente cli = _clienteRepository.findByEmail(dto.getEmail());
        if (cli != null && !cli.getId().equals(uriId))  {
            list.add(new FieldMessage("email", "E-mail já cadastrado."));
        }


        // inclua os testes aqui, inserindo erros na lista;
        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
