## BOJ 2110 S1 공유기 설치
- 이분탐색
- silver1



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2110


도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.

도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.

C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.

<br>

#### ✔ 입력
첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다. 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
<br>

#### ✔ 출력
첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.
<br>


<br><br>

###  💡 풀이

N개의 집이 수직선 위에 있을 때,  
C대의 공유기를 최대한 적절하게 골고루 설치해야 한다.

공유기 사이의 거리를 구하기 위해 이진탐색을 사용했다.
최소값(`left`)는 1이고 최댓값(`right`)는 가장 큰 좌표 값 - 가장 작은 좌표 값으로 max값, min값을 찾아서 구할 수 있다.

- `right` = map[N-1] - map[0]
- `left` = 1
- `mid`
 
처음집부터 마지막 집까지 mid를 검사한다.
- 현재 집과 공유기 설치집의 간격이 mid보다 크거나 같으면 공유기 설치한다. 
	➡ `cnt++`
- 공유기를 C이상 설치했다면 공유기 개수를 좀 줄여본다 ➡ 간격을 줄여본다 
    ➡ `mid-1`
- 공유기가 C 미만 설치되었다면 공유기를 더 설치 해야 한다. ➡ 간격을 늘려본다 
    ➡ `mid+1`

<br><br>

###  💡 소스코드


```java
package week22.BOJ_2110_S1_공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2110_S1_공유기설치 {
	
	static int N, C;
	static int[] map;
	static int left, right, mid, ans; //인접한 두 공유기 사이의 최대 거리
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N]; //집의 좌표

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);
		
		
		left = 1;
		right = map[N-1]-map[0];
		
		
		while(left<=right) {
			mid = (left+right)/2; //공유기 사이 간격

			// C개 이상 설치 가능하면 간격 늘린다, 아니면 줄인다
			if(canInstall(mid)) { //C개 이상 설치 가능하면
				ans = Math.max(ans, mid);
				left = mid + 1;
			}else right = mid - 1;
		}
		
		System.out.println(ans);
		
	}
	
	private static boolean canInstall(int distance) {
		
		int cur = map[0];
		int cnt = 1; //0번집 설치
		
		for (int i = 1; i < N; i++) {
			if(map[i] - cur >= distance) {
				cnt++; //간격이 mid보다 크거나 같으면 공유기 설치
				cur = map[i];
			}
		}

		
		if(cnt>=C) return true; //C개 이상 설치 가능하면
		return false;

	}

}

```

<br><br>

### 🚩 결과
메모리	21140
시간 256