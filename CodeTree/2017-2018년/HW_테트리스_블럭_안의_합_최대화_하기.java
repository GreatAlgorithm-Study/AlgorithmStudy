import java.io.*;
import java.util.*;

public class HW_테트리스_블럭_안의_합_최대화_하기 {
    static int n, m;
    static int[][] arr;
    static int max;
    static int[][][] shapes = {
            {{0,0}, {0,1}, {0,2}, {0,3}}, // ㅡ
            {{0,0}, {1,0}, {2,0}, {3,0}}, // |

            {{0,0}, {0,1}, {1,0}, {1,1}}, // ㅁ

            {{0,0}, {1,0}, {2,0}, {2,1}}, // └
            {{0,0}, {0,1}, {0,2}, {-1,2}}, // ---|
            {{0,0}, {0,1}, {1,1}, {2,1}}, // ┐
            {{0,0}, {1,0}, {0,1}, {0,2}}, // ┌

            {{0,0}, {1,0}, {2,0}, {2,-1}},
            {{-1,0}, {0,0}, {0,1}, {0,2}},
            {{0,0}, {0,1}, {1,0}, {2,0}},
            {{0,0}, {0,1}, {0,2}, {1,2}},

            {{0,0}, {1,0}, {1,1}, {2,1}},
            {{0,0}, {0,1}, {1,-1}, {1,0}},
            {{0,0}, {1,-1}, {1,0}, {2,-1}},
            {{0,-1}, {0,0}, {1,0}, {1,1}},

            {{0,0}, {1,0}, {2,0}, {1,1}},
            {{-1,1}, {0,0}, {0,1}, {0,2}},
            {{1,-1}, {0,0}, {1,0}, {2,0}},
            {{0,0}, {0,1}, {0,2}, {1,1}}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        // 배열 값 입력 받음
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = 0;
        // 배열 탐색 
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for (int k = 0; k < shapes.length; k++) {
                    int[][] shape = shapes[k];
                    int sum = 0;
                    for (int l = 0; l < 4; l++) {
                        int x = i + shape[l][0];
                        int y = j + shape[l][1];

                        if (isRange(x, y)) {
                            sum += arr[x][y];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }

        System.out.println(max);
    }

    static boolean isRange(int x, int y){
        return x>=0 && x<n && y>=0 && y<m;
    }
}