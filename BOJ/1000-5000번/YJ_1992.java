import java.io.*;

//1<=N<=64
//분할정복: 반복해서 작은영역을 탐색할 때는 `재귀`를 생각해보기
public class YJ_1992 {
    static char[][] video;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        video = new char[n][n];
        for(int i=0; i<n; i++){
            String data = br.readLine();
            for(int j=0; j<n; j++){
                video[i][j] = data.charAt(j);
            }
        }

        compress(0,0,n);
        System.out.println(sb.toString());
    }

    static StringBuilder sb = new StringBuilder();
    static void compress(int x, int y, int size){
        if(check(x, y, size)){
            sb.append(video[x][y]);
            return;
        }
        sb.append("(");
        compress(x,y,size/2);   //왼쪽위
        compress(x,y+size/2,size/2);    //오른쪽위
        compress(x+size/2,y,size/2);    //왼쪽아래
        compress(x+size/2,y+size/2,size/2); //오른쪽아래
        sb.append(")");
    }

    static boolean check(int x, int y, int size){
        int value = video[x][y];
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(value != video[i][j]){   //기준(0또는1)과 하나라도 틀리면 분할해야함
                    return false;
                }
            }
        }
        return true;
    }
}
