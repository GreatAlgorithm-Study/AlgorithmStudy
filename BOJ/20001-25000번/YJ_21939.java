import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class YJ_21939 {
    static TreeMap<Integer, TreeSet<Integer>> workbook = new TreeMap<>();  //<난이도,문제번호>
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int number = scanner.nextInt();
            int difficulty = scanner.nextInt();
            workbook.computeIfAbsent(difficulty, set -> new TreeSet<>())
                    .add(number);
        }

        while (scanner.hasNext()) {
            String say = scanner.next();
            switch (say) {
                case "recommend":
                    recommend(scanner.nextInt());
                    break;
                case "add":
                    add(scanner.nextInt(), scanner.nextInt());
                    break;
                case "solved":
                    solved(scanner.nextInt());
                    break;
            }
        }

        scanner.close();
    }

    static void recommend(int difficulty) {
        if (difficulty == 1) {
            System.out.println(workbook.lastEntry().getValue().last());
        } else {
            System.out.println(workbook.firstEntry().getValue().first());
        }
    }

    static void add(int number, int difficulty) {
        workbook.computeIfAbsent(difficulty, set -> new TreeSet<>())
                .add(number);
    }

    static void solved(int number) {
        Iterator<Entry<Integer, TreeSet<Integer>>> iterator = workbook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, TreeSet<Integer>> entry = iterator.next();
            if (entry.getValue().remove(number)) {
                if (entry.getValue().isEmpty()) {
                    iterator.remove();
                }
                return;
            }
        }
    }
}
