package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model,int series) {
      Session sess = sessionFactory.getCurrentSession();
      TypedQuery<User> query=sess.createQuery(
              "from User u LEFT JOIN FETCH u.car where  u.car.model=:modelName and u.car.series=:seriesId");
      query.setParameter("modelName",model);
      query.setParameter("seriesId",series);
      try {
         return query.getSingleResult();
      }catch(NoResultException e){
         return null;
      }
   }


}
