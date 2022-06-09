public class Users {
    private static CustomerUser[] customers = new CustomerUser[]{
            new CustomerUser("Эстобан", "test_1"),
            new CustomerUser("Сюзанна", "test_2"),
            new CustomerUser("Архип", "test_3"),
    };

    private static AdminUser admin = new AdminUser("admin", "test_admin");

    public static CustomerUser[] getCustomers() {
        return customers;
    }

    public static AdminUser getAdmin() {
        return admin;
    }
}
