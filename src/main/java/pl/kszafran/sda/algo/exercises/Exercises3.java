package pl.kszafran.sda.algo.exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Zaimplementuj poniższe algorytmy wyszukiwania.
 */
public class Exercises3 {

    /**
     * Wyszukuje element o wartości value w podanej tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */

//     BinarySearch(A[0..N-1], value) {
//      low = 0
//      high = N - 1
//      while (low <= high) {
//          // invariants: value > A[i] for all i < low
//                         value < A[i] for all i > high
//          mid = (low + high) / 2
//          if (A[mid] > value)
//              high = mid - 1
//          else if (A[mid] < value)
//              low = mid + 1
//          else
//              return mid
//      }
//      return not_found // value would be inserted at index "low"
//  }
    public int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ liście i zwraca jego indeks.
     */
    public <T> Optional<Integer> indexOf(List<T> list, T value, Comparator<T> comparator) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (comparator.compare(list.get(mid), value) > 0) {
                high = mid - 1;
            } else if (comparator.compare(list.get(mid), value) < 0) {
                low = mid + 1;
            } else {
                return Optional.of(mid);
            }
        }
        return Optional.empty();
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Wyszukuje wszystkie elementy o wartości value w podanej POSORTOWANEJ tablicy i zwraca zakres ich indeksów.
     * <p>
     * Uwaga: istnieją dwie możliwe implementacje, jedna o stałej złożoności O(log n)
     * oraz druga, która potrafi zdegradować się do złożoności O(n) w najgorszym przypadku.
     */
    public Optional<IntRange> binarySearchRange(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int maxRange = 0;
        int minRange = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                for (int i = mid; i < high; i++) {
                    if (array[i] == value) {
                        maxRange = i;
                    }
                }
                for (int i = mid; i > low; i--) {
                    if (array[i] == value) {
                        minRange = i;
                    }
                }
                IntRange range = new IntRange(minRange, maxRange);
                return Optional.ofNullable(range);
            }
        }
        return Optional.empty();
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     * <p>
     * Uwaga: należy zastosować wyszukiwanie interpolacyjne (interpolation search).
     */
    public int interpolationSearch(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static class IntRange {

        private final int start;
        private final int end;

        public IntRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
