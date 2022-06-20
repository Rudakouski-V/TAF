package dbEntities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DataBaseService;

import java.sql.ResultSet;

public class CustomersTable {
    Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    DataBaseService dataBaseService;

    public CustomersTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void dropTable() {
        logger.info("...Deleting the customers`s table...");
        String dropTableCustomersSQL = "DROP TABLE Customers;";

        dataBaseService.executeSQL(dropTableCustomersSQL);
    }

    public void createCustomersTable() {
        logger.info("...Creating the customers`s table...");

        String createTableSQL = "CREATE TABLE Customers (" +
                "ID SERIAL PRIMARY KEY, " +
                "FirstName CHARACTER VARYING(30), " +
                "LastName CHARACTER VARYING(30), " +
                "Email CHARACTER VARYING(30), " +
                "Age INTEGER" +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void addCustomer(String firstName, String lastName, String email, int age) {
        logger.info("...Adding a record to the table...");

        String insertTableSQL = "INSERT INTO public.Customers(" +
                "firstname, lastname, email, age)" +
                "VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', " + age + ");";

        dataBaseService.executeSQL(insertTableSQL);
    }

    public ResultSet getCustomers() {
        String selectSQL = "SELECT * FROM public.Customers ORDER BY id ASC;";

        return dataBaseService.executeQuery(selectSQL);
    }

    public ResultSet getCustomerById(int id) {
        String selectSQL = "SELECT * FROM public.Customers WHERE ID = " + id + " ORDER BY id ASC;";

        return dataBaseService.executeQuery(selectSQL);
    }
}
