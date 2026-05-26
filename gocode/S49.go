package main

import (
	"golearn/utils"
)

func groupAnagrams(strs []string) [][]string {
	groups:=make(map[string][]string)

	for _,str :=range strs{
		key:=utils.SortString(str)
		groups[key]=append(groups[key], str)
	}

	result:=make([][]string,len(groups))

	for _,group:=range groups{
		result=append(result, group)
	}
	
	return result
}
