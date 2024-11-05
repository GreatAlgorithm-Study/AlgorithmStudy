import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간초과
public class SB_불안한_무빙워크 {
    static int N, K;
    static Deque<Node> que = new ArrayDeque<>();
    static Deque<Node> people = new ArrayDeque<>();
    static Node[] moving;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        moving = new Node[N * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N*2; i++) {
            int s = Integer.parseInt(st.nextToken());
            Node node = new Node(i, s);
            que.offer(node);
            moving[i] = node;
        }

        int cnt = 0;
        int nIdx = N-1;     // N번째에 있는 무빙 인덱스
        int test = 0;
        while (cnt < K) {
            // 무빙워크 이동
            Node cur = que.pollLast();
//            nIdx = (cur.idx + N - 1) % (N*2);
            nIdx = (nIdx - 1 + (N * 2)) % (N * 2);

           // 무빙 위의 사람 이동
            int size = people.size();
            for (int i = 0; i < size; i++) {
                Node p = people.poll();
                int nxt = (p.idx + 1) % (2 * N);
                boolean flag = true;
                if (!moving[nxt].isPeople && moving[nxt].save > 0) {
                    p.offPeople();
                    moving[nxt].onPeople();
                    moving[nxt].save--;
                    if (nxt == nIdx) {
                        moving[nxt].offPeople();
                        people.remove(moving[nxt]);     // 무빙에 있는 사람리스트에서 삭제
                        flag = false;
                    }
                    if (moving[nxt].save == 0 && !moving[nxt].check) {
                        moving[nxt].check = true;
                        cnt++;
                    }
                }
                if (flag) {            // 도착한 사람은 제외하고
                    people.offer(p); // 다시 people에 추가
                }
            }

            // 0번칸에 사람 올리기
            if (cur.save > 0 && !cur.isPeople) {
                cur.onPeople();
                cur.save--;
                people.add(cur);
                if (cur.save==0 && !cur.check){
                    cur.check = true;
                    cnt++;
                }
            }

            // 현재 노드 다시 삽입
            que.offerFirst(cur);
            test++;
        }

        System.out.println(test);
    }

    static class Node{
        int idx;
        int save;
        boolean isPeople;
        boolean check;

        Node(int idx, int save) {
            this.idx = idx;
            this.save = save;
            this.isPeople = false;
            this.check = false;
        }

        public void onPeople() {
            this.isPeople = true;
        }
        public void offPeople() {
            this.isPeople = false;
        }
    }
}


/*
 * 틀린 두번째 코드
 * Deque<Node>가 아닌 배열로 수정
 */

class SB_불안한_무빙워크_WA {
    static int N, K;
    static int startIdx = 0;
    static int cnt = 0, step = 0;
    static int[] moving;
    static boolean[] isPeople;

    private static void movePeople() {
        for (int i = N - 1; i > 0; i--) {
            int curIdx = (startIdx + i) % (2 * N);
            int nxtIdx = (curIdx + 1) % (2 * N);

            // 사람 한칸 이동
            if (isPeople[curIdx] && !isPeople[nxtIdx] && moving[nxtIdx] > 0) {
                isPeople[curIdx] = false;
                isPeople[nxtIdx] = true;
                moving[nxtIdx]--;
                if (moving[nxtIdx] == 0) cnt++;
            }

            // N번째 칸에 도착하면 내리기
            isPeople[(startIdx + N - 1) % (2 * N)] = false;
        }
    }


    public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        moving = new int[2 * N];
        isPeople = new boolean[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N*2; i++) {
            moving[i] = Integer.parseInt(st.nextToken());
        }

        while (cnt < K) {
            // 무빙워크 이동
            startIdx = (startIdx- 1 + (N * 2)) % (N * 2);

            // 무빙 위의 사람 이동
            movePeople();

            // 0번칸에 사람 올리기
            int zeroIdx = startIdx;
            if (moving[zeroIdx] > 0 && !isPeople[zeroIdx]) {
                isPeople[zeroIdx] = true;
                moving[zeroIdx]--;
                if (moving[zeroIdx] == 0) cnt++;
            }

            step++;
        }

        System.out.println(step);
    }
}