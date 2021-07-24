package week21.Programmers_LV4_도둑질;

public class Programmers_LV4_도둑질 {

	public static void main(String[] args) {
		int[] money = {1, 2, 3, 1, 4};
		System.out.println(solution(money));
	}
	
	static int solution(int[] money) {
		int length = money.length;
		//[][0]: 0번 집을 터는 경우, [][1]: 0번 집을 안터는 경우 
		int[][] dp = new int[length][2];
		
		//0번집을 터는 경우의 초기 값
		dp[0][0] = money[0];
		dp[1][0] = money[0];
		dp[2][0] = money[0] + money[2]; 
		
		//0번 집을 안터는 경우의 초기 값
		dp[1][1] = money[1];
		dp[2][1] = Math.max(money[1], money[2]);
		
		//i번째 집을 터는 경우와 안터는 경우 중 훔치는 돈이 더 큰 경우를 dp[i][]에 저장
		for (int i = 3; i < length; i++) {
			//0번집을 안터는 경우
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-3][0])+money[i]);
			//0번집을 터는 경우
			dp[i][1] = Math.max(dp[i-1][1], Math.max(dp[i-2][1], dp[i-3][1])+money[i]);
		}
		
		//0번집을 터는 경우는 마지막집은 무조건 털지 못함
		dp[length-1][0] = dp[length-2][0];
		
		int result = Math.max(dp[length-1][0], dp[length-1][1]);
		
		return result;
	}

}
