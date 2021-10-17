## BOJ 13558 G2 등차수열의 개수
- 수학
- 조합론
- gold2

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/13558

길이가 N인 수열 A1, A2, ..., AN이 주어진다. 이때, 1 ≤ i < j < k ≤ N 이면서, Aj - Ai = Ak - Aj를 만족하는 (i, j, k) 쌍의 개수를 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 수열의 크기 N (3 ≤ N ≤ 100,000)가 주어진다.

둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 30,000)

#### 출력
1 ≤ i < j < k ≤ N 이면서, Aj - Ai = Ak - Aj를 만족하는 (i, j, k) 쌍의 개수를 출력한다.

###  💡 풀이

3개의 숫자중 중산 숫자를 중심으로 좌측에 있는 각 숫자의 카운트를 저장하는 배열 `leftNumbers`, 우측에 있는 숫자의 카운트를 저장하는 배열 `rightNumbers`를 사용하여 풀었다.

중간 숫자를 중심으로 좌,우로 차이가 같은 두 숫자의 갯수를 곱한 값을 `count`에 더해주도 모든 수열에 대해서 같은 계산을 반복한다

```java
		for (int i = 2; i <= N-1; i++) {
			leftNumbers[arr[i-1]]++;
			rightNumbers[arr[i]]--;
			
			count += leftNumbers[arr[i]]*rightNumbers[arr[i]];
			
			for (int j = 1; (j <= arr[i]-1) && (arr[i] + j <= max); j++) {
				//중간 숫자를 기준으로 좌,우 차이가 같은 수 카운트의 곱만큼 더함
				count += leftNumbers[arr[i] - j]*rightNumbers[arr[i] + j];
				count += leftNumbers[arr[i] + j]*rightNumbers[arr[i] - j];
			}
		}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13558_G2_등차수열의개수 {
	static int N, max;
	static long count;
	static int[] arr;
	static long[] rightNumbers, leftNumbers;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		//기준 숫자 우측에 있는 수 카운트
		rightNumbers = new long[30001];
		//기준 숫자 좌측에 있는 수 카운트
		leftNumbers = new long[30001];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
			rightNumbers[arr[i]]++;
		}
		
		rightNumbers[arr[1]]--;
		
		//중간 숫자 수만큼 반복
		for (int i = 2; i <= N-1; i++) {
			leftNumbers[arr[i-1]]++;
			rightNumbers[arr[i]]--;
			
			count += leftNumbers[arr[i]]*rightNumbers[arr[i]];
			
			for (int j = 1; (j <= arr[i]-1) && (arr[i] + j <= max); j++) {
				//중간 숫자를 기준으로 좌,우 차이가 같은 수 카운트의 곱만큼 더함
				count += leftNumbers[arr[i] - j]*rightNumbers[arr[i] + j];
				count += leftNumbers[arr[i] + j]*rightNumbers[arr[i] - j];
			}
		}
		
		System.out.println(count);
	}
}





```


<br>



메모리|시간
--|--
26204 KB|1672 ms