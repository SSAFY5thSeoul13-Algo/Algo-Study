## BAEKJOON Gold2 19236 청소년 상어
- DFS, 구현, 시뮬레이션
- Gold 2
- https://www.acmicpc.net/problem/19236
<br>

### 문제설명

아기 상어가 성장해 청소년 상어가 되었다.

4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다. 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다. 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다. 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다. 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.

물고기는 번호가 작은 물고기부터 순서대로 이동한다. 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다. 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.

상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.


### 입력
첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다.

물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다.

방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.

### 출력
상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.

### 입출력 예

#### 예제 1
입력

```
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
```
출력

```
33
```

#### 예제 2
입력

```
16 7 1 4 4 3 12 8
14 7 7 6 3 4 10 2
5 2 15 2 8 3 6 4
11 8 2 4 13 5 9 4
```
출력

```
43
```

#### 예제 3
입력

```
12 6 14 5 4 5 6 7
15 1 11 7 3 7 7 5
10 3 8 3 16 6 1 1
5 8 2 7 13 6 9 2
```
출력

```
76
```

#### 예제 4
입력

```
2 6 10 8 6 7 9 4
1 7 16 6 4 2 5 8
3 7 8 6 7 6 14 8
12 7 15 4 11 3 13 3
```
출력

```
39
```


### 풀이 및 과정
하아.. 3시간 넘게 걸린 구현, 시뮬레이션 문제입니다..

전체적인 풀이를 대략 풀어보면
1. 이전 정보 복사
2. 시뮬레이션 진행
3. 이전 정보 복원

순으로 진행되었습니다.

#### 이전 정보 복사
4x4 공간을 어항으로 표기하겠습니다!

먼저 어항과 물고기의 정보(좌표, 방향, 생존유무)를 담고 있는 배열들을 복사해둡니다.


```java
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

```

#### 시뮬레이션 진행
먼저 물고기를 이동시킨후, 상어가 이동할 수 있는 모든 위치로 이동하며, 최댓값에 도달할 수 있는지 시도해봅니다.

상어가 모든 이동을 마치고 난 후, 최댓값을 확인합니다.
```java 
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
```

#### 이전 정보 복원
끝까지 도달하고 난후, 이전 정보로 복원시켜 다음 시도를 하게 해줍니다.

```java
// 어항복구
fishbowl = newBowl;
// 물고기정보 복구
~~fishs = newFishs;
```

### 막힌점
몇가지 빼먹은 부분이 있었는데, 이것을 발견하지 못하였고 또한 미숙한 디버깅 실력이 문제가 되었습니다.
1. 배열의 복사가 제대로 이루어지지 않음.
2. 물고기 상태를 복사할때, isDead부분은 생성자에서 자체생성해서 모든 값이 false가 되어, 물고기가 사망하지 않는상태 발생
3. 물고기가 빈공간으로 이동할 때, 어항말고도 물고기정보배열도 위치정보를 갱신해야 했는데, 하지 않음.. 

### 소스코드
```java
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

```

### 결과
```
메모리 : 11668 KB	
시간 : 80 ms
```
