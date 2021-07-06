package week18.Programmers_LV3_입국심사;

public class Programmers_LV3_입국심사 {
	static int n = 100;
	static int[] times = { 1,100,100, 100 };

	public static void main(String[] args) {
		long result = solution();

		System.out.println(result);
	}

	static long solution() {
		long max = 0;
		long min = Long.MAX_VALUE;

		for (int i = 0; i < times.length; i++) {
			if (max < times[i])
				max = times[i];
			if (min > times[i])
				min = times[i];
		}

		//모든 심사관이 최소 시간만큼만 심사하는 경우
		long leftTime = min;
		//모두 제일 오래걸리는 심사관에게만 심사 받는 경우
		long rightTime = n * max;
		long result = 0;

		//이분탐색
		while (leftTime <= rightTime) {
			//최소 시간과 최대 시간의 중간 값
			long midTime = (leftTime + rightTime) / 2;

			//midTime에서 심사를 받을 수 있는 사람의 수
			long num = 0;

			//모든 심사관에 대해서
			for (int i = 0; i < times.length; i++) {
				//각 심사관이 현재 시간에 심사할 수 있는 사람의 수를 num에 더함
				num += midTime / times[i];
				
				//num의 수가 심사를 받으려는 사람의 수와 같거나 커질경우
				if(num >= n) {
					//현재 시간 저장
					result = midTime;
					//최대 시간을 현재 시간으로 변경하고 중단
					rightTime = midTime-1;
					break;
				}
					
			}
			
			//심사를 받을 수 있는 사람 수가 적은 경우 최소 시간을 현재 시간으로 변경
			if (num < n)
				leftTime = midTime+1;
		}

		//저장된 시간 반환
		return result;
	}
}
