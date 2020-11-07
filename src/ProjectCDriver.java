import java.util.Arrays;

public class ProjectCDriver {


    public static void main(String[] args) {
        FrontBackLimitedSizeListInterface<Integer> list = new LinkedFrontBackLimitedSizeList<Integer>(10);


        System.out.println("*****************************TESTING ISEMPTY AND EMPTY DISPLAY*****************************");
        testIsEmptyFull(list, true, false);
        testSize(list, 0);
        testDisplayMatch(list, "[]");


        System.out.println("\n*****************************TESTING ADD TO BACK*****************************");
        testAdd(list, AddRemovePosition.BACK, new Integer[]{7}, true, "addBack to empty list");
        testDisplayMatch(list, "[7]\thead=7 tail=7");

        testAdd(list, AddRemovePosition.BACK, new Integer[]{9, 5}, true, "addBack to singleton list");
        testIsEmptyFull(list, false, false);
        testSize(list, 3);
        testDisplayMatch(list, "[7, 9, 5]\thead=7 tail=5");

        testAdd(list, AddRemovePosition.BACK, new Integer[]{5, 3, 2, 1, 9, 8, 6}, true, "addBack to fill the list");
        testIsEmptyFull(list, false, true);
        testSize(list, 10);
        testDisplayMatch(list, "[7, 9, 5, 5, 3, 2, 1, 9, 8, 6]\thead=7 tail=6");

        testAdd(list, AddRemovePosition.BACK, new Integer[]{4}, false, "addBack to full list");
        testSize(list, 10);
        testDisplayMatch(list, "[7, 9, 5, 5, 3, 2, 1, 9, 8, 6]\thead=7 tail=6");


        System.out.println("\n*****************************TESTING CLEAR*****************************");
        list.clear();
        testIsEmptyFull(list, true, false);
        testDisplayMatch(list, "[]");


        System.out.println("\n*****************************TESTING ADD TO FRONT*****************************");
        testAdd(list, AddRemovePosition.FRONT, new Integer[]{2}, true, "addFront to empty list");
        testDisplayMatch(list, "[2]\thead=2 tail=2");

        testAdd(list, AddRemovePosition.FRONT, new Integer[]{4, 3}, true, "addFront to singleton list");
        testIsEmptyFull(list, false, false);
        testSize(list, 3);
        testDisplayMatch(list, "[3, 4, 2]\thead=3 tail=2");

        testAdd(list, AddRemovePosition.FRONT, new Integer[]{7, 9, 5, 4, 3, 8, 1}, true, "addFront to fill the list");
        testIsEmptyFull(list, false, true);
        testSize(list, 10);
        testDisplayMatch(list, "[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=1 tail=2");

        testAdd(list, AddRemovePosition.FRONT, new Integer[]{4}, false, "addFront to full list");
        testSize(list, 10);
        testDisplayMatch(list, "[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=1 tail=2");


        System.out.println("\n*****************************TESTING CONTAINS*****************************");
        testContains(list, 1, true, "element is in the first position");
        testContains(list, 2, true, "element is in the last position");
        testContains(list, 5, true, "element is in the middle");
        testContains(list, 3, true, "element is in the list more than once");
        testContains(list, 0, false, "element is not in the list");

        testDisplayMatch(list, "[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=1 tail=2");


        System.out.println("\n*****************************TESTING INDEX OF*****************************");
        testIndexOf(list, IndexPosition.FIRST, 1, 0, "first element in the list");
        testIndexOf(list, IndexPosition.FIRST, 2, 9, "last element in the list");
        testIndexOf(list, IndexPosition.FIRST, 5, 4, "element in the middle of the list");
        testIndexOf(list, IndexPosition.FIRST, 3, 2, "repeated element in the list");
        testIndexOf(list, IndexPosition.FIRST, 0, "element not in the list");

        testDisplayMatch(list, "[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=1 tail=2");


        System.out.println("\n*****************************TESTING LAST INDEX OF*****************************");
        testIndexOf(list, IndexPosition.LAST, 1, 0, "first element in the list");
        testIndexOf(list, IndexPosition.LAST, 2, 9, "last element in the list");
        testIndexOf(list, IndexPosition.LAST, 5, 4, "element in the middle of the list");
        testIndexOf(list, IndexPosition.LAST, 3, 7, "repeated element in the list");
        testIndexOf(list, IndexPosition.LAST, 0, "element not in the list");

        testDisplayMatch(list, "[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=1 tail=2");


        System.out.println("\n*****************************TESTING REMOVES*****************************");
        testRemove(list, AddRemovePosition.FRONT, 1, "remove from non-empty");
        testDisplayMatch(list, "[8, 3, 4, 5, 9, 7, 3, 4, 2]\thead=8 tail=2");

        testRemove(list, AddRemovePosition.FRONT, 8, "remove from non-empty");
        testDisplayMatch(list, "[3, 4, 5, 9, 7, 3, 4, 2\thead=3 tail=2");

        testRemove(list, AddRemovePosition.BACK, 2, "remove from non-empty");
        testDisplayMatch(list, "[3, 4, 5, 9, 7, 3, 4]\thead=3 tail=4");

        testRemove(list, AddRemovePosition.BACK, 4, "remove from non-empty");
        testSize(list, 6);
        testDisplayMatch(list, "[3, 4, 5, 9, 7, 3]\thead=3 tail=3");

        list.clear();
        testRemove(list, AddRemovePosition.FRONT, "remove from empty");
        testIsEmptyFull(list, true, false);
        testSize(list, 0);
        testDisplayMatch(list, "[]");

        testRemove(list, AddRemovePosition.BACK, "remove from empty");
        testIsEmptyFull(list, true, false);
        testSize(list, 0);
        testDisplayMatch(list, "[]");

        list.clear();
        list.addFront(1);
        testRemove(list, AddRemovePosition.FRONT, 1, "remove from singleton");
        testIsEmptyFull(list, true, false);

        list.clear();
        list.addFront(1);
        testRemove(list, AddRemovePosition.BACK, 1, "remove from singleton");
        testIsEmptyFull(list, true, false);

        list.clear();
        list.addBack(1);
        testRemove(list, AddRemovePosition.FRONT, 1, "remove from singleton");
        testIsEmptyFull(list, true, false);

        list.clear();
        list.addBack(1);
        testRemove(list, AddRemovePosition.BACK, 1, "remove from singleton");
        testIsEmptyFull(list, true, false);


        System.out.println("\n*****************************TESTING MIX OF ADDS AND REMOVES*****************************");
        list.clear();
        list.addFront(3);
        list.addBack(2);
        list.addFront(4);
        list.addFront(5);
        list.addBack(3);
        list.addBack(8);
        list.addBack(9);
        testDisplayMatch(list, "[5, 4, 3, 2, 3, 8, 9]\thead=5 tail=9");

        list.removeFront();
        list.removeBack();
        testDisplayMatch(list, "[4, 3, 2, 3, 8]\thead=4 tail=8");


        System.out.println("\n*****************************TESTING GET ENTRY*****************************");
        testGetEntry(list, 0, 4, "getting first position");
        testGetEntry(list, 4, 8, "getting last position");
        testGetEntry(list, 2, 2, "getting a middle position");
        testGetEntry(list, -1, "invalid index");
        testGetEntry(list, 11, "invalid index");
        testGetEntry(list, 7, "empty (invalid) index");


        System.out.println("\n*****************************TESTING WITH STRINGS*****************************");
        FrontBackLimitedSizeListInterface<String> wordList = new LinkedFrontBackLimitedSizeList<String>(20);
        testAdd(wordList, AddRemovePosition.FRONT, new String[]{"job!", "Nice", "it!", "did", "You"}, true, "test with Strings");
        testAdd(wordList, AddRemovePosition.BACK, new String[]{"You", "rock!"}, true, "test with Strings");
        testDisplayMatch(wordList, "[You, did, it!, Nice, job!, You, rock!]\thead=You tail=rock!");
        testContains(wordList, new String("it!"), true, "test with Strings");
        testIndexOf(wordList, IndexPosition.FIRST, new String("You"), 0, "test with Strings");
        testIndexOf(wordList, IndexPosition.LAST, new String("You"), 5, "test with Strings");

        /**/
        // UNCOMMENT IF COMPLETING THE EXTRA CREDIT
        System.out.println("\n*****************************TESTING EXTRA CREDIT*****************************");
        testCompareTo(new Integer[]{}, 10, new Integer[]{}, 10, PosNegZero.ZERO, "both empty lists");
        testCompareTo(new Integer[]{}, 5, new Integer[]{}, 10, PosNegZero.ZERO, "both empty lists with different capacity");
        testCompareTo(new Integer[]{1}, 10, new Integer[]{}, 10, PosNegZero.POSITIVE, "no mismatched elements, listA [1] is longer than listB [] ");
        testCompareTo(new Integer[]{1}, 10, new Integer[]{1, 2}, 10, PosNegZero.NEGATIVE, "no mismatched elements, listA [1] is shorter than listB [1, 2]");
        testCompareTo(new Integer[]{1, 2}, 10, new Integer[]{1, 2}, 10, PosNegZero.ZERO, "no mismatched elements found [1, 2], equal length");
        testCompareTo(new Integer[]{3, 4}, 10, new Integer[]{3, 4}, 5, PosNegZero.ZERO, "no mismatched elements found [1, 2], equal length, different capacity");
        testCompareTo(new Integer[]{1, 2, 3}, 10, new Integer[]{1, 2, 4}, 10, PosNegZero.NEGATIVE, "for first mismatched element, the listA element [1, 2, 3] is smaller than the listB element [1, 2, 4]");
        testCompareTo(new Integer[]{1, 2, 6}, 10, new Integer[]{1, 2, 4}, 10, PosNegZero.POSITIVE, "for first mismatched element, the listA element [1, 2, 6] is larger than the listB element [1, 2, 4]");
        testCompareTo(new Integer[]{1, 2, 3, 4, 7}, 10, new Integer[]{7}, 10, PosNegZero.NEGATIVE, "for first mismatched element, the listA element [1, 2, 3, 4, 7] is smaller than the listB element [7]");
        testCompareTo(new Integer[]{3}, 10, new Integer[]{2, 1, 4, 3, 7}, 10, PosNegZero.POSITIVE, "for first mismatched element, the listA element [1, 2, 3, 4, 7] is smaller than the listB element [7]");
    }

