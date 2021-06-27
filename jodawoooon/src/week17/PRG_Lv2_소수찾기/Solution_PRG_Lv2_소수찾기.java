package week17.PRG_Lv2_소수찾기;

import java.util.HashSet;

public class Solution_PRG_Lv2_소수찾기 {
	static int count;
	static HashSet<Integer> primeSet = new HashSet<>();
	public static void main(String[] args) {
		String numbers = "011";
		//종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성
		System.out.println(solution(numbers));
	}
	
	public static int solution(String numbers) {
       char[] numArr = numbers.toCharArray();
       char[] res = new char[numbers.length()];
       boolean[] visited = new boolean[numbers.length()];
       
       perm(0, numArr, res, visited);
       
       return count;
    }

	private static void perm(int cnt, char[] numArr, char[] res, boolean[] visited) {
		if(cnt>0) {
			if(res[0]=='0') return;//맨 앞에 0 
			
			//소수 이어 붙이기
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < cnt; i++) {
				sb.append(res[i]);
			}

			//만들어진 소수 makeNum
			int makeNum = Integer.parseInt(sb.toString()); 
			
			//hashSet을 이용해 중복검사 후 소수이면 count++
			if(!primeSet.contains(makeNum)&&isPrime(makeNum)){
				primeSet.add(makeNum);
				count++;
			}
		}
		
		//perm
		for (int i = 0; i < numArr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				res[cnt] = numArr[i];
				perm(cnt+1,numArr,res,visited);
				visited[i] = false;
			}
		}
	}

	private static boolean isPrime(int num) {
		if(num<2) return false;
		if(num==2) return true;
		if(num%2==0) return false;
		
		//소수찾기
		for (int i = 3; i <= (int)Math.sqrt(num); i+=2) {
			if(num%i==0) return false;
		}
		
		return true;
	}



}
