import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class JW_21939 {

    static class Problem {
        int num, level;

        Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 문제 레벨과 번호로 정렬해서 저장할 트리 맵
        TreeSet<Problem> ts = new TreeSet<>((o1, o2) -> o1.level != o2.level ? o1.level - o2.level : o1.num - o2.num);
        // 현재 트리 맵에 어떤 정보가 저장되어 있는지 알려줄 해시 맵
        HashMap<Integer, Integer> hm = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l);
            ts.add(problem);
            hm.put(p, l);
        }
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int l;
            // 명령어 처리
            switch (oper) {
            case "recommend":
                if (p == 1)
                    sb.append(ts.last().num);  // 가장 끝에 있는 문제
                else
                    sb.append(ts.first().num); // 처음에 있는 문제
                sb.append("\n");
                break;
            case "add":
                l = Integer.parseInt(st.nextToken()); // 문제 난이도
                ts.add(new Problem(p, l)); // 현재 가지고 있는 문제 트리 맵에 입력
                hm.put(p, l); // 가지고 있는 문제를 조회하기 위한 해시 맵에 입력
                break;
            case "solved":
                l = hm.get(p); // 해당 문제의 난이도를 가져옴
                Problem problem = new Problem(p, l);
                ts.remove(problem); // 만들어진 오브젝트와 동일한 객체가 있다면 제거
                hm.remove(p); // 맵에서도 제거
                break;
            }
        }
        System.out.println(sb);
    }
}