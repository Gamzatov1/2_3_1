package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private List<User> users;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDAOImpl(List<User> users) {
        users = new ArrayList<User>();
        users.add(new User("FirstName", "FirstSurname", (byte) 11));
        users.add(new User("FirstName", "FirstSurname", (byte) 11));
    }

    @Override
    public List<User> allUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public User getOne(long id) {
        return em.createQuery("select u from User u where u.id = :id", User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateUser(Long id, User user) {
        User updateUser = em.find(User.class, id);
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setAge(user.getAge());
        em.merge(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        em.remove(em.find(User.class, id));
    }
}
