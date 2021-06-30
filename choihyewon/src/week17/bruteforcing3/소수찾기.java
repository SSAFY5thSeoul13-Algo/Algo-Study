package week17.bruteforcing3;

import java.util.HashSet;

public class 소수찾기 {
	static String numbers = "011";
	static char[] arr;
	static boolean[] select;
	static char[] tgt;
	static HashSet<Integer> set;
	public static void main(String[] args) {
		int answer = solution(numbers);
		
		System.out.println(answer);
	}
	public static int solution(String numbers) {
        int answer = 0;
        
        arr = new char[numbers.length()];
        select = new boolean[numbers.length()];
        set = new HashSet<Integer>();
        
        for(int i=0; i<numbers.length(); i++) {
        	arr[i] = numbers.charAt(i);
        }
        
        for(int i=1; i<=numbers.length(); i++) {
        	tgt = new char[i];
        	perm(0);
        }
        
        answer = set.size();
        
        return answer;
    }
	
	private static void perm(int tgtIdx) {
		if(tgtIdx==tgt.length) {
			// 맨 앞이 0이 되면 안된다.
			if(tgt[0]=='0')	return;
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<tgt.length; i++) {
				sb.append(tgt[i]);

			}
			int num = Integer.parseInt(sb.toString());
			// 조합이 소수가 맞는지 판별하여 맞다면 list에 넣는다.
			if(isPrime(num)) {
				set.add(num);
			}
			
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(select[i])	continue;
			
			tgt[tgtIdx] = arr[i];
			select[i] = true;
			perm(tgtIdx+1);
			select[i] = false;
		}
		
		
		
	}

	// 에라토스테네스의 체 알고리즘 이용 
	private static boolean isPrime(int num) {
		// 1은 소수가 아니므로 false 
		if(num==1)	return false;
		
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i==0) 	return false;
		}

		return true;
	}

}
