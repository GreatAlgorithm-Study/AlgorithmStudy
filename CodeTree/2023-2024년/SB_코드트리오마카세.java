import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_코드트리오마카세 {
    static int L;
    static HashMap<String, List<Sushi>> waitSushi = new HashMap<>();        // 사람 별 먹을 초밥 관리 대기열
    static HashMap<String, Enter> guestInfo = new HashMap<>();              // 손님의 입장 정보 관리
    static int INF = Integer.MAX_VALUE;
    static PriorityQueue<Node> pq = new PriorityQueue<>();                  // 바로 먹을 초밥 관리
    static int totalSushi = 0;
    static int totalGuest = 0;
    static StringBuilder sb = new StringBuilder();
    private static int getEatTime(Enter enter, Sushi sushi) {       // 손님의 입장 정보와 스시 생성 정보로 먹히는 시간 구하기
        int wait;
        if (enter.tm < sushi.tm) {      // 손님이 스시보다 빨리옴
            if (enter.pos < sushi.pos) wait = L - sushi.pos + enter.pos;    // (초밥이 손님보다 앞에 있음)초밥이 한바퀴 돌아서 손님한테 도착
            else wait = enter.pos - sushi.pos;                              // (초밥이 손님 뒤에 있음) 손님까지 거리만 이동하면 도착
            return sushi.tm + wait;     // 스시가 만들어져서 손님한테 전달되는 시간
        } else {                        // 스시가 손님보다 먼저 만들어짐
          int afterRotatePos = (sushi.pos + (enter.tm - sushi.tm)) % L;     // 손님이 도착할때까지 회전했을때 위치
          if (enter.pos < afterRotatePos) wait = L - afterRotatePos + enter.pos;
          else wait = enter.pos - afterRotatePos;
          return enter.tm + wait;
        }
    }

    private static void makeSushi(int t, int x, String name) {              // 초밥 만들기
        Sushi sushi = new Sushi(t, x, name, INF);
        if (!guestInfo.containsKey(name) || !guestInfo.get(name).isEnter) {   // 사람 안왔는데 스시 미리 만듦
            waitSushi.computeIfAbsent(name, k -> new ArrayList<>()).add(sushi);     // 스시 대기열에서 대기
        } else {        // 사람이 온 후에 스시가 만들어짐
            int eatTime = getEatTime(guestInfo.get(name), sushi);       // 회전해서 스시를 먹는 시간 구하기
            pq.offer(new Node(eatTime, name));                          // 바로먹을 큐에 삽입
        }
        totalSushi++;
    }

    private static void enterGuest(int t, int x, String name, int n) {      // 손님 입장
        guestInfo.put(name, new Enter(t, x, n));
        totalGuest++;

        if (waitSushi.containsKey(name) && !waitSushi.get(name).isEmpty()) {    // 해당 손님의 스시가 있을 경우 스시 먹기
            for (Sushi sushi : waitSushi.get(name)) {
                int eatTime = getEatTime(guestInfo.get(name), sushi);
                pq.offer(new Node(eatTime, name));                              // 바로 먹힐 초밥 관리하는 큐에 정보 저장
            }
            waitSushi.get(name).clear();
        }
    }

    private static void takePhoto(int t) {              // 사진 찍기 겸 진짜 초밥 먹기
        while (!pq.isEmpty()) {
            Node node = pq.peek();
            if (node.eatTm > t) break;                  // 현재 시간보다 늦게 먹히는 초밥이면 그만
            pq.poll();
            totalSushi--;
            guestInfo.get(node.guestName).cnt--;        // 먹은 초밥수--
            if (guestInfo.get(node.guestName).cnt==0) totalGuest--;     // 초밥 다 먹으면 나가기
        }
        sb.append(totalGuest).append(" ").append(totalSushi).append("\n");      // 사진 찍기
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 100) {
                int t = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                makeSushi(t, x, name);
            } else if (cmd == 200) {
                int t = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                enterGuest(t, x, name, n);
            } else {
                int t = Integer.parseInt(st.nextToken());
                takePhoto(t);
            }
        }
        System.out.println(sb);
    }

    static class Sushi{
        int tm, pos, et;     // 시간, 초밥 위치, 먹은 시간
        String owner;        // 초밥 주인

        public Sushi(int tm, int pos, String owner, int et) {
            this.tm = tm;
            this.pos = pos;
            this.owner = owner;
            this.et = et;
        }
    }

    static class Enter{
        int tm, pos, cnt;   // 시간, 위치, 먹은 개수
        boolean isEnter = true;        // 입장여부

        public Enter(int tm, int pos, int cnt) {
            this.tm = tm;
            this.pos = pos;
            this.cnt = cnt;
        }
    }

    static class Node implements Comparable<Node>{
        int eatTm;
        String guestName;

        public Node(int eatTm, String guestName) {
            this.eatTm = eatTm;
            this.guestName = guestName;
        }

        @Override
        public int compareTo(Node o) {
            return this.eatTm - o.eatTm;
        }
    }
}
