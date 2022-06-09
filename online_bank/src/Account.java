public class Account {
    protected String accountNumber;
    protected String accountCurrency;
    protected double accountBalance;
    protected CustomerUser accountOwner;

    public Account(String accountCurrency, int accountBalance, CustomerUser accountOwner) {
        this.accountNumber = generateAccountNumber();
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.accountOwner = accountOwner;
    }

    public void showTransactions(){
        int i = 1;
        System.out.println("Транзакции пользоватея:");
        for(Transaction transaction: this.accountOwner.transactions) {
            if (transaction.getSenderAccount().equals(this) || transaction.getBeneficiaryAccount().equals(this)) {
                System.out.println(i + " - отправитель: " + transaction.getSenderAccount().accountOwner.userName +
                        ", номер счета отправителя: " + transaction.getSenderAccount().accountNumber +
                        ", получатель: " + transaction.getBeneficiaryAccount().accountOwner.userName +
                        ", номер счета получателя: " + transaction.getBeneficiaryAccount().accountNumber +
                        ", сумма: " + transaction.getAmount());
                i++;
            }
        }
    }

    private static String generateAccountNumber() {
        int min = 1;
        int max = 9999;
        int number = (int) (Math.random() * (max - min)) + min;

        String newNumber = "";
        if (number < 10) {
            newNumber = "000" + number;
        } else if (number < 100) {
            newNumber = "00" + number;
        } else if (number < 1000) {
            newNumber = "0" + number;
        } else {
            newNumber = "" + number;
        }

        return newNumber;
    }
}
