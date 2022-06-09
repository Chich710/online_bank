import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerUser extends User implements LogOutUserInterface {
    ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //список транзакций
    ArrayList<Account> accounts = new ArrayList<Account>(); //счета пользователя

    public CustomerUser(String userName, String password) {
        this.userName = userName;
        this.password = password;

        this.accounts.add(new Account("RUB/РУБ", 3000, this));
        this.accounts.add(new Account("USD/ДОЛЛАР", 3000, this));
        this.accounts.add(new Account("EUR/ЕВРО", 3000, this));
    }

    public void logOut() {
        System.out.println("Пользователь " + this.userName + " вышел");
        LoginMenu.show();
    }

    public Map<Integer, Account> showAllAccounts() {
        Map<Integer, Account> map = new HashMap<Integer, Account>();
        if (accounts.size() == 0) {
            System.out.println("У вас нет открытых счетов");
        } else {
            System.out.println("Введите порядковый номер счёта");
            int i = 1;
            for (Account account : accounts) {
                map.put(i, account);
                System.out.println(i + " - " + account.accountNumber + " " + account.accountCurrency);
                i++;
            }
        }
        return map;
    }

    public void newAccountOpen(String currency) {
        Account account = new Account(currency, 0, this);
        accounts.add(account);
    }

    public void transfer(CustomerUser beneficiary, Account selectedBeneficiaryAccount, Account senderAccount, double amount) {
        if (senderAccount.accountCurrency.equals(selectedBeneficiaryAccount.accountCurrency)) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("RUB/РУБ") && selectedBeneficiaryAccount.accountCurrency.equals("USD/ДОЛЛАР")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount / 60;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("RUB/РУБ") && selectedBeneficiaryAccount.accountCurrency.equals("EUR/ЕВРО")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount / 70;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("USD/ДОЛЛАР") && selectedBeneficiaryAccount.accountCurrency.equals("EUR/ЕВРО")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount / 1.16;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("USD/ДОЛЛАР") && selectedBeneficiaryAccount.accountCurrency.equals("RUB/РУБ")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount * 60;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("EUR/ЕВРО") && selectedBeneficiaryAccount.accountCurrency.equals("RUB/РУБ")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount * 70;
            senderAccount.accountBalance -= amount;
        } else if (senderAccount.accountCurrency.equals("EUR/ЕВРО") && selectedBeneficiaryAccount.accountCurrency.equals("USD/ДОЛЛАР")) {
            Transaction newTransaction = new Transaction(selectedBeneficiaryAccount, senderAccount, amount);
            beneficiary.transactions.add(newTransaction);
            this.transactions.add(newTransaction);
            selectedBeneficiaryAccount.accountBalance += amount * 0.85;
            senderAccount.accountBalance -= amount;
        }
    }

    public void showAccounts() {
        int i = 1;
        System.out.println("Счета пользоватея:");
        for (Account account : this.accounts) {
            System.out.println("     " + i + " - " + account.accountNumber + " " + account.accountBalance + " " + account.accountCurrency);
            i++;
        }
    }

    public void showTransactions() {
        int i = 1;
        System.out.println("Транзакции пользоватея:");
        for (Transaction transaction : this.transactions) {
            System.out.println(i + " - отправитель: " + transaction.getSenderAccount().accountOwner.userName +
                    ", номер счета отправителя: " + transaction.getSenderAccount().accountNumber +
                    ", получатель: " + transaction.getBeneficiaryAccount().accountOwner.userName +
                    ", номер счета получателя: " + transaction.getBeneficiaryAccount().accountNumber +
                    ", сумма: " + transaction.getAmount());
            i++;
        }
    }
}
