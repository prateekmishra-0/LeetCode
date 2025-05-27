/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cn = dummy;
        ListNode t1 = l1;
        ListNode t2 = l2;
        int c = 0;
        while(t1 != null || t2 != null){
            int sum = c;
            if(t1!=null) sum = sum+t1.val;
            if(t2!=null) sum = sum+t2.val;
            ListNode temp = new ListNode(sum%10);
            cn.next = temp;
            cn = cn.next;
            c = sum/10;
            if(t1!=null) t1 = t1.next;
            if(t2!=null) t2 = t2.next;    
        }
        if(c!=0){
            ListNode temp = new ListNode(c);
            cn.next = temp;
        }
        return dummy.next;
        }
}