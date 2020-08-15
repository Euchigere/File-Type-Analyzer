import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        int size = Integer.parseInt(scan.nextLine());
        int[] array = Arrays
                .stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(insertionSort(array, size));
    }

    public static int insertionSort(int[] array, int size) {
        /* iterating over elements in the unsorted part */
        int count = 0;
        for (int i = 1; i < size; i++) {
            int elem = array[i]; // take the next element
            int j = i - 1;
            boolean increaseCount = true;

            /* find a suitable position to insert and shift elements to the right */
            while (j >= 0 && array[j] < elem) {
                array[j + 1] = array[j]; // shifting
                j--;
                if (increaseCount) {
                    count += 1;
                    increaseCount = false;
                }
            }
            array[j + 1] = elem; // insert the element in the found position in the sorted part
        }

        return count;
    }
}