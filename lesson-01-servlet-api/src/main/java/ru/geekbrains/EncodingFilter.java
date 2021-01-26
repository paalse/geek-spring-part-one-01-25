package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*") // Шаблон указывающий что все запросы необходимо пропускать через этот фильтр
public class EncodingFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Модифицируем запрос, меняем кодировку
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        chain.doFilter(req,res); // Такой фильтр пропускает через себя все входящие запросы
    }

    @Override
    public void destroy() {

    }
}
