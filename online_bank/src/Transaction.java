public class Transaction {
    private Account beneficiaryAccount;
    private Account senderAccount;
    private double amount;

    public Transaction(Account beneficiaryAccount, Account senderAccount, double amount){
        this.beneficiaryAccount = beneficiaryAccount;
        this.senderAccount = senderAccount;
        this.amount = amount;
    }

    public Account getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public double getAmount() {
        return amount;
    }
}
