/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> ll = new ArrayList<>();
        for(ListNode head : lists){
            while(head!=null){
                ll.add(head.val);
                head = head.next;
            }
        }
        Collections.sort(ll);
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for(int val : ll){
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
}