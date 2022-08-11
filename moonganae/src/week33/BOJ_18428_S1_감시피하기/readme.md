## BAEKJOON Silver1 18428 감시 피하기
- DFS
- SILVER 1
- https://www.acmicpc.net/problem/18428
<br>

### 문제설명
NxN 크기의 복도가 있다. 복도는 1x1 크기의 칸으로 나누어지며, 특정한 위치에는 선생님, 학생, 혹은 장애물이 위치할 수 있다. 현재 몇 명의 학생들은 수업시간에 몰래 복도로 빠져나왔는데, 복도로 빠져나온 학생들은 선생님의 감시에 들키지 않는 것이 목표이다.

각 선생님들은 자신의 위치에서 상, 하, 좌, 우 4가지 방향으로 감시를 진행한다. 단, 복도에 장애물이 위치한 경우, 선생님은 장애물 뒤편에 숨어 있는 학생들은 볼 수 없다. 또한 선생님은 상, 하, 좌, 우 4가지 방향에 대하여, 아무리 멀리 있더라도 장애물로 막히기 전까지의 학생들은 모두 볼 수 있다고 가정하자.

### 입력
첫째 줄에 자연수 N이 주어진다. (3 ≤ N ≤ 6) 

둘째 줄에 N개의 줄에 걸쳐서 복도의 정보가 주어진다. 

각 행에서는 N개의 원소가 공백을 기준으로 구분되어 주어진다. 

해당 위치에 학생이 있다면 S, 선생님이 있다면 T, 아무것도 존재하지 않는다면 X가 주어진다.

단, 전체 선생님의 수는 5이하의 자연수, 전체 학생의 수는 30이하의 자연수이며 항상 빈 칸의 개수는 3개 이상으로 주어진다.

### 출력
첫째 줄에 정확히 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부를 출력한다. 

모든 학생들을 감시로부터 피하도록 할 수 있다면 "YES", 그렇지 않다면 "NO"를 출력한다.

### 입출력 예

#### 예제 1
입력
```
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X
```
출력
```
YES
```

#### 예제 2
입력
```
4
S S S T
X X X X
X X X X
T T T X
```
출력
```
NO
```
### 풀이 및 과정
DFS 알고리즘을 사용하여 문제를 해결하였습니다.

#### DFS 진행과정

- 종료조건은 다음과 같습니다. 이미 이전 수에서 성공을 했거나, 3개의 장애물을 모두 놓았을 경우
```java
if(isSuccess) return;

if(cnt == MAX_SELECT){
    if(check()){
        isSuccess = true;
    }
    return;
}
```

- DFS 진행은 다음과 같습니다.
  - 현재 위치에서 진행이 가능하면 장애물을 설치('O' 설치)하고 다음 수를 진행한다.
  - 단, y == i 진행할때의 첫번째 행때만, x~N-1까지를 고려하고 나머지는 0~N-1을 탐색한다.
```java
for(int i=y; i<N; i++){
    for(int j=x; j<N; j++){
        if(!isInstall(i,j)) continue;


        // i,j를 놓았을때
        map[i][j] = 'O';
        dfs(i,j, cnt+1);
        map[i][j] = 'X';
    }
    // y 번째 줄 이후로는 j는 0~N까지임
    x = 0;
}
```


#### 감시피하기 가능 여부 판단

- 학생 모두가 피할 수 있는지 확인한다. 
  - 학생 리스트와 선생님 리스트를 전체 순회하면서 하나씩 확인
```java
static boolean check(){
    for(Point student : students){
        for(Point teacher : teachers){
            // 선생님이 학생을 찾을 수 있다면 실패
            if(isFind(student, teacher)) return false;
        }
    }

    // 모든 학생이 감시를 피하면 성공
    return true;
}
```

- isFind : 특정 선생님이 특정 학생을 찾을 수 있는지 판단하는 함수
  - 세로 범위 확인과 가로 범위 확인을 나누어 검사함.
