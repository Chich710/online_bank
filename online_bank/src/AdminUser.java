import java.util.HashMap;
import java.util.Map;

public class AdminUser extends User implements LogOutUserInterface {
    public AdminUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void logOut() {
        System.out.println("Администратор " + this.userName + " вышел");
        LoginMenu.show();
    }

    public void showAllUsers() {
        CustomerUser[] customers = Users.getCustomers();
        if (customers.length == 0) {
            System.out.println("Нет активных пользователей");
        } else {
            System.out.println("");
            int i = 1;
            for (CustomerUser customer : customers) {
                System.out.println(i + " - " + customer.userName);
                i++;
                customer.showAccounts();
            }
        }
    }

    public Map<Integer, CustomerUser> showCustomers() {
        Map<Integer, CustomerUser> map = new HashMap<>();
        CustomerUser[] customers = Users.getCustomers();
        if (customers.length == 0) {
            System.out.println("Нет активных пользователей");
        } else {
            System.out.println("");
            int i = 1;
            for (CustomerUser customer : customers) {
                map.put(i, customer);
                System.out.println(i + " - " + customer.userName);
                i++;
            }
        }
        return map;
    }
}
