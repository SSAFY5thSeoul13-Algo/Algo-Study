## BAEKJOON GOLD4 20058 마법사상어와 파이어스톰
- Simulation, BFS
- Gold 4
- https://www.acmicpc.net/problem/20058
<br>

### 문제설명

> 마법사 상어는 파이어볼과 토네이도를 조합해 파이어스톰을 시전할 수 있다. <br>
오늘은 파이어스톰을 크기가 2N × 2N인 격자로 나누어진 얼음판에서 연습하려고 한다. <br> 
위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 얼음의 양을 의미한다.<br> 
A[r][c]가 0인 경우 얼음이 없는 것이다.<br> 
파이어스톰을 시전하려면 시전할 때마다 단계 L을 결정해야 한다.<br> 
파이어스톰은 먼저 격자를 2L × 2L 크기의 부분 격자로 나눈다.<br> 
그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다. <br>
이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.<br>
(r, c)와 인접한 칸은 (r-1, c), (r+1, c), (r, c-1), (r, c+1)이다.<br> 
아래 그림의 칸에 적힌 정수는 칸을 구분하기 위해 적은 정수이다.<br>
마법사 상어는 파이어스톰을 총 Q번 시전하려고 한다. 모든 파이어스톰을 시전한 후, 다음 2가지를 구해보자.<br>
1. 남아있는 얼음 A[r][c]의 합
2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수


>얼음이 있는 칸이 얼음이 있는 칸과 인접해 있으면, 두 칸을 연결되어 있다고 한다. 덩어리는 연결된 칸의 집합이다.

### 입력
첫째 줄에 N과 Q가 주어진다. 

둘째 줄부터 2^N개의 줄에는 격자의 각 칸에 있는 얼음의 양이 주어진다. 

r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.

마지막 줄에는 마법사 상어가 시전한 단계 L1, L2, ..., LQ가 순서대로 주어진다.

### 출력
첫째 줄에 남아있는 얼음 A[r][c]의 합을 출력하고, 

둘째 줄에 가장 큰 덩어리가 차지하는 칸의 개수를 출력한다. 

단, 덩어리가 없으면 0을 출력한다.

### 입출력 예

#### 예제 1
입력
```
3 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
1
```
출력
```
284
64
```

### 풀이 및 과정
시뮬레이션 문제이면서, 내부적으로 BFS를 사용하여 문제를 해결하였습니다.

시뮬레이션의 순서는 다음과 같습니다.

<파이어 스톰 시전>
1. 격자를 부분격자로 나누어, 시계방향으로 90도 회전시킨다.
```java
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
```

2. 상하좌우 얼음이 있는 칸이 3개 미만일때 얼음의 양을 1줄인다.
```java
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

```
<결과구하기>
1. 모든 칸에 대해서 BFS를 수한다.
```java
// A에 대한 DFS, BFS를 사용하여 총 갯수와 가장 큰 덩어리를 구하기
for(int i=0; i<limit; i++) {
    for (int j = 0; j < limit; j++) {
        if(A[i][j] == 0 || vis[i][j]) continue;

        maxJunk = Math.max(maxJunk, bfs(i,j));
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
```

### 소스코드
```java
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

```

### 결과
```
메모리 : 76368KB	
시간 : 416ms
```
