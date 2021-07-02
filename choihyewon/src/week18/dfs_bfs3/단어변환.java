package week18.dfs_bfs3;

public class 단어변환 {
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        
        dfs(0,begin,target,words);
        
        // 만약 target을 찾지 못했다면 answer에 0 저장 
        if(answer==Integer.MAX_VALUE) {
        	answer = 0;
        }
        
        return answer;
    }
	
	private void dfs(int cnt, String current, String target, String[] words) {
		// target과 똑같아지면 최소 count 수를 구하고 return
		if(current.equals(target)) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for(int i=0; i<words.length; i++) {
			if(!visited[i]&&check(current,words[i])) {
				visited[i] = true;
				dfs(cnt+1,words[i],target,words);
				visited[i] = false;
			}
		}
		
	}
	
	private boolean check(String word1, String word2) {

		int diff = 0;
		for(int i=0; i<word1.length(); i++) {
			if(word1.charAt(i)!=word2.charAt(i)) {
				diff++;
			}
			// 비교하는 두 단어가 2개 이상 다를 경우 false return 
			if(diff>1) {
				return false;
			}
		}
		return true;
	}
	
	

}
