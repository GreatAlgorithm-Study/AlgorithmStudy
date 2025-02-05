import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class JW_5021_2 {
    static HashMap<String, Double> bloodMap = new HashMap<>();
    static HashMap<String, ArrayList<String>> childMap = new HashMap<>();
    static HashMap<String, String[]> parentMap = new HashMap<>();
    static HashMap<String, Integer> indegreeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        bloodMap.put(br.readLine(), 1D);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();
            indegreeMap.put(child, 2);
            indegreeMap.putIfAbsent(parent1, 0);
            indegreeMap.putIfAbsent(parent2, 0);
            childMap.putIfAbsent(parent1, new ArrayList<>());
            childMap.putIfAbsent(parent2, new ArrayList<>());
            childMap.get(parent1).add(child);
            childMap.get(parent2).add(child);
            parentMap.put(child, new String[] { parent1, parent2 });
        }
        // 위상 정렬
        Deque<String> dq = new ArrayDeque<>();
        for (String name : indegreeMap.keySet())
            // 위상 정렬 초기화
            if (indegreeMap.get(name) == 0)
                dq.offer(name);
        while (!dq.isEmpty()) {
            String name = dq.poll();
            // 부모가 없는 경우에는 왕족이 아님
            if (!parentMap.containsKey(name))
                bloodMap.putIfAbsent(name, 0D);
            else {
                String[] parents = parentMap.get(name);
                bloodMap.put(name, bloodMap.get(parents[0]) / 2 + bloodMap.get(parents[1]) / 2); // 혈통 계산
            }
            // 진입 차수 줄이기
            for (String child : childMap.getOrDefault(name, new ArrayList<String>())) {
                indegreeMap.put(child, indegreeMap.get(child) - 1);
                // 진입 차수가 0이라면, 큐(덱)에 삽입
                if (indegreeMap.get(child) == 0)
                    dq.offer(child);
            }
        }
        String next = "";
        double maxBlood = -1;
        while (m-- > 0) {
            String name = br.readLine();
            if (bloodMap.getOrDefault(name, 0D) > maxBlood) {
                next = name;
                maxBlood = bloodMap.getOrDefault(name, 0D);
            }
        }
        System.out.println(next);
    }
}
