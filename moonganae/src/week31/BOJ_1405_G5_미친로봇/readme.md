## BAEKJOON GOLD5 1405 미친 로봇
- dfs 백트래킹
- Gold 5
- https://www.acmicpc.net/problem/1405
<br>

### 문제설명
통제 할 수 없는 미친 로봇이 평면위에 있다. 그리고 이 로봇은 N번의 행동을 취할 것이다.

각 행동에서 로봇은 4개의 방향 중에 하나를 임의로 선택한다. 그리고 그 방향으로 한 칸 이동한다.

로봇이 같은 곳을 한 번보다 많이 이동하지 않을 때, 로봇의 이동 경로가 단순하다고 한다. (로봇이 시작하는 위치가 처음 방문한 곳이다.) 

로봇의 이동 경로가 단순할 확률을 구하는 프로그램을 작성하시오. 예를 들어, EENE와 ENW는 단순하지만, ENWS와 WWWWSNE는 단순하지 않다. (E는 동, W는 서, N은 북, S는 남)



### 입력
- 첫째 줄에 N, 동쪽으로 이동할 확률, 서쪽으로 이동할 확률, 남쪽으로 이동할 확률, 북쪽으로 이동할 확률이 주어진다.
- N은 14보다 작거나 같은 자연수이고,  모든 확률은 100보다 작거나 같은 자연수 또는 0이다. 
- 그리고, 동서남북으로 이동할 확률을 모두 더하면 100이다.
- 확률의 단위는 %이다.

### 출력
첫째 줄에 로봇의 이동 경로가 단순할 확률을 출력한다. 절대/상대 오차는 10-9 까지 허용한다.

### 입출력 예

#### 예제 1
입력
```
2 25 25 25 25
```
출력
```
0.75
```

#### 예제 2
입력
```
1 25 25 25 25
```
출력
```
1.0
```

#### 예제 3
입력
```
7 50 0 0 50
```
출력
```
1.0
```

#### 예제 4
입력
```
14 50 50 0 0
```
출력
```
0.0001220703125
```

#### 예제 5
입력
```
14 25 25 25 25
```
출력
```
0.008845493197441101
```

### 풀이 및 과정
DFS로 문제를 해결하였습니다.

- 동서남북 이동의 확률을 입력받았을때, 바로 double로 계산해서 넣었습니다.
```java
// 동서남북 이동확률
static double[] percents = new double[4];

// 동서남북 이동확률 입력
for(int i=0; i<4; i++){
    percents[i] = (double) Integer.parseInt((st.nextToken())) / 100;
}


```

- 최대 N번만 이동가능하기 때문에, 확인할 배열의 크기는 N*2 + 1의 크기입니다. 
``` java
// 특정 위치에서 N번씩만 이동할 수 있으므로 2N+1 크기면 벗어나지 않음.
vis = new boolean[N*2 + 1][N*2 + 1];
```

- 이후 dfs를 수행하는데, 가지치기 조건은 다음과 같습니다. <br>
    1) 해당 방향으로 갈 확률이 0일 경우
    2) 이미 방문한 좌표 == 단순하지 않은 경로일경우
```java
static void dfs(int y, int x, double percent, int num){
    // 단순이동 N번 수행시
    if(num == N){
        // 확률 더하기
        ans += percent;
        return;
    }

    for(int z=0; z<4; z++){
        // 갈 확률이 없을경우
        if(percents[z] == 0.0) continue;
        int zy = y + dy[z];
        int zx = x + dx[z];
        // 단순하지 않은 경로일 경우
        if(vis[zy][zx]) continue;

        vis[zy][zx] = true;
        dfs(zy,zx, percent * percents[z], num+1);
        vis[zy][zx] = false;
    }
}
```

### 소스코드 1
```java
package week31.BOJ_1405_G5_미친로봇;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 이동횟수
    static int N;
    // 단순이동 확률
    static double ans;
    // 동서남북 이동확률
    static double[] percents = new double[4];

    // delta array (동,서,남,북)
    static int[] dy={0,0,1,-1}, dx={1,-1,0,0};
    // dfs용 방문배열
    static boolean[][] vis;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt((st.nextToken()));
        // 동서남북 이동확률 입력
        for(int i=0; i<4; i++){
            percents[i] = (double) Integer.parseInt((st.nextToken())) / 100;
        }

        // 특정 위치에서 N번씩만 이동할 수 있으므로 2N+1 크기면 벗어나지 않음.
        vis = new boolean[N*2 + 1][N*2 + 1];

        vis[N][N] = true;
        dfs(N, N, 1, 0);

        System.out.println(ans);
    }


    static void dfs(int y, int x, double percent, int num){

        // 단순이동 N번 수행시
        if(num == N){
            // 확률 더하기
            ans += percent;
            return;
        }

        for(int z=0; z<4; z++){
            // 갈 확률이 없을경우
            if(percents[z] == 0.0) continue;
            int zy = y + dy[z];
            int zx = x + dx[z];
            // 단순하지 않은 경로일 경우
            if(vis[zy][zx]) continue;

            vis[zy][zx] = true;
            dfs(zy,zx, percent * percents[z], num+1);
            vis[zy][zx] = false;
        }
    }
}


```

### 결과 1
```
메모리 : 12260 KB	
시간 : 132 ms
```
