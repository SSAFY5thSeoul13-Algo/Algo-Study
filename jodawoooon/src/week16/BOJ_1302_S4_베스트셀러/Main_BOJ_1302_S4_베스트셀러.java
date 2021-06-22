package week16.BOJ_1302_S4_베스트셀러;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BOJ_1302_S4_베스트셀러 {
	
	//오늘 하루 동안 팔린 책의 제목이 입력으로 들어왔을 때, 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.
	
	static int N, max;
	static String ans;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine()); //팔린 책의 수
		
		for (int i = 0; i < N; i++) {
			String title = br.readLine();
			
			if(map.get(title)==null) { //1개팔린책
				map.put(title, 1);
			}else {
				map.put(title, map.get(title)+1); //팔린개수만큼
			}
		}
		
		for (String key : map.keySet()) { //팔린 책의 개수 검사
				
			if(map.get(key)>max) { //더 많이 팔렸으면
				max = map.get(key);  //max값 갱신
				ans = key; //ans 갱신
				
			}else if(map.get(key)==max) { //가장 많이 팔린 책이 여러개면
				if(key.compareTo(ans)<0) { //사전순으로 더 앞서면
					ans = key; //ans 갱신
				}
			}
		}
		
		System.out.println(ans);
		
		
	}
}
