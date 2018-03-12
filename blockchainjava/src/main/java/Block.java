import com.google.gson.Gson;
import util.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class Block {

    private long timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private int nounce;
    private MessageDigest sha256;
    private Gson gson = new Gson();


    Block(long timestamp, List<Transaction> transactions) {
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.hash = calculateHash();
    }

    public String calculateHash() {

        return Util.toBytesHexString(sha256.digest((previousHash + timestamp + gson.toJson(transactions) + nounce).getBytes(StandardCharsets.UTF_8)));
    }

    public void mineBlock(int difficulty) {
        long start = new Date().getTime();
        long finish;
        while (!hash.substring(0, difficulty).equals(Util.arrayToString(new int[difficulty]))) {
            nounce++;
            hash = calculateHash();
        }
        finish = new Date().getTime();
        System.out.println((nounce / ((finish - start) / 1000f)) + " h/s in " + (finish - start) / 1000f + " seg.");
        System.out.println("Block mined: " + hash);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
