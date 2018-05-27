package pl.kszafran.sda.algo.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Exercises7 {

    /**
     * Zwraca true jeśli elementy w podanej tablicy tworzą kopiec,
     * tzn. tworzą kompletne drzewo binarne, w którym każdy węzeł
     * ma wartość większą lub równą wartości swoich dzieci.
     */
    public boolean isHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if ((2 * i + 1 < array.length) && (array[i] < array[(2 * i + 1)]) ||
                    ((2 * i + 2 < array.length) && array[i] < array[(2 * i + 2)])) {
                return false;
            }
        }
        return true;
    }

    public <T extends Comparable<T>> SdaHeap<T> createHeap(T[] heap, int capacity) {
        return new FixedSizeSdaHeap<>(heap, capacity);
    }

    public interface SdaHeap<T extends Comparable<T>> {

        /**
         * Dodaje nowy element do kopca. Po dodaniu elementu do tablicy, własność kopca musi zostać zachowana.
         *
         * @throws IllegalStateException jeśli kopiec jest pełny
         */
        void push(T element);

        /**
         * Usuwa z kopca i zwraca element o najwyższej wartości.
         *
         * @throws NoSuchElementException jeśli kopiec jest pusty
         */
        T pop();

        /**
         * Zwraca ilość elementów na kopcu.
         */
        int size();

        /**
         * Zwraca tablicę zawierającą elementy kopca.
         */
        T[] toArray();
    }

    private static class FixedSizeSdaHeap<T extends Comparable<T>> implements SdaHeap<T> {

        private T[] heap;
        private int size;

        // zakładamy, że "heap" jest poprawnym kopcem
        public FixedSizeSdaHeap(T[] heap, int capacity) {
            this.heap = Arrays.copyOf(heap, capacity);
            this.size = heap.length;
        }

//i = A.length-1
//
//while (i > 1 && A[parent(i)] < A[i])
//
//  swap(A[i], A[parent(i)])
        @Override
        public void push(T element) {
            if (heap.length == size) {
                throw new IllegalStateException("Can't eat anymore, I'm full!");
            }
            heap[size] = element;
            size++;
            int i = size - 1;
            while (i > 0 && heap[(i - 1) / 2].compareTo(heap[i]) < 0) {
                T temp = heap[(i - 1) / 2];
                heap[(i - 1) / 2] = heap[i];
                heap[i] = temp;
                i = ((i - 1) / 2);
            }
        }

//        take out A[1]
//
//A[1] = A[A.length-1]
//
//i = 1; A.length--
//
//while (i < A.length)
//
//  if A[i] < (L = the larger of i's children)
//
//    swap(A[i], L)
        @Override
        public T pop() {
            if (size == 0) {
                throw new NoSuchElementException("There's nobody here but us chickens!");
            }
            T result = heap[0];
            heap[0] = heap[size - 1];
            size--;
            int i = 0;
            while(i < size) {
                int childFirst = (2 * i + 1);
                int childSecond = (2 * i + 2);
                int larger = 0;
                if ((childFirst < size && childSecond < size && heap[childFirst] != null &&  heap[childSecond] != null )
                        && heap[childFirst].compareTo(heap[childSecond]) < 0) {
                    larger = childSecond;
                } else if(((childFirst < size && childSecond < size && heap[childFirst] != null &&  heap[childSecond] != null )
                        && heap[childFirst].compareTo(heap[childSecond]) > 0)
                        || childSecond >= size) {
                    larger = childFirst;
                }
                if( heap[i].compareTo(heap[larger]) < 0) {
                    T temp = heap[larger];
                    heap[larger] = heap[i];
                    heap[i] = temp;
                    i = larger;
                }
            }
            return result;
        }

        @Override
        public int size() {
            return size;
        }

        public T[] toArray() {
            return Arrays.copyOf(heap, size);
        }
    }

    public <T extends Comparable<T>> SdaBst<T> createBst(T[] elements) {
        return new SdaBstImpl<>(elements);
    }

    public interface SdaBst<T extends Comparable<T>> {

        /**
         * Wstawia nowy element do drzewa BST.
         * <p>
         * Jeśli element o takiej samej wartości już znajduje się w drzewie,
         * zostaje zastąpiony przez nowy element.
         */
        void insert(T element);

        /**
         * Zwraca true, jeśli podany element znajduje się w drzewie.
         * <p>
         * Uwaga: elementy należy porównywać poprzez .compareTo(..), nie .equals(..).
         */
        boolean contains(T element);

        /**
         * Usuwa element z drzewa BST (jeśli taki istnieje).
         * <p>
         * Podpowiedź: należy rozpatrzyć trzy przypadki:
         * - usuwany węzeł nie ma dzieci
         * - usuwany węzeł ma jedno dziecko
         * - usuwany węzeł ma dwoje dzieci
         * <p>
         * Uwaga: zauważ, że nasza implementacja Node nie przechowuje referencji na rodzica (parent),
         * więc nie każde rozwiazanie znalezione w Internecie się dla nas nadaje.
         * <p>
         * Podpowiedź: ta stronka może się tutaj bardziej przydać niż Wikipedia:
         * https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
         */
        void delete(T element);

        /**
         * Zwraca listę zawierającą wszystkie elementy, posortowane.
         */
        List<T> toList();
    }

    private static class SdaBstImpl<T extends Comparable<T>> implements SdaBst<T> {

        private Node root;

        public SdaBstImpl(T[] elements) {
            // to już jest OK, nie ruszać :)
            for (T element : elements) {
                insert(element);
            }
        }

        @Override
        public void insert(T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public boolean contains(T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void delete(T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public List<T> toList() {
            List<T> list = new ArrayList<>();
            inOrder(root, list::add);
            return list;
        }

        private void inOrder(Node node, Consumer<T> visitor) {
            if (node == null) {
                return;
            }
            inOrder(node.left, visitor);
            visitor.accept(node.value);
            inOrder(node.right, visitor);
        }

        private class Node {

            private T value;
            private Node left;
            private Node right;

            public Node(T value, Node left, Node right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }
        }
    }
}
