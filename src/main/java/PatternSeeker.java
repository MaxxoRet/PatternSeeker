

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PatternSeeker {
    static Integer phraseWords = 4;

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> result = new HashMap<>();
        Scanner sc = new Scanner(new File("C://dumps/textout2.txt"));
        List<String> strings = new ArrayList<>();
        while (sc.hasNextLine()) {
            strings.add(sc.nextLine());
        }

        for (int i = 0; i < strings.size(); i++) {
            var words = Arrays.stream(strings.get(i).split(" ")).toList();
            for (int j = 0; j < words.size(); j++) {
                var phrase = words.get(j);
                var iter = 0;
                while (iter <= phraseWords && j + iter < words.size()) {
                    var count = 0;
                    for (var s : strings
                    ) {
                        if (s.contains(phrase)) count++;
                    }
                    if (count > 0 && phrase.length() > 3) result.merge(phrase, count, Integer::sum);
                    iter++;
                    if (j + iter < words.size()) phrase = phrase + " " + words.get(j + iter);
                }
            }
        }

        System.out.println(sortByValue(result));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}