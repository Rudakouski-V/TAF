package dao;

import dbEntities.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CustomerDao {
    public Customer findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Customer.class, id);
    }

    public void save(Customer user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(Customer user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(Customer user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<Customer> findAll() {
        List<Customer> customersList = (List<Customer>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Customer").list();
        return customersList;
    }
}
