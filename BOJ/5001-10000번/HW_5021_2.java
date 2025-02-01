import java.io.*;
import java.util.*;

class HW_5021_2 {
    static Map<String, List<String>> graph = new HashMap<>(); // 부모 정보를 저장하는 그래프
    static Map<String, Double> blood = new HashMap<>(); // 혈통 비율 저장
    static String king; // 왕조 창시자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 가족 관계 개수
        int M = Integer.parseInt(st.nextToken()); // 후보 수
        king = br.readLine(); // 왕조 창시자 이름

        blood.put(king, 1.0); // 창시자의 혈통 확률은 1.0

        // 그래프 및 혈통 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();

            // 부모 연결 정보 저장 (자식 -> 부모 리스트)
            graph.putIfAbsent(parent1, new ArrayList<>());
            graph.putIfAbsent(parent2, new ArrayList<>());
            graph.putIfAbsent(child, new ArrayList<>());
            graph.get(child).add(parent1);
            graph.get(child).add(parent2);

            // 혈통 정보 초기화 (없다면 0.0)
            blood.putIfAbsent(parent1, 0.0);
            blood.putIfAbsent(parent2, 0.0);
            blood.putIfAbsent(child, 0.0);
        }

        // BFS를 통해 혈통 확률 계산
        bfs();

        // 왕위 계승자 찾기
        String successor = "";
        double maxBlood = -1.0;

        for (int i = 0; i < M; i++) {
            String candidate = br.readLine();
            if (blood.containsKey(candidate) && blood.get(candidate) > maxBlood) {
                maxBlood = blood.get(candidate);
                successor = candidate;
            }
        }

        System.out.println(successor);
    }

    // BFS를 이용한 혈통 확률 계산
    private static void bfs() {
        Queue<String> queue = new LinkedList<>();

        // 창시자(왕조 시작 인물)부터 시작
        queue.add(king);

        while (!queue.isEmpty()) {
            String person = queue.poll();

            if (!graph.containsKey(person)) continue; // 부모가 없는 경우 스킵

            for (String child : graph.keySet()) {
                List<String> parents = graph.get(child);
                if (parents.size() < 2) continue; // 부모가 없으면 무시

                double parent1Blood = blood.getOrDefault(parents.get(0), 0.0);
                double parent2Blood = blood.getOrDefault(parents.get(1), 0.0);

                double newBlood = (parent1Blood + parent2Blood) / 2.0;

                if (newBlood > blood.get(child)) { // 새로운 혈통이 더 크면 갱신
                    blood.put(child, newBlood);
                    queue.add(child); // 혈통이 갱신된 경우 자식을 큐에 추가
                }
            }
        }
    }
}