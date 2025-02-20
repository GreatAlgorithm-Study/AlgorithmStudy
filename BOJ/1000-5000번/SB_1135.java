import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1135 {
    static int N;
    static List<List<Integer>> underling = new ArrayList<>();

    private static int call(int node) {
        ArrayList<Integer> lst = new ArrayList<>();

        if (underling.get(node).isEmpty()) {    // 리프노드면 0반환
            return 0;
        }

        for (int ud : underling.get(node)) {
            lst.add(call(ud));          // 각 부하들의 전화단계 구하기
        }

        lst.sort(Collections.reverseOrder());
        for (int i = 0; i < lst.size(); i++) {
            lst.set(i, lst.get(i) + i + 1);
        }

        return Collections.max(lst);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            underling.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss==-1) continue;
            underling.get(boss).add(i);
        }

        System.out.println(call(0));
    }
}
