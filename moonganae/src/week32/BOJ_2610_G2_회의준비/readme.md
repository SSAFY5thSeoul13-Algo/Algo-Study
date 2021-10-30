## BAEKJOON GOLD2 2610 회의준비
- Union&find, floydWarshall
- Gold 2
- https://www.acmicpc.net/problem/2610
<br>

### 문제설명
KOI 준비를 위해 회의를 개최하려 한다. 주최측에서는 회의에 참석하는 사람의 수와 참석자들 사이의 관계를 따져 하나 이상의 위원회를 구성하려고 한다. 위원회를 구성하는 방식은 다음과 같다.

서로 알고 있는 사람은 반드시 같은 위원회에 속해야 한다.
효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 한다.
이런 방식으로 위원회를 구성한 후에 각 위원회의 대표를 한 명씩 뽑아야 한다. 각 위원회의 대표만이 회의 시간 중 발언권을 가지며, 따라서 회의 참석자들이 자신의 의견을 말하기 위해서는 자신이 속한 위원회의 대표에게 자신의 의견을 전달해야 한다. 그런데 각 참석자는 자신이 알고 있는 사람에게만 의견을 전달할 수 있어 대표에게 의견을 전달하기 위해서는 때로 여러 사람을 거쳐야 한다. 대표에게 의견을 전달하는 경로가 여러 개 있을 경우에는 가장 적은 사람을 거치는 경로로 의견을 전달하며 이때 거치는 사람의 수를 참석자의 의사전달시간이라고 한다.

위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표를 정하는 프로그램을 작성하시오.

예를 들어 1번, 2번, 3번 세 사람으로 구성되어 있는 위원회에서 1번과 2번, 2번과 3번이 서로 알고 있다고 하자. 1번이 대표가 되면 3번이 대표인 1번에게 의견을 전달하기 위해서 2번을 거쳐야만 한다. 반대로 3번이 대표가 되어도 1번이 대표인 3번에게 의견을 전달하려면 2번을 거쳐야만 한다. 하지만 2번이 대표가 되면 1번과 3번 둘 다 아무도 거치지 않고 대표에게 직접 의견을 전달 할 수 있다. 따라서 이와 같은 경우 2번이 대표가 되어야 한다.



### 입력
첫째 중에 회의에 참석하는 사람의 수 N이 주어진다. 참석자들은 1부터 N까지의 자연수로 표현되며 회의에 참석하는 인원은 100 이하이다. 

둘째 줄에는 서로 알고 있는 관계의 수 M이 주어진다. 

이어 M개의 각 줄에는 서로 아는 사이인 참석자를 나타내는 두개의 자연수가 주어진다.

### 출력
첫째 줄에는 구성되는 위원회의 수 K를 출력한다. 

다음 K줄에는 각 위원회의 대표 번호를 작은 수부터 차례로 한 줄에 하나씩 출력한다. 

한 위원회의 대표가 될 수 있는 사람이 둘 이상일 경우 그중 한 명만 출력하면 된다.

### 입출력 예

#### 예제 1
입력
```
8
7
1 2
2 3
4 5
5 6
4 6
6 7
7 4
```
출력
```
3
2
4
8
```

### 풀이 및 과정
위원회를 나누기 위해서 Union&Find를, 최대 의견전달시간을 구하기위해서 floydWarshall 알고리즘을 사용하였습니다.

1. 먼저 관계들을 입력받으며, union 함수로 연결시켜주고, 각 관계들의 가중치는 1을 할당합니다.
```java
for(int i=0; i<M; i++){
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 인덱스가 0부터 시작하기 때문에 -1
    int a = Integer.parseInt(st.nextToken())-1;
    int b = Integer.parseInt(st.nextToken())-1;

    // 결합
    union(a,b);

    // 의견 경로의 wegith는 1
    A[a][b] = A[b][a] = 1;
}
```
2. floydWarshall 알고리즘을 사용하여 최단 의견전달 길이를 구해줍니다.
```java
 // 플로이드 워샬 - 의견전달시간 구하기
floydWarshall();

static void floydWarshall(){
  // 거쳐가는 노드
  for(int k=0; k<N; k++){
    // 출발노드
    for(int i=0; i<N; i++){
      // 도착노드
      for(int j=0; j<N; j++){
        if(i==j) continue;
        if(A[i][k] + A[k][j] < A[i][j])
          A[i][j] = A[i][k] + A[k][j];
      }
    }
  }
}
 ```
