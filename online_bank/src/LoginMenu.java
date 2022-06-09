import java.util.Scanner;

public class LoginMenu {
    public static void show() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Вход");
        System.out.println("-------------------------");
        System.out.print("Введите логин: ");
        String userName = input.nextLine();
        System.out.print("Введите пароль: ");
        String password = input.nextLine();

        AdminUser admin = Users.getAdmin();
        CustomerUser[] customers = Users.getCustomers();
        if (userName.equals(admin.userName) && password.equals(admin.password)) {
            AdminMenu adminMenu = new AdminMenu(admin);
            adminMenu.adminMainMenu();
        } else {
            for (CustomerUser customer : customers) {
                if (userName.equals(customer.userName) && password.equals(customer.password)) {
                    CustomerMenu customerMenu = new CustomerMenu(customer);
                    customerMenu.mainMenu();
                }
            }
            System.out.println("Неверный логин или пароль");
            show();
        }
    }
}