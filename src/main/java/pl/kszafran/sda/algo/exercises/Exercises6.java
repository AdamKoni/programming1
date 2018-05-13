package pl.kszafran.sda.algo.exercises;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

/**
 * Zaimplementuj poniższe metody operujące na drzewie binarnym.
 */
public class Exercises6 {

    /**
     * Przechodzi podane drzewo w kolejności pre-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traversePreOrder(SdaTree<T> tree) {
        List<T> treeList = new ArrayList<>();
        traversePre(tree, treeList);
        return treeList;
    }

    private <T> void traversePre(SdaTree<T> tree, List<T> list) {
        list.add(tree.getValue());
        if (tree.getLeftChild().isPresent()) {
            list.addAll(traversePreOrder(tree.getLeftChild().get()));
        }
        if (tree.getRightChild().isPresent()) {
            list.addAll(traversePreOrder(tree.getRightChild().get()));
        }
    }

    /**
     * Przechodzi podane drzewo w kolejności in-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traverseInOrder(SdaTree<T> tree) {
        List<T> treeList = new ArrayList<>();
        traverseIn(tree, treeList);
        return treeList;
    }

    private <T> void traverseIn(SdaTree<T> tree, List<T> list) {
        if (tree.getLeftChild().isPresent()) {
            list.addAll(traverseInOrder(tree.getLeftChild().get()));
        }
        list.add(tree.getValue());
        if (tree.getRightChild().isPresent()) {
            list.addAll(traverseInOrder(tree.getRightChild().get()));
        }

    }

    /**
     * Przechodzi podane drzewo w kolejności post-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem rekurencji.
     */
    public <T> List<T> traversePostOrder(SdaTree<T> tree) {
        List<T> treeList = new ArrayList<>();
        traversePost(tree, treeList);
        return treeList;
    }

    private <T> void traversePost(SdaTree<T> tree, List<T> list) {
        if (tree.getLeftChild().isPresent()) {
            list.addAll(traversePostOrder(tree.getLeftChild().get()));
        }
        if (tree.getRightChild().isPresent()) {
            list.addAll(traversePostOrder(tree.getRightChild().get()));
        }
        list.add(tree.getValue());
    }

    /**
     * Funkcja działa tak samo jak traversePreOrder.
     * <p>
     * Uwaga: metodę należy zaimplementować z wykorzystaniem stosu (bez rekurencji).
     */

//    iterativePreorder(node)
//  if (node = null)
//    return
//  s ← empty stack
//  s.push(node)
//  while (not s.isEmpty())
//    node ← s.pop()
//    visit(node)
//    //right child is pushed first so that left is processed first
//    if (node.right ≠ null)
//      s.push(node.right)
//    if (node.left ≠ null)
//      s.push(node.left)
    public <T> List<T> traversePreOrderIterative(SdaTree<T> tree) {
        List<T> treeList = new ArrayList<>();
        traverseIterative(tree, treeList::add);
        return treeList;
    }

    private <T> void traverseIterative(SdaTree<T> tree, Consumer<T> list) {
        Deque<SdaTree<T>> treeDeque = new ArrayDeque<>(Collections.singletonList(tree));
        while (!treeDeque.isEmpty()) {
            SdaTree<T> node = treeDeque.pop();
            list.accept(node.getValue());
            if (node.getRightChild().isPresent()) {
                treeDeque.push(node.getRightChild().get());
            }
            if (node.getLeftChild().isPresent()) {
                treeDeque.push(node.getLeftChild().get());
            }
        }
    }

    /**
     * Przechodzi podane drzewo w kolejności level-order i zwraca listę
     * elementów w kolejności takiej, w jakiej były napotkane.
     * <p>
     * Podpowiedź: implementacja jest bardzo podobna do traversePreOrderIterative,
     * ale zamiast stosu wykorzystuje inną strukturę danych.
     */
    public <T> List<T> traverseLevelOrder(SdaTree<T> tree) {
        List<T> treeList = new ArrayList<>();
        traverseLevel(tree, treeList::add);
        return treeList;
    }

    private <T> void traverseLevel(SdaTree<T> tree, Consumer<T> list) {
        Deque<SdaTree<T>> treeDeque = new ArrayDeque<>(Collections.singletonList(tree));
        while (!treeDeque.isEmpty()) {
            SdaTree<T> node = treeDeque.poll();
            list.accept(node.getValue());
            if (node.getLeftChild().isPresent()) {
                treeDeque.offer(node.getLeftChild().get());
            }
            if (node.getRightChild().isPresent()) {
                treeDeque.offer(node.getRightChild().get());
            }
        }
    }

