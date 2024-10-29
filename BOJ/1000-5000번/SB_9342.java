import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_9342 {
    static List<Character> lst = new ArrayList<>();     // {A, B, C, D, E, F}
    static int idx;
    private static boolean check1(char c){
        return lst.contains(c);
    }

    private static boolean check2(char target, char[] chars) {
        int cnt = 0;
        while (idx < chars.length && chars[idx] == target) {
            idx++;
            cnt++;
        }
        return cnt > 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        String yes = "Infected!";
        String no = "Good";
        for (int i = 65; i <= 70; i++) {
            lst.add((char)i);
        }

        for (int t = 0; t < T; t++) {
            char[] chars = br.readLine().toCharArray();
            idx = 0;

            if (check1(chars[idx])){
                if (chars[idx]!='A') idx++;        // 첫번째 조건
            }
            else {
                sb.append(no).append('\n');
                continue;
            }

            if (check2('A', chars) && check2('F', chars) && check2('C', chars)){    // 중간 조건
                if (idx==chars.length || (idx==chars.length-1 && check1(chars[idx]))){     // 마지막 조건
                    sb.append(yes).append('\n');
                }else sb.append(no).append('\n');
            } else sb.append(no).append('\n');
        }
        System.out.println(sb);
    }
}
