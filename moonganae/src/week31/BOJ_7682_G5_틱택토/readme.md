## BAEKJOON Gold5 7682 틱택토
- 구현 백트래킹
- Gold 5
- https://www.acmicpc.net/problem/7682
<br>

### 문제설명

> 틱택토 게임은 두 명의 사람이 번갈아가며 말을 놓는 게임이다. 게임판은 3×3 격자판이며, 처음에는 비어 있다. 두 사람은 각각 X 또는 O 말을 번갈아가며 놓는데, 반드시 첫 번째 사람이 X를 놓고 두 번째 사람이 O를 놓는다. 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는 데 성공하면 게임은 즉시 끝난다. 게임판이 가득 차도 게임은 끝난다.
게임판의 상태가 주어지면, 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별하시오.



### 입력
- 입력은 여러 개의 테스트 케이스로 이루어져 있다.
- 각 줄은 9개의 문자를 포함하며, 'X', 'O', '.' 중 하나이다.
- '.'은 빈칸을 의미하며, 9개의 문자는 게임판에서 제일 윗 줄 왼쪽부터의 순서이다.
- 입력의 마지막에는 문자열 "end"가 주어진다.

### 출력
각 테스트 케이스마다 한 줄에 정답을 출력한다. 가능할 경우 "valid", 불가능할 경우 "invalid"를 출력한다.

### 입출력 예

#### 예제 1
입력

```
XXXOO.XXX
XOXOXOXOX
OXOXOXOXO
XXOOOXXOX
XO.OX...X
.XXX.XOOO
X.OO..X..
OOXXXOOXO
end
```
출력

```
invalid
valid
invalid
valid
valid
invalid
invalid
invalid
```


### 풀이 및 과정
그냥 단순한 구현인데... 예외조건을 다 처리하지 못해서 계속해서 틀렸습니다.


#### 막힌점
- 해당 케이스는 9개의 돌을 모두 놓았을때, X의 갯수 - O의 갯수가 1 초과일때의 예외를 처리해주었다.
```java
// X돌과 O돌의 차이가 1개 초과이면 안된다.
if(cntX-cntO > 1) return false;
```

- 9개의 돌을 모두 놓았을때, O가 이기지만 않으면 되는조건
```java
// 놓을 말이 없는경우 O가 이기지 않으면 된다.
        else if(cntX+cntO == 9 && cnt[2] == 0) return true;
```

### 소스코드
```java
package week31.BOJ_7682_G5_틱택토;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println((int)'O'%10 - 7); // 2
//        System.out.println((int)'X'%10 - 7); // 1
//        System.out.println((int)'O'%10 - 7 | (int)'X'%10 - 7); // 3
        int idx = 0;
        while(true){

            String input = br.readLine();

            if(input.equals("end")) break;

            if(solve(input)) System.out.println("valid");
            else System.out.println("invalid");
        }
    }

    static boolean solve(String input){


        int[] cnt = new int[3];

        int cntO=0, cntX=0;
        // OX 갯수세기
        for(char c : input.toCharArray()){
            if(c == 'O') cntO++;
            if(c == 'X') cntX++;
        }

        // O가 X보다 많으면 안됨.
        if(cntO > cntX) return false;
        // X돌과 O돌의 차이가 1개 초과이면 안된다.
        if(cntX-cntO > 1) return false;

        // 0 1 2 , 0 3 6
        // 3 4 5 , 1,4,7
        // 6,7.8 , 2,5,8
        // 빙고 갯수 세기
        for(int i=0; i<3; i++){
            // 가로
            int horizon = i + i*2;
            if(input.charAt(horizon) != '.' && input.charAt(horizon) == input.charAt(horizon +1) && input.charAt(horizon) == input.charAt(horizon+2) ) {
                cnt[(int)input.charAt(horizon)%10 - 7]++;
            }
            // 세로
            if(input.charAt(i) != '.' && input.charAt(i) == input.charAt(i+3) && input.charAt(i) == input.charAt(i+6) ) {
                cnt[(int)input.charAt(i)%10 - 7]++;
            }
        }
        int idx = (int) input.charAt(4)%10 - 7;
        // 대각선
        if(idx != -1 && input.charAt(0) == input.charAt(4) && input.charAt(4) == input.charAt(8)) cnt[idx]++;
        if(idx != -1 && input.charAt(2) == input.charAt(4) && input.charAt(4) == input.charAt(6)) cnt[idx]++;

        // OX 빙고가 동시에 존재하면 안된다.
        if(cnt[1] > 0 && cnt[2] > 0) return false;
            // X가 이겼다면 X의 수가 O의 수보다 1개
        else if(cnt[1]>0 && (cntX-cntO) == 1 ) return true;
            // O가 이겼다면 X의 수와 O의 수가 같다.
        else if(cnt[2]>0 && cntX == cntO) return true;
            // 놓을 말이 없는경우 O가 이기지 않으면 된다.
        else if(cntX+cntO == 9 && cnt[2] == 0) return true;

        return false;
    }

}
```

### 결과
```
메모리 : 221180 KB	
시간 : 912 ms
```
