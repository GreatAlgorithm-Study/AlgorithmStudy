import java.io.*;
import java.util.*;
public class JY_2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		int ans = 0;
		int tmp = 1;
		for(int i=0; i<line.length(); i++) {
			char ch = line.charAt(i);
			// 열린괄호
			if(ch == '(') {
				stack.push(ch);
				tmp *= 2;
			} else if(ch == '[') {
				stack.push(ch);
				tmp *= 3;
			}
			// 닫힌 괄호
			else {
				if(stack.isEmpty()) {
					ans = 0;
					break;
				}
				// 올바르지 않은 괄호
				if((ch == ')' && stack.peek() == '[') || (ch == ']' && stack.peek() =='(')) {
					ans = 0;
					break;
				}
				// 이전 값이 올바른 괄호라면 값 저장
				if(ch == ')') {
					if(line.charAt(i-1)=='(') ans += tmp;
					tmp /= 2;
				} else {
					if(line.charAt(i-1)=='[') ans += tmp;
					tmp /= 3;
				}
				stack.pop();
			}
		}
		
		if(!stack.isEmpty()) System.out.println(0);
		else System.out.println(ans);
	}

}
