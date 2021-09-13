package ru.paalse;

import org.hibernate.cfg.Configuration;
import ru.paalse.persist.Contact;
import ru.paalse.persist.User;

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

        // INSERT for one to many
//        em.getTransaction().begin();
//
//        User user = new User("user1", "mail1.mail.ru", "password1");
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact("home phone", "(3412)23-23-23", user));
//        contacts.add(new Contact("work phone", "(3412)75-56-46", user));
//        contacts.add(new Contact("mobile phone", "(912)775-33-00", user));
//        contacts.add(new Contact("home address", "Russia, Izhevsk, Lenina 23-32", user));
//
//        contacts.forEach(em::persist);
//
//        em.getTransaction().commit();

        // SELECT for one to many
//        User user = em.find(User.class, 1L);
//        user.getContacts().forEach(System.out::println);

        // Выборка только мобильных телефонов по всем пользователям
//        List<Contact> contacts = em.createQuery("select c from User u " +
//                "inner join Contact c on u.id=c.user.id " +
//                "where c.type = 'mobile phone'", Contact.class)
//                .getResultList();
//
//        contacts.forEach(System.out::println);

        // Выборка пользователей у которых есть контакт с мобильным телефоном
        List<String> usernames = em.createQuery("select new java.lang.String(u.username) from User u " +
                "inner join Contact c on u.id=c.user.id " +
                "where c.type = 'mobile phone'", String.class)
                .getResultList();

        usernames.forEach(System.out::println);

        em.close();
    }
}