    /*
     * The methods below are designed to help support the tests cases run from main. You don't
     * need to use, modify, or understand these methods. You can safely ignore them. :)
     */

    public static <T> void testDisplayMatch(FrontBackLimitedSizeListInterface<T> list, String expectedOutput) {
        System.out.println("\nExpected output: " + expectedOutput);
        System.out.println("Actual output:   " + list.toString() + "\n");
    }

    public static <T> void testIsEmptyFull(FrontBackLimitedSizeListInterface<T> list, boolean expectedEmptyResult, boolean expectedFullResult) {
        System.out.println("\nisEmpty: expected=" + expectedEmptyResult + " actual=" + list.isEmpty());
        if (expectedEmptyResult != list.isEmpty()) {
            System.out.println("\t***Test failed for isEmpty on list: " + list);
        }

        System.out.println("isFull:  expected=" + expectedFullResult + " actual=" + list.isFull());
        if (expectedFullResult != list.isFull()) {
            System.out.println("\t***Test failed for isFull on list: " + list);
        }
    }

    public static <T> void testSize(FrontBackLimitedSizeListInterface<T> list, int expectedSize) {
        System.out.println("\nSize: expected=" + expectedSize + " actual=" + list.size());
        if (expectedSize != list.size()) {
            System.out.println("\t***Test failed for size on list: " + list);
        }
    }

