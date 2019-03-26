package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.Handle("/src", http.FileServer(http.Dir("./src")))

	s := http.ListenAndServe(":8080", nil)
	//s := http.Server{
	// Addr:":8080",
	////Handler:        base.GetRouter(),
	//ReadTimeout:    100 * time.Second,
	//WriteTimeout:   100 * time.Second,
	//MaxHeaderBytes: 1 << 20,
	//}

	fmt.Printf("service init successful %s \n ", s)
}
