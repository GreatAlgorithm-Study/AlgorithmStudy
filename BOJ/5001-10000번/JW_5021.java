import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class JW_5021 {

    static HashMap<String, String[]> tree = new HashMap<>();   // 가계도
    static HashMap<String, Double> bloodMap = new HashMap<>(); // 혈통

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 트리 구조 생성
        String root = br.readLine();
        bloodMap.put(root, 1D);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();
            tree.put(child, new String[] { parent1, parent2 });
            bloodMap.put(child, 0D);
            bloodMap.putIfAbsent(parent1, 0D);
            bloodMap.putIfAbsent(parent2, 0D);
        }
        String answer = "";
        double maxBlood = 0;
        while (m-- > 0) {
            String name = br.readLine();
            double blood = recursive(name); // 재귀로 타겟의 혈통의 값을 찾아주기
            if (blood > maxBlood) {
                answer = name;
                maxBlood = blood;
            }
        }
        System.out.println(answer);
    }

    // 트리에서의 DP
    private static double recursive(String person) {
        // 미리 계산된 값이 있다면 반환 -> 메모이제이션
        if (bloodMap.containsKey(person) && bloodMap.get(person) > 0) {
            return bloodMap.get(person);
        }
        // 외부인일 경우 0을 반환
        if (!tree.containsKey(person)) {
            return 0;
        }
        String[] parents = tree.get(person);
        double parent1Blood = recursive(parents[0]) / 2.0; // 부모의 혈통 정보의 절반
        double parent2Blood = recursive(parents[1]) / 2.0; // 부모의 혈통 정보의 절반
        
        double personBlood = parent1Blood + parent2Blood; // 둘의 혈통을 합친 것이 타겟의 혈통
        bloodMap.replace(person, personBlood); // 메모이제이션
        return personBlood;
    }
}