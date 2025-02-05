import java.util.*;

class HW_118669 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int w = paths[i][2];
            graph[a].add(new int[]{b, w}); // 양방향
            graph[b].add(new int[]{a, w});
        }

        Set<Integer> gateSet = new HashSet<>(); // 출입구
        Set<Integer> summitSet = new HashSet<>(); // 산봉우리
        for (int i = 0; i < gates.length; i++) {
            gateSet.add(gates[i]);
        }
        for (int i = 0; i < summits.length; i++) {
            summitSet.add(summits[i]);
        }

        int[] intensity = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            intensity[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int i = 0; i < gates.length; i++) { // 모든 출입구를 시작점으로 초기화
            pq.offer(new int[]{gates[i], 0});
            intensity[gates[i]] = 0;
        }

        while (!pq.isEmpty()) { // 다익스트라
            int[] cur = pq.poll();
            int now = cur[0];
            int curIntensity = cur[1];

            if (intensity[now] < curIntensity) { // 현재 intensity가 이미 최솟값보다 크면 무시
                continue;
            }

            for (int i = 0; i < graph[now].size(); i++) { // 현재 지점에서 이동 가능한 모든 경로 탐색
                int[] next = graph[now].get(i);
                int nextNode = next[0];
                int weight = next[1];

                if (gateSet.contains(nextNode)) { // 산봉우리는 거쳐갈 수 없음
                    continue;
                }

                int newIntensity = Math.max(curIntensity, weight); // 현재 경로에서의 최대 intensity 갱신

                if (newIntensity < intensity[nextNode]) { // 더 작은 intensity로 업데이트
                    intensity[nextNode] = newIntensity;
                    pq.offer(new int[]{nextNode, newIntensity});
                }
            }
        }

        int minIntensity = Integer.MAX_VALUE;
        int bestSummit = -1;

        Arrays.sort(summits); // 산봉우리 번호가 낮은 것을 우선 처리하기 위함
        for (int i = 0; i < summits.length; i++) {
            int summit = summits[i];
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                bestSummit = summit;
            }
        }

        return new int[]{bestSummit, minIntensity};
    }
}