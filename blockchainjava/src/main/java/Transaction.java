public class Transaction {
    private String fromAddress;
    private String toAddress;
    private Double amount;

    public Transaction(String fromAddress, String toAddress, Double amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public Double getAmount() {
        return amount;
    }

}
