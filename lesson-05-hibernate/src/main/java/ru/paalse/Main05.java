package ru.paalse;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main05 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

       // EntityManager em = emFactory.createEntityManager();


        UserRepository userRepository = new UserRepository(emFactory);
        // 1. Создание пользователей
        userRepository.insert(new User("user1", "mail1.mail.ru", "password1"));
        userRepository.insert(new User("user2", "mail2.mail.ru", "password2"));
        userRepository.insert(new User("user3", "mail3.mail.ru", "password3"));
        userRepository.insert(new User("user4", "mail4.mail.ru", "password4"));







        //INSERT
//        em.getTransaction().begin(); // Открытие транзакции
//        User user = new User("user1", "mail.mail.ru", "password1");
//        User user1 = new User("user2", "mail2.mail.ru", "password2");
//        User user2 = new User("user3", "mail3.mail.ru", "password3");
//        User user3 = new User("user4", "mail4.mail.ru", "password4");
//        em.persist(user);
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.getTransaction().commit();
//        em.close();

//        //SELECT
//        User user = em.find(User.class, 1L); // Поиск по идентификатору
//        System.out.println(user);
//
//        // HQL, JPQL Выборка всех пользователей
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        // HQL, JPQL Выборка пользователя по имени
//        Object user1 = em.createQuery("from User u where u.username = :username")
//                .setParameter("username", "user3")
//                .getSingleResult();
//        System.out.println(user1);
//
//        // SQL
//        userList = em.createNativeQuery("select  * from users", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//
//        // Именованный запрос заданный в класе User
//        Object user2 = em.createNamedQuery("userByName")
//                .setParameter("username", "user2")
//                .getSingleResult();
//        System.out.println(user2);

        //UPDATE
//        User user = em.find(User.class, 1L);
//        em.getTransaction().begin();
//        user.setPassword("qerqwerqwer");
//        em.getTransaction().commit();
//        em.close();

        //DELETE
        //1-й способ
//        em.getTransaction().begin();
//        User user = em.find(User.class, 1L);
//        if(user != null) {
//            em.remove(user);
//            em.getTransaction().commit();
//        }
//        em.close();

        //2-й способ
//        em.getTransaction().begin();
//        em.createQuery("delete from User where username =:username")
//                .setParameter("username", "user3")
//                .executeUpdate();
//        em.getTransaction().commit();
//        em.close();



    }
}
