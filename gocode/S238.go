package main

func productExceptSelf(nums []int) []int {
	n := len(nums)

	left := make([]int, n)
	left[0] = 1

	for i := 1; i < n; i++ {
		left[i] = left[i-1] * nums[i]
	}
	
	right := make([]int, n)
	right[n-1] = 1
	for i := n - 2; i >= 0; i-- {
		right[i] = right[i+1] * nums[i]
	}
	answer := make([]int, n)
	for i := 0; i < n; i++ {
		answer[i] = left[i] * right[i]
	}
	return answer
}
