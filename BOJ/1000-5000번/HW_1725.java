
import java.io.*;
import java.util.*;

// N<= 10^5 시간 복잡도 : O(N)
public class HW_1725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N+1];

        for(int i=0; i<N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(simulation(heights));
    }
    static long simulation(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // 막대 높이를 저장
        long max = 0; // 막대의 높이(10^9) * 너비(10^5) -> long

        for(int i=0; i<heights.length; i++) { // 직사각형의 최대 높이 계산
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) { // 현재 높이가 스택의 최상단에 있는 높이보다 작으면
                int h = heights[stack.pop()]; // 스택에서 높이를 꺼냄
                int w = stack.isEmpty() ? i : i - stack.peek()-1;
                // 스택이 비어있는 경우 -> 현재 인덱스(i)가 넓이 w=i;
                // 스택이 비어있지X 경우 -> 왼쪽 경계(왼쪽에 남아있는 막대) w = i - stack.peek()-1;
                // 현재 막대와의 간격만큼만 직사각형을 확장할 수 있기 때문
                max = Math.max(max, (long)h*w);// 꺼낸 높이 기준으로 넓이 계산
            }
            stack.push(i);
        }
        return max;
    }
}
