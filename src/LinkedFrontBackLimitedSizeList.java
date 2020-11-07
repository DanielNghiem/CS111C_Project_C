import java.util.Arrays;

/**
 * @author  Daniel Do, Daniel Nghiem, Jacqueline Sinn
 * @version Project C
 * Date: 11/09/2020
 *
 * A class that implements FrontBackLimitedSizeListInterface using linked nodes, while keeping
 * track of the head and tail nodes.  Entries can be added/removed at the front or back of the
 * list.  Entries can be accessed in any position.  Entries begin at index 0. The capacity of
 * the list cannot be changed after initialization.  Also implements the Comparable interface.
 *
 * @param <T>   A generic type that implements Comparable
 */
public class LinkedFrontBackLimitedSizeList<T extends Comparable<? super T>>
        implements FrontBackLimitedSizeListInterface<T>, Comparable<LinkedFrontBackLimitedSizeList<T>> {

    private Node head, tail;
    private int maxCapacity;

    /**
     * Constructor for LinkedFrontBackLimitedSizeList. Initializes an empty list
     * with a fixed maximum capacity.
     * @param desiredCapacity   the maximum allowed size of the list
     */
    public LinkedFrontBackLimitedSizeList(int desiredCapacity) {
        if (desiredCapacity >= 0) {
            maxCapacity = desiredCapacity;
        } else {
            throw new IllegalArgumentException(
                    "Attempt to create a list with negative capacity");
        }
    }

    /**
     * Adds an entry to the front of the list.
     * @param newEntry  an entry to add
     * @return          true if successful, false if the list is full
     */
    @Override
    public boolean addFront(T newEntry) {
        boolean result = false;
        if (!isFull()) {
            if (isEmpty()) {
                head = new Node(newEntry);
                tail = head;
            } else {
                Node newNode = new Node(newEntry);
                newNode.next = head;
                head = newNode;
            }
            result = true;
        }
        return result;
    }

    /**
     * Adds an entry to the end of the list.
     * @param newEntry  an entry to add
     * @return          true if successful, false if the list is full
     */
    @Override
    public boolean addBack(T newEntry) {
        boolean result = false;
        if (!isFull()) {
            if (isEmpty()) {
                addFront(newEntry);
            } else {  // assign the new tail
                tail.next = new Node(newEntry);
                tail = tail.next;
            }
            result = true;
        }
        return result;
    }

    /**
     * Removes the first entry of the list.
     * @return  the object removed, or null if the list was initially empty
     */
    @Override
    public T removeFront() {
        T removed = null;
        if (!isEmpty()) {
            removed = head.data;
            head = head.next;
        }
        return removed;
    }

    /**
     * Removes the last entry of the list.
     * @return  the object removed, or null if the list was initially empty
     */
    @Override
    public T removeBack() {
        T removed = null;
        if (!isEmpty()) {
            removed = tail.data;

            if (size() == 1) {
                clear();
            } else { // size() > 1
                Node current = head;

                while (current.next.next != null) {  // traverse to second to last node
                    current = current.next;
                }
                current.next = null;
                tail = current;
            }
        }
        return removed;
    }

    /**
     * Empties the list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Gets the entry at the given position.
     *
     * @param givenPosition An integer that indicates the position of the desired entry.
     * @return              The data at the given position, null if the position is invalid.
     */
    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if (givenPosition >= 0 && givenPosition < size()) {
            Node current = head;
            for (int i = 0; i < givenPosition; i++) {
                current = current.next;
            }
            result = current.data;
        }
        return result;
    }

    /**
     * Formats the entries of the list and the head and tail data.
     * @return      A string of the form "[data0, data1, ..., dataN  ]  head=data0 tail=dataN"
     */
    @Override
    public String toString() {
        T[] arrayList = (T[]) new Comparable[size()];

        Node current = head;
        int i = 0;
        while (current != null) {
            arrayList[i] = current.data;
            current = current.next;
            i++;
        }

        if (isEmpty()) {
            return "[]";
        } else {
            return Arrays.toString(arrayList) + "\thead=" + head.data.toString()
                    + " tail=" + tail.data.toString();
        }
    }

    /**
     * Locates the first index of a given entry.
     *
     * @param anEntry the object to search for in the list
     * @return        the index of the entry, or -1 if not found
     */
    @Override
    public int indexOf(T anEntry) {
        int counter = -1;
        boolean isFound = false;
        Node current = head;

        while (current != null && !isFound) {
            if (current.data.equals(anEntry)) {
                isFound = true;
            }
            current = current.next;
            counter++;
        }
        if (isFound) {
            return counter;
        } else {
            return -1;
        }
    }

    /**
     * Locates the last index of a given entry.
     *
     * @param anEntry the object to search for in the list
     * @return        the last index of the entry, or -1 if not found
     */
    @Override
        public int lastIndexOf(T anEntry) {
            int size = size();
            int lastIndex = -1;
            Node current = head;

            for (int i = 0; i < size; i++) {
                if (current.data.equals(anEntry)) {
                    lastIndex = i;
                }
                current = current.next;
            }
            return lastIndex;
    }

    /**
     * Determines if a given entry is in the list.
     *
     * @param anEntry the object to search for in the list.
     * @return      true if the given entry is in the list, false otherwise
     */
    @Override
    public boolean contains(T anEntry) {
        return (indexOf(anEntry) != -1);
    }

    /**
     * Counts the number of entries in the list. Runs in O(n) time.
     *
     * @return  the number of elements in the list
     */
    @Override
    public int size() {
        int count = 0;

        if (!isEmpty()) {
            Node current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
        }
        return count;
    }

    /**
     * Determines is the list is empty.
     * @return     true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Determines if the list is at maximum capacity.
     *
     * @return      true if full, false otherwise
     */
    @Override
    public boolean isFull() {
        if (size() == maxCapacity) {
            return true;
        } else if (size() > maxCapacity) {
            System.out.println("The size is greater than the max capacity!");
        }
        return false;
    }

    /**Compares two lists element by element. Compares the length of the lists if one list
     * contains the other.  Runs in O(n) time.
     *
     * @param tLinkedFrontBackLimitedSizeList   the other list
     * @return                                  positive if the list is greater, negative if
     *                                          the other list is greater, and zero if they are equal
     */
    @Override
    public int compareTo(LinkedFrontBackLimitedSizeList<T> tLinkedFrontBackLimitedSizeList) {
        Node current1 = head;
        Node current2 = tLinkedFrontBackLimitedSizeList.head;

        // traverse two lists until one of them reaches the end
        while(current1 != null && current2 != null) {
            int comparison = current1.data.compareTo(current2.data);

            if (comparison == 0) {
                // same elements, so move to next nodes
                current1 = current1.next;
                current2 = current2.next;
            } else {
                // if not a match, compare the elements
                return comparison;
            }
        }
        /* This block runs only if both lists have the same elements and at least
            one of the lists ended */
        if (current1 != null && current2 == null) {
            return 1;
        } else if (current1 == null && current2 != null) {
            return -1;
        } else {  // both are null and both lists are are same
            return 0;
        }
    }


    public class Node {
        public T data;
        public Node next;

        private Node(T dataPortion) {
            data = dataPortion;
            next = null;
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }

}
