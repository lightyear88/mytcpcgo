package main

func trap(height []int) int {
	left,right:=0,len(height)-1
	lm,rm,result:=0,0,0
	for left<=right{
		if lm<rm{
			result+=max(lm-height[left],0)
			lm=max(lm,height[left])
			left++
		}else{
			result+=max(rm-height[right],0)
			rm=max(rm,height[right])
			right--
		}
	}
	return result
}