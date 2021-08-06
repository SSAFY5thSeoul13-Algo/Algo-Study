package week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_5052_G4_전화번호_목록 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			List<String> list = new ArrayList<String>();
			
			for(int i=0; i<n; i++) {
				String number = br.readLine();
				list.add(number);				
			}
			
			Collections.sort(list);
			
			boolean isCheck = true;
			for(int i=0; i<n-1; i++) {
				if(list.get(i+1).startsWith(list.get(i))) {
					isCheck = false;
				}
			}
			
			if(isCheck) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}

	}

}
