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

    @Override
    public T removeFront() {
        T removed = null;
        if (!isEmpty()) {
            removed = head.data;
            head = head.next;
        }
        return removed;
    }

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

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

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

    @Override
        public int lastIndexOf(T anEntry) {
            int lastIndex = -1;
            Node current = head;

            for (int i = 0; i < size(); i++) {
                if (current.data.equals(anEntry)) {
                    lastIndex = i;
                }
                current = current.next;
            }
            return lastIndex;
    }

    @Override
    public boolean contains(T anEntry) {
        return (indexOf(anEntry) != -1);
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
        return (head == null);
    }

    @Override
    public boolean isFull() {
        if (size() == maxCapacity) {
            return true;
        } else if (size() > maxCapacity) {
            System.out.println("The size is greater than the max capacity!");
        }
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
