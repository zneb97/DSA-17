public class MyLinkedList<T> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node(T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
		Node newNode = new Node(c,tail,null);
		if(size == 0){
			head = newNode;
		}
		else{
			tail.next = newNode;
		}
		tail = newNode;
		size++;
	}

	public void addFirst(T c) {
		Node newNode = new Node(c,null, head);
        if(size == 0){
            tail=newNode;
        }
        else {
            head.prev = newNode;
        }
		head = newNode;
		size++;
	}

	public T get(int index) {
		//Check for invalid index
		if(index > size){
			return null;
		}

		Node currentNode = head;
		for(int i = 0; i < index; i++){
			currentNode = currentNode.next;
		}

		return currentNode.val;
	}

	public T remove(int index) {
        //0 size edge case
        if (size == 0) {
            throw new IndexOutOfBoundsException("Not a valid index");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size-1) {
            return removeLast();
        }
        else {
            Node save = head;
            for (int i = 0; i < index; i++) {
                save = save.next;
            }
            save.prev.next = save.next;
            save.next.prev = save.prev;
            size--;
            return save.val;
        }
	}

	public T removeFirst() {
        //0 size edge case
        if(size == 0){
            throw new IndexOutOfBoundsException("Not a valid index");
        }

        T save = head.val;
        //1 size edge case
        if(size == 1){
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return save;

	}

	public T removeLast() {
        //0 size edge case
		if(size == 0) {
			throw new IndexOutOfBoundsException("Not a valid index");
		}

        Node save = tail;
        //1 size edge case
        if(size == 1){
            tail = null;
            head = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
		return save.val;
	}
}