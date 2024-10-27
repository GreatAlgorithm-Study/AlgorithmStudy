import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 왕위 계승 주장하는 사람 중, 혈통가까운 사람 찾기
// - 모든 자식의 부모는 유일, 계층적
// 1. 후보들의 부모 피를 바탕으로 혈통 계산하기
// 2. 그러기 위해선 가족구성도 만들고, 이를 바탕으로 피 계산하기
public class SB_5021 {
    static int N, M;
    static String king;
    static HashMap<String, Double> info = new HashMap<>();
    static HashMap<String, List<String>> family = new HashMap<>();

    private static double cal(String p) {
        if (info.containsKey(p)) return info.get(p);
        if (!family.containsKey(p)) return 0.0;

        List<String> parents = family.get(p);
        String p1 = parents.get(0);
        String p2 = parents.get(1);

        double bp1 = cal(p1);
        double bp2 = cal(p2);
        double blood = (bp1 + bp2) / 2;

        info.put(p, blood);
        return blood;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        king = br.readLine();
        info.put(king, 1.0);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String p1 = st.nextToken();
            String p2 = st.nextToken();

            family.putIfAbsent(child, new ArrayList<>());
            family.get(child).add(p1);
            family.get(child).add(p2);
        }

        for (String p : family.keySet()) {
            cal(p);
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
