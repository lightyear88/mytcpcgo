package main

func maxSubArray(nums []int) int {
	currentMax, globalMax := nums[0], nums[0]

	for i := 1; i < len(nums); i++ {
		currentMax = max(nums[i], currentMax+nums[i])
		globalMax = max(currentMax, globalMax)
	}
	return globalMax
}
