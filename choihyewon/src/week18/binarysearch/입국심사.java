package week18.binarysearch;

import java.util.Arrays;

public class 입국심사 {
	public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long start = 1;	// 심사를 보는데 걸리는 최소 시간 
        long end = n * (long) times[times.length-1]; // 심사를 보는데 걸리는 최대 시간 
         
        
        while(start<=end) {
        	long mid = (start+end)/2;	
        	long cnt = 0;		// 심사관 별로 맡을 수 있는 입국자 수 
        	for(int i=0; i<times.length; i++) {
        		// 입국자 수 = 추정 최솟값 / 각 심사관이 한 명을 심사하는데 걸리는 시간 
        		cnt += mid/times[i];
        	}
        	
        	// n보다 작으므로 시간을 늘려야 한다.
        	if(cnt<n) {
        		start = mid + 1;
        	}
        	// n보다 크므로 시간을 줄일 수 있다. 
        	else {
        		end = mid - 1;
        		answer = mid;
        	}
        	
        }
        
        return answer;
    }

}
