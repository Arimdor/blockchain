package main

type Transaction struct {
	fromAddress, toAddress string
	amount                 int
}

func newTransaction(fromAddres string, toAddress string, amount int) *Transaction {
	return &Transaction{fromAddres,toAddress,amount}
}