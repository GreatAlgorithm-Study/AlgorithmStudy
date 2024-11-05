import java.util.ArrayDeque;
import java.util.Deque;

public class JW_불안한_무빙워크 {

    static class Pad {      // 무빙워크의 각 칸
        int safety;         // 해당 칸의 안정성
        boolean person;     // 해당 칸에 사람이 있는지 없는지

        Pad(int safety) {
            this.safety = safety;
            this.person = false;
        }
    }

    static Deque<Pad> upDq = new ArrayDeque<>(); // 위쪽 무빙워크
    static Deque<Pad> dwDq = new ArrayDeque<>(); // 아랫쪽 무빙워크
    static int n, cnt = 0;                       // 무빙워크의 길이 및 안정성 0인 칸의 수

    public static void main(String[] args) throws Exception {
        n = read();
        int k = read();
        for (int i = 0; i < n; i++)
            upDq.offer(new Pad(read()));
        for (int i = 0; i < n; i++)
            dwDq.offer(new Pad(read()));
        int time = 0;                       // 시험 횟수
        while (cnt < k) {                   // 종료 조건
            time++;
            trial();                        // 실험 실행
        }
        System.out.println(time);           // 횟수 출력
    }

    private static void trial() {
        // 1. 회전
        dwDq.offerFirst(new Pad(upDq.pollLast().safety));
        upDq.offerFirst(new Pad(dwDq.pollLast().safety));
        
        // 2. 사람 이동
        // 앞에서 꺼내서 뒤에 집어넣는 작업을 n번 해주면 원래대로 돌아옴
        // 회전하면서 사람만 이동하도록 로직 작성
        
        // 첫 사람은 그냥 빠져나감
        upDq.offerFirst(new Pad(upDq.pollLast().safety));
        
        for (int i = 1; i < n; i++) {
            // 움직여야 할 칸에 사람이 있다면
            if (upDq.peekLast().person)
                // 앞에 사람이 있는지, 안전한지 확인
                if (!upDq.peekFirst().person && upDq.peekFirst().safety > 0) {
                    upDq.peekFirst().safety--;          // 앞 칸 안정성 감소
                    if (upDq.peekFirst().safety == 0)   // 안정성이 0이 되면 cnt 증가
                        cnt++;
                    upDq.peekFirst().person = true;     // 앞 칸에 사람 이동
                    upDq.peekLast().person = false;     // 현재 칸은 비움
                }
            upDq.offerFirst(upDq.pollLast());           // 칸 이동
        }
        
        // 3. 사람 추가
        // 맨 첫 칸에 사람이 없고 안전하다면 사람 추가
        if (!upDq.peekFirst().person && upDq.peekFirst().safety > 0) {
            upDq.peekFirst().safety--;
            if (upDq.peekFirst().safety == 0)
                cnt++;
            upDq.peekFirst().person = true;
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}