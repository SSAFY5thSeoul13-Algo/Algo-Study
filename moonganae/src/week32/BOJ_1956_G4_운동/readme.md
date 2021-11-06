## BAEKJOON GOLD4 1956 운동
- FloydWashall
- Gold 4
- https://www.acmicpc.net/problem/1956
<br>

### 문제설명

V개의 마을와 E개의 도로로 구성되어 있는 도시가 있다. 도로는 마을과 마을 사이에 놓여 있으며, 일방 통행 도로이다. 마을에는 편의상 1번부터 V번까지 번호가 매겨져 있다고 하자.

당신은 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다. 운동을 한 후에는 다시 시작점으로 돌아오는 것이 좋기 때문에, 우리는 사이클을 찾기를 원한다. 단, 당신은 운동을 매우 귀찮아하므로, 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.

도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램을 작성하시오. 두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.

### 입력
첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1)) 

다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다. 

a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의) 

거리는 10,000 이하의 자연수이다. 

(a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.

### 출력
첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다.

운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

### 입출력 예

#### 예제 1
입력
```
3 4
1 2 1
3 2 1
1 3 5
2 3 2
```
출력
```
3
```

### 풀이 및 과정
플로이드 워샬 알고리즘을 사용하였습니다.

전체적인 풀이는 다음과 같습니다.
1. 플로이드 워샬 알고리즘으로 최단거리를 구한다.
2. i->j, j->i의 경로가 존재 == cycle이 존재할 때의 최단 거리를 구한다.
```java
// 플로이드 워샬알고리즘으로 최단거리를 먼저 구해준다.
floydWashall();

// 가장 짧은 사이클을 찾고 거리를 구한다.
int ans = findCycle();

if(ans == INF) System.out.println(-1);
else System.out.println(ans);
```

#### 1. 플로이드 워샬
```java
static void floydWashall(){
    for(int k=0; k<V; k++){
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(A[i][k] + A[k][j] < A[i][j])
                    A[i][j] = A[i][k] + A[k][j];
            }
        }
    }
}
```
#### 2. 사이클 찾기
A[i][j] != INF && A[j][i] != INF 일때 사이클이 존재한다고 할 수 있다.<br>
(i->j, j->i 경로가 있다는 말!) 
```java
 static int findCycle(){
    int ans = INF;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i==j) continue;
                // 양방향으로 갈 수 있다 == 사이클이 존재한다.
                if(A[i][j] != INF && A[j][i] != INF){
                    ans = Math.min(ans, A[i][j] + A[j][i]);
                }
            }
        }
        return ans;
    }
```
### 소스코드
```java
package week32.BOJ_1956_G4_운동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static final int INF = 4000000;
    static int[][] A;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        A = new int[V][V];
        for(int i=0; i<V; i++){
            Arrays.fill(A[i], INF);
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            A[from][to] = w;
        }
        // 플로이드 워샬알고리즘으로 최단거리를 먼저 구해준다.
        floydWashall();

        // 가장 짧은 사이클을 찾고 거리를 구한다.
        int ans = findCycle();

        if(ans == INF) System.out.println(-1);
        else System.out.println(ans);
    }
    static void floydWashall(){
        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(A[i][k] + A[k][j] < A[i][j])
                        A[i][j] = A[i][k] + A[k][j];
                }
            }
        }
    }
    static int findCycle(){
        int ans = INF;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i==j) continue;
                // 양방향으로 갈 수 있다 == 사이클이 존재한다.
                if(A[i][j] != INF && A[j][i] != INF){
                    ans = Math.min(ans, A[i][j] + A[j][i]);
                }
            }
        }
        return ans;
    }
}


```

### 결과
```
메모리 : 57876 KB	
시간 : 556 ms
```
