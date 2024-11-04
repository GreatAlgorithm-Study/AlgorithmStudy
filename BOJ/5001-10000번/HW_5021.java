import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_5021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 가족 관계 수
        int M = Integer.parseInt(st.nextToken()); // 왕위를 주장하는 사람 수

        String king = br.readLine(); // 나라를 세운 사람
        final long INF = 1L << 52; // 혈통 최대값 설정
        Map<String, Long> blood = new HashMap<>();
        blood.put(king, INF); // 설립자의 혈통 비율을 최대값으로 설정

        Map<String, List<String>> childMap = new HashMap<>(); // 부모 자식 관계 저장
        Map<String, Integer> parentCount = new HashMap<>(); // 자식의 부모 수 (위상 정렬용)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken(); // 자식 이름
            String parent1 = st.nextToken(); // 부모1 이름
            String parent2 = st.nextToken(); // 부모2 이름

            // 부모-자식 관계 저장
            childMap.putIfAbsent(parent1, new ArrayList<>());
            childMap.putIfAbsent(parent2, new ArrayList<>());
            childMap.get(parent1).add(child);
            childMap.get(parent2).add(child);

            // 자식의 부모 수 증가
            parentCount.put(child, parentCount.getOrDefault(child, 0) + 2);
            parentCount.putIfAbsent(parent1, 0);
            parentCount.putIfAbsent(parent2, 0);

            blood.putIfAbsent(child, 0L);  // 자식의 초기 혈통 값은 0
            blood.putIfAbsent(parent1, 0L); // 부모의 혈통 초기화
            blood.putIfAbsent(parent2, 0L); // 부모의 혈통 초기화
        }

        Queue<String> queue = new LinkedList<>();
        for (String person : parentCount.keySet()) {
            if (parentCount.get(person) == 0) {
                queue.add(person); // 부모가 없는 사람은 큐에 추가
            }
        }

        // 혈통 계산
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (childMap.containsKey(current)) {
                for (String child : childMap.get(current)) {
                    blood.put(child, blood.get(child) + blood.get(current) / 2);  // 부모로부터 혈통을 물려받음
                    parentCount.put(child, parentCount.get(child) - 1); // 부모수 -1
                    if (parentCount.get(child) == 0) {
                        queue.add(child); // 부모가 모두 계산된 자식은 큐에 추가
                    }
                }
            }
        }

        // 왕위를 주장하는 사람들 중 최고 혈통 찾기
        String answer = "";
        long maxBlood = 0;

        for (int i = 0; i < M; i++) {
            String claimant = br.readLine();
            long claimantBlood = blood.getOrDefault(claimant, 0L);

            if (claimantBlood > maxBlood) {
                maxBlood = claimantBlood;
                answer = claimant;
            }
        }

        System.out.println(answer);
    }
}