package com.lk.cursomc.services.validation;

import com.lk.cursomc.domain.Cliente;
import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.dto.ClienteNewDTO;
import com.lk.cursomc.repositories.ClienteRepository;
import com.lk.cursomc.resource.exception.FieldMessage;
import com.lk.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository _clienteRepository;


    @Override
    public void initialize(ClienteInsert ann){
    }

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context){

        List<FieldMessage> list = new ArrayList<>();

        // === VALIDAÇÕES ===

        // Valida CPF valido.
        if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(dto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Inválido!"));
        }

        // Valida CNPJ Valido.
        if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(dto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Inválido!"));
        }

        // Valida E-mail já existente.
        Cliente cli = _clienteRepository.findByEmail(dto.getEmail());
        if (cli != null){
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
