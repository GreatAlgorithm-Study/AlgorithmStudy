import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SB_23326 {
    static int N, Q;
    static TreeSet<Integer> place = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(st.nextToken())==1) place.add(i);
        }

        int cur = 0;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    int p = Integer.parseInt(st.nextToken()) - 1;
                    if (place.contains(p)) place.remove(p);
                    else place.add(p);
                    break;
                case 2:
                    int val = Integer.parseInt(st.nextToken());
                    cur = (cur + val) % N;
                    break;
                case 3:
                    if (place.isEmpty()) sb.append(-1).append('\n');
                    else if (place.contains(cur)) sb.append(0).append('\n');
                    else {
                        Integer right = place.higher(cur);
                        if (right != null) sb.append(right - cur).append('\n');
                        else {
                            Integer left = place.first();
                            sb.append(N - cur + left).append('\n');
                        }
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
