package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class Main05 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


        //---------------------------------------------
        // Работа с пользователем через репозиторий
        //---------------------------------------------
//        UserRepository userRepository = new UserRepository(emFactory);
//
//        // 1.Создание пользователей
//        User user = new User("user1", "password1", "sdfsd@mail.ru");
//        User user1 = new User("user2", "password2", "3532dfsd@mail.ru");
//        User user2 = new User("user3", "password3", "fdghfd@mail.ru");
//        userRepository.insert(user);
//        userRepository.insert(user1);
//        userRepository.insert(user2);
//
//        // 2.Получение всех пользователей
//        List<User> userList = userRepository.findAll();
//        System.out.println(userList);
//
//        // 3.Получение пользователя по ID
//        System.out.println(userRepository.findById(1));
//
//        // 4.Удаление пользователя по ID
//        userRepository.deleteById(1);
//
//        //5. Изменение пользователя
//        User user3 = userRepository.findById(2);
//        user3.setPassword("geyethbdfgb546456");
//        userRepository.saveOrUpdate(user3);
//
//
//        //---------------------------------------------
//        // Работа с продуктом через репозиторий
//        //---------------------------------------------
//        ProductRepository productRepository =new ProductRepository(emFactory);
//
//        // 1.Создание продуктов
//        Product product = new Product("product1", 10);
//        Product product1 = new Product("product2", 20);
//        Product product2 = new Product("product3", 30);
//
//        productRepository.insert(product);
//        productRepository.insert(product1);
//        productRepository.insert(product2);
//
//        // 2.Получение всех ghjlernjd
//        List<Product> productList = productRepository.findAll();
//        System.out.println(productList);
//
//        // 3.Получение продукта по ID
//        System.out.println(productRepository.findById(1));
//
//        // 4.Удаление продукта по ID
//        productRepository.deleteById(1);
//
//        //5. Изменение продукта
//        Product product3 = productRepository.findById(2);
//        product3.setTitle("geyethbdfgb546456");
//        productRepository.saveOrUpdate(product3);


//        EntityManager em = emFactory.createEntityManager();

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
//        em.getTransaction().begin();
//        em.createQuery("delete from User where username =:username")
//                .setParameter("username", "user3")
//                .executeUpdate();
//        em.getTransaction().commit();
//        em.close();

    }
}
