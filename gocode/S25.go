package main

func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{}
	dummy.Next = head
	pre := dummy
	end := dummy
	for end.Next != nil {
		for i := 0; i < k && end.Next != nil; i++ {
			end = end.Next
		}
		if end == nil {
			break
		}
		nextGroup := end.Next
		end.Next = nil
		start := pre.Next
		pre.Next = reverseList(start)
		start.Next = nextGroup
		pre = start
		end = pre
	}
	return dummy.Next
}
