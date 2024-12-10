package hexlet.code;

import static hexlet.code.formatters.Formatter.getFormatter;
import static hexlet.code.FileUtils.parseFile;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;
import java.util.TreeSet;
import java.util.HashMap;

public final class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
//        System.out.println("data1 is: " + parseFile(filepath1));
//        System.out.println("data2 is: " + parseFile(filepath2));
        Map<String, Map<String, Object>> differences = getDiff(parseFile(filepath1), parseFile(filepath2));
//        System.out.println("differences is: " + differences);
        return getFormatter(formatName).format(differences);
    }

    private static Map<String, Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());
//        System.out.println("All keys: " + allKeys);
        Map<String, Map<String, Object>> result = new TreeMap<>();

        for (String key : allKeys) {
            try {
//                System.out.println("Processing key: " + key);

                Object value1 = data1.get(key);
                Object value2 = data2.get(key);

//                System.out.println("Value1: " + value1 + ", Value2: " + value2);

                Map<String, Object> diffEntry = new HashMap<>();
                if (data1.containsKey(key) && !data2.containsKey(key)) {
//                    System.out.println("Key removed: " + key + ", Value: " + value1);
                    diffEntry.put("type", "removed");
                    diffEntry.put("value1", value1);
                    diffEntry.put("value2", null);
                } else if (!data1.containsKey(key) && data2.containsKey(key)) {
//                    System.out.println("Key added: " + key + ", Value: " + value2);
                    diffEntry.put("type", "added");
                    diffEntry.put("value1", null);
                    diffEntry.put("value2", value2);
                } else if (Objects.equals(value1, value2)) {
//                    System.out.println("Key unchanged: " + key + ", Value: " + value1);
                    diffEntry.put("type", "unchanged");
                    diffEntry.put("value1", value1);
                    diffEntry.put("value2", value2);
                } else {
//                    System.out.println("Key changed: " + key + ", Value1: " + value1 + ", Value2: " + value2);
                    diffEntry.put("type", "changed");
                    diffEntry.put("value1", value1);
                    diffEntry.put("value2", value2);
                }

                result.put(key, diffEntry);
            } catch (Exception e) {
//                System.out.println("Error processing key: " + key);
                e.printStackTrace();
            }
        }

        return result;
    }

}
