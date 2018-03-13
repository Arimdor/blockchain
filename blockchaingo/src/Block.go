package main

import (
	"crypto/sha256"
	"fmt"
	"time"
)

type Block struct {
	timestamp    int
	transactions []*Transaction
	previousHash string
	hash         string
	nounce       int
}

func newBlock(timestamp int, transactions []*Transaction, ) *Block {
	return &Block{timestamp, transactions, "", "1234567", 0}
}

func generateHash(block *Block) string {
	var crypto = sha256.New()
	var blockByte = fmt.Sprintf("%v", block)
	crypto.Write([]byte (blockByte))
	return fmt.Sprintf("%x", crypto.Sum(nil))
}

func (block *Block) mineBlock(difficulty int) {
	var startTime = time.Now()
	for !(block.hash[0:difficulty] == "0000000") {
		block.nounce ++
		block.hash = generateHash(block)
	}
	var total = time.Now().Sub(startTime)
	var hps, _ = fmt.Printf("%.2f", float64(block.nounce)/(total.Seconds()))
	fmt.Println(hps, "h/s in", total, "seg.")
	fmt.Println("Block mioned:", block.hash)

}
