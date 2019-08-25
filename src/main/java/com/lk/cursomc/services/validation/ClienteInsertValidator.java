package com.lk.cursomc.services.validation;

import com.lk.cursomc.domain.enums.TipoCliente;
import com.lk.cursomc.dto.ClienteNewDTO;
import com.lk.cursomc.resource.exception.FieldMessage;
import com.lk.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann){
    }

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context){

        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista;

        if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(dto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Inválido!"));
        }

        if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(dto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Inválido!"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
