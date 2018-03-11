let Transaction = require("./Transaction");
let Blockchain = require('./Blockchain');

let coin = new Blockchain();
coin.createTransaction(new Transaction("address1", "address2", 100));
coin.createTransaction(new Transaction("address2", "address1", 50));

coin.minePendingTransactions("arimdor");
console.log(`Balance of arimdor is ${coin.getBalanceOfAddress('arimdor')}`);


