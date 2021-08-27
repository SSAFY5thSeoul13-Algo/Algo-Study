## BOJ 2428 G4 내려가기
- dp
- gold4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2096

N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 이 제약 조건을 그림으로 나타내어 보면 다음과 같다.

![](https://images.velog.io/images/jodawooooon/post/ca5d3387-cc1d-4a49-8ce6-679a4585f09a/image.png)

별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.

<br>

#### ✔ 입력
첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
<br>

#### ✔ 출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
<br>


<br>

###  💡 풀이

`얻을 수 있는 최대 점수와 최소 점수`를 구하는 문제이다.<br> 
점수는 원룡이가 위치한 곳의 수의 합이다.

게임에서 숫자를 고른 후 다음 행으로 내려가는데,  
내려갈 때 0,1,2열에 따라 내려갈 수 있는 곳이 다르다.  
바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다  

<br>
dp 점화식은 다음과 같다.  

1. 0열
	다음 행의 0,1열로 이동 가능하다
2. 1열
	다음 행의 0,1,2열로 이동 가능하다
3. 2열
	다음 행의 1,2열로 이동 가능하다
<br>


<br><br>

###  💡 소스코드


```java
package week24.BOJ_2096_G4_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2096 내려가기
 * @Author : Daun JO
 * @Date : 2021. 8. 27. 
 * @Algorithm : DP
 *
 */
public class Main_BOJ_2096_G4_내려가기 {
	
	static int N, arr[][], max[][], min[][], maxAns, minAns;
	public static void main(String[] args)  throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		

		arr = new int[N][3];
		max = new int[N][3];
		min = new int[N][3];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=0; i<3;i++) {
			max[0][i] = arr[0][i];
			min[0][i] = arr[0][i];
		}
		
		for(int i=1;i<N;i++) {
			
			
			max[i][0] = arr[i][0] + Math.max(max[i-1][0], max[i-1][1]); //0열
			max[i][1] = arr[i][1] + Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]); //1열
			max[i][2] = arr[i][2] + Math.max(max[i-1][1], max[i-1][2]); //2열
			
			min[i][0] = arr[i][0] + Math.min(min[i-1][0], min[i-1][1]); //0열
			min[i][1] = arr[i][1] + Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]); //1열
			min[i][2] = arr[i][2] + Math.min(min[i-1][1], min[i-1][2]); //2열
		}
		
		maxAns = Math.max(Math.max(max[N-1][0], max[N-1][1]), max[N-1][2]);
		minAns = Math.min(Math.min(min[N-1][0], min[N-1][1]), min[N-1][2]);
		
		System.out.println(maxAns+" "+minAns);
	}
}

```

<br><br>


###  💯 채점 결과
메모리 51304	시간 360
	