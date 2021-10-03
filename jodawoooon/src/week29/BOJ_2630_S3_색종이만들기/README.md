## BOJ 2630 S3 색종이만들기

- silver3
- 분할정복



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2630

아래 <그림 1>과 같이 여러개의 정사각형칸들로 이루어진 정사각형 모양의 종이가 주어져 있고, 각 정사각형들은 하얀색으로 칠해져 있거나 파란색으로 칠해져 있다. 주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의 하얀색 또는 파란색 색종이를 만들려고 한다.

![img](https://www.acmicpc.net/upload/images/bwxBxc7ghGOedQfiT3p94KYj1y9aLR.png)

전체 종이의 크기가 N×N(N=2k, k는 1 이상 7 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.

전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 <그림 2>의 I, II, III, IV와 같이 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다. 나누어진 종이 I, II, III, IV 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다. 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.

위와 같은 규칙에 따라 잘랐을 때 <그림 3>은 <그림 1>의 종이를 처음 나눈 후의 상태를, <그림 4>는 두 번째 나눈 후의 상태를, <그림 5>는 최종적으로 만들어진 다양한 크기의 9장의 하얀색 색종이와 7장의 파란색 색종이를 보여주고 있다.

![img](https://www.acmicpc.net/upload/images/VHJpKWQDv.png)

입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오.

<br>

#### ✔ 입력
첫째 줄에는 전체 종이의 한 변의 길이 N이 주어져 있다. N은 2, 4, 8, 16, 32, 64, 128 중 하나이다. 색종이의 각 가로줄의 정사각형칸들의 색이 윗줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1로 주어지며, 각 숫자 사이에는 빈칸이 하나씩 있다.
<br>

#### ✔ 출력
첫째 줄에는 잘라진 햐얀색 색종이의 개수를 출력하고, 둘째 줄에는 파란색 색종이의 개수를 출력한다.
<br>


<br>

###  💡 풀이



재귀함수를 이용하여 풀었다.

문제의 조건에 맞게 재귀함수를 구현했다.



기저조건은 두가지이다.

1. 잘라진 종이가 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때

   색종이의 한 변의 길이인 `size`가 1 일 때.

  ```java
   		if(size==1) { 
               //기저조건 1. 하나의 정사각형 칸이 되어 더 이상 자를 수 없다
   			if(map[r][c]==0) white++;
   			else blue++;
   			return;
   		}
   ```

   

2. 해당 색종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있을 때

   `isSame`이 true 일 때.

  ```java
   		if(isSame(r,c, map[r][c],size)){ 
               //기저조건 2. 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져있다.
   			if(map[r][c]==0) white++;
   			else blue++;
               return;
   		}
   ```

   



그리고 위 두 가지 조건을 만족하지 않을 경우 계속해서 네 개의 색종이로 나눌 수 있다.

```java
		search(r,c,size/2);
		search(r+size/2,c,size/2);
		search(r,c+size/2,size/2);
		search(r+size/2,c+size/2,size/2);
```



<br><br>

###  💬 소스코드

```java

package week29.BOJ_2630_S3_색종이만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2630_S3_색종이만들기 {
	static int N, map[][], white, blue;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		map = new int[N][N];
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j< N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void search(int r, int c, int size) {
		
		if(size==1) { 
            //기저조건 1. 하나의 정사각형 칸이 되어 더 이상 자를 수 없다
			if(map[r][c]==0) white++;
			else blue++;
			return;
		}
		
		if(isSame(r,c, map[r][c],size)){ 
            //기저조건 2. 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져있다.
			if(map[r][c]==0) white++;
			else blue++;
            return;
		}
        
        //같은 색이 아니라면 더 자른다
		search(r,c,size/2);
		search(r+size/2,c,size/2);
		search(r,c+size/2,size/2);
		search(r+size/2,c+size/2,size/2);

		
		
	}

	private static boolean isSame(int r, int c, int color, int size) {
        //색종이가 모두 같은 색으로 칠해져 있는지 체크
		for(int i = r ; i < r + size ; i++) {
			for(int j = c ; j < c + size ; j++) {
				if(color!=map[i][j]) return false;
			}
		}
		return true;
	}
}

```
<br><br>


###  💯 채점 결과

메모리 13128KB

시간 108ms
