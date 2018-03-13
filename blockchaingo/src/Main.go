package main

import "fmt"

func main() {
	var liteCoin = new(Blockchain)
	liteCoin.initBlockchain()
	liteCoin.createTransactions(newTransaction("address1","address2",100))
	liteCoin.createTransactions(newTransaction("address2","address1",50))

	liteCoin.minePendingTransactions("arimdor")
	fmt.Println("Balance of arimdor is not implemented yet.")
}
