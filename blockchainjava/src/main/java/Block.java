import com.google.gson.Gson;
import util.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Block {

    private long timestamp;
    private List<Transaction> transactions;
    private String previousHash = "";
    private String hash;
    private int nounce = 0;
    private MessageDigest sha256;

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
        try {
            Gson gson = new Gson();
            return Util.toBytesHexString(sha256.digest((previousHash + timestamp + gson.toJson(transactions) + nounce).getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void mineBlock(int difficulty) {
        while (!hash.substring(0, difficulty).equals(Util.arrayToString(new int[difficulty]))) {
            nounce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getNounce() {
        return nounce;
    }

    public void setNounce(int nounce) {
        this.nounce = nounce;
    }
}
