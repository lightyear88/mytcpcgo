package main



func longestConsecutive(nums []int) int {
	if len(nums)==0{
		return 0
	}
	 set:=make(map[int]struct{})
     max:=1
	 for _,num:=range nums{
		set[num]=struct{}{}
	 }

	 for key:=range(set){
		if _,ok:=set[key-1];ok{
			continue
		}
		temp:=1
		current:=key
		for{
			if _,ok:=set[current+1];ok{
				temp++
				current++
			}else{
				break
			}
		}
		if temp>max{
			max=temp
		}
	 }
	 return max
	 
}