package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        //INSERT
//        em.getTransaction().begin();
//        User user = new User("user3", "password", "sdfsd@mail.ru");
//        User user1 = new User("user4", "password", "xcvx@mail.ru");
//        em.persist(user);
//        em.persist(user1);
//        em.getTransaction().commit();
//        em.close();

        //SELECT
//        //1-й способ
//        System.out.println("Select user with id=1");
//        User user = em.find(User.class, 1l);
//        System.out.println(user);
//
//        //2-й способ, с помощью HQL или JPQL
//        System.out.println("Select all users in table");
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        //3-й извлечение по имени пользователя
//        System.out.println("Select user with name=User3");
//        Object user3 = em.createQuery("from User u where u.username = :username")
//                .setParameter("username", "user3")
//                .getSingleResult();
//        System.out.println(user3);
//
//        //4-й, можно прямо писать SQL запрос
//        userList = em.createNativeQuery("select * from users", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        //5-й использование заданных в классе запросов
//        em.createNamedQuery("userByName")
//                .setParameter("username", "user3")
//                .getSingleResult();


        //UPDATE
//        User user = em.find(User.class, 1L);
//        em.getTransaction().begin();
//        user.setPassword("dsfqwerw123");
//        em.getTransaction().commit();
//        em.close();

        //DELETE
//        //1-й способ
//        em.getTransaction().begin();
//        User user = em.find(User.class, 1L);
//        if(user != null) {
//            em.remove(user);
//            em.getTransaction().commit();
//        }
//        em.close();

        //2-й способ
        em.getTransaction().begin();
        em.createQuery("delete from User where username =:username")
                .setParameter("username", "user3")
                .executeUpdate();
        em.getTransaction().commit();
        em.close();

    }
}