    /**
     * Funkcja zwraca liczbę liści w podanym drzewie.
     */
//    1) Create an empty Queue Node and push root node to Queue.
//    2) Do following while nodeQeue is not empty.
//   a) Pop an item from Queue and process it.
//      a.1) If it is full node then increment count++.
//   b) Push left child of popped item to Queue, if available.
//   c) Push right child of popped item to Queue, if available.
    public int countLeaves(SdaTree<?> tree) {
        int count = 0;
        Deque<SdaTree<?>> treeQueue = new ArrayDeque<>(Collections.singleton(tree));
        while (!treeQueue.isEmpty()) {
            SdaTree<?> node = treeQueue.pop();
            if (!node.getRightChild().isPresent() && !node.getLeftChild().isPresent()) {
                count++;
            }
            if (node.getLeftChild().isPresent()) {
                treeQueue.push(node.getLeftChild().get());
            }
            if (node.getRightChild().isPresent()) {
                treeQueue.push(node.getRightChild().get());
            }
        }
        return count;
    }

    /**
     * Tworzy drzewo binarne na podstawie podanego tekstu.
     * <p>
     * Tekst zawiera tyle linijek, ile poziomów ma drzewo.
     * Każda linijka zawiera wartości węzłów na odpowiednim poziomie rozdzielone spacjami, po kolei,
     * czyli każda linijka zawiera dwa razy więcej wartości niż poprzednia.
     * Wartość "-" oznacza, że węzeł na danej pozycji nie istnieje.
     * <p>
     * Np. drzewo ze slajdów przedstawione byłoby jako "F\nB G\nA D - I\n- - C E - - H -",
     * czyli zapisując w wielu liniach:
     * F
     * B G
     * A D - I
     * - - C E - - H -
     * <p>
     * Uwaga: nie należy modyfikować klas SdaTree i SdaTreeImpl.
     *
     * @throws IllegalArgumentException jeśli któraś z linijek zawiera nieprawidłową ilość wartości
     */
    public SdaTree<String> buildTree1(String input) {
        String[][] array = Arrays.stream(input.split("\\n"))
                .map(e->e.split("\\s"))
                .toArray(String[][]::new);
        check(array)
        buildTreeExample(array, 0, 0);
    }

    private void check(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            int index = 1 << i;
            if (array.length < index) {
                throw new IllegalArgumentException("Not enough tree elements");
            }
        }
    }

    private void buildTreeExample(String[][] array, int a, int b) {

    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Tworzy drzewo binarne na podstawie podanego tekstu.
     * <p>
     * Każda linijka zawiera informację o parze rodzic-dziecko.
     * Format każdej linijki wygląda następująco:
     * <p>
     * left(rodzic)=dziecko
     * <p>
     * lub
     * <p>
     * right(rodzic)=dziecko
     * <p>
     * dla lewego i prawego dziecka odpowiednio.
     * <p>
     * Uwaga: nie należy modyfikować klas SdaTree i SdaTreeImpl.
     *
     * @throws IllegalArgumentException jeśli któraś z linijek jest niezgodna z powyższym formatem
     */
    public SdaTree<String> buildTree2(String input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja oblicza wysokość drzewa.
     * <p>
     * Przypomnienie: wysokość drzewa składającego się jedynie z korzenia to 0.
     */
    public int calcHeight(SdaTree<?> tree) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja znajduje największy element w drzewie.
     */
    public <T> T findMax(SdaTree<T> tree, Comparator<T> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public interface SdaTree<T> {

        /**
         * Tworzy nowe drzewo z korzeniem o podanej wartości oraz danych poddrzewach.
         */
        static <T> SdaTree<T> of(T value, SdaTree<T> left, SdaTree<T> right) {
            return new SdaTreeImpl<>(value, left, right);
        }

        /**
         * Tworzy nowy liść drzewa (węzeł bez dzieci).
         */
        static <T> SdaTree<T> leaf(T value) {
            return new SdaTreeImpl<>(value, null, null);
        }

        /**
         * Zwraca wartość tego węzła.
         */
        T getValue();

        /**
         * Zwraca lewe poddrzewo.
         */
        Optional<SdaTree<T>> getLeftChild();

        /**
         * Zwraca prawe poddrzewo.
         */
        Optional<SdaTree<T>> getRightChild();
    }

    private static class SdaTreeImpl<T> implements SdaTree<T> {

        private final T value;
        private final SdaTree<T> left;
        private final SdaTree<T> right;

        private SdaTreeImpl(T value, SdaTree<T> left, SdaTree<T> right) {
            this.value = Objects.requireNonNull(value, "value must not be null");
            this.left = left;
            this.right = right;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public Optional<SdaTree<T>> getLeftChild() {
            return Optional.ofNullable(left);
        }

        @Override
        public Optional<SdaTree<T>> getRightChild() {
            return Optional.ofNullable(right);
        }
    }
}
