package week18.dfs_bfs1;

public class 타겟_넘버 {
	static int answer = 0;
	
	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target= 3;
		dfs(numbers, target, 0, 0);
        System.out.println(answer);
	}

	private static void dfs(int[] numbers, int target, int idx, int sum) {
		// numbers의 값을 모두 계산했으면 
		if(idx==numbers.length) {
			if(sum==target) {
				answer++;
			}
			
		}else{
			dfs(numbers, target, idx+1, sum+numbers[idx]);
			dfs(numbers, target, idx+1, sum-numbers[idx]);
		}
		

		
	}

}
