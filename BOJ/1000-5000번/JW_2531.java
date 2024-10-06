public class Main {
    public static void main(String[] args) throws Exception {
        int n = read(), d = read(), k = read(), c = read();
        int[] dishes = new int[n];          // 전체 초밥들의 종류를 저장할 배열
        for (int i = 0; i < n; i++)
            dishes[i] = read();
        int[] used = new int[d + 1];        // 초밥이 종류별로 몇 개 사용됐는지 저장할 배열
        int max = 0, cnt = 0;               // 최대 종류의 수, 현재 종류의 수
        // 슬라이딩 윈도우 초기화
        for (int i = 0; i < k; i++) {
            // 사용하지 않은 초밥이라면
            if (used[dishes[i]] == 0)
                cnt++;                      // 종류 증가
            used[dishes[i]]++;              // 초밥 개수 증가
        }
        // 쿠폰 그릇을 사용에 따른 종류 개수
        max = used[c] == 0 ? cnt + 1 : cnt;
        // 슬라이딩 윈도우 진행
        for (int i = 1; i < n; i++) {
            used[dishes[i - 1]]--;                  // 초밥 개수 감소
            // 해당 초밥의 개수가 0이 되면
            if (used[dishes[i - 1]] == 0)           
                cnt--;                              // 종류 감소
            // 다음 초밥이 처음 추가되는 거라면
            if (used[dishes[(i + k - 1) % n]] == 0)
                cnt++;                              // 종류 증가
            used[dishes[(i + k - 1) % n]]++;        // 초밥 개수 증가
            // 쿠폰 그릇을 사용에 따른 종류 개수
            max = Math.max(max, used[c] == 0 ? cnt + 1 : cnt);
        }
        System.out.println(max);
    }

    // 빠른 입력을 위한 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}