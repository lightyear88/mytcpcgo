package main



func maxArea(height []int) int {
	n:=len(height)
    
	maxArea:=0
	for left,right:=0,n-1;left<right;{
		currArea:=(right-left) *  min(height[left], height[right])
		if currArea>maxArea{
			maxArea=currArea
		}
		if(height[right]<height[left]){
			right--
		}else{
			left++
		}
	}
	return maxArea
}