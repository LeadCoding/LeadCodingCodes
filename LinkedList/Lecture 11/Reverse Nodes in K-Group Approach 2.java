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
/* In Approach - 2, we will NOT be using any extra Recursive Stack Space
To save space, we will use be using iterative approach to Reverse Nodes in Groups of K
Instead of using Start, here we will be using beforeStart which points to the Node before Start Pointer
We also need to store the End's Next in temp variable
After reversing, we just have to make:
a) beforeStart.next = End
b) Start.next = temp
Move beforeStart to Start & end to temp for next set of reversals
*/

class Solution {

// reverse(start , end) takes two pointers - Start and End and reverses the LinkedList from Start till End
// reverse() function uses our iterative approach to Reverse the Linked List

    private void reverse(ListNode s, ListNode e){

        // To reverse, we initalise 3 pointers:-
        // a) Previous(p) points to null
        // b) Current(c) points to Start
        // c) Next(n) points to the Next Node of Start

        ListNode p=null;
        ListNode c=s;
        ListNode n=s.next;

    // We will stop when our Previous(p) pointer becomes equal to our End Pointer


        while(p!=e){

            // We make Current's Next point to Previous 

            c.next=p;

             // We make Previous move to Current and Current moves to Next

            p=c;
            c=n;

             // Next moves to it's next node unless it's pointing to null

            if(n!=null){
                n=n.next;
            }
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {

        // If our head is equal NULL OR if our head is a Single Node or if K is equal to 1
        // In all the 3 cases, we don't need to reverse the LinkedList so we directly return head


        if(head==null || head.next==null || k==1){
            return head;
        }

        // Initally, we don't have any Node before head, so we create a Dummy Node and make beforeStart point to it

        ListNode dummy=new ListNode(-1);

        // We make Dummy's Next point to head, so that it becomes a part of the LinkedList

        dummy.next=head;

        ListNode beforeStart=dummy;

        // Our End pointer will initally start from end


        ListNode e=head;

        // i will keep track of the Number of Nodes we have traversed

        int i=0;

        while(e!=null){

            i++;

            // If (i % k) is equal to 0, it means we have travesed an entire LinkedList of Length K


            if(i%k==0){

                // Our Start pointer will start from beforeStart's Next

                ListNode s=beforeStart.next;

                // We store the Next Node of End in temp pointer, so that connections can be made again


                ListNode temp=e.next;

                 // We call the reverse() function to Reverse the LinkedList starting from Start to End
                
                reverse(s,e);

                // After reversing is done, beforeStart's next should now point to End pointer

                beforeStart.next=e;

                // Start's next should point to temp(intial End's Next)


                s.next=temp;

                // After that, beforeStart should be moved to the place of Start Pointer

                beforeStart=s;

                // End should start from temp


                e=temp;

            }else{

                // Else if we haven't covered the LinkedList of Length K, we make End move to it's Next Node

                e=e.next;

            }
        }

        // At the end, we return Dummy's Next which will be our new Head after reversing all the Nodes in Group of K

        return dummy.next;
        
    }
}


/*
Time  Complexity: O(N)
Space Complexity: O(1) 
*/