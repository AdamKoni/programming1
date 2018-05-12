package pl.kszafran.sda.algo.exercises;

import org.w3c.dom.Node;

import java.util.*;

/**
 * Zaimplementuj poniższe metody operujące na liście wiązanej jednokierunkowej.
 */
public class Exercises4 {

    /**
     * Tworzy nową listę zawierającą podane elementy.
     */
    public <T> SdaList<T> createList(T... elements) {
        return new SdaLinkedList<>(elements);
    }

    public interface SdaList<T> extends Iterable<T> {

        /**
         * Zwraca true jeśli lista jest pusta.
         */
        boolean isEmpty();

        /**
         * Zwraca rozmiar listy (ilość elementów).
         */
        int size();

        /**
         * Zwraca pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getFirst();

        /**
         * Zwraca ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getLast();

        /**
         * Pobiera element listy pod podanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        T get(int index);

        /**
         * Usuwa wszystkie elementy z listy.
         */
        void clear();

        /**
         * Dodaje nowy element na początku listy.
         */
        void addFirst(T element);

        /**
         * Dodaje nowy element na końcu listy.
         */
        void addLast(T element);

        /**
         * Usuwa pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeFirst();

        /**
         * Usuwa ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeLast();

        ////////////////////////////////////////////
        //                                        //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
        //                                        //
        ////////////////////////////////////////////

        /**
         * Zwraca iterator po elementach listy.
         *
         * @see java.lang.Iterable
         */
        @Override
        Iterator<T> iterator();

        /**
         * Zamienia element pod podanym indeksem.
         */
        void setAt(int index, T element);

        /**
         * Dodaje nowy element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void addAt(int index, T element);

        /**
         * Usuwa element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void removeAt(int index);
    }

    private static class SdaLinkedList<T> implements SdaList<T> {

        private Node<T> head;

        public SdaLinkedList(T[] elements) {
            for (int i = elements.length - 1; i >= 0; i--) {
                head = new Node<>(elements[i], head);
            }
        }

        @Override
        public boolean isEmpty() {
            return head == null;
        }

        @Override
        public int size() {
            int counter = 0;
            Node<T> item = head;
            while (item != null) {
                counter++;
                item = item.next;
            }
            return counter;
        }

        @Override
        public T getFirst() {
            Node<T> item = head;
            if (item != null) {
                return head.element;
            }
            throw new NoSuchElementException("No elements");
        }

        @Override
        public T getLast() {
            Node<T> item = head;
            if (item != null) {
                while (item.next != null) {
                    item = item.next;
                }
                return item.element;
            }
            throw new NoSuchElementException("No elements");
        }

        @Override
        public T get(int index) {
            Node<T> item = head;
            int i = 0;
            if (index >= 0) {
                while (i != index) {
                    if (item.next != null) {
                        item = item.next;
                        i++;
                    } else {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                }
                return item.element;
            }
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        @Override
        public void clear() {
            head = null;
        }

        @Override
        public void addFirst(T element) {
            head = new Node<>(element, head);
        }

        @Override
        public void addLast(T element) {
            if (isEmpty()) {
                addFirst(element);
            } else {
                Node<T> item = head;
                while (item.next != null) {
                    item = item.next;
                }
                item.next = new Node<>(element, null);
            }
        }

        @Override
        public void removeFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("No list");
            } else {
                head = head.next;
            }
        }

        @Override
        public void removeLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("No list");
            } else if (head.next == null) {
                clear();
            } else {
                Node<T> last = head;
                Node<T> secondToLast = null;
                while (last.next != null) {
                    secondToLast = last;
                    last = last.next;
                }
                secondToLast.next = null;
            }
        }

        ////////////////////////////////////////////
        //                                        //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
        //                                        //
        ////////////////////////////////////////////

        @Override
        public Iterator<T> iterator() {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void setAt(int index, T element) {
            nodeAt(index).element = element;
        }

        @Override
        public void addAt(int index, T element) {
            if (index == 0) {
                addFirst(element);
            } else {
                Node<T> preIndex = nodeAt(index - 1);
                preIndex.next = new Node<>(element, preIndex.next);
            }
        }

        @Override
        public void removeAt(int index) {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            } else if(index == 0) {
                removeFirst();
            } else {
                Node<T> preNode = nodeAt(index - 1);
                preNode.next = preNode.next.next;
            }
        }

        private Node<T> nodeAt(int index) {
            if(index < 0) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            } else if (isEmpty()) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            Node<T> item = head;
            for (int i = 0; i < index; i++) {
                item = item.next;
                if(item == null) {
                    throw new IndexOutOfBoundsException("Index out of bounds");
                }
            }
            return item;
        }


        private static class Node<T> {

            private T element;
            private Node<T> next;

            private Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }
        }
    }
}
