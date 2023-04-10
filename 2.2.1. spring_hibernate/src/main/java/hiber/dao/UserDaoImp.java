package hiber.dao;

import hiber.model.User;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   final static String GET_USER_BY_CAR = "FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";

   private SessionFactory sessionFactory;

   @Autowired
   UserDaoImp(SessionFactory sessionFactory){
      this.sessionFactory=sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery(GET_USER_BY_CAR, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> users = query.getResultList();
      return users.isEmpty() ? null : users.get(0);
   }
}
