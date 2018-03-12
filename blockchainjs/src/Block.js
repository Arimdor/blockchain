const sha256 = require('crypto-js/sha256');

class Block {
    constructor(timestamp, transactions, previousHash = '') {
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
        this.nonce = 0;
    }

    calculateHash() {
        return sha256(this.previousHash + this.timestamp + JSON.stringify(this.transactions) + this.nonce).toString();
    }

    mineBlock(difficulty) {
        let start = new Date().getTime();
        let finish;
        while (this.hash.substring(0, difficulty) !== Array(difficulty + 1).join("0")) {
            this.nonce++;
            this.hash = this.calculateHash();
        }
        finish = new Date().getTime();
        console.log(`${Math.round(this.nonce / ((finish - start) / 1000))} h/s in ${(finish - start) / 1000} seg.`);
        console.log(`Block mined: ${this.hash}`);
    }
}

Block.sha256 =

module.exports = Block;