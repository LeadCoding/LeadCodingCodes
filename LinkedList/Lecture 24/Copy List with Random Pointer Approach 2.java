/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// In Approach - 2, we will not be using a extra Map to store the copy of every Node from the Original LinkedList. Instead, we will be modifying the existing connections only.
//    For every original Node, we will make it's Next pointer point to it's Deep Copy.
//    And it's Deep Copy Next pointer should point to Original Node's Next.
//    For example - A(Original Node) , A'' (Deep Copy Of A) , A Next --> B
//    After modification, the LinkedList will look like:
//    A Next --> A'' ;
//    A'' Next -- B ;
//    To clarify further, please watch the YouTube Video. This modfications will help us to get the random pointers of every original node very easily.

class Solution {

    // CopyList() function will help us to create a Deep Copy of the given LinkedList

    public void copyList(Node head) {

        // Temp pointer will initally point to Head and help us to iterate over the LinkedList

        Node temp = head;

        // N pointer will store the Address of Head's Next initally


        Node n = head.next;

        while (temp != null) {

             // We create a copy of the original Node using Temp's Value

            Node copy = new Node(temp.val);

            // Temp's Next pointer should point to it's Copy

            temp.next = copy;

            // Temp's Copy should point to N(Temp's Next)

            copy.next = n;

            // Temp moves to it's next node

            temp = n;

            if (n != null) {

             // If N is not NULL, we make N move to it's Next Node

                n = n.next;
            }
        }
    }

    // handleRandom() will help us to change the Random connections of the Copied LinkedList


    public void handleRandom(Node head) {

        // Temp pointer is used to iterate over the given LinkedList

        Node temp = head;

        while (temp != null) {

        // Temp's Next points to Copied Node of Temp
        // Temp's Random's Next points to the Copied Node to whom Temp's was initally pointing 
        // So we are changing the connections as discussed in the lecture
        // We need to make sure Temp's Random doesn't point to NULL

            if (temp.random != null) {

                temp.next.random = temp.random.next;

            }
            temp = temp.next.next;

        }
    }

    // detach() function will help us to seperate out the Two LinkedLists


    public Node detach(Node head) {

        // Dummy Node is used to get the Head of the Copied LinkedList very easily


        Node dummy = new Node(-1);

        // Tail pointer is used to make connections of the Copied LinkedList


        Node tail = dummy;

        Node temp = head;

        while (temp != null) {

            // Tail's Next pointer should point to Copied Node of Temp(pointed by Temp's Next)

            tail.next = temp.next;
       
            // We move Tail to Tail's Next Node


            tail = tail.next;

            // Temp's Next should point to Tail's Next

            temp.next = tail.next;

            // We move Temp to it's Next Node

            temp = temp.next;
        }

        // At the end, we return Dummy's Next pointing to the Head of the Copied LinkedList

        return dummy.next;

    }

    public Node copyRandomList(Node head) {

        if (head == null) {

             // If Head is NUll, we directly return NULL

            return null;

        }

        // We call the functions accordingly

        copyList(head);

        handleRandom(head);

        return detach(head);

    }

}

/* 
Time Complexity:  O(N)
Space Complexity: O(1)
*/
