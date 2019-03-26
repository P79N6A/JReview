package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	//读文件
	//readFile()
	//	写文件
	writeFile()
}

func writeFile() {
	str := "package main \n\nimport \"fmt\" \n\nfunc main(){ \n	fmt.Println(\"Helloworld\") \n}"
	bytes := []byte(str)
	err := ioutil.WriteFile("./src/main/go/main.go", bytes, 0644)
	if err != nil {
		fmt.Println(err)
		panic(err)
	}

}

func readFile() {
	file, err := ioutil.ReadFile("/Users/czbcxy/Documents/JReview/src/main/go/MapSort.go")
	if err != nil {
		fmt.Println(err)
	}
	//fmt.Println(file) //[]byte
	str := string(file)
	fmt.Println(str)
}
