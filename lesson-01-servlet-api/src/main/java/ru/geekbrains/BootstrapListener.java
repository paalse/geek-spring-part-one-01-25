package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Срабатывает при вызове нашего приложения, создает объекты которые могут использовать все сервлеты
 */
@WebListener
public class BootstrapListener implements ServletContextListener {

    // Метод вызывается перед созданием сервлетов
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        UserRepository userRepository = new UserRepository(); // Создание репозиторя пользователей
        sc.setAttribute("userRepository", userRepository); // Делаем доступным наш репозиторий все сервлетам

        userRepository.insert(new User("user1")); // Создаем пользователей
        userRepository.insert(new User("user2"));
        userRepository.insert(new User("user3"));
    }
}