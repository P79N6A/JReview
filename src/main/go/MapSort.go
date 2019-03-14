package main

import (
	"fmt"
	"sort"
)

func main() {
	TestTock()
}

func TestTock() {
	var mp = make(map[string]int)
	mp["1"] = 1
	mp["3"] = 3
	mp["2"] = 2
	mp["5"] = 5
	mp["4"] = 4
	//根据key排序
	//SortKey(mp)
	//	根据value排序
	SortValue(mp)
}

//根据value排序
func SortValue(mp map[string]int) {
	var newMp = make([]int, 0)
	var newMpKey = make([]string, 0)

	for key, value := range mp {
		newMp = append(newMp, value)
		newMpKey = append(newMpKey, key)
	}
	sort.Ints(newMp)
	for key, value := range newMp {
		fmt.Println("根据value排序后的新集合》》  key:", newMpKey[key], "    value:", value)
	}
}

//根据key排序
func SortKey(mp map[string]int) {
	var newMp = make([]string, 0)
	for key, _ := range mp {
		newMp = append(newMp, key)
	}
	sort.Strings(newMp)
	for _, key := range newMp {
		fmt.Println("根据key排序后的新集合 >>>   key:", key, "    value:", mp[key])
	}
}
