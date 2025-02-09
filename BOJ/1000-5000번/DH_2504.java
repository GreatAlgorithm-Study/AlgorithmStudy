import java.io.*;
import java.util.*;

/*
 * 괄호의 값
 */

public class DH_2504 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		long result = 0;
		
		Stack<Character> stack = new Stack<Character>(); // 괄호, 숫자 존재에 대한 정보 저장
		Stack<Long> numStack = new Stack<Long>(); // 숫자에 대한 정보 저장
		
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			
			if(ch == '(' || ch == '[') stack.push(ch); // 여는 괄호라면 스택에 넣어주기
			else {

				boolean flag = false; // 괄호가 잘 닫혔을 때, flag를 true로 만들어주기
				
				ch = ch == ')' ? '(' : '['; // 괄호 뒤집어주기
				
				if(!stack.isEmpty()) {
					
					long tmp = 0; // 숫자 계산을 위한 변수
					
					while(!stack.isEmpty()) {
						// 괄호가 같을 때
						if(ch == stack.peek()) {
							stack.pop();
							stack.push('.'); // 숫자가 있음을 표시
							
							int mul = ch == '(' ? 2 : 3;
							numStack.push(tmp == 0 ? mul : mul * tmp);
							
							// 괄호가 닫혀야 flag true로 해줌
							flag = true;
							break;
						}
						
						// 숫자가 존재할 때
						else if(stack.peek() == '.') {
							stack.pop();
							tmp += numStack.pop();
						} 
						// 유효하지 않은 입력일 때
						else break;
					}
				} 
				
				if(!flag) break;
			}
			
		}
		
		boolean flag = true;
		
		// stack에 괄호가 있는지 확인하면서 최종값 계산해주기
		while(!stack.isEmpty()) {
			if(stack.pop() == '.') result += numStack.pop();
			else {
				flag = false;
				break;
			}
		}
			
		if(!flag) System.out.println(0);
		else System.out.println(result);
	}
}
