let Block = require('./Block');
let Transaction = require('./Transaction');

class Blockchain {
    constructor() {
        this.chain = [Blockchain.createGenesisBlock()];
        this.difficulty = 4;
        this.pendingTransactions = [];
        this.miningReward = 12.5;
    }

    static createGenesisBlock() {
        return new Block("01/01/2017", "Genesis block", "0");
    }

    getLastBlock() {
        return this.chain[this.chain.length - 1];
    }

    minePendingTransactions(miningRewardAddress) {
        let startTime = new Date().getTime();
        let finishTime;

        console.log(`Starting mining plase wait...`);
        this.pendingTransactions = [
            new Transaction('coin-system', miningRewardAddress, this.miningReward)
        ];
        let block = new Block(Date.now(), this.pendingTransactions);
        block.mineBlock(this.difficulty);
        this.chain.push(block);

        finishTime = new Date().getTime();
        console.log(`Succefully mined! in ${(finishTime - startTime) / 1000} segundos.`);
    }

    createTransaction(transaction) {
        this.pendingTransactions.push(transaction);
    }

    getBalanceOfAddress(address) {
        let balance = 0;
        for (const block of this.chain) {
            for (const trans of block.transactions) {
                if (trans.fromAddress === address) {
                    balance -= trans.amount;
                }
                if (trans.toAddress === address) {
                    balance += trans.amount;
                }
            }
        }
        return balance
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

module.exports = Blockchain;