package week23.BOJ_5052_G4_전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BOJ_5052_G4_전화번호목록 {
	static int T, N;
	//입력한 전화번호에 대한 모든 prefix를 저장하는 set
	static Set<String> telList;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			telList = new TreeSet<>();
			
			String[] telArr = new String[N];
			
			for (int i = 0; i < N; i++) {
				String tel = br.readLine();
				telArr[i] = tel;
			}
			
			//번호를 정렬
			Arrays.sort(telArr);
			
			boolean isPrefix = false;
			
			for (int i = telArr.length-1; i >= 0; i--) {
				//prefix가 존재하는 경우
				if(telList.contains(telArr[i])){
					isPrefix = true;
					break;
				}
				
				//현재 번호에서 해당하는 모든 prefix를 저장
				for (int j = 0; j < telArr[i].length(); j++) {
					telList.add(telArr[i].substring(0,j));
				}
			}
			
			if(isPrefix) {
				sb.append("NO").append("\n");
			}
			else {
				sb.append("YES").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