3. 그런 다음 위원장들을 찾아줍니다.
```java
// 위원장들 찾기
List<int[]> leaders = findLeaders();

static List<int[]> findLeaders(){

  // 위원장 리스트 (int[0] : 위원장 인덱스번호, int[1] : 최대 의견전달시간)
  List<int[]> leaders = new ArrayList<>();
  
  // 각 그룹별 leaders 리스트의 인덱스 보관
  int[] idxs = new int[N];
  for(int i=0; i<N; i++){
    idxs[i] = -1;
  }
  
  for(int i=0; i<N; i++){
  
    int max = 0, group = find(i);
    
    // 최대 의견전달 시간 구하기
    for(int j=0; j<N; j++){
      if(A[i][j] == INF) continue;
      max = Math.max(max, A[i][j]);
    }
    
    int groupIdx = idxs[group];
    
    // 아직 그룹의 최대 의견전달 시간이 없을때
    if(groupIdx == -1){
      idxs[group] = leaders.size();
      leaders.add(new int[]{i,max});
    }else if(leaders.get(groupIdx)[1] > max){
      // 위원장 및 의견전달 시간 갱신
      leaders.get(groupIdx)[0] = i;
      leaders.get(groupIdx)[1] = max;
    }
  }
  
  return leaders;
}
```
### 소스코드 1
```java
package week31package week32.BOJ_2610_G2_회의준비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  // 사람수, 관계수
  static int N, M;
  static final int INF = 1000;

  // Union & find
  static int[] parent;

  // 관계배열
  static int[][] A;
  public static void main(String[] args) throws Exception{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    parent = new int[N];
    A = new int[N][N];

    // 관계배열 및 부모배열 초기화
    for(int i=0; i<N; i++){
      Arrays.fill(A[i], INF);
      parent[i] = i;
    }

    for(int i=0; i<M; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 인덱스가 0부터 시작하기 때문에 -1
      int a = Integer.parseInt(st.nextToken())-1;
      int b = Integer.parseInt(st.nextToken())-1;

      // 결합
      union(a,b);

      // 의견 경로의 wegith는 1
      A[a][b] = A[b][a] = 1;
    }

    // 플로이드 워샬 - 의견전달시간 구하기
    floydWarshall();

    // 위원장들 찾기
    List<int[]> leaders = findLeaders();

    // 위원장 인덱스 순으로 정렬
    Collections.sort(leaders, (e1, e2) -> e1[0]-e2[0]);~~
    // 위원회수 출력
    System.out.println(leaders.size());
    // 위원장 인덱스 출력
    for(int[] group : leaders){
      System.out.println(group[0] + 1);
    }
  }

  static int find(int t){
    if(t == parent[t]) return t;

    return parent[t] = find(parent[t]);
  }

  static void union(int a, int b){
    int pa = find(a);
    int pb = find(b);

    if(pa <= pb) parent[pb] = pa;
    else parent[pa] = pb;
  }

  static void floydWarshall(){
    // 거쳐가는 노드
    for(int k=0; k<N; k++){
      // 출발노드
      for(int i=0; i<N; i++){
        // 도착노드
        for(int j=0; j<N; j++){
          if(i==j) continue;
          if(A[i][k] + A[k][j] < A[i][j])
            A[i][j] = A[i][k] + A[k][j];
        }
      }
    }
  }

  static List<int[]> findLeaders(){

    // 위원장 리스트 (int[0] : 위원장 인덱스번호, int[1] : 최대 의견전달시간)
    List<int[]> leaders = new ArrayList<>();

    // 각 그룹별 leaders 리스트의 인덱스 보관
    int[] idxs = new int[N];
    for(int i=0; i<N; i++){
      idxs[i] = -1;
    }

    for(int i=0; i<N; i++){

      int max = 0, group = find(i);

      // 최대 의견전달 시간 구하기
      for(int j=0; j<N; j++){
        if(A[i][j] == INF) continue;
        max = Math.max(max, A[i][j]);
      }

      int groupIdx = idxs[group];

      // 아직 그룹의 최대 의견전달 시간이 없을때
      if(groupIdx == -1){
        idxs[group] = leaders.size();
        leaders.add(new int[]{i,max});
      }else if(leaders.get(groupIdx)[1] > max){
        // 위원장 및 의견전달 시간 갱신
        leaders.get(groupIdx)[0] = i;
        leaders.get(groupIdx)[1] = max;
      }
    }

    return leaders;
  }


  static void printA(){
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void printBoss(){
    for (int i = 0; i < N; i++) {
      System.out.print(parent[i] + " ");
    }
    System.out.println();
  }
}



```

### 결과 1
```
메모리 : 19112 KB	
시간 : 268 ms
```
