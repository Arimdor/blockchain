package main

import (
	"fmt"
	"time"
)

const DIFFICULTY = 7
const MINING_REWARD = 12

type Blockchain struct {
	chain               []*Block
	pendingTransactions []*Transaction
}

func (bc *Blockchain) initBlockchain() {
	bc.chain = append(bc.chain, createGenesisBlock())
}

func createGenesisBlock() *Block {
	return newBlock(1520669653, nil)
}

func (bc *Blockchain) minePendingTransactions(address string)  {
	fmt.Println("Mining plase wait...")
	bc.pendingTransactions = append(bc.pendingTransactions, newTransaction("system",address,MINING_REWARD))
	var block = newBlock(int(time.Now().Unix()),bc.pendingTransactions)
	block.mineBlock(DIFFICULTY)
	bc.chain= append(bc.chain, block)
}

func (bc *Blockchain) createTransactions(transaction *Transaction)  {
	bc.pendingTransactions = append(bc.pendingTransactions, transaction)
}