    public static <T> void testAdd(FrontBackLimitedSizeListInterface<T> list, AddRemovePosition positionToAdd, T[] valuesToAdd, boolean expectedResult, String testDescription) {
        System.out.println("Adding " + Arrays.toString(valuesToAdd) + " to " + positionToAdd + " of list");
        System.out.println("\tBefore: " + list);

        int beforeSize = list.size();
        for (T value : valuesToAdd) {
            boolean actualResult;
            if (positionToAdd == AddRemovePosition.FRONT) {
                actualResult = list.addFront(value);
            } else { // positionToAdd==Position.BACK
                actualResult = list.addBack(value);
            }
            if (actualResult != expectedResult) {
                System.out.println("\t***Test failed when adding " + value + " to " + list + "; test:" + testDescription);
                System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
            }
        }
        System.out.println("\tAfter:  " + list);
        if (expectedResult == true) {
            int afterSize = list.size();
            int expectedAfterSize = beforeSize + valuesToAdd.length;
            if (expectedAfterSize != afterSize) {
                System.out.println("\t***Test failed when adding " + Arrays.toString(valuesToAdd) + " to " + list + "; test:" + testDescription);
                System.out.println("\t   Expected after size=" + expectedAfterSize + "\tActual after size=" + afterSize);
            }
        }
    }

