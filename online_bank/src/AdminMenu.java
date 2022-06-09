import java.util.Map;
import java.util.Scanner;

public class AdminMenu {
    private AdminUser admin;

    public AdminMenu(AdminUser admin) {
        this.admin = admin;
    }

    public void adminMainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Главное меню");
        System.out.println("-------------------------");
        System.out.println("1 - Просмотр счетов пользователя");
        System.out.println("2 - Просмотр баланс счетов пользователя");
        System.out.println("3 - Просмотр всех транзакций пользователя");
        System.out.println("4 - Просмотр транзакций счёта пользователя");
        System.out.println("5 - Выйти");

        int selection = 0;

        try {
            selection = input.nextInt();
        } catch (Exception e) {
            System.out.println("Необходимо ввести число");
            adminMainMenu();
        }

        switch (selection) {
            case 1:
                System.out.println("1");
                admin.showAllUsers();
                adminMainMenu();
                break;
            case 2:
                System.out.println("2");
                userAccounts();
                break;
            case 3:
                System.out.println("3");
                userTransactions();
                break;
            case 4:
                System.out.println("4");
                accountTransactions();
                break;
            case 5:
                admin.logOut();
            default:
                System.out.println("Неверный пункт меню");
                adminMainMenu();
        }
    }

    private void userTransactions() {
        Map<Integer, CustomerUser> customers = admin.showCustomers();
        System.out.println("Выберите пользователя ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        customers.get(selection).showTransactions();
        adminMainMenu();
    }

    private void userAccounts() {
        Map<Integer, CustomerUser> customers = admin.showCustomers();
        System.out.println("Выберите пользователя ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        customers.get(selection).showAccounts();
        adminMainMenu();
    }

    private void accountTransactions() {
        Map<Integer, CustomerUser> customers = admin.showCustomers();
        System.out.println("Выберите пользователя ");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        CustomerUser customer = customers.get(selection);
        Map<Integer, Account> accounts = customer.showAllAccounts();
        input = new Scanner(System.in);
        selection = input.nextInt();
        Account account = accounts.get(selection);
        account.showTransactions();
        adminMainMenu();
    }
}