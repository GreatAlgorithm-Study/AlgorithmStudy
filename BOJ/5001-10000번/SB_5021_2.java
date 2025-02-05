import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class SB_5021_2 {
    static int N, M;
    static HashMap<String, Double> info = new HashMap<>();
    static HashMap<String, List<String>> parents = new HashMap<>();

    private static double cal(String x) {
        if (info.containsKey(x)) return info.get(x);    // 이미 계산된 값이면 재사용
        if (!parents.containsKey(x)) return 0.0;        // 부모 정보 없으면 혈통 없음

        List<String> pp = parents.get(x);

        double bp1 = cal(pp.get(0));
        double bp2 = cal(pp.get(1));
        double blood = (bp1 + bp2) / 2;

        info.put(x, blood);     // 계산된 혈통 값 저장 (메모이제이션)
        return blood;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String king = br.readLine();
        info.put(king, 1.0);        // 자기 혈통 정보

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String p1 = st.nextToken();
            String p2 = st.nextToken();

            // 부모정보 구성하기
            if (!parents.containsKey(child)) parents.put(child, new ArrayList<>());
            parents.get(child).add(p1);
            parents.get(child).add(p2);
        }

        // 만들어진 부모 정보를 바탕으로 혈통 계산
        for (String me : parents.keySet()) {
            cal(me);
        }

        double mx = -1.0;
        String ans = "";
        for (int i = 0; i < M; i++) {
            String candi = br.readLine();
            double blood = info.getOrDefault(candi, 0.0);
            if (blood > mx) {
                mx = blood;
                ans = candi;
            }
        }
        System.out.println(ans);
    }
}
