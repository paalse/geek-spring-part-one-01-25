package ru.paalse.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private Map<Long, User> userMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new User("User1"));
        this.insert(new User("User2"));
        this.insert(new User("User3"));
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById(long id) {
        return userMap.get(id);
    }

    public void insert (User user) {
        long id = identity.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
    }

    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    public void delete(Long id) {
        userMap.remove(id);
    }
}
