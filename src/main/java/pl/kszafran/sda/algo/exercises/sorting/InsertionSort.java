package pl.kszafran.sda.algo.exercises.sorting;

public class InsertionSort implements IntSortingAlgorithm {

    //    mark first element as sorted
//for each unsorted element X
//  'extract' the element X
//  for j = lastSortedIndex down to 0
//    if current element j > X
//      move sorted element to the right by 1
//    break loop and insert X here    @Override

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}