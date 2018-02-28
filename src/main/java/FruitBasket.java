
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FruitBasket {
    private static String fruit = "/Users/igortimokhin/Documents/MyProjects/ComcastAssignment/basket.csv";

    public static void main(String[] args) throws IOException {

        countFruitsTypes(fruit);
    }

    //1.	Total number of fruit (count lines without header)
    //2.	Total types of fruit
    //3.	The number of each type of fruit in descending order
    //4.	The characteristics (size, color, shape, etc.) of each fruit by type
    //5.	Have any fruit been in the basket for over 3 days

    public static void countFruitsTypes(String items) throws IOException {
        int count = 0;
        String line;
        String keyChar;
        String keyChar1;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(items));

        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> hashMapChar = new HashMap<>();
        Map<String, Integer> hashMapDays = new HashMap<>();

        boolean header = true;
        while ((line = bufferedReader.readLine()) != null) {
            if (header) {
                header = false;
                continue;
            }
            count++;
            String[] strings = line.split(",");
            keyChar = strings[0] + " : " + strings[2].trim() + ", " + strings[3].trim();
            keyChar1 = strings[0] + " : " + strings[3].trim() + ", " + strings[2].trim();
            if (!hashMap.containsKey(strings[0])) {
                hashMap.put(strings[0], 1);
            } else {
                hashMap.replace(strings[0], hashMap.get(strings[0]),
                        hashMap.get(strings[0]) + 1);
            }
            if (!hashMapChar.containsKey(keyChar) && !hashMapChar.containsKey(keyChar1)) {
                hashMapChar.put(keyChar, 1);
            } else if (hashMapChar.containsKey(keyChar)) {
                hashMapChar.replace(keyChar,
                        hashMapChar.get(keyChar),
                        hashMapChar.get(keyChar) + 1);
            } else {
                hashMapChar.replace(keyChar1,
                        hashMapChar.get(keyChar1),
                        hashMapChar.get(keyChar1) + 1);
            }
            if (Integer.parseInt(strings[1]) > 3) {
                if (!hashMapDays.containsKey(strings[0])) {
                    hashMapDays.put(strings[0], 1);
                } else {
                    hashMapDays.replace(strings[0], hashMapDays.get(strings[0]),
                            hashMapDays.get(strings[0]) + 1);
                }
            }
        }
        System.out.println("Total number of fruits: " + count);
        System.out.println("Total types of fruit: " + hashMap.size());

        System.out.println("The number of each type of fruit in descending order: ");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            {
                System.out.println(key + " : " + value);
            }

        }
        System.out.println("The characteristics of each fruit by type: ");
        for (Map.Entry<String, Integer> entryChar : hashMapChar.entrySet()) {
            String key = entryChar.getKey();
            Object value = entryChar.getValue();
            System.out.println(value + " " + key);
        }
        System.out.println("Have any fruit been in the basket for over 3 days:");
        for (Map.Entry<String, Integer> entryDays : hashMapDays.entrySet()) {
            String key = entryDays.getKey();
            Object value = entryDays.getValue();

            System.out.println(value + " " + key);


        }
    }
}

