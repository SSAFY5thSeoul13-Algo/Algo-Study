## BAEKJOON Gold5 9663 N-Queen
- 브루트포스, 백트래킹, DFS
- Gold 5
- https://www.acmicpc.net/problem/9663
<br>

### 문제설명

N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

### 입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

### 출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

### 입출력 예

#### 예제 1
입력

```
8
```
출력

```
92
```


### 풀이 및 과정
재귀 DFS알고리즘에 백트래킹 조건을 추가해서 문제를 해결하였습니다.

#### 재귀 DFS
0번 행부터 N-1행 까지 총 N번의 재귀로 수행되는 함수를 만듭니다.

- 종료조건
  - Queen을 N개 놓았다면 함수를 종료
```java
// 끝까지 놓았을때,
if(r==N){
    ans++;
    return;
}
```

- Queen 놓기 및 백트래킹
  - 4개의 방문배열을 사용하여, 충돌가능한 Queen이 있는지 확인한다.
  
    ```java
         // 열의 번호 0~N-1
        for(int c=0; c<N; c++){

            // 충돌이 있다면 넘어가기
            if(isConflict(r,c)) continue;
        }
    ```
  - 0 ~ N-1번째 행을 순회하면서 돌을 놓고, 다음 행으로 이동한다.
    ```java
          setVis(r,c,true);

          // 탐색
          dfs(r+1);

          // 빼기
          setVis(r,c,false);    
    ```
#### 충돌확인
  4개의 방문배열을 사용하여 문제를 해결합니다.
1. 가로방문 배열 : rowVis[i] : i번째 행 사용여부
2. 세로 방문 배열 : colVis[i] : i번째 행 사용여부
3. 좌하 -> 우상 대각선 : lVis[r+c] : (r+c)번째 대각선 사용여부
    ```
   N = 4 일때 대각선 번호
   0 1 2 3
   1 2 3 4
   2 3 4 5
   3 4 5 6
   ```
4. 죄상 -> 우하 대각선 : rVis[(N-1) - r + c] : [(N-1) - r + c]번째 대각선 사용여부
    ```
   N = 4 일때 대각선 번호
   3 4 5 6
   2 3 4 5
   1 2 3 4
   0 1 2 3
   ```

### 소스코드
```java
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

```

### 결과
```
메모리 : 12296 KB	
시간 : 2960 ms
```
