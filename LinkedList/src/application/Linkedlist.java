package application;

public class Linkedlist<T extends Comparable<T>> implements Listable<T> {
	Node<T> head; // The head node, which is the starting point of the linked list

	// Method to insert a new node in a sorted manner
	public void insert(T data) {
		Node<T> newNode = new Node<>(data); // Create a new node with the given data
		Node<T> current = head; // Start traversing from the head
		Node<T> previous = null; // To track the previous node

		// Traverse the list to find the correct position for the new node
		for (; current != null
				&& (newNode.getData().compareTo(current.getData()) > 0); previous = current, current = current
						.getNext())
			;

		// Case 0: Empty list, insert at head
		if (head == null) {
			head = newNode;
		}
		// Case 2: Insert at the end of the list
		else if (current == null) {
			previous.setNext(newNode);
		}
		// Case 1: Insert at the start of the list
		else if (current == head) {
			newNode.setNext(head);
			head = newNode;
		}
		// Case 3: Insert somewhere in the middle
		else {
			newNode.setNext(current);
			previous.setNext(newNode);
		}
	}

	// Method to delete a node with the given data
	public boolean delete(T data) {
		if (head == null) { // Case when the list is empty
			System.out.println("The list is empty, nothing to delete.");
			return false;
		}

		Node<T> prev = null, curr = head; // Initialize prev and curr pointers

		// Traverse the list to find the node to delete
		for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		// If the node is found
		if (curr != null && curr.getData().compareTo(data) == 0) {
			// If the node to delete is the head
			if (prev == null) {
				head = curr.getNext();
			}
			// If the node is somewhere else
			else {
				prev.setNext(curr.getNext());
			}
			return true; // Node successfully deleted
		}
		return false; // Node not found
	}

	// Method to search for a node with the given data
	@Override
	public boolean search(T data) {
		Node<T> curr = head; // Start from the head

		// Traverse the list
		while (curr != null) {
			// If the data is found
			if (curr.getData().equals(data)) {
				return true;
			}
			// Since the list is sorted, stop if data is greater
			if (curr.compare(data) > 0) {
				return false;
			}
			curr = curr.getNext();
		}
		return false; // Data not found
	}

	// Method to get the size of the list
	public int size() {
		int size = 0;
		Node<T> curr = head; // Start from the head

		// Traverse the list and count the nodes
		while (curr != null) {
			size++;
			curr = curr.getNext();
		}
		return size;
	}

	// Method to get the size of the list using recursion
	public int sizeRecursive() {
		if (head != null)
			return sizeRecursive(head); // Recursive helper method
		return 0;
	}

	// Recursive method to calculate size
	private int sizeRecursive(Node<T> curr) {
		if (curr == null) {
			return 0;
		}
		return 1 + sizeRecursive(curr.getNext());
	}

	// Method to search for data using recursion
	public boolean searchRecursive(T data) {
		if (head == null)
			return false;
		return searchRecursive(data, head);
	}

	// Recursive method for searching
	private boolean searchRecursive(T data, Node<T> current) {
		if (current == null)
			return false;
		if (current.getData().compareTo(data) == 0)
			return true;
		if (current.getData().compareTo(data) > 0)
			return false;
		return searchRecursive(data, current.getNext());
	}

	// Method to clear the list
	@Override
	public void clear() {
		head = null; // Simply set the head to null to clear the list
	}

	// Method to print the list
	@Override
	public void print() {
		Node<T> current = head; // Start from the head

		// Check if the list is empty
		if (current == null) {
			System.out.println("The list is empty.");
			return;
		}

		// Traverse and print each node's data
		System.out.print("LinkedList: ");
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println();
	}

	// Method to get the data at a specific index
	@Override
	public T getAt(int index) {
		// Check if the index is out of bounds
		if (head == null || index >= size() || index < 0) {
			return null;
		}
		return getAt(index, head, 0); // Use the recursive helper method
	}

	// Recursive method to get data at a specific index
	private T getAt(int index, Node<T> curr, int cIndex) {
		if (curr != null) {
			if (index == cIndex) {
				return curr.getData();
			}
			return getAt(index, curr.getNext(), cIndex + 1);
		}
		return null;
	}

	// Method to delete data using recursion
	public boolean deleteRecursive(T data) {
		// Check if the list is empty
		if (head == null) {
			System.out.println("The list is empty, nothing to delete.");
			return false;
		}

		head = deleteRecursively(head, data); // Use the recursive helper method
		return head != null; // Return true if deletion is successful
	}

	// Recursive method to delete a node
	private Node<T> deleteRecursively(Node<T> current, T data) {
		if (current == null) {
			return null;
		}
		// If the current node matches the data to be deleted
		if (current.getData().compareTo(data) == 0) {
			return current.getNext(); // Skip over the node to delete it
		}
		// Recursively call the next node
		current.setNext(deleteRecursively(current.getNext(), data));
		return current; // Return the updated current node
	}
}
