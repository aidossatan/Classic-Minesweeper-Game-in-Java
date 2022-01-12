import java.util.Random;
import java.util.Scanner;


public class Main {
    static int countOfClosedBoxes = 81;
    static char [][][] matrix = new char[9][9][2];
    static boolean [][] memory = new boolean[9][9];

    static Random bomb = new Random();
    public static char p = 'Y';

    //creates three dimensional matrix and randomly creates the positions for ten bombs
    public static void creatematrix() {
        for(int i = 0; i<9;i++) {
            for(int j = 0; j< 9; j++) {
                matrix[i][j][0]='0';
                matrix[i][j][1]='*';
                memory[i][j] = false;
            }
        }
        int k = 0;
        while(k!=10) {
            int x = 0, y = 0;
            x = bomb.nextInt(9);
            y = bomb.nextInt(9);
            if(matrix[x][y][0]!='B') {
                matrix[x][y][0]='B';
                k++;
            }
        }


    }

    //print the table(matrix) to the console
    public static void show_matrix() {
        for (int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print(matrix[i][j][1] + " ");
            }
            System.out.println();
        }
    }

    //converts char to int
    public static int chartoint(char c) {
        return (int)c;
    }

    //converts int to char
    public static char inttochar(int x) {
        return (char)x;
    }


    //puts the numbers around created bombs
    public static void put_numbers(int x, int y) {
        if(x-1 >= 0 && x-1 < 9 && matrix[x-1][y][0] != 'B') matrix[x-1][y][0] = inttochar(chartoint(matrix[x-1][y][0])+1);
        if(x-1 >= 0 && x-1 < 9 && y-1 >= 0 && y-1 < 9 && matrix[x-1][y-1][0] != 'B') matrix[x-1][y-1][0] = inttochar(chartoint(matrix[x-1][y-1][0])+1);
        if(y-1 >= 0 && y-1 < 9 && matrix[x][y-1][0] != 'B') matrix[x][y-1][0] = inttochar(chartoint(matrix[x][y-1][0])+1);
        if(x+1 >= 0 && x+1 < 9 && y-1 >= 0 && y-1 < 9 && matrix[x+1][y-1][0] != 'B') matrix[x+1][y-1][0] = inttochar(chartoint(matrix[x+1][y-1][0])+1);
        if(x+1 >= 0 && x+1 < 9 && matrix[x+1][y][0] != 'B') matrix[x+1][y][0] = inttochar(chartoint(matrix[x+1][y][0])+1);
        if(x+1 >= 0 && x+1 < 9 && y+1 >= 0 && y+1 < 9 && matrix[x+1][y+1][0] != 'B') matrix[x+1][y+1][0] = inttochar(chartoint(matrix[x+1][y+1][0])+1);
        if(y+1 >= 0 && y+1 < 9 && matrix[x][y+1][0] != 'B') matrix[x][y+1][0] = inttochar(chartoint(matrix[x][y+1][0])+1);
        if(x-1 >= 0 && x-1 < 9 && y+1 >= 0 && y+1 < 9 && matrix[x-1][y+1][0] != 'B') matrix[x-1][y+1][0] = inttochar(chartoint(matrix[x-1][y+1][0])+1);
    }

    //generates the numbers by accessing the put_number function
    public static void generate_numbers() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(matrix[i][j][0] =='B') put_numbers(i,j);
            }
        }
    }

    //make a splash in the matrix
    public static void splashinmatrix(int i, int j) {
        memory[i][j] = true;
        matrix[i][j][1] = matrix[i][j][0];
        countOfClosedBoxes--;

        //right
        if(i+1 < 9 && memory[i+1][j] == false) {
            if(matrix[i+1][j][0] != '0' && matrix[i+1][j][0] != 'B') {memory[i+1][j] = true;matrix[i+1][j][1] = matrix[i+1][j][0]; countOfClosedBoxes--; }
            else if(matrix[i+1][j][0]=='0')splashinmatrix(i+1,j);
        }
        //left
        if(i-1 > -1 && memory[i-1][j] == false) {
            if(matrix[i-1][j][0] != '0' && matrix[i-1][j][0] != 'B') {memory[i-1][j] = true;matrix[i-1][j][1] = matrix[i-1][j][0];countOfClosedBoxes--;}
            else if(matrix[i-1][j][0]=='0')splashinmatrix(i-1,j);
        }

        //up
        if(j+1 < 9 && memory[i][j+1] == false) {
            if(matrix[i][j+1][0] != '0' && matrix[i][j+1][0] != 'B') {memory[i][j+1] = true;matrix[i][j+1][1] = matrix[i][j+1][0];countOfClosedBoxes--;}
            else if(matrix[i][j+1][0]=='0')splashinmatrix(i,j+1);
        }

        //down
        if(j-1 > -1 && memory[i][j-1] == false) {
            if(matrix[i][j-1][0] != '0' && matrix[i][j-1][0] != 'B') {memory[i][j-1] = true;matrix[i][j-1][1] = matrix[i][j-1][0];countOfClosedBoxes--;}
            else if(matrix[i][j-1][0]=='0')splashinmatrix(i,j-1);
        }
    }

    //works with user
    public static void play() {
        countOfClosedBoxes = 81;
        show_matrix();
        Scanner input = new Scanner (System.in);
        System.out.print("Enter your move (row[1-9] column[1-9]): ");
        while(countOfClosedBoxes > 10) {
            int x = input.nextInt();
            int y = input.nextInt();
            while (x>9 || x<1 || y>9 || y<1) {
                System.out.print("The numbers that you entered are out of bound. So try again!\n\n");
                System.out.print("Enter your move (row[1-9] column[1-9]): ");
                x = input.nextInt();
                y = input.nextInt();
            }
            x--;
            y--;

            //splashinmatrix(x,y);
            if(matrix[x][y][0]=='0')splashinmatrix(x,y);
            else {
                matrix[x][y][1] = matrix[x][y][0];
                countOfClosedBoxes--;
            }
            show_matrix();

            if (matrix[x][y][0] == 'B') {
                System.out.print("Oops! You lose. Would you like to play again? (Y/N): ");
                p = input.next().charAt(0);
                return;

            }
            else System.out.print("Enter your next move (row[1-9] column[1-9]):");
            if(countOfClosedBoxes == 10) {
                System.out.println("Congratulations! You won! Would you like to play again? (Y/N):");
                p = input.next().charAt(0);
                return;
            }
        }
    }




    public static void main (String[] arg) {

        p = 'Y';
        while(p == 'Y' || p == 'y') {
            creatematrix();
            generate_numbers();
            play();
        }
        System.out.println("Thank you for the game!");
    }
}
