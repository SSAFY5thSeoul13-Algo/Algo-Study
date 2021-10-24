package week29.BOJ_20058_G4_마법사상어와파이어스톰;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // N, 파이어스톰 시전수, 격자의 길이
    static int N, Q, limit;
    // 가장 큰 덩어리, 총 얼음의 합
    static int maxJunk,totalIce;
    // 격자
    static int[][] A;
    // BFS를 위한 방문배열
    static boolean[][] vis;
    // delta array
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    // 좌표클래스
    static class Point{
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        limit = (int) Math.pow(2, N);
        A = new int[limit][limit];
        vis = new boolean[limit][limit];

        // 격자입력
        for(int i=0; i<limit; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<limit; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 파이어 스톰 Q 번 시전
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++){
            // 영역분리 및 회전
            separate(Integer.parseInt(st.nextToken()));

            // 녹이기
            melt();
        }

        // A에 대한 DFS, BFS를 사용하여 총 갯수와 가장 큰 덩어리를 구하기
        for(int i=0; i<limit; i++) {
            for (int j = 0; j < limit; j++) {
                if(A[i][j] == 0 || vis[i][j]) continue;

                maxJunk = Math.max(maxJunk, bfs(i,j));
            }
        }

        System.out.println(totalIce);
        System.out.println(maxJunk);
    }
    // 영역 분리
    static void separate(int L){

        int len = (int) Math.pow(2, L);

        for(int i=0; i<limit; i += len){
            for(int j=0; j<limit; j+=len){
                rotate(i,j,L);
            }
        }
    }
    // 회전
    static void rotate(int ty, int tx, int l){

        int len = (int)Math.pow(2, l);
        int[][] tmp = new int[len][len];

        // tmp 배열에 회전된 상태 복사
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                tmp[i][j] = A[ty + (len-1-j)][tx + i];
            }
        }

        // A(격자)에 tmp 배열을 복사
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                A[ty+i][tx+j] = tmp[i][j];
            }
        }
    }
    // 얼음 녹이기
    static void melt(){

        List<Point> meltList = new ArrayList<>();
        // A를 순회하며, 녹는 좌표 List에 삽입
        for(int i=0; i<limit; i++){
            for (int j=0; j<limit; j++) {
                if(A[i][j] == 0 ) continue;
                int cnt = 0;
                for (int z = 0; z < 4; z++) {
                    int zy = i + dy[z];
                    int zx = j + dx[z];

                    if (zy < 0 || zx < 0 || zy >= limit || zx >= limit) continue;
                    if (A[zy][zx] != 0) cnt++;
                }

                if (cnt < 3) meltList.add(new Point(i, j));
            }
        }
        // 한번에 녹이기
        for(Point p : meltList){
            A[p.y][p.x]--;
        }
    }

    static int bfs(int y, int x){

        Queue<Point> q = new LinkedList<>();
        vis[y][x] = true;
        totalIce += A[y][x];
        q.offer(new Point(y,x));
        int cnt = 1;
        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int z=0; z<4; z++){
                int zy = cur.y + dy[z];
                int zx = cur.x + dx[z];

                if (zy < 0 || zx < 0 || zy >= limit || zx >= limit) continue;
                if(vis[zy][zx]) continue;
                vis[zy][zx] = true;
                if(A[zy][zx] == 0 ) continue;
                totalIce += A[zy][zx];
                cnt++;
                q.offer(new Point(zy,zx));
            }
        }
        return cnt;
    }

}
