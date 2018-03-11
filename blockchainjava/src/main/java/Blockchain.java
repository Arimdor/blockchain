import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Blockchain {
    private static final int DIFFICULTY = 5;
    private static final Double MINING_REWARD = 12.5;
    private List<Block> chain = new ArrayList<>();
    private List<Transaction> pendingTransactions = new ArrayList<>();

    Blockchain() {
        this.chain.add(createGenesisBlock());
    }

    private static Block createGenesisBlock() {
        return new Block(1520669653, new ArrayList<>(Arrays.asList(new Transaction("genesis","genesis",0.0))));
    }

    public Block getLastBlock() {
        return chain.iterator().next();
    }

    public void minePendingTransactions(String address) {
        long startTime = new Date().getTime();
        long finishTime;

        System.out.println("Mining plase wait...");
        pendingTransactions.add(new Transaction("system", address, MINING_REWARD));
        Block block = new Block(new Date().getTime(), pendingTransactions);
        block.mineBlock(DIFFICULTY);
        chain.add(block);

        finishTime = new Date().getTime();
        System.out.println("Successfully mined! in " + ((finishTime - startTime) / 1000f) + " segundos.");
    }

    public void createTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public Double getBalanceOfAddress(String address) {
        double balance = 0;
        for (Block block : chain) {
            for (Transaction transaction : block.getTransactions()) {
                if (transaction.getFromAddress().equals(address)) {
                    balance -= transaction.getAmount();
                }
                if (transaction.getToAddress().equals(address)) {
                    balance += transaction.getAmount();
                }
            }
        }
        return balance;
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            final Block currentBlock = chain.get(i);
            final Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

}

