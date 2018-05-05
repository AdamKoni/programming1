package pl.kszafran.sda.algo.exercises.sorting;

public class SelectionSort implements IntSortingAlgorithm {

    //    repeat (numOfElements - 1) times
//      set the first unsorted element as the minimum
//          for each of the unsorted elements
//              if element < currentMinimum
//                  set element as new minimum
//     swap minimum with first unsorted position
    @Override
    public void sort(int[] array) {
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength-1; i++) {
            int min = array[i];
            int indexOfMin = i;
            for (int j = i+1; j < arrayLength; j++) {
                if(array[j] < min) {
                    min = array[j];
                    indexOfMin = j;
                }
            }
            int temp = array[i];
            array[i] = array[indexOfMin];
            array[indexOfMin] = temp;
        }
    }
}
