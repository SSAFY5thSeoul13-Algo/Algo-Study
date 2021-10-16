## BOJ 2630 S3 색종이 만들기
- 분할정복
- silver3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2630

전체 종이의 크기가 N×N(N=2k, k는 1 이상 7 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.

전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다. 나누어진 종이 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다. 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.

입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오.

#### 입력
첫째 줄에는 전체 종이의 한 변의 길이 N이 주어져 있다. N은 2, 4, 8, 16, 32, 64, 128 중 하나이다. 색종이의 각 가로줄의 정사각형칸들의 색이 윗줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1로 주어지며, 각 숫자 사이에는 빈칸이 하나씩 있다.

#### 출력
첫째 줄에는 잘라진 햐얀색 색종이의 개수를 출력하고, 둘째 줄에는 파란색 색종이의 개수를 출력한다.

###  💡 풀이

처음 시작 색좀이를 기준으로 분할정복을 하며 풀었다.

각 색종이의 시작점이 되는 위치의 좌표와 현재 잘라진 색종이의 길이를 파라미터로 받는 `checkMap`메소드를 이룔하여 분할정복을 했다.

길이가 1인 경우는 바로 종이의 색을 확인하고 그렇지 않으면 해당 영역에 시작지점의 종이와 다른색의 종이가 있는 지 확인한다.

다른 색의 종이가 있는 경우 현재 영역을 4등분하는 `checkMap`을 4개 재귀 호출하여 위 과정을 반복하였다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_S3_색종이만들기 {
	static int N, countSkyBlue, countWhite;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		checkMap(0, 0, N);

		System.out.println(countWhite);
		System.out.println(countSkyBlue);

	}

	static void checkMap(int startX, int startY, int length) {
		int startColor = map[startY][startX];
		
		if(length == 1) {
			if(startColor == 1) {
				countSkyBlue++;
			}
			else {
				countWhite++;
			}
			
			return;
		}
		
		boolean isDifferent = false;
		int endY = startY + length -1;
		int endX = startX + length -1;
		
		Loop:for (int i = startY; i <= endY; i++) {
			for (int j = startX; j <= endX; j++) {
				if(map[i][j] != startColor) {
					isDifferent = true;
					break Loop;
				}
			}
		}
		
		
		if(isDifferent) {
			checkMap(startX, startY, length/2);
			checkMap(startX+length/2, startY, length/2);
			checkMap(startX, startY+length/2, length/2);
			checkMap(startX+length/2, startY+length/2, length/2);
		}
		else {
			if(startColor == 1) {
				countSkyBlue++;
			}
			else {
				countWhite++;
			}
		}
	}

}





```


<br>



메모리|시간
--|--
13028 KB|112 ms