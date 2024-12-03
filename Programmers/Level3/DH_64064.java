import java.util.*;

/*
 * 불량 사용자
 */

public class DH_64064 {
	static int answer;
	static HashSet<Integer> set;
	
	static int solution(String[] user_id, String[] banned_id) {
		set = new HashSet<Integer>();
		
		for(int i = 0; i < banned_id.length; i++) banned_id[i] = banned_id[i].replaceAll("\\*", "[a-z0-9]");
		
		func(0, 0, 0, user_id, banned_id, banned_id.length);
		return answer;
	}
	
	static void func(int depth, int idx, int status, String[] userId, String[] bannedId, int bannedLength) {
		if(depth == bannedLength) {
			if(!set.contains(status)) {
				set.add(status);
				answer += 1;
			}
			return;
		}
		
		for(int i = idx; i < userId.length; i++) {
			if((status & (1 << i)) > 0) continue; // 이미 불량 사용자 리스트에 있는 회원이라면 continue
			if(!userId[i].matches(bannedId[depth])) continue;
			
			status |= (1 << i); // i 번째 회원 비트 키기
			
			int nextIdx = 0;

			// banned_id[depth]와 banned_id[depth]가 같은 경우
			if(depth < bannedId.length - 1 && bannedId[depth].equals(bannedId[depth + 1])) nextIdx = i + 1;
			
			func(depth + 1, nextIdx, status, userId, bannedId, bannedLength);
			status &= ~(1 << i); // i번째 회원 비트 끄기
		}
	}
	
	public static void main(String[] args) {
		String[] user_id = {"aa", "ab", "ac", "ad", "ae", "be"};
		String[] banned_id = {"*b", "*c", "*d", "*e", "a*", "**"};
		
		System.out.println(solution(user_id, banned_id));
	}
}
