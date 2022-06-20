package services;

import dao.CustomerDao;
import dbEntities.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();

    public CustomerService() {
    }

    public Customer findUser(int id) {
        return customerDao.findById(id);
    }

    public void saveUser(Customer user) {
        customerDao.save(user);
    }

    public void deleteUser(Customer user) {
        customerDao.delete(user);
    }

    public void updateUser(Customer user) {
        customerDao.update(user);
    }

    public List<Customer> findAllUsers() {
        return customerDao.findAll();
    }
}
