package week33.BOJ_18428_S1_감시피하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[][] map;
    static final int MAX_SELECT = 3;
    static boolean isSuccess = false;

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static List<Point> students, teachers;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        students = new ArrayList<>();
        teachers = new ArrayList<>();

        map = new char[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                char data = st.nextToken().charAt(0);
                map[i][j] = data;
                if(data == 'S') students.add(new Point(i,j));
                else if(data == 'T') teachers.add(new Point(i,j));
            }
        }

        dfs(0,0,0);
        if(isSuccess) System.out.println("YES");
        else System.out.println("NO");
    }

    static void solve(){


    }
    static void dfs(int y, int x, int cnt){

        if(isSuccess) return;
        if(cnt == MAX_SELECT){
            if(check()){
                isSuccess = true;
            }
            return;
        }

        for(int i=y; i<N; i++){
            for(int j=x; j<N; j++){
                if(!isInstall(i,j)) continue;


                // i,j를 놓았을때
                map[i][j] = 'O';
                dfs(i,j+1, cnt+1);
                map[i][j] = 'X';
            }
            // y 번째 줄 이후로는 j는 0~N까지임
            x = 0;
        }
    }

    // 감시를 피할 수 있는지 확인
    static boolean check(){
        for(Point student : students){
            for(Point teacher : teachers){
                // 선생님이 학생을 찾을 수 있다면 실패
                if(isFind(student, teacher)) return false;
            }
        }

        // 모든 학생이 감시를 피하면 성공
        return true;
    }

    // 선생님이 학생을 찾을 수 있는가?
    static boolean isFind(Point student, Point teacher){

        // y, x 좌표가 모두 다르면 못찾음
        if(student.y != teacher.y && student.x != teacher.x) return false;

        // 장애물 확인
        else if(student.y == teacher.y){
            int s=0, e=0;
            if(student.x > teacher.x){
                s = teacher.x;
                e = student.x;
            } else{
                s = student.x;
                e = teacher.x;
            }

            if(existObstacle(student.y, s, e, 0)) return false;

        }else if(student.x == teacher.x){
            int s=0, e=0;
            if(student.y > teacher.y){
                s = teacher.y;
                e = student.y;
            } else{
                s = student.y;
                e = teacher.y;
            }

            if(existObstacle(student.x, s, e, 1)) return false;
        }

        // 장애물이 없다면 찾아버린것임..
        return true;
    }

    // 배열에서 장애물이 존재하는지 확인
    static boolean existObstacle(int fix, int s, int e, int type){
        // 세로고정
        if(type == 0){
            for(int i=s+1; i<e; i++){
                if(map[fix][i] == 'O') return true;
            }
        }
        // 가로고정
        else {
            for(int i=s+1; i<e; i++){
                if(map[i][fix] == 'O') return true;
            }
        }

        return false;
    }

    // DFS 진행시 (y,x) 위치에 장애물을 놓을 수 잇는지 확인
    static boolean isInstall(int y, int x){
        char obj = map[y][x];
        if(obj != 'X') return false;

        return true;
    }
}
