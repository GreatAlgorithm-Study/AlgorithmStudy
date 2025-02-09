import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SB_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        int ans = 0;
        int tmp = 1;

        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            switch (cur) {
                case '(':
                    stack.addFirst(cur);
                    tmp*=2;
                    break;
                case '[':
                    stack.addFirst(cur);
                    tmp*=3;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peekFirst() != '('){
                        System.out.println(0);
                        return;
                    }
                    if (line.charAt(i - 1) == '(') ans+=tmp;
                    tmp/=2;
                    stack.pollFirst();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peekFirst() != '['){
                        System.out.println(0);
                        return;
                    }
                    if (line.charAt(i-1)=='[') ans+=tmp;
                    tmp/=3;
                    stack.pollFirst();
                    break;
            }
        }

        // 남아있는 괄호 여부 체크
        if (stack.isEmpty()) System.out.println(ans);
        else System.out.println(0);
    }
}
