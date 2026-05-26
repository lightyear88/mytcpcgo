package main

import "sort"


func threeSum(nums []int) [][]int {
	sort.Ints(nums)
    res:=make([][]int,0)

	for k:=0;k<len(nums)-2;k++{
		if nums[k]>0{
			break
		}
		if k>0&&nums[k]==nums[k-1]{
			continue
		}
		for i,j:=k+1,len(nums)-1;i<j;{
			sum:=nums[i]+nums[j]+nums[k]
			if sum>0 {
				j--
			}else if sum<0{
				i++
			}else{
				res=append(res,[]int{nums[i],nums[j],nums[k]})
				for i<j&&nums[i]==nums[i+1]{
					i++
				}
				for i<j&&nums[j]==nums[j-1]{
					j--
				}
				i++
				j--
			}
		}
		
	}
	return  res
}