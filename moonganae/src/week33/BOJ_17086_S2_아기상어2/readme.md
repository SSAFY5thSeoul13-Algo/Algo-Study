## BAEKJOON SILVER2 17086 아기상어 2
- BFS
- Silver 2
- https://www.acmicpc.net/problem/17086
<br>

### 문제설명

N×M 크기의 공간에 아기 상어 여러 마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 아기 상어가 최대 1마리 존재한다.

어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와의 거리이다. 두 칸의 거리는 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수이고, 이동은 인접한 8방향(대각선 포함)이 가능하다.

안전 거리가 가장 큰 칸을 구해보자.


### 입력
첫째 줄에 공간의 크기 N과 M(2 ≤ N, M ≤ 50)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어지며, 0은 빈 칸, 1은 아기 상어가 있는 칸이다.

빈 칸의 개수가 한 개 이상인 입력만 주어진다.

### 출력
첫째 줄에 안전 거리의 최댓값을 출력한다.

### 입출력 예

#### 예제 1
입력

```
5 4
0 0 1 0
0 0 0 0
1 0 0 0
0 0 0 0
0 0 0 1
```
출력

```
2
```

#### 예제 2
입력

```
7 4
0 0 0 1
0 1 0 0
0 0 0 0
0 0 0 1
0 0 0 0
0 1 0 0
0 0 0 1
```
출력

```
2
```

### 풀이 및 과정
BFS를 이용하여 문제를 해결하였습니다.

이 문제같은 경우, 아기상어가 있는칸부터 시작해서 바이러스 전파같이 빈공간이 없도록 전파하여 풀 수 있는 문제입니다.

1. 아기상어 위치좌표 모으기
```java
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
```
2. BFS진행
   1. 안전거리를 구하기위해, Queue 사이즈에 맞추어 동작하는 BFS로 구현합니다.
```java
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
```

### 소스코드
```java
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


```

### 결과
```
메모리 : 12080 KB	
시간 : 100 ms
```
