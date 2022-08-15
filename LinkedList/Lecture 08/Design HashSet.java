/* We need to design a HashSet Class which contains only Unique Values like any HashSet Class.
Our HashSet class contains the following operations:
a) void add(int key) --> Adds an element into the HashSet
b) void remove(int key) --> Removes the key element from the HashSet
c) bool contains(int key) --> Returns true if key is present in our HashSet else returns false
Instead of using unneccessary space, we can use limited space by using the concept of Hashing & Chaining
We require chaining to prevent collisions in our HashSet
*/

class MyHashSet {
    // We use a ArrayList of Linked List to store all our elements of HashSet Class
	ArrayList<LinkedList<Integer>> list;
	int size = 100;

	public MyHashSet() {
        // Inside the constructor we initialise arraylist with empty linked list
		list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			list.add(new LinkedList<Integer>());
		}
	}

        // Our hash function simply returns the index by taking modulo with list.size()
	public int hash(int key) {
		return key % list.size();
	}
    // search operation is being performed here
	public int search(int key) {
		int i = hash(key);

		LinkedList<Integer> temp = list.get(i);
		int ans = -1;

		for (int j = 0; j < temp.size(); j++) {
			if (key == temp.get(j)) {
				return j;
			}
		}
		return ans;
	}
	//Search() function returns us the position where the key is present

	public void add(int key) {
		// If the key is already present then there is no need to add again
		//Otherwise , we will find the index i where we need to add our key using hash() function
		if (search(key) == -1) {
			int i = hash(key);
			// we add the key at the end in O(1) Time Complexity 
			list.get(i).add(key);
		}
	}
	// remove method will delete the given key from our ArrayList<LinkedList<Integer>>
	public void remove(int key) {
		if (search(key) != -1) {
			int i = hash(key);
			list.get(i).remove(Integer.valueOf(key));
		}
	}
	// contains() function return true if the key is present , else it will return false
	public boolean contains(int key) {
		return search(key) != -1;
	}
}

/* 
Time Complexity:  O(N) --> In the worst case, we need to search the entire list[i] to check if key is present
Space Complexity: O(N) --> We use a ArrayList<LinkedList<Integer>> list; to store all the Unique Keys 
*/



/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
