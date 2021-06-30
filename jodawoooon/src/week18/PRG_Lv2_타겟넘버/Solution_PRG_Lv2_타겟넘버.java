package week18.PRG_Lv2_타겟넘버;

public class Solution_PRG_Lv2_타겟넘버 {
	static int ans = 0;
	
	public static void main(String[] args) {
		int[] numbers = new int[] {1,1,1,1,1};
		int target = 3;
		
		System.out.println(solution(numbers,target));
	}
    public static int solution(int[] numbers, int target) {
        
        dfs(0,0,numbers, target);
        
        return ans;
    }
    
    private static void dfs(int idx, int sum, int[] numbers, int target) {
		if(idx==numbers.length) {
			if(sum==target) {
				ans++;
			}
			return;
		}
		
		dfs(idx+1, sum+numbers[idx], numbers, target);
		dfs(idx+1, sum-numbers[idx], numbers, target);
	}
}
