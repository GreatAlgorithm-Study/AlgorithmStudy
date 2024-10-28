import java.util.*;
import java.io.*;

public class YJ_21939 {
    static TreeMap<Integer, TreeSet<Integer>> workbook = new TreeMap<>();  //<난이도,문제번호>
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int difficulty = Integer.parseInt(st.nextToken());
            workbook.computeIfAbsent(difficulty, set -> new TreeSet<>())
                    .add(number);
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String say = st.nextToken();
            switch (say) {
                case "recommend":
                    recommend(Integer.parseInt(st.nextToken()));
                    break;
                case "add":
                    add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    break;
                case "solved":
                    solved(Integer.parseInt(st.nextToken()));
                    break;
            }
        }

        br.close();
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
        Iterator<Map.Entry<Integer, TreeSet<Integer>>> iterator = workbook.entrySet().iterator();
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
