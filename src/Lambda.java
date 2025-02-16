import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Lambda {
    public static void main(String[] args) {
        List<String> words = List.of("hello", "world", "java", "stream", "lambda");
        words.stream()
            .filter(n -> n.length() > 4)
                .filter(n -> !n.contains("a"))
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
    }
}
