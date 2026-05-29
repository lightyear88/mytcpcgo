package main

func swapPairs(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	one := head
	two := one.Next
	three := two.Next

	two.Next = one
	one.Next = swapPairs(three)
	return two
}
