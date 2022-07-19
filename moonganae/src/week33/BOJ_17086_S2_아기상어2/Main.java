package week33.BOJ_17086_S2_아기상어2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /* 공간의 길이(세로,가로) */
    static int N, M;
    /* 공간 */
    static int[][] map;
    /* delta array 8방 */
    static int[] dy ={-1,-1,-1,0,0,1,1,1}, dx={-1,0,1,-1,1,-1,0,1};
    /* 빈공강 상수 */
    static final int EMPTY = 0;
    /* 위치좌표 클래스 */
    static class Point{
        int y, x;
        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Queue<Point> q = new LinkedList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int data = Integer.parseInt(st.nextToken());
                if(data == 1){
                    q.offer(new Point(i,j));
                }
                map[i][j] = data;
            }
        }

        // 남은 빈공간 크기
        int remainBlank = N*M - q.size();
        System.out.println(bfs(q,remainBlank));

    }
    static int bfs(Queue<Point> q, int remainBlank){

        // 안전거리
        int distance = 0;
        while(!q.isEmpty()){
            // 빈공간이 없다면 종료
            if(remainBlank==0) break;

            int size = q.size();
            while(size-- > 0){
                Point cur = q.poll();
                int val = map[cur.y][cur.x];
                for(int z=0; z<8; z++){
                    int zy = cur.y + dy[z];
                    int zx = cur.x + dx[z];
                    // 범위체크
                    if(zy<0 || zx<0 || zy>=N || zx>=M) continue;
                    // 빈공간인지 확인
                    if(map[zy][zx] != EMPTY) continue;

                    map[zy][zx] = val+1;
                    remainBlank--;
                    q.offer(new Point(zy,zx));
                }
            }
            distance++;
        }

        return distance;
    }
}
