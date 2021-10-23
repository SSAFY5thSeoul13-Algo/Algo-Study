## BOJ 7682 G5 틱택토
- 구현
- 백트래킹
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/7682

틱택토 게임은 두 명의 사람이 번갈아가며 말을 놓는 게임이다. 게임판은 3×3 격자판이며, 처음에는 비어 있다. 두 사람은 각각 X 또는 O 말을 번갈아가며 놓는데, 반드시 첫 번째 사람이 X를 놓고 두 번째 사람이 O를 놓는다. 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는 데 성공하면 게임은 즉시 끝난다. 게임판이 가득 차도 게임은 끝난다.

게임판의 상태가 주어지면, 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별하시오.


#### 입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 줄은 9개의 문자를 포함하며, 'X', 'O', '.' 중 하나이다. '.'은 빈칸을 의미하며, 9개의 문자는 게임판에서 제일 윗 줄 왼쪽부터의 순서이다. 입력의 마지막에는 문자열 "end"가 주어진다.

#### 출력
각 테스트 케이스마다 한 줄에 정답을 출력한다. 가능할 경우 "valid", 불가능할 경우 "invalid"를 출력한다.

###  💡 풀이

모든 경우를 구하고 나올 수 없는 경우를 제외하여 리스트를 만들어 풀이했다

1. X의 수가 O보다 2이상 큰 경우
2. O의 수가 X보다 큰 경우
3. 대각선을 제외하고 같은 방향으로의 빙고가 2개 이상 있는 경우
4. X == O 인데  X빙고가 있는 경우
5. X > O 인데 O빙고가 있는 경우
6. 빈칸이 있는데 빙고가 없는 경우


###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		makeCase(0, "");

		while(true) {
			String str = br.readLine();
			
			if("end".equals(str))	break;
			
			if(list.contains(str)) {
				sb.append("valid").append("\n");
			}
			else {
				sb.append("invalid").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void makeCase(int length, String str) {
		if(length == 9) {
			checkCase(str);
			return;
		}
		
		makeCase(length+1, str+".");
		makeCase(length+1, str+"X");
		makeCase(length+1, str+"O");
	}
	
	static void checkCase(String str) {
		char[][] map = new char[3][3];
		
		int xVerCnt = 0;
		int xHorCnt = 0;
		int xCnt = 0;
		
		int oVerCnt = 0;
		int oHorCnt = 0;
		int oCnt = 0;
		
		int pointCnt = 0;
		
		for (int i = 0; i < 9; i++) {
			char c = str.charAt(i);
			map[i/3][i%3] = c;
			
			if('.' == c)	pointCnt++;
			else if('X' == c)	xCnt++;
			else if('O' == c)	oCnt++;
		}
		
		// x - o 는 0아니면 1이어야 함
		if(xCnt - oCnt > 1) {
			return;
		}
		if(oCnt > xCnt)	return;
		
		//가로, 세로 빙고 확인
		for (int i = 0; i < 3; i++) {
			char c1 = map[i][0];
			char c2 = map[i][1];
			char c3 = map[i][2];
			
			if( c1 == 'X' && c2 == 'X' && c3 == 'X' ) {
				xVerCnt++;
			}
			if( c1 == 'O' && c2 == 'O' && c3 == 'O' ) {
				oVerCnt++;
			}
			
			char c4 = map[0][i];
			char c5 = map[1][i];
			char c6 = map[2][i];
			
			if( c4 == 'X' && c5 == 'X' && c6 == 'X' ) {
				xHorCnt++;
			}
			if( c4 == 'O' && c5 == 'O' && c6 == 'O' ) {
				oHorCnt++;
			}
			

		}
		
		//같은 방향의 빙고는 2개가 있을 수 없음
		if(oHorCnt > 1 || xHorCnt > 1 || oVerCnt > 1 || xVerCnt > 1)	return;
		
		//대각선 빙고 확인
		char c1 = map[0][0];
		char c2 = map[1][1];
		char c3 = map[2][2];
		char c5 = map[2][0];
		char c6 = map[0][2];
		
		int xBingoCnt = xHorCnt + xVerCnt;
		int oBingoCnt = oHorCnt + oVerCnt;
		
		if( c1 == 'X' && c2 == 'X' && c3 == 'X' )	xBingoCnt++;
		if( c1 == 'O' && c2 == 'O' && c3 == 'O' )	oBingoCnt++;
		
		if( c5 == 'X' && c2 == 'X' && c6 == 'X' )	xBingoCnt++;
		if( c5 == 'O' && c2 == 'O' && c6 == 'O' )	oBingoCnt++;
		
		// x == o 이면 x빙고가 없어야 함
		if(xCnt == oCnt && xBingoCnt > 0) {
			return;
		}
		
		// x > o 이면 o빙고가 없어야 함
		if(xCnt > oCnt && oBingoCnt > 0) {
			return;
		}
		
		//빈칸이 없으면 valid
		if(pointCnt == 0) {
			list.add(str);
		}
		
		//빈 칸이 있는데 빙고가 없으면 invalid
		if(xBingoCnt + oBingoCnt == 0)	return;
		
		//나머지는 모두 valid
		list.add(str);
	}

}





```


<br>



메모리|시간
--|--
19284 KB|140 ms