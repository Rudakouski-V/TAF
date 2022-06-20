package tests.db;

import dbEntities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import services.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest extends BaseDBTest {

    Logger logger = LoggerFactory.getLogger(BaseDBTest.class);

    @Test
    public void firstTest() {
        logger.info("...Test is started...");

        ResultSet rs = customersTable.getCustomers();

        try {
            while (rs.next()) {
                String userid = rs.getString("ID");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                logger.info("userid: " + userid);
                logger.info("firstname: " + firstName);
                logger.info("lastname: " + lastName);
                logger.info("email: " + email);
                logger.info("age: " + age);
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        }

        logger.info("Test is completed!");
    }

    @Test
    public void getCustomerByIdTest() {
        logger.info("...Test is started...");

        ResultSet rs = customersTable.getCustomerById(2);

        try {
            while (rs.next()) {
                String userid = rs.getString("ID");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                logger.info("userid: " + userid);
                logger.info("firstname: " + firstName);
                logger.info("lastname: " + lastName);
                logger.info("email: " + email);
                logger.info("age: " + age);
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        }

        logger.info("Test is completed!");
    }

    @Test
    public void hibernateTest() {
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer("Vasya", "Petrov", "xxx@xxx.com", 30);
        customerService.saveUser(customer);

        List<Customer> customerList = customerService.findAllUsers();

        for (Customer cust : customerList) {
            logger.info(cust.toString());
        }
    }
}