```java
// 선생님이 학생을 찾을 수 있는가?
    static boolean isFind(Point student, Point teacher){

        // y, x 좌표가 모두 다르면 못찾음
        if(student.y != teacher.y && student.x != teacher.x) return false;

            // 장애물 확인
        else if(student.y == teacher.y){
            int s=0, e=0;
            if(student.x > teacher.x){
                s = teacher.x;
                e = student.x;
            } else{
                s = student.x;
                e = teacher.x;
            }

            if(existObstacle(student.y, s, e, 0)) return false;

        }else if(student.x == teacher.x){
            int s=0, e=0;
            if(student.y > teacher.y){
                s = teacher.y;
                e = student.y;
            } else{
                s = student.y;
                e = teacher.y;
            }

            if(existObstacle(student.x, s, e, 1)) return false;
        }

        // 장애물이 없다면 찾아버린것임..
        return true;
    }
```

- existObstacle : 원하는 범위에 장애물이 존재하는지 확인한다.
  - type에 따라 가로 탐색과 세로탐색이 정해져 있다.
```java
// 배열에서 장애물이 존재하는지 확인
static boolean existObstacle(int fix, int s, int e, int type){
    // 세로고정
    if(type == 0){
        for(int i=s+1; i<e; i++){
            if(map[fix][i] == 'O') return true;
        }
    }
    // 가로고정
    else {
        for(int i=s+1; i<e; i++){
            if(map[i][fix] == 'O') return true;
        }
    }

    return false;
}
```

### 소스코드 1
```java
package week33.BOJ_18428_S1_감시피하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[][] map;
    static final int MAX_SELECT = 3;
    static boolean isSuccess = false;

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static List<Point> students, teachers;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        students = new ArrayList<>();
        teachers = new ArrayList<>();

        map = new char[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                char data = st.nextToken().charAt(0);
                map[i][j] = data;
                if(data == 'S') students.add(new Point(i,j));
                else if(data == 'T') teachers.add(new Point(i,j));
            }
        }

        dfs(0,0,0);
        if(isSuccess) System.out.println("YES");
        else System.out.println("NO");
    }

    static void solve(){


    }
    static void dfs(int y, int x, int cnt){

        if(isSuccess) return;
        if(cnt == MAX_SELECT){
            if(check()){
                isSuccess = true;
            }
            return;
        }

        for(int i=y; i<N; i++){
            for(int j=x; j<N; j++){
                if(!isInstall(i,j)) continue;


                // i,j를 놓았을때
                map[i][j] = 'O';
                dfs(i,j+1, cnt+1);
                map[i][j] = 'X';
            }
            // y 번째 줄 이후로는 j는 0~N까지임
            x = 0;
        }
    }

    // 감시를 피할 수 있는지 확인
    static boolean check(){
        for(Point student : students){
            for(Point teacher : teachers){
                // 선생님이 학생을 찾을 수 있다면 실패
                if(isFind(student, teacher)) return false;
            }
        }

        // 모든 학생이 감시를 피하면 성공
        return true;
    }

    // 선생님이 학생을 찾을 수 있는가?
    static boolean isFind(Point student, Point teacher){

        // y, x 좌표가 모두 다르면 못찾음
        if(student.y != teacher.y && student.x != teacher.x) return false;

            // 장애물 확인
        else if(student.y == teacher.y){
            int s=0, e=0;
            if(student.x > teacher.x){
                s = teacher.x;
                e = student.x;
            } else{
                s = student.x;
                e = teacher.x;
            }

            if(existObstacle(student.y, s, e, 0)) return false;

        }else if(student.x == teacher.x){
            int s=0, e=0;
            if(student.y > teacher.y){
                s = teacher.y;
                e = student.y;
            } else{
                s = student.y;
                e = teacher.y;
            }

            if(existObstacle(student.x, s, e, 1)) return false;
        }

        // 장애물이 없다면 찾아버린것임..
        return true;
    }

    // 배열에서 장애물이 존재하는지 확인
    static boolean existObstacle(int fix, int s, int e, int type){
        // 세로고정
        if(type == 0){
            for(int i=s+1; i<e; i++){
                if(map[fix][i] == 'O') return true;
            }
        }
        // 가로고정
        else {
            for(int i=s+1; i<e; i++){
                if(map[i][fix] == 'O') return true;
            }
        }

        return false;
    }

    // DFS 진행시 (y,x) 위치에 장애물을 놓을 수 잇는지 확인
    static boolean isInstall(int y, int x){
        char obj = map[y][x];
        if(obj != 'X') return false;

        return true;
    }
}


```

### 결과 1
```
메모리 : 11940 KB	
시간 : 92 ms
```
