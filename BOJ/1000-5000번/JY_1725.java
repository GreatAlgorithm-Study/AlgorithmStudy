import java.io.*;
import java.util.*;

public class JY_1725 {

	static int N;
	static long[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		for(int i=0; i<N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		System.out.println(histogram(0, N-1));
		

	}
	public static long histogram(int s, int e) {
		if(s == e) return arr[s];
		
		int mid = (s + e) / 2;
		long tmp = arr[mid];
		long h = arr[mid];
		int left = mid - 1;
		int right = mid + 1;
		while(s <= left || right <= e) {
			if(s > left) {
				h = Math.min(h, arr[right]);
				right += 1;
			} else if(right > e) {
				h = Math.min(h, arr[left]);
				left -= 1;
			} else if(arr[left] > arr[right]) {
				h = Math.min(h, arr[left]);
				left -= 1;
			} else {
				h = Math.min(h, arr[right]);
				right += 1;
			}
			tmp = Math.max(tmp, (right-left-1)*h);
		}
		tmp = Math.max(tmp, Math.max(histogram(s, mid), histogram(mid+1, e)));
		
		return tmp;
	}
}
