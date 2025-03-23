import java.util.Arrays;

public class SB_389481 {
    private long getBanIndex(String s) {
        long idx = 0;
        for(char c : s.toCharArray()) {
            idx = (idx*26) + (c-'a'+1);     // 1-base로 몇번째인지 구함
        }
        return idx;
    }
    public String solution(long n, String[] bans) {
        // 삭제된 주문을 고려한 최종 n번째 구하기
        Arrays.sort(bans);

        int cnt = 0;
        for(String b : bans) {
            if(getBanIndex(b) < n) cnt++;
        }
        n+=cnt;

        // n번째 주문의 자리수 찾기(26의 몇승에 해당하는지 찾기)
        long digit = 26;
        int len = 1;
        long tmp = n;
        while(tmp > digit) {
            tmp-=digit;
            len++;
            digit *= 26;
        }

        // 최종문자 구하기
        char[] ans = new char[len];
        for(int i=len-1; i>=0; i--){
            long res = (n-1)%26;
            ans[i] = (char)('a' + res);      // 'a'-a=0으로, 0-base로 맞춰줘야 %연산 오류없이 가능
            n = (n-1) / 26;
        }

        return new String(ans);
        
    }
}
