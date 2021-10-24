## BAEKJOON GOLD2 17837 새로운 게임 2
- 구현, 시뮬레이션
- Gold 2
- https://www.acmicpc.net/problem/17837
<br>

### 문제설명
재현이는 주변을 살펴보던 중 체스판과 말을 이용해서 새로운 게임을 만들기로 했다. 새로운 게임은 크기가 N×N인 체스판에서 진행되고, 사용하는 말의 개수는 K개이다. 말은 원판모양이고, 하나의 말 위에 다른 말을 올릴 수 있다. 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나로 색칠되어있다.

게임은 체스판 위에 말 K개를 놓고 시작한다. 말은 1번부터 K번까지 번호가 매겨져 있고, 이동 방향도 미리 정해져 있다. 이동 방향은 위, 아래, 왼쪽, 오른쪽 4가지 중 하나이다.

턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다. 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다. 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르며 아래와 같다. 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.

- A번 말이 이동하려는 칸이 
  - 흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
    - A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
    - 예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
  - 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
    - A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
    - A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
  - 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다. 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
  - 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
  


체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호를 구해보자.



### 입력
- 첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다. 
- 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.
- 다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.
- 같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.
### 출력
- 게임이 종료되는 턴의 번호를 출력한다. 
- 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.

### 입출력 예

#### 예제 1
입력
```
4 4
0 0 2 0
0 0 1 0
0 0 1 2
0 2 0 0
2 1 1
3 2 3
2 2 1
4 1 2
```
출력
```
-1
```

#### 예제 2
입력
```
4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
1 4 1
```
출력
```
1
```

#### 예제 3
입력
```
4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
2 4 3
```
출력
```
1
```

#### 예제 4
입력
```
4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
3 3 3
```
출력
```
2
```

#### 예제 5
입력
```
6 10
0 1 2 0 1 1
1 2 0 1 1 0
2 1 0 1 1 0
1 0 1 1 0 2
2 0 1 2 0 1
0 2 1 0 2 1
1 1 1
2 2 2
3 3 4
4 4 1
5 5 3
6 6 2
1 6 3
6 1 2
2 4 3
4 2 1
```
출력
```
7
```

### 풀이 및 과정
구현문제입니다.

#### 체스판 관리
체스판 관리는 다음 2개의 배열로 이루어집니다.
1. Stack<Integer>[i][j] map : (i,j)위치에 존재하는 말들의 인덱스를 순서에 맞게 관리하는 이차원배열
2. int[i][j] color : (i,j) 위치의 색깔을 관리하는 배열

#### 체스말들의 위치정보
먼저 체스말을 클래스로 정의하였고, 인덱스 번호 순서대로 이동하기 때문에 이를 관리할 hourseList를 사용하였습니다.
```java
// 말의 정보
static class Horse {
    // 말번호, 좌표값
    int num, y, x;
    // 방향
    Direction dir;

    public Horse(int num, int y, int x, Direction dir) {
        this.num = num;
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}
```

#### enum
2개의 enum 클래스를 사용하였습니다.
1. ColorType : 흰색, 빨간색, 파란색의 색깔을 나타냅니다.
2. Direction : 오른쪽, 왼쪽, 위쪽, 아랫쪽의 말들이 이동할 방향을 나타냅니다.
```java
 // 색깔 타입을 나타내는 enum
static enum ColorType {
    WHITE, RED, BLUE
}
// 방향 타입을 나타내는 enum
static enum Direction{
    RIGHT, LEFT, UP, DOWN
}
```


#### 풀이과정 

1. 먼저 0번 ~ K-1번 체스말들이 순서대로 이동하고, 종료여부를 확인합니다.
```java
int time = 0;

// 최대 1000번
while(time++ <= 1000){
    // 0 ~ K-1번말 이동시작
    for(Horse horse : horseList){
        boolean isEnd = move(horse);
        if(isEnd){
            System.out.println(time);
            return;
        }
    }
}
```
2. move 함수에서는 가장 먼저 해당 체스말이 이동하는 여부를 판단합니다.
```java
int my = horse.y + dy[horse.dir.ordinal()];
int mx = horse.x + dx[horse.dir.ordinal()];

// 번위를 벗어나거나 파란색 좌표일경우
if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()){

    // 반대방향 좌표 가져오기
    Direction reverseDir = getReverse(horse.dir);
    my = horse.y + dy[reverseDir.ordinal()];
    mx = horse.x + dx[reverseDir.ordinal()];

    // 말의 방향 반대로 변경
    horse.dir = reverseDir;
    // 이동가능 여부확인 -> 변화가 없으므로, 종료 x
    if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()) return false;
}
```
3. 이동을 한다면 이동할 체스말들을 deque에 옮깁니다.
```java
// 이동할 말들 가져오기
Deque<Integer> moveHorse = new ArrayDeque<>();
Stack<Integer> curPoint = map[horse.y][horse.x];
while(!curPoint.isEmpty() && curPoint.peek() != horse.num){
    int num = curPoint.pop();
    Horse anotherHorse = horseList.get(num);
    anotherHorse.y = my;
    anotherHorse.x = mx;
    moveHorse.add(num);
}
moveHorse.add(curPoint.pop());
horse.y = my;
horse.x = mx;
```
4. 그리고 판의 색깔마다 순서를 반대로 혹은 그대로 옮깁니다.
```java
// 빨간색은 순서 반대로 -> 스택에서 꺼냈으므로, 큐처럼 빼오면 순서반대
if(color[my][mx] == ColorType.RED.ordinal()){
    while(!moveHorse.isEmpty()){
        map[my][mx].push(moveHorse.poll());
    }
}
// 흰색, 파란색은 숫자 그대로 -> 스택에서 꺼냈으므로, 스택처럼 빼오면 순서반대
else{
    while(!moveHorse.isEmpty()){
        map[my][mx].push(moveHorse.pollLast());
    }
}
```
5. 옮긴 위치에 체스말이 4개이상(종료조건)인지를 판단합니다.
```java
// 옮길 위치에 말이 4개이상 존재할 경우 종료 플래그 반환
if(map[my][mx].size()> 3) return true;

return false;
```

