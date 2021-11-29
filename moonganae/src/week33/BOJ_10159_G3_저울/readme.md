## BAEKJOON Gold3 10159 저울
- 플로이드 워샬
- Gold 3
- https://www.acmicpc.net/problem/10159
<br>

### 문제설명

무게가 서로 다른 N 개의 물건이 있다. 각 물건은 1부터 N 까지 번호가 매겨져 있다. 우리는 일부 물건 쌍에 대해서 양팔 저울로 어떤 것이 무거운 것인지를 측정한 결과표를 가지고 있다. 이 결과표로부터 직접 측정하지 않은 물건 쌍의 비교 결과를 알아낼 수도 있고 알아내지 못할 수도 있다. 예를 들어, 총 6개의 물건이 있고, 다음 5개의 비교 결과가 주어졌다고 가정하자. ([1]은 1번 물건의 무게를 의미한다.)

[1]>[2], [2]>[3], [3]>[4], [5]>[4], [6]>[5]

우리는 [2]>[3], [3]>[4]로부터 [2]>[4]라는 것을 알 수 있다. 하지만, 물건 2와 물건 6을 비교하는 경우, 앞서의 결과만으로는 어느 것이 무거운지 알 수 없다. 이와 같이, 물건 2는 물건 1, 3, 4와의 비교 결과는 알 수 있지만, 물건 5, 6과의 비교 결과는 알 수 없다. 물건 4는 모든 다른 물건과의 비교 결과를 알 수 있다.

비교 결과가 모순되는 입력은 없다고 가정한다. 위 예제의 기존 측정 결과에 [3]>[1]이 추가되었다고 가정하자. 이 경우 [1]>[2], [2]>[3]이므로 우리는 [1]>[3]이라는 것을 예측할 수 있는데, 이는 기존에 측정된 결과 [3]>[1]과 서로 모순이므로 이러한 입력은 가능하지 않다.

물건의 개수 N 과 일부 물건 쌍의 비교 결과가 주어졌을 때, 각 물건에 대해서 그 물건과의 비교 결과를 알 수 없는 물건의 개수를 출력하는 프로그램을 작성하시오.


### 입력
첫 줄에는 물건의 개수 N 이 주어지고, 둘째 줄에는 미리 측정된 물건 쌍의 개수 M이 주어진다.

단, 5 ≤ N ≤ 100 이고, 0 ≤ M ≤ 2,000이다. 

다음 M개의 줄에 미리 측정된 비교 결과가 한 줄에 하나씩 주어진다. 

각 줄에는 측정된 물건 번호를 나타내는 두 개의 정수가 공백을 사이에 두고 주어지며, 앞의 물건이 뒤의 물건보다 더 무겁다.

### 출력
여러분은 N개의 줄에 결과를 출력해야 한다. 

i 번째 줄에는 물건 i 와 비교 결과를 알 수 없는 물건의 개수를 출력한다.

### 입출력 예

#### 예제 1
입력

```
6
5
1 2
2 3
3 4
5 4
6 5
```
출력

```
2
2
2
0
3
3
```

#### 예제 2
입력

```
9
11
2 1
3 1
2 8
2 9
7 8
4 5
6 7
6 3
1 7
6 2
1 9
```
출력

```
2
3
3
7
7
2
3
3
4
```

### 풀이 및 과정

플로이드 워샬로 문제를 해결하였습니다.

결과는 다음과 같이 2번의 과정을 통해 구할 수 있습니다.

1. 플로이드워샬 알고리즘을 사용하여 무거운 것의 표시
```java
// 플로이드워샬
// a->b, b->c => a->c 만 연결
for(int k=0; k<N; k++){
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(weight[i][k] != 0 && weight[k][j] != 0){
                weight[i][j] = 1;
            }
        }
    }
}
```
   
2. 둘다 비교우위를 알 수 없을때 카운팅
```java
// 비교우위를 알 수 없는 것의 갯수 카운팅
for(int i=0; i<N; i++){
    int cnt = 0;
    for(int j=0; j<N; j++){
        if(i==j) continue;

        if(weight[i][j]==0 && weight[j][i]==0) cnt++;

    }
    System.out.println(cnt);
}
```
#### 막힌점
- 처음에는 크면 1, 작으면 -1을 체크하며 비교우위를 나타내려고 했는데, 1-1=0 이 되면서 자꾸 틀린로직이 되었습니다.

### 소스코드
```java
package week33.BOJ_10159_G3_저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] weight;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        weight = new int[N][N];

        // 무거운 것만 체크
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int heavier = Integer.parseInt(st.nextToken()) - 1;
            int lighter = Integer.parseInt(st.nextToken()) - 1;

            weight[heavier][lighter] = 1;
        }

        solve();
    }

    static void solve(){

        // 플로이드워샬
        // a->b, b->c => a->c 만 연결
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(weight[i][k] != 0 && weight[k][j] != 0){
                        weight[i][j] = 1;
                    }
                }
            }
        }

        // 비교우위를 알 수 없는 것의 갯수 카운팅
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=0; j<N; j++){
                if(i==j) continue;

                if(weight[i][j]==0 && weight[j][i]==0) cnt++;

            }
            System.out.println(cnt);
        }

    }

    static void printWeight(){
        System.out.println("--------------------");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
    }
}

}

```

### 결과
```
메모리 : 12468 KB	
시간 : 124 ms
```
