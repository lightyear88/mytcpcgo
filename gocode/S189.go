package main

func rotate(nums []int, k int) {
	n := len(nums)
	if n == 0 {
		return
	}

	k = k % n
	if k == 0 {
		return
	}
	temp := make([]int, n)
	for i := 0; i < n; i++ {
		temp[(i+k)%n] = nums[i]
	}
	copy(nums, temp)
}
