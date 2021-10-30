package week31.BOJ_17837_G2_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이시간 : 1시간 40분
 */
public class Main {
    // 체스판 크기, 체스말의 갯수
    static int N, K;
    // 체스판
    static Stack<Integer>[][] map;
    // 체스판 색깔
    static int[][] color;

    // 체스말 관리 리스트
    static List<Horse> horseList = new ArrayList<>();
    // delta array
    static int[] dy={0,0,-1,1}, dx={1,-1,0,0};

    // 색깔 타입을 나타내는 enum
    static enum ColorType {
        WHITE, RED, BLUE
    }
    // 방향 타입을 나타내는 enum
    static enum Direction{
        RIGHT, LEFT, UP, DOWN
    }

    // 말의 정보
    static class Horse{
        // 말번호, 좌표값
        int num, y,x;
        // 방향
        Direction dir;
        public Horse(int num, int y, int x, Direction dir){
            this.num = num;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        @Override
        public String toString(){
            return "num="+(num+1) + " y="+(y+1) + " x="+(x+1) +" dir="+dir;
        }
    }
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Stack[N][N];
        color = new int[N][N];

        // 체스판 및 체스판 색깔 설정
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = new Stack<>();
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 말의 정보 입력
        Direction[] directions = Direction.values();
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            // 좌표나 색깔이 1부터 시작하는데 0부터 시작하기 위한 -1
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            horseList.add(new Horse(i,y,x, directions[dir]));

            map[y][x].push(i);
        }

        int time = 0;

        // 최대 1000번
        while(time++ <= 1000){
            // 0 ~ K-1번말 이동시작
            for(Horse horse : horseList){
                boolean isEnd = move(horse);
                if(isEnd){
                    System.out.println(time);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
    static void printHorse(){
        for(Horse horse : horseList){
            System.out.println(horse);
        }
    }


    static boolean move(Horse horse){

        int my = horse.y + dy[horse.dir.ordinal()];
        int mx = horse.x + dx[horse.dir.ordinal()];

        // 번위를 벗어나거나 파란색 좌표일경우
        if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()){

            // 반대방향 좌표 가져오기
            Direction reverseDir = getReverse(horse.dir);
            my = horse.y + dy[reverseDir.ordinal()];
            mx = horse.x + dx[reverseDir.ordinal()];

            // 말의 방향 반대로 변경
            horse.dir = reverseDir;
            // 이동가능 여부확인 -> 변화가 없으므로, 종료 x
            if(my<0 || mx<0 || my>=N || mx>=N || color[my][mx] == ColorType.BLUE.ordinal()) return false;
        }

        // 이동할 말들 가져오기
        Deque<Integer> moveHorse = new ArrayDeque<>();
        Stack<Integer> curPoint = map[horse.y][horse.x];
        while(!curPoint.isEmpty() && curPoint.peek() != horse.num){
            int num = curPoint.pop();
            Horse anotherHorse = horseList.get(num);
            anotherHorse.y = my;
            anotherHorse.x = mx;
            moveHorse.add(num);
        }
        moveHorse.add(curPoint.pop());
        horse.y = my;
        horse.x = mx;

        // 빨간색은 순서 반대로 -> 스택에서 꺼냈으므로, 큐처럼 빼오면 순서반대
        if(color[my][mx] == ColorType.RED.ordinal()){
            while(!moveHorse.isEmpty()){
                map[my][mx].push(moveHorse.poll());
            }
        }
        // 흰색, 파란색은 숫자 그대로 -> 스택에서 꺼냈으므로, 스택처럼 빼오면 순서반대
        else{
            while(!moveHorse.isEmpty()){
                map[my][mx].push(moveHorse.pollLast());
            }
        }

        // 옮길 위치에 말이 4개이상 존재할 경우 종료 플래그 반환
        if(map[my][mx].size()> 3) return true;

        return false;
    }

    // 반대방향을 반환해주는 메서드
    static Direction getReverse(Direction dir){
        if(dir == Direction.LEFT) return Direction.RIGHT;
        else if(dir == Direction.RIGHT) return Direction.LEFT;
        else if(dir == Direction.UP) return Direction.DOWN;

        return Direction.UP;
    }
}
