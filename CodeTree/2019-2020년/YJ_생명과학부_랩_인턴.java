import java.io.*;
import java.util.*;

//2 ≤ n, m ≤ 100 > 10^4
public class YJ_생명과학부_랩_인턴 {
    static class Mold {
        int x;
        int y;
        int s;  //이동거리
        int d;  //이동방향
        int b;  //곰팡이 크기

        Mold(int x, int y, int s, int d, int b){
            this.x = x;
            this.y = y;
            this.s = calculateS(s,d);
            this.d = d;
            this.b = b;
        }

        private int calculateS(int s, int d){
            if(d<=1){
                return s % (2*n-2);
            }else{
                return s % (2*m-2);
            }
        }

        public void changeD(){
            //위(1)↔아래(2) , 오른쪽(3)↔왼쪽(4)
            this.d = this.d%2 == 0? this.d+1 : this.d-1;
        }

        public boolean isBiggerThan(Mold m){
            if(Objects.isNull(m)){
                return true;
            }
            return this.b > m.b;
        }
    }

    static Mold[][] lab;
    static List<Mold> inventory = new ArrayList<>();    //인턴이 채취한 곰팡이들
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if(k == 0){
            System.out.println(0);
        }else{
            lab = new Mold[n][m];
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                lab[x-1][y-1] = new Mold(x-1,y-1,s,d-1,b);
            }

            //모든 열 검사
            for(int i=0; i<m; i++){
                search(i);
                moveMolds();
            }
            //인턴이 채취한 곰팡9이 크기
            System.out.println(inventory.stream().mapToInt(mold -> mold.b).sum());
        }
    }

    //곰팡이 채취하기
    //열의 위에서 아래로 내려가며 탐색할 때 제일 빨리 발견한 곰팡이를 채취
    public static void search(int j){
        for(int i=0; i<n; i++){
            Mold mold = lab[i][j];
            if(!Objects.isNull(mold)){
                inventory.add(mold);
                lab[i][j] = null;
                break;
            }
        }
    }

    //곰팡이 이동
    public static void moveMolds(){
        Mold[][] tempLab = new Mold[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                Mold mold = lab[i][j];
                if(Objects.isNull(mold)){
                    continue;
                }
                Mold movedMold = move(mold);    //곰팡이 움직이기
                //기존 곰팡이보다 더 클 경우 기존 곰팡이 제거
                if(movedMold.isBiggerThan(tempLab[movedMold.x][movedMold.y])){
                    tempLab[movedMold.x][movedMold.y] = movedMold;
                }
            }
        }
        //tempLab > lab 으로 옮기기 : 기존의 lab 에서 곰팡이를 이동시키면 중복 이동이 발생할 수도 있음
        lab = tempLab;
    }

    private static Mold move(Mold mold){
        //위 아래 오른쪽 왼쪽
        int[] nx = {-1,1,0,0};
        int[] ny = {0,0,1,-1};
        //거리만큼 이동
        int tempS = mold.s;
        while(tempS-- > 0){
            int x = mold.x + nx[mold.d];
            int y = mold.y + ny[mold.d];
            //격자 범위를 벗어날 경우 방향전환
            if(x < 0 || y < 0 || x >= n || y >= m){
                mold.changeD();
                mold.x = mold.x + nx[mold.d];
                mold.y = mold.y + ny[mold.d];
            }else{  //격자 범위 내일 경우 그대로 이동
                mold.x = x;
                mold.y = y;
            }
        }
        return mold;
    }
}