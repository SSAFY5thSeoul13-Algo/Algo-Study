package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 에라토스테네스의_체_2960 {
	static int N,K,result;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=2; i<=N; i++) {
			list.add(i);
		}
		
		int cnt = 0;
		boolean isBreak = false;
		while(true) {
			if(isBreak) {
				break;
			}
			// list에서 가장 작은 수 저장 
			int tmp = list.get(0);
			
			for(int i=0; i<list.size(); i++) {
				// tmp의 배수인 경우 remove 
				if(list.get(i)%tmp == 0) {
					cnt++;
					result = list.remove(i);
					
					if(cnt==K) {
						System.out.println(result);
						isBreak = true;
					}
					
				}
			}
			
			
		}


	}

}

