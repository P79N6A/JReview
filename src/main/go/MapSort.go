package main

import (
	"fmt"
	"sort"
)

func main() {
	////map排序
	//TestTock()
	//arr := []int{1, 3, 5, 7, 9, 88, 99, 2, 4, 6, 8, 10, 32, 321, 32, 3, 45, 65, 43, 1, 3241, 32, 14, 32, 5, 43, 11, 46, 65, 4, 24, 1, 32, 132, 143, 214, 3214}
	////	冒泡排序
	//now1 := time.Now()
	//for i := 0; i < 2000; i++ {
	//	BubbleSort(arr)
	//}
	//now2 := time.Now()
	//fmt.Println("冒泡排序： ", now2.Sub(now1))
	//now3 := time.Now()
	////	快速排序
	//for i := 0; i < 2000; i++ {
	//	QuicklySort(arr)
	//}
	//now4 := time.Now()
	//fmt.Println("快速排序： ", now4.Sub(now3))
}

func BubbleSort(arr []int) {
	length := len(arr)
	for i := 0; i < length-1; i++ {
		for j := 0; j < length-i-1; j++ {
			if arr[j] > arr[j+1] {
				arr[j], arr[j+1] = arr[j+1], arr[j]
			}
		}
	}
	//fmt.Println(arr)
}

func QuicklySort(arr []int) {
	length := len(arr)
	for i := 0; i < length; i++ {
		for j := i + 1; j < length; j++ {
			if arr[i] > arr[j] {
				arr[i], arr[j] = arr[j], arr[i]
			}
		}
	}
	//fmt.Println(arr)
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