### 막힌점
1. Deque에서 poll(), pop() 메서드가 같은 역할을 하는지 몰랐음.
   1. pop대신 pollLast() 메서드를 사용하여 해결

### 소스코드 1
```java
package week31.BOJ_17837_G2_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이시간 : 1시간 40분
 */
public class Main {
    // 체스판 크기, 체스말의 갯수
    static int N, K;
    // 체스판
    static Stack<Integer>[][] map;
    // 체스판 색깔
    static int[][] color;

    // 체스말 관리 리스트
    static List<Horse> horseList = new ArrayList<>();
    // delta array
    static int[] dy={0,0,-1,1}, dx={1,-1,0,0};

    // 색깔 타입을 나타내는 enum
    static enum ColorType {
        WHITE, RED, BLUE
    }
    // 방향 타입을 나타내는 enum
    static enum Direction{
        RIGHT, LEFT, UP, DOWN
    }

    // 말의 정보
    static class Horse{
        // 말번호, 좌표값
        int num, y,x;
        // 방향
        Direction dir;
        public Horse(int num, int y, int x, Direction dir){
            this.num = num;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        @Override
        public String toString(){
            return "num="+(num+1) + " y="+(y+1) + " x="+(x+1) +" dir="+dir;
        }
    }
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Stack[N][N];
        color = new int[N][N];

        // 체스판 및 체스판 색깔 설정
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = new Stack<>();
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 말의 정보 입력
        Direction[] directions = Direction.values();
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            // 좌표나 색깔이 1부터 시작하는데 0부터 시작하기 위한 -1
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            horseList.add(new Horse(i,y,x, directions[dir]));

            map[y][x].push(i);
        }

        int time = 0;

        // 최대 1000번
        while(time++ <= 1000){
            // 0 ~ K-1번말 이동시작
            for(Horse horse : horseList){
                boolean isEnd = move(horse);
                if(isEnd){
                    System.out.println(time);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
    static void printHorse(){
        for(Horse horse : horseList){
            System.out.println(horse);
        }
    }


    static boolean move(Horse horse){

        int my = horse.y + dy[horse.dir.ordinal()];
        int mx = horse.x + dx[horse.dir.ordinal()];

        // 번위를 벗어나거나 파란색 좌표일경우
        if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()){

            // 반대방향 좌표 가져오기
            Direction reverseDir = getReverse(horse.dir);
            my = horse.y + dy[reverseDir.ordinal()];
            mx = horse.x + dx[reverseDir.ordinal()];

            // 말의 방향 반대로 변경
            horse.dir = reverseDir;
            // 이동가능 여부확인 -> 변화가 없으므로, 종료 x
            if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()) return false;
        }

        // 이동할 말들 가져오기
        Deque<Integer> moveHorse = new ArrayDeque<>();
        Stack<Integer> curPoint = map[horse.y][horse.x];
        while(!curPoint.isEmpty() && curPoint.peek() != horse.num){
            int num = curPoint.pop();
            Horse anotherHorse = horseList.get(num);
            anotherHorse.y = my;
            anotherHorse.x = mx;
            moveHorse.add(num);
        }
        moveHorse.add(curPoint.pop());
        horse.y = my;
        horse.x = mx;

        // 빨간색은 순서 반대로 -> 스택에서 꺼냈으므로, 큐처럼 빼오면 순서반대
        if(color[my][mx] == ColorType.RED.ordinal()){
            while(!moveHorse.isEmpty()){
                map[my][mx].push(moveHorse.poll());
            }
        }
        // 흰색, 파란색은 숫자 그대로 -> 스택에서 꺼냈으므로, 스택처럼 빼오면 순서반대
        else{
            while(!moveHorse.isEmpty()){
                map[my][mx].push(moveHorse.pollLast());
            }
        }

        // 옮길 위치에 말이 4개이상 존재할 경우 종료 플래그 반환
        if(map[my][mx].size()> 3) return true;

        return false;
    }

    // 반대방향을 반환해주는 메서드
    static Direction getReverse(Direction dir){
        if(dir == Direction.LEFT) return Direction.RIGHT;
        else if(dir == Direction.RIGHT) return Direction.LEFT;
        else if(dir == Direction.UP) return Direction.DOWN;

        return Direction.UP;
    }
}

```

### 결과 1
```
메모리 : 14056 KB	
시간 : 116		ms
```
