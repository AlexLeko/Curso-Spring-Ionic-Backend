package com.lk.cursomc.services;

import com.lk.cursomc.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoBoleto(PagamentoBoleto pagamento, Date instantePedido) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(instantePedido);
        calendario.add(Calendar.DAY_OF_MONTH, 7);

        pagamento.setDataVencimento(calendario.getTime());
    }


}
