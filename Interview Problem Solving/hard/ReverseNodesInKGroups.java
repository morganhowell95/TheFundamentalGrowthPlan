/*
*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
*If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
*You may not alter the values in the nodes, only nodes itself may be changed.
*Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/


public class ReverseNodesInKGroups {
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
    public ListNode reverseKGroup(ListNode head, int k) {
        int tick = 1;
        ListNode tail = head;
        ListNode finalHead = head;
        ListNode oldHead = head;
        boolean start = true;
        
        if(head == null || head.next == null || k==0 || k==1) {
            return head;
        } 
        
        while(tail != null && tail.next != null) {
            //iterate up unto ending point that needs to be reversed
            while(tick%k != 0 && tail.next != null) {
                tail = tail.next;
                tick++;
            }
            if(tick%k == 0) {
                //keep reference to head of next unreversed section
                ListNode headOfNextRound = tail.next;
                //we need reference of the front of new list as well as back, because we connect new back to next section
                //new back will be old front
                //new front will be old back
                //{newHead, newBack} - first newBack is the head of finalList
                ListNode prevHead = head;
                head = auxReverse(head, headOfNextRound, headOfNextRound);
                //first pass, set the finalHead to the first section reversed
                if(start) {
                    finalHead = head;
                    start = !start;
                } else {
                    oldHead.next = head;
                    oldHead = prevHead;
                }
                //reset to next section to be reversed
                head = headOfNextRound;
                tail = headOfNextRound;
                tick++;
            }
        }
        return finalHead;
    }
    
    public ListNode auxReverse(ListNode head, ListNode prev, ListNode quitRef) {
        if(head == null || head == quitRef) {
            return prev;
        }
        ListNode tmp = head.next;
        head.next = prev;
        prev = head;
        head = tmp;
        return auxReverse(head, prev, quitRef);
    }
}


