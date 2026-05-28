import java.util.*;

/**
 * @author QiuShui
 * @date 2026/3/30  下午3:30
 * @description
 */
@SuppressWarnings("ALL")
public class Codetop {
    private static final Random random = new Random();

    class LRUCache {
        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Node head;
        private Node tail;
        private int size;
        private int capacity;
        private HashMap<Integer, Node> cache = new HashMap<>();

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node == null) {
                Node newnode = new Node(key, value);
                cache.put(key, newnode);
                addToHead(newnode);
                size++;
                if (size > capacity) {
                    Node removednode = removeTail();
                    cache.remove(removednode.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }

        }

        private void addToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private Node removeTail() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxlen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                char d = s.charAt(left++);
                map.put(d, map.get(d) - 1);
            }
            maxlen = Math.max(maxlen, right - left);
        }
        return maxlen;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode nextGroup = end.next;
            end.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = nextGroup;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k);
    }

    private int quickSelect(int[] nums, int k) {
        int pivot = nums[random.nextInt(nums.length)];
        int[] bigger = Arrays.stream(nums).filter(n -> n > pivot).toArray();
        int[] equal = Arrays.stream(nums).filter(n -> n == pivot).toArray();
        int[] smaller = Arrays.stream(nums).filter(n -> n < pivot).toArray();

        if (k <= bigger.length) {
            return quickSelect(bigger, k);
        } else if (k > (bigger.length + equal.length)) {
            return quickSelect(smaller, k - bigger.length - equal.length);
        } else {
            return pivot;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = 1; k < nums.length - 2; k++) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) j--;
                else if (sum < 0) i++;
                else {
                    res.add(new ArrayList<>(List.of(nums[i], nums[j], nums[k])));
                    while (i < j && nums[++i] == nums[i]) ;
                    while (i < j && nums[--j] == nums[j]) ;
                }
            }
        }
        return res;

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                curr.next = list2;
                list2 = list2.next;
            } else {
                curr.next = list1;
                list1 = list1.next;
            }
            curr = curr.next;
        }
        if (list1 != null) {
            curr.next = list1;
        }
        if (list2 != null) {
            curr.next = list2;
        }
        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preLeft = dummy;
        for (int i = 0; i < left - 1; i++) {
            preLeft = preLeft.next;
        }
        ListNode leftNode = preLeft.next;
        ListNode rightNode = leftNode;
        for (int i = left; i < right; i++) {
            rightNode = rightNode.next;
        }
        ListNode afterRight = rightNode.next;
        rightNode.next = null;
        reverseList(leftNode);
        preLeft.next = rightNode;
        leftNode.next = afterRight;
        return dummy.next;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;
        int start = 0;
        int maxlen = 1;
        boolean[][] dp = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        int len = j - i + 1;
                        if (len > maxlen) {
                            maxlen = len;
                            start = i;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxlen);
    }
    public void reorderList(ListNode head) {
        if(head==null||head.next==null)return;
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode curr=head;
        while (curr!=null){
            list.add(curr);
            curr=curr.next;
        }
        int i=0;int j=list.size()-1;
        while (i<j){
            list.get(i).next=list.get(j);
            i++;
            if(i==j)break;
            list.get(j).next=list.get(i);
            j--;
        }
        list.get(i).next=null;
    }

}
