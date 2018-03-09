const sha256 = require('crypto-js/sha256');

class Block {
    constructor(index, timestamp, data, previousHash = '') {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
        this.nonce = 0;
    }

    calculateHash() {
        return sha256(this.index + this.previusHash + this.timestamp + JSON.stringify(this.data) + this.nonce).toString();
    }

    mineBlock(difficulty) {
        while (this.hash.substring(0, difficulty) !== Array(difficulty + 1).join("0")) {
            this.nonce++;
            this.hash = this.calculateHash();
        }
        console.log(`Block mined: ${this.hash}`);
    }
}

class Blockchain {
    constructor() {
        this.chain = [Blockchain.createGenesisBlock()];
        this.difficulty = 5;
    }

    static createGenesisBlock() {
        return new Block(0, "01/01/2017", "Genesis block", "0");
    }

    getLastBlock() {
        return this.chain[this.chain.length - 1];
    }

    addBlock(newBlock) {
        newBlock.previousHash = this.getLastBlock().hash;
        newBlock.mineBlock(this.difficulty);
        this.chain.push(newBlock);
    }

    isChainValid() {
        for (let i = 1; i < this.chain.length; i++) {
            const currentBlock = this.chain[i];
            const previousBlock = this.chain[i - 1];

            if (currentBlock.hash !== currentBlock.calculateHash()) {
                return false;
            }
            if (currentBlock.previousHash !== previousBlock.hash) {
                return false;
            }
        }
        return true
    }
}

let coin = new Blockchain();
console.log("Mining block 1....");
coin.addBlock(new Block(1, "09/03/2018", {amount: 4}));

console.log("Mining block 2....");
coin.addBlock(new Block(2, "09/03/2018", {amount: 5}));

console.log("Mining block 3....");
coin.addBlock(new Block(3, "09/03/2018", {amount: 10}));

console.log("Mining block 4....");
coin.addBlock(new Block(4, "09/03/2018", {amount: 15}));