    public static <T> void testContains(FrontBackLimitedSizeListInterface<T> list, T element, boolean expectedResult, String testDescription) {
        boolean actualResult = list.contains(element);
        System.out.println("List contains " + element + ": " + actualResult + "\t" + list);
        if (expectedResult != actualResult) {
            System.out.println("\t***Test failed when checking if list contained " + element + "; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);

        }
    }

    public static <T> void testIndexOf(FrontBackLimitedSizeListInterface<T> list, IndexPosition indexPosition, T element, String testDescription) {
        testIndexOf(list, indexPosition, element, -1, testDescription);
    }

    public static <T> void testIndexOf(FrontBackLimitedSizeListInterface<T> list, IndexPosition indexPosition, T element, int expectedResult, String testDescription) {
        int actualResult;
        if (indexPosition == IndexPosition.FIRST) {
            actualResult = list.indexOf(element);
        } else { // position==IndexPosition.LAST
            actualResult = list.lastIndexOf(element);
        }
        System.out.println("The " + indexPosition + " index of " + element + "=" + actualResult + "\t" + list);
        if (expectedResult < 0 && actualResult >= 0) {
            System.out.println("\t***Test failed when finding the index of an element not in the list; test:" + testDescription);
            System.out.println("\t   Expected result should indicate the element is not on the list. \tActual result=" + actualResult);
        } else if (expectedResult != actualResult) {
            System.out.println("\t***Test failed when finding the index; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);

        }
    }

    public static <T> void testGetEntry(FrontBackLimitedSizeListInterface<T> list, int position, String testDescription) {
        testGetEntry(list, position, null, testDescription);
    }

    public static <T> void testGetEntry(FrontBackLimitedSizeListInterface<T> list, int position, T expectedResult, String testDescription) {
        T actualResult = list.getEntry(position);
        System.out.println("Entry in position " + position + " is " + actualResult + "\t" + list);
        if (expectedResult == null && actualResult != null) {
            System.out.println("\t***Test failed when using an invalid position; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
        } else if (expectedResult != null && !expectedResult.equals(actualResult)) {
            System.out.println("\t***Test failed when getting the element at position " + position + "; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);

        }
    }

    public static <T> void testRemove(FrontBackLimitedSizeListInterface<T> list, AddRemovePosition positionToRemove, String testDescription) {
        testRemove(list, positionToRemove, null, testDescription);
    }

    public static <T> void testRemove(FrontBackLimitedSizeListInterface<T> list, AddRemovePosition positionToRemove, T expectedResult, String testDescription) {
        System.out.println("Removing from " + positionToRemove + " of list:\t" + list);

        int beforeSize = list.size();

        T actualResult;
        if (positionToRemove == AddRemovePosition.FRONT) {
            actualResult = list.removeFront();
        } else { // positionToRemove==Position.BACK
            actualResult = list.removeBack();
        }

        System.out.println("List after removing:\t\t" + list);
        if (expectedResult != null) {
            int afterSize = list.size();
            int expectedAfterSize = beforeSize - 1;
            if (expectedAfterSize != afterSize) {
                System.out.println("\t***Test failed when removing from " + positionToRemove + "; test:" + testDescription);
                System.out.println("\t   Expected after size=" + expectedAfterSize + "\tActual after size=" + afterSize);
            }
        }
        if (expectedResult == null && actualResult != null) {
            System.out.println("\t***Test failed when removing from " + positionToRemove + "; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);
        } else if (expectedResult != null && !expectedResult.equals(actualResult)) {
            System.out.println("\t***Test failed when removing from " + positionToRemove + "; test:" + testDescription);
            System.out.println("\t   Expected result=" + expectedResult + "\tActual result=" + actualResult);

        }
    }

    public static <T extends Comparable<? super T>> void testCompareTo(T[] contentsListA, int sizeA, T[] contentsListB, int sizeB, PosNegZero expectedResult, String testDescription) {
        LinkedFrontBackLimitedSizeList<T> listA = new LinkedFrontBackLimitedSizeList<>(sizeA);
        for (T item : contentsListA) {
            listA.addBack(item);
        }
        LinkedFrontBackLimitedSizeList<T> listB = new LinkedFrontBackLimitedSizeList<>(sizeB);
        for (T item : contentsListB) {
            listB.addBack(item);
        }
        int result = listA.compareTo(listB);
        boolean resultMatches = expectedResult.matches(result);
        String resultString;
        if (result > 0) {
            resultString = "listA is bigger";
        } else if (result < 0) {
            resultString = "listA is smaller";
        } else {
            resultString = "lists are equivalent";
        }

        System.out.println("List A: " + Arrays.toString(contentsListA) + "\tListB: " + Arrays.toString(contentsListB) + "\tlistA.compareTo(listB) \tresult=" + resultString);
        if (!resultMatches) {
            System.out.println("\t***Test failed when comparing lists; test: " + testDescription);
            System.out.println("\t   Expected result should be " + expectedResult + "\tActual result=" + result);

        }

    }

    public static enum AddRemovePosition {
        FRONT, BACK;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public static enum IndexPosition {
        FIRST, LAST;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public static enum PosNegZero {
        POSITIVE, NEGATIVE, ZERO;

        public String toString() {
            return super.toString().toLowerCase();
        }

        public boolean matches(int value) {
            if (this == POSITIVE) {
                return value > 0;
            } else if (this == NEGATIVE) {
                return value < 0;
            } else {
                return value == 0;
            }
        }
    }
}