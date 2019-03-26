package main

import (
	"fmt"
)

/**
 * goto下边的不会在执行了

 * author chengzb
 * date 2019-03-18 16:55:59
 */
func main() {
	fmt.Println("=====")
	goto send
	fmt.Println("456")
send:
	fmt.Println("123")
}
