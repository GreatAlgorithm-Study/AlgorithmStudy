import java.io.*;
import java.util.*;

public class DH_전자사전_만들기 {
    static class Node {
        String str; // 입력된 문자의 정보
        int idx;    // 몇 번쨰로 입력됐는지 저장하는 변수

        public Node(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }
    static BufferedReader br;
    static StringTokenizer st;
    static Node[] arr;
    static int N, T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
        System.out.println(sb);
    }

    static void solution() throws Exception {
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String str = st.nextToken();

            // lowerIdx를 찾으며 사전에서 str로 시작하는 단어의 시작 idx 찾기
            int lowerIdx = getLowerIdx(str);
            // uppderIdx를 찾으며 str로 시작하는 단어가 끝난 직후 idx 찾기
            int uppderIdx = getUpperIdx(str);

            int diff = (uppderIdx - lowerIdx) - 1;
            if(diff < a - 1) sb.append(- 1 + "\n");
            else {
                sb.append((arr[lowerIdx + (a - 1)].idx + 1) + "\n");
            }
        }
    }

    static int getUpperIdx(String str) {
        int s = 0, e = arr.length;

        while(s < e) {
            int m = (s + e) / 2;
            // arr[m]가 사전순으로 같거나, 더 앞서있거나
            // arr[m]가 str로 시작한다면 시작점 옮겨주기
            if(arr[m].str.compareTo(str) <= 0 || arr[m].str.startsWith(str)) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return s;
    }

    static int getLowerIdx(String str) {
        int s = 0, e = arr.length;

        while(s < e) {
            int m = (s + e) / 2;

            // arr[m]이 사전순으로 더 앞에 있다면 시작점 옮겨주기
            if(arr[m].str.compareTo(str) < 0) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return s;
    }

    static void initInput() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new Node[N];
        for(int i = 0; i < N; i++) {
            arr[i] = new Node(br.readLine(), i);
        }

        // 문자의 오름자순으로 배열 정렬
        Arrays.sort(arr, (o1, o2) -> o1.str.compareTo(o2.str));
    }
}
