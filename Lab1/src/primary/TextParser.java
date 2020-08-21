package primary;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextParser {
    //список слов -> список фраз длины N
    private static Map<ArrayList<String>, Long> wordsToStreamOfPhrases(List<String> wordsList, int n) {
        return IntStream
                .range(0, wordsList.size() - n + 1) //стрим из числового промежутка [start..end)
                .mapToObj(i -> new ArrayList<>(wordsList.subList(i, i + n))) //реобразуем в список фраз(списков слов)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
                        //собираем в Map(одинаковые фразы, их количество)
    }

    public void parse(List<String> words, int length, int minOfCount) {
        Map<ArrayList<String>, Long> mapOfnGrams = wordsToStreamOfPhrases(words, length);

        mapOfnGrams = mapOfnGrams //подсчет и сортировка фраз по количеству
                .entrySet()//
                .stream() //стрим из Map
                .filter(o -> o.getValue() >= minOfCount) // фильтр по значению(повторения)
                .sorted(Map.Entry.comparingByValue()) // сортировка по значению
                .collect(Collectors.toMap(//преобразование потока в Map
                        Map.Entry::getKey, //
                        Map.Entry::getValue/*,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new*/));

        mapOfnGrams // выыод ответа в консоль
                .forEach((key, value) -> System.out.println(key+" (" + value + ")"));
    }

}
