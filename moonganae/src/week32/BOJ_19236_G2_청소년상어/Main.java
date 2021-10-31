package week32.BOJ_19236_G2_청소년상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 먹을 수 있는 번호의 합
    static int ans;
    // 상어, 빈공간, 어항의 크기, 물고기 최대수
    static final int SHARK = -1, EMPTY=0, MAX_SIZE = 4, FISH_NUM_MAX = MAX_SIZE*MAX_SIZE;
    // 어항
    static int[][] fishbowl= new int[MAX_SIZE][MAX_SIZE];
    // 방향 벡터
    static int[] dy = {-1,-1,0,1,1,1,0,-1}, dx = {0,-1,-1,-1,0,1,1,1};

    // 물고기 객체
    static class Fish{
        int y, x, dir;
        boolean isDead;
        Fish(int y, int x, int dir, boolean isDead){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.isDead = isDead;
        }
    }
    // 물고기 정보배열
    static Fish[] fishs;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishs = new Fish[FISH_NUM_MAX+1];
        for(int i=0; i<MAX_SIZE; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<MAX_SIZE; j++){
                int idx = Integer.parseInt(st.nextToken());
                // 방향이 1이 아닌 0부터 시작해게 -1
                int dir = Integer.parseInt(st.nextToken())-1;

                fishbowl[i][j] = idx;
                fishs[idx] = new Fish(i,j,dir, false);
            }
        }

        // 0,0에 있는 물고기 먹어치우기
        int fishIdx = fishbowl[0][0];
        fishbowl[0][0] = SHARK;
        fishs[fishIdx].isDead = true;

        // 탐샋시작
        dfs(0,0,fishs[fishIdx].dir, fishIdx);

        System.out.println(ans);
    }
    static StringBuilder sb = new StringBuilder();
    static void dfs(int sy, int sx, int sDir, int sum){
        // 어항복사
        int[][] newBowl = new int[MAX_SIZE][MAX_SIZE];
        for(int i=0; i<MAX_SIZE; i++){
            for(int j=0; j<MAX_SIZE; j++){
                newBowl[i][j] = fishbowl[i][j];
            }
        }
        // 물고기정보 복사
        Fish[] newFishs = new Fish[FISH_NUM_MAX+1];
        for(int i=1; i<=FISH_NUM_MAX; i++){
            Fish fish = fishs[i];
            newFishs[i] = new Fish(fish.y, fish.x, fish.dir, fish.isDead);
        }

        // 물고기 이동
        moveFishs();

        // 잡아먹기 -> 순서대로\
        int my = sy;
        int mx = sx;
        for(int i=0; i<MAX_SIZE; i++){
            my += dy[sDir];
            mx += dx[sDir];

            // 범위를 벗어나는 경우확인 : 상어 집으로가기
            if(my<0 || mx<0 || my>=MAX_SIZE || mx>=MAX_SIZE) break;

            // 사망 물고기 판단
            int fishIdx = fishbowl[my][mx];
            if(fishIdx == EMPTY) continue;

            // 물고기 꾸울꺽
            fishs[fishIdx].isDead = true;
            fishbowl[my][mx] = SHARK;
            fishbowl[sy][sx] = EMPTY;

            // 이동

            dfs(my, mx, fishs[fishIdx].dir, sum+fishIdx);

            // 물고기 복구
            fishs[fishIdx].isDead = false;
            fishbowl[my][mx] = fishIdx;
            fishbowl[sy][sx] = SHARK;
        }

        // 상어가 집간 상태 결과물 확인
        ans = Math.max(ans, sum);

        // 어항복구
        fishbowl = newBowl;
        // 물고기정보 복구
        fishs = newFishs;
    }

    static void moveFishs(){
        // 1번부터 16번 물고기까지 순회하며, 이동
        for(int i=1; i<=FISH_NUM_MAX; i++){
            if(fishs[i].isDead) continue;

            move(i);
        }
    }

    static void move(int idx){

        Fish fish = fishs[idx];
        int dir = fish.dir;

        for(int i=0; i<8; i++){

            int my = fish.y + dy[dir];
            int mx = fish.x + dx[dir];
            // 갈수있다면
            if(canGo(my,mx)){

                fish.dir = dir;

                int fishIdx = fishbowl[my][mx];
                // 빈공간일때
                if(fishIdx == EMPTY){
                    fishbowl[fish.y][fish.x] = EMPTY;
                    fishbowl[my][mx] = idx;
                    fish.y = my;
                    fish.x = mx;
                }else {
                    // 물고기가 존재하면 위치바꾸기
                    swapFish(idx, fishIdx);
                }

                return;
            }

            // 못가면, 45도 반시계 회전
            dir = (dir+1) % 8;
        }
    }

    static boolean canGo(int y, int x){
        // 범위 확인
        if(y<0 || x<0 || y>=MAX_SIZE || x>=MAX_SIZE) return false;
        // 상어인지 확인
        if(fishbowl[y][x] == SHARK) return false;

        return true;
    }
    static void swapFish(int idxA, int idxB){
        int tmpY = fishs[idxA].y;
        int tmpX = fishs[idxA].x;

        fishbowl[fishs[idxA].y][fishs[idxA].x] = idxB;
        fishbowl[fishs[idxB].y][fishs[idxB].x] = idxA;

        fishs[idxA].y = fishs[idxB].y;
        fishs[idxA].x = fishs[idxB].x;

        fishs[idxB].y = tmpY;
        fishs[idxB].x = tmpX;


    }

    static void printFishBowl(){
        for (int i=0; i<MAX_SIZE; i++){
            System.out.print(sb.toString());
            for(int j=0; j<MAX_SIZE; j++){
                System.out.print(sb.toString() + fishbowl[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void printDir(){
        for (int i=0; i<MAX_SIZE; i++){
            System.out.print(sb.toString());
            for(int j=0; j<MAX_SIZE; j++){
                int idx = fishbowl[i][j];
                if(idx == SHARK || idx == EMPTY) System.out.print(idx + " ");
                else System.out.print(fishs[idx].dir + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 물고기이동
        // 이동 x 범위 범어나는 칸, 상어가 있는 칸, 이동할수 있을때까지 45도 방향 반시계 회전 다 돌았는데 안되면 이동 X
        // 이동 O,
    // 상어 이동
        // 현재 방향에서 한칸 이동할텐데 여러칸을 뛰어 넘을 수 있음.
        // 물고기가 존재하는 칸만 이동가능
}
