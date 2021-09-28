## BOJ 10986 G3 나머지 합
- 누적합
- gold3



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/10986

수 N개 A1, A2, ..., AN이 주어진다. 이때, 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오.

즉, Ai + ... + Aj (i ≤ j) 의 합이 M으로 나누어 떨어지는 (i, j) 쌍의 개수를 구해야 한다.



<br>

#### ✔ 입력
첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 106, 2 ≤ M ≤ 103)

둘째 줄에 N개의 수 A1, A2, ..., AN이 주어진다. (0 ≤ Ai ≤ 109)
<br>

#### ✔ 출력
첫째 줄에 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
<br>


<br>

###  💡 풀이

누적합을 이용하여 푸는 문제이다.

Ai + ... + Aj (i ≤ j) 의 합이 M으로 나누어 떨어지는 (i, j) 쌍의 개수를 구해야 한다.
`arr[i]` 에 1부터 i까지의 누적합을 저장하여, Ai +...+Aj의 합은 `arr[j]-arr[i-1]`으로 구할 수 있다.

완전탐색으로 문제를 풀 경우 시간초과가 발생한다.
따라서 수식을 이용해서 풀어야한다.

<br>

`(arr[j]-arr[i-1])%M==0`인 i,j 쌍의 개수를 구한다고 하면
`arr[j]%M == arr[i-1]%M`이다.

즉 누적합을 M으로 나머지 연산한 값이 같은 i,j를 찾으면 된다.
이 문제는 i,j의 쌍의 갯수만 구하면 되므로 `같은 누적합을 가진 것 중 2개를 뽑는 조합의 갯수`를 구하면 답을 구할 수 있다.

<br>


코드 구현은 다음과 같다
```java
		long sum = 0;
		for(int i = 0; i< N ; i++) {
			sum += Integer.parseInt(st.nextToken());
			if(sum%M==0) cnt++; 	// (1,i) 구간
			mod[(int)(sum%M)]++;		// 누적합의 나머지 카운트
		}
```
arr[i]에 (1,i)구간의 누적합을 저장한다.  
그리고 `arr[i]%M==0`일 경우 (1,i)구간에 대해 조건을 만족하므로 답에 포함시킨다.  
그리고 `arr[i]%M` 연산의 나머지 값의 갯수를 `mod` 배열에 체크한다.
<br>

```java
		for(int i = 0; i < M ; i++) {
			cnt += (mod[i]*(mod[i]-1))/2;
		}
```
`mod` 배열을 검사하여 `mod[i]`개 중 2개를 고르는 조합의 수를 구한다.
<br><br>

###  💬 소스코드

```java
package week28.BOJ_10986_G3_나머지합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 10986 나머지 합
 * @Author : Daun JO
 * @Date : 2021. 9. 27. 
 * @Algorithm : Prefix Sum
 *
 */

public class Main_BOJ_10986_G3_나머지합 {
	static int N, M;
	static long mod[];
	static long cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mod = new long[M];
		st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int i = 0; i< N ; i++) {
			sum += Integer.parseInt(st.nextToken());
			if(sum%M==0) cnt++; 	// (1,i) 구간
			mod[(int)(sum%M)]++;		// 누적합의 나머지 카운트
		}
		
		for(int i = 0; i < M ; i++) {
			cnt += (mod[i]*(mod[i]-1))/2; //mod[i]C2
		}
		System.out.println(cnt);
		
	}
}

```
<br><br>


###  💯 채점 결과
160104	440