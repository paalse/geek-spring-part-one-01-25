package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/first-servlet")
public class FirstServlet implements Servlet {

    private ServletConfig config; //

    /**
     * Инициализация сервлета
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    /**
     * Возвращает конфиг
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    /**
     * Когда будет приходить на наш сервер запрос, будет вызываться этот метод
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>Привет от сервлета !!!</h1>");
    }

    /**
     * Возвращает информацию, любую которую укажем
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * Вызывается при завершении приложения
     */
    @Override
    public void destroy() {

    }
}
