package main

func lengthOfLongestSubstring(s string) int {
	lastOccurred := make(map[byte]int)
	start := 0
	maxLength := 0

	for end := 0; end < len(s); end++ {
		ch := s[end]
		if lastIdx, ok := lastOccurred[ch]; ok && lastIdx >= start {
			start = lastIdx + 1
		}
		lastOccurred[ch] = end

		currentLength := end - start + 1
		if currentLength > maxLength {
			maxLength = currentLength
		}
	}
	return maxLength
}
