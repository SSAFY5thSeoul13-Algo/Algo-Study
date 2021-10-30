## BAEKJOON GOLD4 17140 이차원 배열과 연산
- 구현, 정렬
- Gold 4
- https://www.acmicpc.net/problem/17140
<br>

### 문제설명

크기가 3×3인 배열 A가 있다. 배열의 인덱스는 1부터 시작한다. 1초가 지날때마다 배열에 연산이 적용된다.

- R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 ≥ 열의 개수인 경우에 적용된다.
- C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수인 경우에 적용된다.

한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다. 그 다음, 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다. 그 다음에는 배열 A에 정렬된 결과를 다시 넣어야 한다. 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.

예를 들어, [3, 1, 1]에는 3이 1번, 1가 2번 등장한다. 따라서, 정렬된 결과는 [3, 1, 1, 2]가 된다. 다시 이 배열에는 3이 1번, 1이 2번, 2가 1번 등장한다. 다시 정렬하면 [2, 1, 3, 1, 1, 2]가 된다.

정렬된 결과를 배열에 다시 넣으면 행 또는 열의 크기가 달라질 수 있다. R 연산이 적용된 경우에는 가장 큰 행을 기준으로 모든 행의 크기가 변하고, C 연산이 적용된 경우에는 가장 큰 열을 기준으로 모든 열의 크기가 변한다. 행 또는 열의 크기가 커진 곳에는 0이 채워진다. 수를 정렬할 때 0은 무시해야 한다. 예를 들어, [3, 2, 0, 0]을 정렬한 결과는 [3, 2]를 정렬한 결과와 같다.

행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.

배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.

### 입력
- 첫째 줄에 r, c, k가 주어진다. (1 ≤ r, c, k ≤ 100)
- 둘째 줄부터 3개의 줄에 배열 A에 들어있는 수가 주어진다. 배열 A에 들어있는 수는 100보다 작거나 같은 자연수이다.

### 출력
- A[r][c]에 들어있는 값이 k가 되기 위한 연산의 최소 시간을 출력한다.
- 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.

### 입출력 예

#### 예제 1
입력
```
1 2 2
1 2 1
2 1 3
3 3 3
```
출력
```
0
```
#### 예제 2
입력
```
1 2 1
1 2 1
2 1 3
3 3 3
```
출력
```
1
```

#### 예제 4
입력
```
1 2 4
1 2 1
2 1 3
3 3 3
```
출력
```
52
```

#### 예제 5
입력
```
1 2 5
1 2 1
2 1 3
3 3 3
```
출력
```
-1
```

### 풀이 및 과정

구현의 문제인데 상당히 많은 리스트와 정렬을 사용하였습니다.

1. 연산에 따라 같은 행 또는 열의 값을 가져옵니다.(0은 제외)
```java
// 같은 행에서 0을 제외한 수를 가져오기
List<Integer> list = new ArrayList<>();
for(int j=0; j<cLen; j++){
    if(A[i][j] == 0) continue;
    list.add(A[i][j]);
}
```

2. 이를 이용하여 NumberList를 가져옵니다.<br>
Number 클래스는 특정 수와 해당 수의 갯수를 가진 클래스입니다.
```java
static class Number implements Comparable<Number> {
    int val, cnt;

    public Number(int val, int cnt) {
        this.val = val;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Number b) {
        if (this.cnt == b.cnt) {
            return this.val - b.val;
        }
        return this.cnt - b.cnt;
    }
}

static List<Number> getNumberList(List<Integer> list){
    Collections.sort(list);
    int prev = list.get(0), cnt=1;

    List<Number> numberList = new ArrayList<>();
    for(int j=1; j<list.size(); j++){

        if(prev == list.get(j)){
            cnt++;
        }else{
            numberList.add(new Number(prev, cnt));
            prev = list.get(j);
            cnt=1;
        }
    }
    numberList.add(new Number(prev, cnt));
    Collections.sort(numberList);

    return numberList;
}
```

3. numberList를 이용하여 배열 A에 정렬값을 넣어준다.
```java
// 원래 배열 A에 넣기
int idx = 0;
for(Number num : numberList){
    A[i][idx++] = num.val;
    A[i][idx++] = num.cnt;
}
```

