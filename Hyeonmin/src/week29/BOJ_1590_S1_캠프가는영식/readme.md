## BOJ 1590 S1 캠프가는 영식
- 수학
- silver1

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1590

영식이는 민식이와 함게 고속버스를 타고 캠프를 가야 하지만, 민식이는 영식이를 깨우지 않고 혼자 버스를 타고 캠프에 가버렸다.

영식이는 혼자 고속버스터미널까지 가서 캠프에 오려고 한다. 터미널에는 캠프 장소까지 운행하는 N가지의 버스가 있다. 각각의 버스는 시작 시각, 간격, 대수의 정보를 가지고 있다. 예를 들어, 어떤 버스의 시작 시각이 특점 시점을 기준으로 10분 후이고, 간격은 10분이고, 대수가 5대이면, 이 버스는 10분, 20분, 30분, 40분, 50분에 한 대씩 출발한다.

영식이는 버스터미널에 T분에 도착했다. 영식이가 버스를 타려면 최소 몇 분을 더 기다려야 하는지 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 버스의 개수 N과 영식이가 버스터미널에 도착하는 시간 T가 주어진다. 둘째 줄부터 총 N개의 줄에 각 버스의 시작 시각, 간격, 대수가 공백을 사이에 두고 주어진다. 버스의 개수와 각 버스의 대수는 100,000보다 작거나 같은 자연수이며, T와 각 버스의 시작 시각과 간격은 1,000,000,000보다 작거나 같은 자연수이다.

#### 출력
첫째 줄에 영식이가 기다려야 하는 시간을 출력한다. 영식이가 도착하는 동시에 버스가 출발하면 정답은 0이다. 만약 버스가 없어서 캠프에 갈 수 없으면 -1을 출력한다. 정답은 231보다 작다.

###  💡 풀이

각 버스별로 영식이가 버스를 탈 때까지 기다리는 시간의 최소값들을 구하고 그 최소값들 중에서 가장 작은 값을 `min`으로 한다

버스를 타지 못하는 경우는 마지막 차와 같은 시간에 도착하는 경우 1가지 이다

버스를 탈 수 있는 경우는 
1.첫차가 오기 전에 도착한 경우
2.버스 도착 시간과 동시에 도착한 경우
3.두 버스 출발 시간 사이에 도착한 경우이다

버스를 탈 수 있는 경우가 생기면 `isPossible`값을 true로 바꿔준다

모든 버스에 대해서 최대 값을 구했으면 `isPossible`이 false이면 `-1`, true이면 `min` 값을 출력한다





<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1590_S1_캠프가는영식 {
	static int N, T, min = 1000000001;
	static boolean isPossible = false;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int interval = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			int end = start + interval*(count-1);
			
			//마지막 차를 못타는 경우
			if(end < T) {
				continue;
			}
			
			//마지막 차와 같은 시간에 도착
			if(end == T) {
				isPossible = true;
				min = 0;
				break;
			}
			
			//기다리기 시작하는 시간
			int startTime = T - start;
			
			//아직 첫차가 오기 전
			if(startTime <= 0) {
				isPossible = true;
				min = Math.min(min, startTime*-1);
				continue;
			}
			
			//가장 최근 떠난 버스로부터 지난 시간
			int waitTime = startTime%interval;
			
			if(waitTime == 0) {
				isPossible = true;
				min = 0;
				break;
			}
			else {
				isPossible = true;
				min = Math.min(min, interval - waitTime);
			}
		}
		
		System.out.println(isPossible ? min : -1);
	}

}





```


<br>



메모리|시간
--|--
11588 KB|80 ms