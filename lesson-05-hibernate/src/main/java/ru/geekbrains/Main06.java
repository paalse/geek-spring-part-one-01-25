package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Contact;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main06 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();



        //  INSERT
//        em.getTransaction().begin();
//        User user = new User("user2","password1","user1@mail.ru");
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact("home phone", "+7(3412)31-32-43", user));
//        contacts.add(new Contact("work phone", "+7(3412)91-62-34", user));
//        contacts.add(new Contact("mobile phone", "+7(912)791-62-34", user));
//        contacts.add(new Contact("home address", "Russia, Izhevsk, Pushkinskaya 27-45", user));
//
//        contacts.forEach(em::persist);
//
//        em.getTransaction().commit();

// SELECT for one to many
//        User user = em.find(User.class, 1l);
//        user.getContacts().forEach(System.out::println);
//
//        // Выборка с параметрами
//        List<Contact> contacts = em.createQuery(
//                "select c from User u " +
//                        "inner join Contact c on u.id=c.user.id " +
//                        "where c.type = 'mobile phone'", Contact.class)
//                .getResultList();
//
//        contacts.forEach(System.out::println);

        em.close();
    }
}