4. 모든 행 또는 열에 대해 수행을 완료했다면 나머지 영역은 0으로 채운다.
```java
 // 숫자를 제외한 나머지 영역은 0으로 채우기
for(int i=0; i<rLen; i++){
    for(int j=lenList.get(i); j<max; j++){
        A[i][j] = 0;
    }
}

// 열의길이 갱신
cLen = max;
```
### 소스코드
```java
package week31.BOJ_17140_G4_이차원배열과연산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 확인할 좌표(R,C), 찾을 값(K)
    static int R,C,K;
    // 배열 A
    static int[][] A = new int[100][100];
    // 행의 수, 열의 수
    static int rLen = 3, cLen = 3;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) -1;
        C = Integer.parseInt(st.nextToken()) -1;
        K = Integer.parseInt(st.nextToken());

        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while(time <= 100){
            if(A[R][C] == K) {
                System.out.println(time);
                return;
            }

            if(rLen >= cLen){
                sortR();

            }else {
                sortC();
            }
            time++;
        }
        System.out.println(-1);

    }
    static void printA(){
        System.out.println("----------A------------");
        for(int i=0; i<rLen; i++){
            for(int j=0; j<cLen; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
    // R 연산
    static void sortR(){

        // 정렬을 하고난 후의 길이 보관 -> 남은 공간 0으로 채우기 위함
        List<Integer> lenList = new ArrayList<>();
        int max = -1;
        for(int i=0; i<rLen; i++){

            // 같은 행에서 0을 제외한 수를 가져오기
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<cLen; j++){
                if(A[i][j] == 0) continue;
                list.add(A[i][j]);
            }

            // Number 리스트 가져오기
            List<Number> numberList = getNumberList(list);

            // 원래 배열 A에 넣기
            int idx = 0;
            for(Number num : numberList){
                A[i][idx++] = num.val;
                A[i][idx++] = num.cnt;
            }

            max = Math.max(max, idx);
            lenList.add(idx);
        }

        // 숫자를 제외한 나머지 영역은 0으로 채우기
        for(int i=0; i<rLen; i++){
            for(int j=lenList.get(i); j<max; j++){
                A[i][j] = 0;
            }
        }

        // 열의길이 갱신
        cLen = max;
    }

    // C연산
    static void sortC(){

        // 정렬을 하고난 후의 길이 보관 -> 남은 공간 0으로 채우기 위함
        List<Integer> lenList = new ArrayList<>();
        int max = -1;
        for(int i=0; i<cLen; i++){

            // 같은 열에서 0을 제외한 수를 가져오기
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<rLen; j++){
                if(A[j][i] == 0) continue;
                list.add(A[j][i]);
            }

            // Number 리스트 가져오기
            List<Number> numberList = getNumberList(list);

            // 원래 배열 A에 넣기
            int idx = 0;
            for(Number num : numberList){
                A[idx++][i] = num.val;
                A[idx++][i] = num.cnt;
            }

            max = Math.max(max, idx);
            lenList.add(idx);
        }

        // 숫자를 제외한 나머지 영역은 0으로 채우기
        for(int i=0; i<cLen; i++){
            for(int j=lenList.get(i); j<max; j++){
                A[j][i] = 0;
            }
        }
        // 행의 길이 갱신
        rLen = max;
    }

    static List<Number> getNumberList(List<Integer> list){
        Collections.sort(list);
        int prev = list.get(0), cnt=1;

        List<Number> numberList = new ArrayList<>();
        for(int j=1; j<list.size(); j++){

            if(prev == list.get(j)){
                cnt++;
            }else{
                numberList.add(new Number(prev, cnt));
                prev = list.get(j);
                cnt=1;
            }
        }
        numberList.add(new Number(prev, cnt));
        Collections.sort(numberList);

        return numberList;
    }

    static class Number implements Comparable<Number>{
        int val, cnt;
        public Number(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number b){
            if(this.cnt == b.cnt){
                return this.val - b.val;
            }
            return this.cnt - b.cnt;
        }

        @Override
        public String toString(){
            return "val="+this.val + " cnt="+this.cnt;
        }
    }
}

```

### 결과
```
메모리 : 15500KB	
시간 : 136ms
```
