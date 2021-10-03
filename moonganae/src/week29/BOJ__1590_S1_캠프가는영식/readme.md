## BAEKJOON SILVER1 1590 캠프가는 영식 
- 수학
- Silver1
- https://www.acmicpc.net/problem/1590
<br>

### 문제설명
> 영식이는 민식이와 함게 고속버스를 타고 캠프를 가야 하지만, 민식이는 영식이를 깨우지 않고 혼자 버스를 타고 캠프에 가버렸다.
영식이는 혼자 고속버스터미널까지 가서 캠프에 오려고 한다. 터미널에는 캠프 장소까지 운행하는 N가지의 버스가 있다. 각각의 버스는 시작 시각, 간격, 대수의 정보를 가지고 있다. 예를 들어, 어떤 버스의 시작 시각이 특점 시점을 기준으로 10분 후이고, 간격은 10분이고, 대수가 5대이면, 이 버스는 10분, 20분, 30분, 40분, 50분에 한 대씩 출발한다.
영식이는 버스터미널에 T분에 도착했다. 영식이가 버스를 타려면 최소 몇 분을 더 기다려야 하는지 구하는 프로그램을 작성하시오.



### 입력
- 첫째 줄에 버스의 개수 N과 영식이가 버스터미널에 도착하는 시간 T가 주어진다.
- 둘째 줄부터 총 N개의 줄에 각 버스의 시작 시각, 간격, 대수가 공백을 사이에 두고 주어진다.
- 버스의 개수와 각 버스의 대수는 100,000보다 작거나 같은 자연수이며, T와 각 버스의 시작 시각과 간격은 1,000,000,000보다 작거나 같은 자연수이다.

### 출력
- 첫째 줄에 영식이가 기다려야 하는 시간을 출력한다.
- 영식이가 도착하는 동시에 버스가 출발하면 정답은 0이다.
- 만약 버스가 없어서 캠프에 갈 수 없으면 -1을 출력한다. 
- 정답은 2^31보다 작다.

### 입출력 예

#### 예제 1
입력
```
1 285
150 50 10
```
출력
```
15
```

### 풀이 및 과정
필요한 최소시간을 수학적으로 계산하여 문제를 해결하였습니다.

주어진 예시로 설명하면 다음과 같습니다. 

T : 영식이 도착시간
stime : 버스 시작시간
term : 버스 간격
cnt : 버스 총 대수

1. T가 stime 보다 빠르면 버스 시작시간이 영식이가 타야할 버스시간입니다.<br>
주어진 예제에서는 stime(150) < T(285) 이므로, 해당사항 없습니다.

2. 아니라면, 먼저 T와 stime의 차이(diff) 를 구합니다.<br>
diff = T-stime = 285 - 150 = 135

3. 그런 다음, diff보다 큰 값을 가질 수 있는 버스의 수(bussCnt)를 구합니다.<br>
bussCnt = diff/term + (diff%term != 0) ? 1 : 0 

4. 버스 총 대수보다 작다면 => 영식이가 버스를 탈수있다면 해당 버스시간으로 차이를 계산합니다.


### 소스코드 1
```java
package week29.BOJ__1590_S1_캠프가는영식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, T, ans=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int stime = Integer.parseInt(st.nextToken());
			int term = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			
			int result = getMinTime(stime, term, cnt);
			
			if(result != -1) ans = Math.min(ans, result);
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}

	static int getMinTime(int stime, int term, int cnt) {
		
		/* 시작시간전에 이미 영식이가 도착했다면 해당 노선의 첫차시간 */
		if(T <= stime) return stime - T;
		
		
		/* 영식이 도착시간 이후에 제일 작은 시간구하기 */
		int diff = T - stime;
		
		
		// 필요한 해당 노선의 버스대수
		int bussCnt = diff/term;
		bussCnt += (diff%term != 0) ? 1 : 0; 
		
		/* 막힌점1 : 첫시간도 해당노선의 버스 1대가 사용된것이므로, 남은 대수를 계산할때는 1대를 빼주어야함
		 * 따라서, bussCnt <= cnt로 하면 틀린다! 
		 * */
		if(bussCnt < cnt) {
			return bussCnt*term - diff;
		}
		
		
		return -1;
	}
}
```

### 결과 1
```
메모리 : 11588 KB	
시간 : 84	ms
```
