import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_28707 {
    static int N, M;
    static Node[] cmd;
    static HashMap<Integer, Integer> map = new HashMap<>();

    private static int[] swap(int a, int b, int[] cur){
        int[] tmpArr = Arrays.copyOf(cur, cur.length);
        int tmp = tmpArr[a];
        tmpArr[a] = tmpArr[b];
        tmpArr[b] = tmp;
        return tmpArr;
    }

    private static int arr2int(int[] arr) {
        int num = 0;
        for (int a : arr) {
            num = num * 10 + a;
        }
        return num;
    }

    private static void dijkstra(int[] start) {     // 각 배열의 상태를 노드로 보는 다익스트라 진행
        Queue<int[]> que = new ArrayDeque<>();      // 배열 스왑을 진행해야하기에 큐 값은 배열로 가짐
        que.add(start);

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curNum = arr2int(cur);              // 현재 배열을 숫자로 변경한 값
            for (Node cm : cmd) {
                int[] nxt = swap(cm.u, cm.v, cur);  // 스왑 진행
                int nxtNum = arr2int(nxt);
                if (map.containsKey(nxtNum)){       // 이미 해당 배열 상태가 존재할 경우
                    if (map.get(nxtNum) > map.get(curNum) + cm.c) {     // 비용이 더 적으면 업데이트
                        map.put(nxtNum, map.get(curNum) + cm.c);
                        que.add(nxt);
                    }
                } else {                            // 존재하지 않으면 새롭게 값 추가
                    map.put(nxtNum, map.get(curNum) + cm.c);
                    que.add(nxt);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        cmd = new Node[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            cmd[i] = new Node(l, r, c);
        }

        int start = arr2int(arr);       // 처음 시작 값 설정
        map.put(start, 0);

        dijkstra(arr);                  // 다익스트라 실행
        Arrays.sort(arr);               // 정렬된 타겟 값 구하기
        int target = arr2int(arr);

        if (map.containsKey(target)) {  // map에 타겟값이 있으면 비용 반환
            System.out.println(map.get(target));
        } else System.out.println(-1);  // 없으면 -1

    }
    static class Node{
        int u, v, c;
        public Node(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
}
