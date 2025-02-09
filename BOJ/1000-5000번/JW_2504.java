import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JW_2504 {

    static String str;
    static int idx = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        int value = recursive();
        if (value == -1 || idx != str.length())
            System.out.println(0);
        else
            System.out.println(value);
    }

    private static int recursive() {
        int sum = 0;
        while (idx < str.length()) {
            char c = str.charAt(idx);
            if (c == '(' || c == '[') {
                char openC = c;
                idx++;
                int value = recursive();
                if (value == -1 || idx == str.length())
                    return -1;
                char closeC = str.charAt(idx);
                idx++;
                if (!isValid(openC, closeC))
                    return -1;
                if (value == 0)
                    sum += openC == '(' ? 2 : 3;
                else
                    sum += openC == '(' ? value * 2 : value * 3;
            } else
                break;
        }
        return sum;
    }

    private static boolean isValid(char openC, char closeC) {
        return (openC == '(' && closeC == ')') || (openC == '[' && closeC == ']');
    }
}