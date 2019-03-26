package main

import (
	"crypto/tls"
	"fmt"
	"net/http"
)

func main() {
	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{Transport: tr}
	for {
		resp, _ := client.Get("https://www.gsgroup.club")
		//fmt.Println(err)
		fmt.Println(resp)
		defer resp.Body.Close()
	}

}
