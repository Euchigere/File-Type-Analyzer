import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        int noOfSequence = Integer.parseInt(scan.nextLine());
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < noOfSequence; i++) {
            String[] str = scan.nextLine().split("\\s");
            arrayList.addAll(
                Arrays.stream(str)
                      .filter(x -> x != str[0])
                      .map(Integer::parseInt)
                      .collect(Collectors.toList())
            );
        }
        int[] array = arrayList
                .stream()
                .mapToInt(x -> x)
                .toArray();
        mergeSort(array, 0, array.length);
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
    }

    public static void mergeSort(int[] array, int leftIncl, int rightExcl) {
        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        /* divide: calculate the index of the middle element */
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSort(array, middle, rightExcl); // conquer: sort the right subarray

        /* combine: merge both sorted subarrays into sorted one */
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int i = left;   // index for the left subarray
        int j = middle; // index for the right subarray
        int k = 0;      // index for the temp subarray

        int[] temp = new int[right - left]; // temporary array for merging

    /* get the next lesser element from one of two subarrays
       and then insert it in the array until one of the subarrays is empty */
        while (i < middle && j < right) {
            if (array[i] > array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }

        /* insert all the remaining elements of the left subarray in the array */
        for (; j < right; j++, k++) {
            temp[k] = array[j];
        }

        /* insert all the remaining elements of the right subarray in the array */
        for (; i < middle; i++, k++) {
            temp[k] = array[i];
        }

        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }
}