package week19.Programmers_LV3_순위;

public class Programmers_LV3_순위 {
	static boolean[][] matchResult;
	static boolean[] checked;
	static int count;
	
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {
				{1, 4},
				{4, 2},
				{2, 5},
				{5, 3}
		};
		
		int result = solution(n, results);
		
		System.out.println(result);
	}
	
	static int solution(int n, int[][] results) {
		matchResult = new boolean[n+1][n+1];
		
		for (int i = 0; i < results.length; i++) {
			int win = results[i][0];
			int lose = results[i][1];
			
			matchResult[win][lose] = true;
		}
		
		//각 선수들에 대해서 순위를 매길 수 있는지 여부 확인
		for (int i = 1; i <= n; i++) {
			checked = new boolean[n+1];
			int num = checkRank(i);

			//순위를 매길 수 있는 경우
			if(num == n-1) {
				count++;
			}
		}
		
		return count;
		
	}
	
	static int checkRank(int idx) {
		//이 선수가 이길 수 있는 선수 수
		int win = 0;
		//이 선수가 이길 수 없는 선수 수
		int lose = 0;
		checked[idx] = true;
		
		for (int i = 1; i < matchResult.length; i++) {
			//자신을 제외
			if(!checked[i] && i != idx ) {
				//자신이 이기는 경우
				if(matchResult[idx][i]) {
					win += calcWinCount(i, 0);
				}
				//자신이 지는 경우
				else if(matchResult[i][idx]) {
					lose += calcLoseCount(i, 0);
				}
			}
		}
		
		return win+lose;
	}
	
	static int calcWinCount(int idx, int num) {
		num++;
		checked[idx] = true;
		
		//아직 확인하지 않은 선수들 중에서 이길 수 있는 선수가 있는 경우 를 확인
		for (int i = 1; i < matchResult.length; i++) {
			if(!checked[i] && i != idx && matchResult[idx][i]) {
				num += calcWinCount(i, 0);
			}
		}
		
		return num;
	}
	
	static int calcLoseCount(int idx, int num) {
		num++;
		checked[idx] = true;
		
		//아직 확인하지 않은 선수들 중에서 자신을 이기는 선수가 있는 지 확인
		for (int i = 1; i < matchResult.length; i++) {
			if(!checked[i] && i != idx && matchResult[i][idx]) {
				num += calcLoseCount(i, 0);
			}
		}
		
		return num;
	}

}
