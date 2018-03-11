import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Blockchain liteCoin = new Blockchain();
        liteCoin.createTransaction(new Transaction("address1", "address2", 100.00));
        liteCoin.createTransaction(new Transaction("address2", "address1", 50.00));

        liteCoin.minePendingTransactions("arimdor");
        System.out.println("Balance of arimdor is "+ liteCoin.getBalanceOfAddress("arimdor"));
    }
}
