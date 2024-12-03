import java.util.*;

/*
 * 파일명 정렬
 */

public class DH_17686 {
	static class FileInfo implements Comparable<FileInfo> {
		int idx;
		String head;
		int number;

		public FileInfo(int idx, String head, String number) {
			this.idx = idx;
			this.head = head.toLowerCase();
			this.number = Integer.parseInt(number);
		}
		
		@Override
		public int compareTo(FileInfo o) {
			if(this.head.equals(o.head)) return Integer.compare(this.number, o.number);
			return this.head.compareTo(o.head);
		}
	}
	
	static ArrayList<FileInfo> fileList;
	
	static String[] solution(String[] files) {
		
		String[] answer = new String[files.length];
		fileList = new ArrayList<>();
		
		// 파일명 구성: HEAD, NUMBER, TAIL
		for(int idx = 0; idx < files.length; idx++) {
			String file = files[idx];
			
			// HEAD 구성하기
			int currentIdx = 0;
			for(int i = 0; i < file.length(); i++) {
				char ch = file.charAt(i);
				if(ch - '0' >= 0 && ch - '0' < 10) break;
				currentIdx += 1;
			}
			
			String head = file.substring(0, currentIdx);
			
			// NUMBER 구성하기
			int startIdx = currentIdx;
			for(int i = currentIdx; i < file.length(); i++) {
				char ch = file.charAt(i);
				if(ch - '0' < 0 || ch - '0' >= 10) break;
				currentIdx += 1;
			}
			
			String number = file.substring(startIdx, currentIdx);
			
			fileList.add(new FileInfo(idx, head, number));
//			System.out.println(head + " " + number);
		}
		
		Collections.sort(fileList);

		for(int i = 0; i < fileList.size(); i++) answer[i] = files[fileList.get(i).idx];
		return answer;
	}
	public static void main(String[] args) {
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		System.out.println(Arrays.toString(solution(files)));
	}
}
