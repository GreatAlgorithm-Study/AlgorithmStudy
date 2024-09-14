public class DH_2531 {
    static int N, d, k, c, arr[], eat[];

    // 투포인터
    // * 조건
    //   - s ~ e 거리가 k보다 작다면 e += 1
    //   - s ~ e 거리가 k보다 크거나 같다면 s += 1;
    //   - s ~ e 거리가 k랑 같다면 초밥 종류의 개수 찾기
    static void solution() {
        int s = 0, e = 0;
        int result = 0;

        eat[arr[s]]++;

        while(s <= e && s < N) {
            if(e - s + 1 < k) {
                e++;
                int tmpE = e % N;
                eat[arr[tmpE]]++;
            } else {
                eat[arr[s]]--;
                s++;
            }

            if(e - s + 1 == k) result = Math.max(result, getCnt());
        }

        System.out.println(result);
    }

    static int getCnt() {
        int result = 0;
        for(int e: eat) {
            if(e == 0) continue;
            result += 1;
        }

        if(eat[c] == 0) result += 1;
        return result;
    }

    public static void main(String[] args) throws Exception {

        N = read(); d = read(); k = read(); c = read();

        arr = new int[N];
        eat = new int[d + 1]; // 초밥의 가지수

        for(int i = 0; i < N; i++) arr[i] = read();

        solution();
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
