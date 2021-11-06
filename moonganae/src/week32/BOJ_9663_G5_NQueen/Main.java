package week32.BOJ_9663_G5_NQueen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    // 퀸 갯수, 퀸을 놓을 방법의 수
    static int N, ans;

    // 세로, 가로, (0,0)->(N-1,N-1) 대각선, (N-1,0)->(0,N-1) 대각선
    static boolean[] rowVis, colVis, lVis, rVis;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        rowVis = new boolean[N];
        colVis = new boolean[N];
        lVis = new boolean[2*N-1];
        rVis = new boolean[2*N-1];

        dfs(0);
        System.out.println(ans);
    }

    // r : 현재 놓는 행의 번호
    static void dfs(int r){
        // 끝까지 놓았을때,
        if(r==N){
            ans++;
            return;
        }

        // 열의 번호 0~N-1
        for(int c=0; c<N; c++){

            // 충돌이 있다면 넘어가기
            if(isConflict(r,c)) continue;

            // 놓기
            setVis(r,c,true);

            // 탐색
            dfs(r+1);

            // 빼기
            setVis(r,c,false);
        }
    }

    static void setVis(int r, int c, boolean val){
        rowVis[r] = val;
        colVis[c] = val;
        lVis[r+c] = val;
        rVis[N-1 - r +c] = val;
    }

    static boolean isConflict(int r, int c){
        if(rowVis[r]) return true;
        else if(colVis[c]) return true;
        else if(lVis[r+c]) return true;
        else if(rVis[N-1 - r +c]) return true;

        return false;
    }
}
