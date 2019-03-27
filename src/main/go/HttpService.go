package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.Handle("/src", http.FileServer(http.Dir("./src")))
	s := http.ListenAndServe(":8080", nil)
	fmt.Printf("service init successful %s \n ", s)
}
