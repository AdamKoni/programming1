package pl.kszafran.sda.algo.exercises;

import java.util.*;

public class Exercises5 {

    /**
     * Funkcja przyjmuje ciąg znaków zawierający nawiasy okrągłe: "(", ")",
     * a następnie zwraca true jeśli nawiasy są prawidłowo zagnieżdżone.
     * <p>
     * Inne znaki są ignorowane.
     * <p>
     * Np. dla "(())" zwraca true, ale dla "())(" zwraca false.
     */
    public boolean balancedParens(String input) {
        Deque<String> balanceStack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equals("(")) {
                balanceStack.push("(");
            } else if (input.substring(i, i + 1).equals(")")) {
                if (balanceStack.isEmpty()) {
                    return false;
                }
                balanceStack.pop();
            }
        }
        return balanceStack.isEmpty();
    }

    /**
     * Funkcja działa analogicznie do balancedParens, ale sprawdza także
     * poprawne zagnieżdzenie nawiasów kwadratowych "[", "]" oraz klamrowych "{", "}".
     * <p>
     * Np. dla "[(){}]" zwraca true, ale dla "[(])" zwraca false.
     */
    public boolean balancedAnyParens(String input) {
        Deque<String> balanceStack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            switch (input.substring(i, i + 1)) {
                case "(":
                    balanceStack.push("(");
                    break;
                case "[":
                    balanceStack.push("[");
                    break;
                case "{":
                    balanceStack.push("{");
                    break;
                case ")":
                    if (balanceStack.isEmpty()) {
                        return false;
                    } else if (balanceStack.peek() != null && balanceStack.peek().equals("(")) {
                        balanceStack.pop();
                    }
                    break;
                case "]":
                    if (balanceStack.isEmpty()) {
                        return false;
                    } else if (balanceStack.peek() != null && balanceStack.peek().equals("[")) {
                        balanceStack.pop();
                    }
                    break;
                case "}":
                    if (balanceStack.isEmpty()) {
                        return false;
                    } else if (balanceStack.peek() != null && balanceStack.peek().equals("{")) {
                        balanceStack.pop();
                    }
                    break;
            }
        }
        return balanceStack.isEmpty();
    }

    /**
     * Funkcja odwraca podaną kolejkę.
     * <p>
     * Uwaga: wolno używać jedynie metod poll(), offer(), peek() i isEmpty()
     * oraz nie wolno tworzyć innych kolekcji.
     */
    public <T> void reverseQueue(Deque<T> queue) {
        if (queue.size() > 1) {
            T temp = queue.poll();
            reverseQueue(queue);
            queue.offer(temp);
        }
    }

    /**
     * Tworzy nową kolejkę o stałej pojemności 'capacity', zawierającą podane elementy.
     * <p>
     * Uwaga: kolejkę należy zaimplementować w oparciu o bufor cykliczny.
     * Opis do znalezienia na Wikipedii: https://pl.wikipedia.org/wiki/Bufor_cykliczny
     * <p>
     * Uwaga: najłatwiej będzie implementować metody w takiej kolejności jak są zadeklarowane.
     * <p>
     * Podpowiedź: są dwa podstawowe sposoby na zaimplementowanie bufora cyklicznego:
     * - przechowywać dwa wskaźniki: na początek i na konieć kolejki
     * - przechowywać wskaźnik na początek kolejki oraz ilość elementów (wg mnie prostszy sposób)
     * Dla zainteresowanych tematem: https://www.snellman.net/blog/archive/2016-12-13-ring-buffers/
     *
     * @throws IllegalArgumentException jeśli ilość elementów przekracza pojemność
     */
    public <T> SdaQueue<T> createQueue(int capacity, T... elements) {
        return new SdaCircularBuffer<>(capacity, elements);
    }

    public interface SdaQueue<T> {

        /**
         * Zwraca true jeśli kolejka jest pusta.
         */
        boolean isEmpty();

        /**
         * Zwraca true jeśli kolejka jest pełna.
         */
        boolean isFull();

        /**
         * Zwraca rozmiar listy (ilość elementów).
         */
        int size();

        /**
         * Zwraca pierwszy element kolejki bez usuwania go.
         *
         * @throws NoSuchElementException jeśli kolejka jest pusta
         */
        T peek();

        /**
         * Usuwa i zwraca pierwszy element kolejki.
         *
         * @throws NoSuchElementException jeśli kolejka jest pusta
         */
        T dequeue();

        /**
         * Dodaje nowy element na końcu kolejki.
         *
         * @throws IllegalStateException jeśli kolejka jest pełna
         */
        void enqueue(T element);
    }

    /**
     * Tworzy nową kolejkę o stałej pojemności 'capacity', zawierającą podane elementy.
     * <p>
     * Uwaga: kolejkę należy zaimplementować w oparciu o bufor cykliczny.
     * Opis do znalezienia na Wikipedii: https://pl.wikipedia.org/wiki/Bufor_cykliczny
     * <p>
     * Uwaga: najłatwiej będzie implementować metody w takiej kolejności jak są zadeklarowane.
     * <p>
     * Podpowiedź: są dwa podstawowe sposoby na zaimplementowanie bufora cyklicznego:
     * - przechowywać dwa wskaźniki: na początek i na konieć kolejki
     * - przechowywać wskaźnik na początek kolejki oraz ilość elementów (wg mnie prostszy sposób)
     * Dla zainteresowanych tematem: https://www.snellman.net/blog/archive/2016-12-13-ring-buffers/
     *
     * @throws IllegalArgumentException jeśli ilość elementów przekracza pojemność
     */
    private static class SdaCircularBuffer<T> implements SdaQueue<T> {

        private final T[] elements;
        private int elementsNumber;
        private int queueBeginning;

        public SdaCircularBuffer(int capacity, T[] elements) {
            if (elements.length > capacity) {
                throw new IllegalArgumentException("Too many elements");
            }
            this.elements = Arrays.copyOf(elements, capacity);
            this.elementsNumber = elements.length;
        }

        @Override
        public boolean isEmpty() {
            return elementsNumber == 0;
        }

        @Override
        public boolean isFull() {
            return elements.length == elementsNumber;
        }

        @Override
        public int size() {
            return elementsNumber;
        }

        @Override
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("There's nobody here but us chickens");
            }
            return elements[queueBeginning];
        }

        @Override
        public T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("There's nobody here but us chickens");
            }
            T firstElement = elements[queueBeginning];
            elements[queueBeginning] = null;
            queueBeginning = (queueBeginning + 1) % elements.length;
            elementsNumber = elementsNumber - 1;
            return firstElement;
        }

        @Override
        public void enqueue(T element) {
            if (isFull()) {
                throw new IllegalStateException("Can't eat anything else, we're already full!");
            }
            elements[((queueBeginning + elementsNumber) % elements.length)] = element;
            elementsNumber++;
        }
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Funkcja odwraca podany stos.
     * <p>
     * Uwaga: wolno używać jedynie metod pop(), push(), peek() i isEmpty()
     * oraz nie wolno tworzyć innych kolekcji.
     * <p>
     * Podpowiedź: pytać o podpowiedzi :)
     */
    public <T> void reverseStack(Deque<T> stack) {
        if (!stack.isEmpty()) {
            T temp = stack.pop();
            reverseStack(stack);
            reverseOrder(stack, temp);
        }
    }

    private <T> void reverseOrder(Deque<T> stack, T temp) {
        if (stack.isEmpty()) {
            stack.push(temp);
        } else {
            T temp2 = stack.pop();
            reverseOrder(stack,temp);
            stack.push(temp2);
        }
    }

    /**
     * Funkcja oblicza podane wyrażenie, np: dla "2 * 3 + 7 * 8" zwraca 62.
     * <p>
     * Dla ułatwienia przyjmij, że wszystkie symbole (tzn. liczby, operatory, nawiasy) są rozdzielone spacjami:
     * "( 2 + 6 ) * 10 / 8" jest poprawnym wyrażeniem
     * "(2 + 6) * 10 / 8" jest niepoprawnym wyrażeniem
     * <p>
     * Uwaga: całą logikę najlepiej jest zaimplementować w osobnej klasie, np. Evaluator.
     * <p>
     * Podpowiedź: ewaluacja odbywa się w dwóch krokach:
     * 1. należy użyć algorytmu shunting-yard aby przekształcić wyrażenie do notacji postfiksowej,
     * czyli tzn. Odwrotnej Notacji Polskiej (Reverse Polish Notation)
     * 2. należy obliczyć wyrażenie zapisane w notacji postfiksowej
     * <p>
     * Oba algorytmy są opisane na Wikipedii: https://pl.wikipedia.org/wiki/Odwrotna_notacja_polska
     * <p>
     * W wersji podstawowej należy zaimplementować jedynie operatory +, -, * oraz /.
     * <p>
     * W wersji bardziej zaawansowanej należy zaimplementować dodatkowo dwuargumentowe funkcje min, max, np.:
     * "4 * min ( 30 , max ( 10 , 50 ) )" zwraca 120
     * <p>
     * Podpowiedź: wszystkie wymagane operatory (+, -, *, /) są lewostronnie łaczne.
     *
     * @throws IllegalArgumentException jeśli wyrażenie jest niepoprawne
     */
    public int evaluate(String expression) {
//        Deque<String> evaluateArray = new ArrayDeque<>();
//        expression.split("\\W");
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
