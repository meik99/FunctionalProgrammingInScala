package index;

import at.jku.ssw.fp.util.Numbered;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordIndex {

    private static final String FILE_NAME = "files/sampletext.txt";
//    private static final String FILE_NAME = "files/faust_1.txt";

    public static void main(String[] args) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {

            SortedMap<String, SortedSet<Integer>> index =
                    // Task 7.1: Numbering the lines
                    number(lines)
                            // Task 7.2: Words with line numbers
                            .flatMap(numbered -> Arrays
                                    .stream(numbered.value.split("[ .,;?!.:()]"))
                                    .map(word -> word.trim().toLowerCase(Locale.ROOT))
                                    .filter(word -> word.length() > 0 && Character.isLetter(word.charAt(0)))
                                    .map(word -> Numbered.of(numbered.n, word))
                            )
                            // Task 7.3:  Build the line index
                            .collect(Collectors.groupingBy(
                                    numberedWords -> numberedWords.value,
                                    TreeMap<String, SortedSet<Integer>>::new,
                                    Collector.of(
                                            TreeSet<Integer>::new,
                                            (lineSet, numberedWord) -> lineSet.add(numberedWord.n),
                                            (a, b) -> {
                                                a.addAll(b);
                                                return a;
                                            }
                                    )
                            ));

            System.out.println("\nIndex: ");

            // TODO: Task 7.4: Output
            index.forEach((word, lineNumbers) -> {
                System.out.printf("%s: %s\n",
                        word,
                        lineNumbers.stream()
                                .map(number -> String.valueOf(number))
                                .collect(Collectors.joining(", ")));
            });
        }

    }

    // TODO: Task 7.5: Numbering
    private static <A extends Comparable<A>> Stream<Numbered<A>> number(Stream<A> as) {
        return as.reduce(new LinkedList<Numbered<A>>(), (list, line) -> {
                    list.add(Numbered.of(list.size() + 1, line));
                    return list;
                }, (a, b) -> {
                    a.addAll(b);
                    return a;
                })
                .stream();
    }

}
