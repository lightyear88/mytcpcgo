package main

func findAnagrams(s string, p string) []int {
	var res []int
	lenS, lenP := len(s), len(p)
	if lenS < lenP {
		return res
	}
	pCount := [26]int{}
	sCount := [26]int{}
	for i := 0; i < lenP; i++ {
		pCount[p[i]-'a']++
	}
	for i := 0; i < lenS; i++ {
		sCount[s[i]-'a']++
		if i >= lenP {
			sCount[s[i-lenP]-'a']--
		}

		if i >= lenP-1 && sCount == pCount {
			res = append(res, i-lenP+1)
		}
	}
	return res

}
