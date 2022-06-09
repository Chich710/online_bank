import java.util.Map;
import java.util.Scanner;

public class CustomerMenu {
    private CustomerUser user;

    public CustomerMenu(CustomerUser user) {
        this.user = user;
    }

    public void mainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Главное меню");
        System.out.println("-------------------------");
        System.out.println("1 - Просмотр баланса счета");
        System.out.println("2 - Переводы");
        System.out.println("3 - Открыть счёт");
        System.out.println("4 - Закрыть счёт");
        System.out.println("5 - Выйти");

        int selection = 0;

        try {
            selection = input.nextInt();
        } catch (Exception e) {
            System.out.println("Необходимо ввести число");
            mainMenu();
        }

        switch (selection) {
            case 1:
                System.out.println("1");
                accountViewMenu();
                break;
            case 2:
                System.out.println("2");
                transferMenu();
                break;
            case 3:
                System.out.println("3");
                accountOpenMenu();
                break;
            case 4:
                System.out.println("4");
                accountCloseMenu();
                break;
            case 5:
                user.logOut();
            default:
                System.out.println("Неверный пункт меню");
                mainMenu();
        }
    }

    private void accountViewMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Просмотр баланса счета");
        System.out.println("-------------------------");
        Map<Integer, Account> accountsMap = user.showAllAccounts();
        if (accountsMap.size() == 0) {
            mainMenu();
        }

        int selection = 0;
        try {
            selection = input.nextInt();
        } catch (Exception e) {
            System.out.println("Необходимо ввести число");
            mainMenu();
        }
        if(accountsMap.containsKey(selection)) {
            Account selectedAccount = accountsMap.get(selection);
            System.out.println(selectedAccount.accountBalance + " " + selectedAccount.accountCurrency);
            System.out.println();
        } else {
            System.out.println("Неверный номер счёта");
        }

        mainMenu();
    }

    private void transferMenu() {
        Map<Integer, Account> userAccounts = user.showAllAccounts();
        if (userAccounts.size() > 0) {
            Scanner input = new Scanner(System.in);
            int selection = 0;
            try {
                selection = input.nextInt();
            } catch(Exception e) {
                System.out.println("Необходимо ввести число");
                transferMenu();
            }

            if (userAccounts.containsKey(selection)) {
                Account selectedSenderAccount = userAccounts.get(selection);
                System.out.println("Введите имя получателя");
                input = new Scanner(System.in);
                String selectedBeneficiary = input.nextLine().trim();

                CustomerUser[] customers = Users.getCustomers();
                for (CustomerUser beneficiary : customers) {
                    if (beneficiary.userName.equals(selectedBeneficiary)) {
                        if (beneficiary.accounts.size() > 0) {
                            Map<Integer, Account> beneficiaryAccounts = beneficiary.showAllAccounts();
                            if (beneficiaryAccounts.size() == 0) {
                                mainMenu();
                            }
                            int selectedBeneficiaryAccountNum = 0;
                            try {
                                selectedBeneficiaryAccountNum = input.nextInt();
                            } catch(Exception e) {
                                System.out.println("Необходимо ввести число");
                                transferMenu();
                            }
                            if (beneficiaryAccounts.containsKey(selectedBeneficiaryAccountNum)) {
                                System.out.print("Введите сумму для перевода: ");
                                double amount = 0;
                                try {
                                    amount = input.nextDouble();
                                } catch (Exception e) {
                                    System.out.println("Необходимо ввести число для указания суммы");
                                    mainMenu();
                                }
                                if (amount < 0) {
                                    System.out.println("Необходимо ввести положительно число");
                                    mainMenu();
                                } else if (amount <= selectedSenderAccount.accountBalance) {
                                    Account selectedBeneficiaryAccount = beneficiaryAccounts.get(selectedBeneficiaryAccountNum);
                                    user.transfer(beneficiary, selectedBeneficiaryAccount, selectedSenderAccount, amount);
                                    System.out.println("Перевод выполнен");
                                    mainMenu();
                                } else {
                                    System.out.println("На вашем счёте недостаточно средств");
                                    mainMenu();
                                }
                            } else {
                                System.out.println("Необходимо выбрать счёт из списка по порядковому номеру. Пока будешь выбирать несуществующие пункты, будешь в главном меню жить");
                                mainMenu();
                            }
                        } else {
                            System.out.println("У получателя нет активных счетов");
                            mainMenu();
                        }
                    }
                }

                System.out.println("Данного пользователя нет в системе");
                mainMenu();
            } else {
                System.out.println("Неверный пункт меню");
                mainMenu();
            }
        } else {
            System.out.println("У вас нет активных счетов");
            mainMenu();
        }
        mainMenu();
    }

    private void accountOpenMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Открыть счет");
        System.out.println("-------------------------");
        System.out.println("1 - Рублевый счет RU");
        System.out.println("2 - Долларовый счет USD");
        System.out.println("3 - Евровый счет EUR");
        System.out.println("4 - Назад");

        int selection = 0;
        try {
            selection = input.nextInt();
        } catch (Exception e) {
            System.out.println("Необходимо ввести число");
            mainMenu();
        }

        switch (selection) {
            case 1:
                System.out.println("1");
                user.newAccountOpen("RUB/РУБ");
                System.out.println("Открыт рублевый счёт");
                mainMenu();
                break;
            case 2:
                System.out.println("2");
                user.newAccountOpen("USD/ДОЛЛАР");
                System.out.println("Открыт долларовый счёт");
                mainMenu();
                break;
            case 3:
                System.out.println("3");
                user.newAccountOpen("EUR/ЕВРО");
                System.out.println("Открыт евровый счёт");
                mainMenu();
                break;
            case 4:
                System.out.println("4");
                mainMenu();
                break;
            default:
                System.out.println("Неверный пункт меню");
                mainMenu();
        }
    }

    private void accountCloseMenu() {
        Map<Integer, Account> userAccounts = user.showAllAccounts();
        if (userAccounts.size() == 0) {
            mainMenu();
        }
        Scanner input = new Scanner(System.in);

        int selection = 0;
        try {
            selection = input.nextInt();
        } catch (Exception e) {
            System.out.println("Необходимо ввести число");
            mainMenu();
        }

        if (userAccounts.containsKey(selection)) {
            Account selectedAccount = userAccounts.get(selection);
            if (selectedAccount.accountBalance == 0) {
                user.accounts.remove(selectedAccount);
                System.out.println("Счёт закрыт");
            } else {
                System.out.println("Закрыть можно только счёт с нулевым балансом");
            }
        } else {
            System.out.println("Неверный пункт меню");
            mainMenu();
        }
        mainMenu();
    }
}