import java.util.*;

public class DH_3020 {
    // arr1: 석순, arr2: 종유석
    static int N, H, min, cnt, arr1[], arr2[];

    static void solution() {

        // 개똥벌레가 날아다니는 위치를 1부터 H까지 설정하면서
        // 부딪히는 석순과 종유석의 개수를 구함
        for(int i = 1; i < H + 1; i++) {
            // tmp: 높이가 i일 때, 개똥벌레가 부딪히는 (석순 + 종유석) 개수
            int tmp = getCnt(arr1, i) + getCnt(arr2, H - i + 1);

            if(min == tmp) cnt++;
            else if(min > tmp) {
                min = tmp;
                cnt = 1;
            }
        }
    }

    // 정렬된 석순, 종유석에 대해 높이 기준 큰 것들의 개수 구함 (깨질 수 있는 높이)
    static int getCnt(int arr[], int height) {
        int s = 0, e = arr.length;

        while(s < e) {
            int m = (s + e) / 2;

            if(arr[m] >= height) e = m;
            else if(arr[m] < height) s = m + 1;
        }

        return arr.length - e;
    }
    public static void main(String[] args) throws Exception {
        N = read();
        H = read();

        arr1 = new int[N >> 1]; // 석순
        arr2 = new int[N >> 1]; // 종유석

        min = Integer.MAX_VALUE;

        for(int i = 0; i < N >> 1; i++) {
            int a = read(), b = read();
            arr1[i] = a; arr2[i] = b;
        }

        // 석순과 종유석을 오름차순으로 정렬
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        solution();
        System.out.print(min + " " + cnt);
    }


    static int read() throws Exception {
        // System.in.read(): 1byte 크기만 읽어 ASCII 코드에 해당하는 int 값 리턴
        // n = System.in.read()일 때, 한글자의 수를 읽으면 48 <= n <58
        int c, n = System.in.read() & 15;

        // ASCII 코드 48이상인 경우 (0-9의 숫자)
        while((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if(c == 13) // 입력이 Enter일 경우 다음 바이트를 읽어 해당 문자 건너뛰기
            System.in.read();

        return n;
    }
}
