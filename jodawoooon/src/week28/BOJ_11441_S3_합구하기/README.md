## BOJ 11441 S3 합 구하기
- 구간합
- silver3



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/11441

N개의 수 A1, A2, ..., AN이 입력으로 주어진다. 총 M개의 구간 i, j가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.


<br>

#### ✔ 입력
첫째 줄에 수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 둘째 줄에는 A1, A2, ..., AN이 주어진다. (-1,000 ≤ Ai ≤ 1,000) 셋째 줄에는 구간의 개수 M이 주어진다. (1 ≤ M ≤ 100,000) 넷째 줄부터 M개의 줄에는 각 구간을 나타내는 i와 j가 주어진다. (1 ≤ i ≤ j ≤ N)
<br>

#### ✔ 출력
총 M개의 줄에 걸쳐 입력으로 주어진 구간의 합을 출력한다.
<br>


<br>

###  💡 풀이

`arr` 배열에 i 인덱스에 1부터 i까지의 누적합을 저장하여
x,y의 구간합을 구할 때에는 `arr[y]-arr[x-1]`을 빼서 구하였다.


<br><br>

###  💬 소스코드

```java
package week28.BOJ_11441_S3_합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 11411 합구하기
 * @Author : Daun JO
 * @Date : 2021. 9. 24. 
 *
 */
public class Main_BOJ_11441_S3_합구하기 {
	
	static int N, M, arr[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];	//누적합

		st = new StringTokenizer(br.readLine());
		
		int sum = 0 ;
		for(int i = 1; i<=N ; i++) {
			sum += Integer.parseInt(st.nextToken());
			arr[i] = sum;
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			
			System.out.println(arr[y]-arr[x-1]); //x~y 구간합
		}
	}

}


```
<br><br>


###  💯 채점 결과
	68160	2060