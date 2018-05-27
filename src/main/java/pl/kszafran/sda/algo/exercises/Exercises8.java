package pl.kszafran.sda.algo.exercises;

import java.util.*;

public class Exercises8 {

    /**
     * Funkcja zwraca zbiór wartości, które występują więcej niż raz w liście "values".
     * <p>
     * Uwaga: rozwiązanie musi działać w czasie O(n).
     */
    public <T> Set<T> findDuplicates(List<T> values) {
        Set<T> duplicates = new HashSet<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.subList(i + 1, values.size()).contains(values.get(i))) {
                duplicates.add(values.get(i));
            }
        }
        return duplicates;
    }

    /**
     * Funkcja zwraca mapę przypisującą każdej wartości jej ilość wystąpień w liście "values".
     */
    public <T> Map<T, Integer> countOccurrences(List<T> values) {
        Map<T, Integer> countMap = new HashMap<>();
        for (T value : values) {
            if (countMap.containsKey(value)) {
                countMap.replace(value, countMap.get(value) + 1);
            } else {
                countMap.put(value, 1);
            }
        }
        return countMap;
    }

    /**
     * Funkcja zwraca zbiór wszystkich wartości występujących w obu podanych listach.
     */
    public <T> Set<T> findCommonValues(List<T> list1, List<T> list2) {
        Set<T> commonValues = new HashSet<>(list1);
        commonValues.retainAll(list2);
        return commonValues;
    }

    /**
     * Funkcja łączy wartości nagłówków HTTP o tej samej nazwie.
     * <p>
     * Argumentem funkcji jest lista nagłówków HTTP w postaci "nazwa:wartość".
     * Każdy nagłówek znajduje się w osobnej linijce.
     * Nazwa nagłówka nie może zawierać znaku ":".
     * <p>
     * Jeśli nagłówek o tej samej nazwie występuje wielokrotnie, w wartości wynikowej powinien
     * pojawić się tylko raz, a jego wartości powinny zostać złączone znakiem ",".
     * <p>
     * Np. dla:
     * <p>
     * aaa:123
     * bbb:897
     * aaa:432
     * <p>
     * funkcja zwraca:
     * <p>
     * aaa:123,432
     * bbb:897
     * <p>
     * Jeśli ta sama wartość pojawia się wielokrotnie, powinna zostać zamieszczona tylko raz.
     * <p>
     * Np. dla:
     * <p>
     * aaa:123
     * bbb:897
     * aaa:123
     * <p>
     * funkcja zwraca:
     * <p>
     * aaa:123
     * bbb:897
     * <p>
     * Względna kolejność nagłówków musi zostać zachowana.
     * Względna kolejność wartości danego nagłówka musi zostać zachowana.
     *
     * @throws IllegalArgumentException jeśli linijka zawiera niepoprawny nagłówek, puste linie są dopuszczalne
     */
    public String mergeHeaders(String headers) {
        Map<String, String> mergeMap = new LinkedHashMap<>();
        for (String header : headers.split("\n")) {
            if (!header.matches("\\w+:\\w+")) {
                throw new IllegalArgumentException("You shall not pass!");
            }
            String[] element = header.split(":", 2);
            mergeMap.put(element[0],element[1]);
        }
        return mergeMap.toString();
    }

    /**
     * Funkcja działa tak samo jak mergeHeaders, z tym że:
     * - nagłówki zwracane są w kolejności alfabetycznej
     * - wartości danego nagłówka ustawione są w kolejności alfabetycznej
     */
    public String normalizeHeaders(String headers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
