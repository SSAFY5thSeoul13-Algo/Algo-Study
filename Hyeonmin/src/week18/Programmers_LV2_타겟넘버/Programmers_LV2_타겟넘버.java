package week18.Programmers_LV2_타겟넘버;

public class Programmers_LV2_타겟넘버 {
	static int[] numbers = {1,1,1,1,1};
	static int target = 3, count, result;
	
	public static void main(String[] args) {
		
		//주어진 숫자의 개수
		count = numbers.length;
		
		//dfs 실행
		dfs(0, 0);
		
		System.out.println(result);
		
	}
	
	static void dfs(int depth, int num) {
		//모든 숫자를  다 고려한 경우
		if(depth == count) {
			//연산의 결과가 target과 같으면 result 1 증가
			if(num ==  target)
				result++;
			
			//종료
			return;
		}
		
		//다음 숫자를 더하는 경우
		dfs(depth+1, num+numbers[depth]);
		//다음 숫자를 빼는 경우
		dfs(depth+1, num-numbers[depth]);
	}
	
}
