package main

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{}
	cur := dummy

	carry := 0
	sum := 0
	for l1 != nil || l2 != nil || carry != 0 {
		sum = carry
		if l1 != nil {
			sum += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			sum += l2.Val
			l2 = l2.Next
		}
		temp := sum % 10
		cur.Next = &ListNode{Val: temp}
		cur = cur.Next
		carry = sum / 10
	}
	return dummy.Next

}
