package ru.geekbrains.service;

import java.util.List;
import java.util.Optional;

// Хорошая практика при работе со спрингом, если создаем Бин
// то необходимо создавать для него интерфейс и этот интерфейс использовать при внедрении
// это повышает гибкость приложения, что позволит создать другой класс отличный от UserServiceImpl
// и хранить информацию не только в базе данных но и гдето еще
public interface UserService {

    List<UserRepr> findAll();

    List<UserRepr> findWithFilter(String usernameFilter);

    Optional<UserRepr> findById(long id) ;

    void save(UserRepr user);

    void delete(long id);
}