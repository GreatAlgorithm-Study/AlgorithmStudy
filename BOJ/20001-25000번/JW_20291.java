import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class JW_20291 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> tm = new TreeMap<>();  // 확장자를 정렬하고, 카운트하기 위한 트리맵
        for (int i = 0; i < n; i++) {
            String ext = br.readLine().split("\\.")[1]; // 확장자
            tm.put(ext, tm.getOrDefault(ext, 0) + 1);   // 확장자 카운트
        }
        StringBuilder sb = new StringBuilder();
        // 조건에 맞게 출력
        for (String ext : tm.keySet())
            sb.append(ext).append(" ").append(tm.get(ext)).append("\n");
        System.out.println(sb);
    }
}