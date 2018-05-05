package pl.kszafran.sda.algo.exercises.sorting;

public class Quicksort implements IntSortingAlgorithm {
//for each (unsorted) partition
//set first element as pivot
//  storeIndex = pivotIndex + 1
//  for i = pivotIndex + 1 to rightmostIndex
//    if element[i] < element[pivot]
//      swap(i, storeIndex); storeIndex++
//  swap(pivot, storeIndex - 1)

// QUICKSORT ( A, p, r)
//        if p < r
//           then q ← PARTITION(A, p, r)
//                QUICKSORT(A, p, q-1)
//                QUICKSORT(A, q+1, r)

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length);
    }

    private void sort(int[] array, int beginning, int end) {
        if (end - beginning <= 1) {
            return;
        }
        int pivot = array[beginning];
        int storeIndex = beginning + 1;
        for (int i = beginning + 1; i < end; i++) {
            if (array[i] < pivot) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, beginning, storeIndex - 1);
        sort(array, beginning, storeIndex - 1);
        sort(array, storeIndex, end);
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
}

