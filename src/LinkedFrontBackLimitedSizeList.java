import java.util.Arrays;

public class LinkedFrontBackLimitedSizeList<T extends Comparable<? super T>>
        implements FrontBackLimitedSizeListInterface<T>, Comparable<LinkedFrontBackLimitedSizeList<T>> {

    private Node head, tail;
    private int maxCapacity;

    public LinkedFrontBackLimitedSizeList(int desiredCapacity) {
        if (desiredCapacity >= 0) {
            maxCapacity = desiredCapacity;
        } else {
            throw new IllegalArgumentException(
                    "Attempt to create a list with negative capacity");
        }
    }

    @Override
    public boolean addFront(T newEntry) {
        boolean result = false;
        if (!isFull()) {
            if (head == null) {
                head = new Node(newEntry);
                head = tail;
            } else {
                Node newNode = new Node(newEntry);
                newNode.next = head;
                head = newNode;
            }
            result = true;
        }

        return result;
    }

    @Override
    public boolean addBack(T newEntry) {
        boolean result = false;
        if (!isEmpty()) {
            Node current = head;
            while (current != null) {
                current = current.next;
            }
            current.next = new Node(newEntry);
            result = true;
        }
        return result;
    }

    @Override
    public T removeFront() {
        T removed = null;
        if (head != null) {
            removed = head.data;
            head = head.next;
        }
        return removed;
    }

    @Override
    public T removeBack() {
        if (head != null) {

        }
        return null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    @Override
    public String toString() {
         T[] arrayList = (T[]) new Object[size()]; // just to make sure: is this considered a O(n)?
        //if (!isEmpty()) {
        Node current = head;
        int i = 0;
        while (current != null) {
            arrayList[i] = current.data;
            current = current.next;
            i++;
        }
        //     }
        return Arrays.toString(arrayList);
    }

    @Override
    public int indexOf(T anEntry) {
        return 0;
    }

    @Override
    public int lastIndexOf(T anEntry) {
        return 0;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

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

    @Override
    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    @Override
    public boolean isFull() {

        return false;
    }

    @Override
    public int compareTo(LinkedFrontBackLimitedSizeList<T> tLinkedFrontBackLimitedSizeList) {
        return 0;
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
