## BOJ 7682 틱택토 
- Simulation 
- 🥇 Gold5
- 🔗[틱택토 문제 바로가기](https://www.acmicpc.net/problem/7682)



## 풀이

저는 각각의 조건에 따라 구현하였습니다. 
먼저 격자판에 모든 말이 있을 경우 말의 총 합은 9가 되고 이때 X 개수는 O보다 1더 많아야 합니다.
이 경우에는 X가 이기면서 O가 지거나, X,O 모두 졌을경우 (=비긴경우) 틱택토가 가능합니다.

~~~java
		if(sum==9 && xCnt==oCnt+1) {
			if(isWin('X') && !isWin('O')) {
				return true;
			}else if(!isWin('X') && !isWin('O')) {
				return true;
			}else {
				return false;
			}
		}
~~~

격자판위에 말이 다 있지 않은 경우 두 말의 개수가 같다면 O가 마지막으로 놓았으므로 무조건 O가 이겨야합니다. X가 O보다 클경우에는 X를 마지막으로 놓은 경우이므로 무조건 X가 이겨야합니다.

~~~java
else {
			// 두개의 개수가 같은 경우 
			if(xCnt == oCnt) {
				if(isWin('O') && !isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			// X가 O보다 1개 더 많은 경우 
			else if(xCnt==oCnt+1) {
				if(!isWin('O') && isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			
		}
~~~

## 막힌 점 
세세한 조건을 주어야했기때문에 구현부분은 어렵지 않았으나 테스트케이스에서 틀리는 경우가 있었습니다.

## 소스코드
~~~java
import java.util.*;
import java.io.*;
public class BOJ_7682_G5_틱택토 {
	static char[][] arr;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			
			if(str.equals("end")) {
				break;
			}
			
			arr = new char[3][3];
			for(int i=0; i<9; i++) {
				arr[i/3][i%3] = str.charAt(i);
			}
			
			if(tictactoc()) {
				System.out.println("valid");
			}else {
				System.out.println("invalid");
			}
			
		}

	}
	private static boolean tictactoc() {
		int xCnt = 0;
		int oCnt = 0;
		int sum = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(arr[i][j]=='X') {
					xCnt++;
				}else if(arr[i][j] == 'O') {
					oCnt++;
				}
			}
		}
		sum = xCnt + oCnt;
		
		if(xCnt<oCnt) {
			return false;
		}
		
		// 게임이 끝난 경우 X부터 시작하므로 X가 5개, O가 4개 여야한다.
		// 이 경우 마지막에 X로 끝나야 성립되므로 X가 이기거나 비겨야한다.
		if(sum==9 && xCnt==oCnt+1) {
			if(isWin('X') && !isWin('O')) {
				return true;
			}else if(!isWin('X') && !isWin('O')) {
				return true;
			}else {
				return false;
			}
		}else {
			// 두개의 개수가 같은 경우 
			if(xCnt == oCnt) {
				if(isWin('O') && !isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			// X가 O보다 1개 더 많은 경우 
			else if(xCnt==oCnt+1) {
				if(!isWin('O') && isWin('X')) {
					return true;
				}else {
					return false;
				}
			}
			
		}
		
		return false;
		
	}
	// 이겼으면 true return 
	private static boolean isWin(char c) {
		// 가로 빙고 
		for(int i=0; i<3; i++) {
			if(arr[i][0] == c && arr[i][1] == c && arr[i][2] == c) {
				return true;
			}
		}
		
		// 세로 빙고 
		for(int i=0; i<3; i++) {
			if(arr[0][i] == c && arr[1][i] == c && arr[2][i] == c) {
				return true;
			}
		}
		
		// 대각선 빙고 
		if(arr[0][0] == c && arr[1][1] == c && arr[2][2] == c) {
			return true;
		}
		if(arr[0][2] == c && arr[1][1] == c && arr[2][0] == c) {
			return true;
		}

		
		return false;
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|11284kb| 84ms|