package week15.BOJ_2869_B1_달팽이는올라가고싶다;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2869_B1_달팽이는올라가고싶다 {
	static int A, B, V;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		//하루가 끝나면 올라가는 높이
		int oneDay = A - B;
		
		//V-A지점까지 올라가는데 걸리는 날
		int totalDay =  (V-A) / oneDay;
		//total Day만큼 날짜가 지났을 때 V-A 보다 아래이면 하루가 더 지난 다음에 V-A에 도달
		int temp = (V-A) % oneDay;
		if(temp != 0) {
			totalDay++;
		}
		
		//위에서 구한 날의 밤낮이 지난 후의 높이
		int height = totalDay + oneDay;
		
		//현재 높이가 정상보다 낮으면 그 차이가 A보다 크지 않기 때문에 하루가 지난 경우가 정상에 도착하는 날
		if(height < V)
			totalDay++;
		
		System.out.println(totalDay);

	}
}
