package com.lk.cursomc.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HeaderExposureFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // Cria a propriedade LOCATION no cabeçalho da resposta.
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.addHeader("access-control-expose-headers", "Location");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
