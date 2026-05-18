package main

import "fmt"

func twoSum(nums []int, target int) []int {
	numsMap := make(map[int]int)

	for index, val := range nums {
		another := target - val

		if pos, ok := numsMap[another]; ok {
			return []int{pos, index}
		}
		numsMap[val] = index
	}
	return []int{}

}
func main() {
	// 测试用例1
	nums1 := []int{2, 7, 11, 15}
	target1 := 9
	fmt.Printf("测试用例1结果：%v\n", twoSum(nums1, target1)) // [0 1]

	// 测试用例2
	nums2 := []int{3, 2, 4}
	target2 := 6
	fmt.Printf("测试用例2结果：%v\n", twoSum(nums2, target2)) // [1 2]
}
