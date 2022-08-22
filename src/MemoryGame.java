import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MemoryGame {
    public static int ScoreH = 0;
    public static int ScoreE = 0;
    public static int chanceh = 15;
    public static int chancee = 10;
    public static String[][] planszaHard = new String[4][4];
    public static String[][] planszaeasy = new String[2][4];
    public static String[][] kartyhard = new String[4][4];
    public static String[][] kartyez = new String[2][4];

    public static Scanner koordynaty = new Scanner(System.in);

    public static void showhardgame() {
        for (int i = 0; i < 4; i++) {
            System.out.println("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(planszaHard[i][j]);
                System.out.print("|");
            }
        }
        System.out.println();
    }

    public static void showeasygame() {
        for (int i = 0; i < 2; i++) {
            System.out.println("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(planszaeasy[i][j]);
                System.out.print("|");
            }
        }
        System.out.println();
    }

    public static void wordshard() throws IOException {
        Random random = new Random();
        List<String> words = Files.readAllLines(new File("src/Words.txt").toPath());
        List<String> hardWords = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            int index = random.nextInt(words.size());
            hardWords.add(words.get(index));
            hardWords.add(words.get(index));
        }

        Collections.shuffle(hardWords);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                kartyhard[i][j] = hardWords.get(index);
                index++;
                words.remove(index);
            }
        }
    }

    public static void wordseasy() throws IOException {
        Random random = new Random();

        List<String> words = Files.readAllLines(new File("src/Words.txt").toPath());
        List<String> easyWords = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            int index = random.nextInt(words.size());
            easyWords.add(words.get(index));
            easyWords.add(words.get(index));
        }

        Collections.shuffle(easyWords);

        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                kartyez[i][j] = easyWords.get(index);
                index++;
                words.remove(index);
            }
        }
    }

    public static boolean GameOverhard() {
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (planszaHard[i][j].equals("_")) {
                    result = false;
                } else if (chanceh == 0) {
                    result = true;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }


    public static boolean GameOvereasy() {
        boolean result = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (planszaeasy[i][j].equals("_")) {
                    result = false;
                } else if (chancee == 0) {
                    result = true;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    public static void gra1(String[][] kartyhard){
        while(true){
            if(!GameOverhard()){
                System.out.println("Row: ");
                int row1 = koordynaty.nextInt();
                System.out.println("Column: ");
                int column1 = koordynaty.nextInt();

                if(!planszaHard[row1-1][column1-1].equals("_")){
                    System.out.println("U have already guessed this word");
                    System.out.println();
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showhardgame();
                    continue;
                }else{
                    planszaHard[row1-1][column1-1] = " " + kartyhard[row1-1][column1-1] + " ";
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showhardgame();
                }
                System.out.println("Row: ");
                int row2 = koordynaty.nextInt();
                System.out.println("Column: ");
                int column2 = koordynaty.nextInt();

                if(!planszaHard[row2-1][column2-1].equals("_")){
                    System.out.println("U have already guessed this word");
                    planszaHard[row1-1][column1-1] = "_";
                    System.out.println();
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Chances: " + chanceh);
                    showhardgame();
                }else{
                    planszaHard[row2-1][column2-1] = " " + kartyhard[row2-1][column2-1] + " ";
                    if(planszaHard[row1-1][column1-1].equals(planszaHard[row2-1][column2-1])){
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Chances: " + chanceh);
                        showhardgame();
                        System.out.println("+ 1 point");
                        ScoreH = ScoreH + 1;
                    }else{
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Chances: " + chanceh);
                        showhardgame();
                        chanceh = chanceh - 1;
                        System.out.println("- 1 chance");
                        planszaHard[row1-1][column1-1] = "_";
                        planszaHard[row2-1][column2-1] = "_";
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Chances: " + chanceh);
                        showhardgame();
                    }

                }
            }else{
                System.out.println("GAME OVER");
                System.out.println("Score: " + ScoreH);
                System.out.println("Cjances: " + chanceh);
                break;
            }
        }
    }

    public static void gra2(String[][] kartyez){
        while(true){
            if(!GameOvereasy()){
                System.out.println("Row: ");
                int row1 = koordynaty.nextInt();
                System.out.println("Column: ");
                int column1 = koordynaty.nextInt();

                if(!planszaeasy[row1-1][column1-1].equals("_")){
                    System.out.println("U have already guessed this word");
                    System.out.println();
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showeasygame();
                    continue;
                }else{
                    planszaeasy[row1-1][column1-1] = " " + kartyez[row1-1][column1-1] + " ";
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showeasygame();
                }
                System.out.println("Row: ");
                int row2 = koordynaty.nextInt();
                System.out.println("Column: ");
                int column2 = koordynaty.nextInt();

                if(!planszaeasy[row2-1][column2-1].equals("_")){
                    System.out.println("U have already guessed this word");
                    planszaeasy[row1-1][column1-1] = "_";
                    System.out.println();
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showeasygame();
                }else{
                    planszaeasy[row2-1][column2-1] = " " + kartyez[row2-1][column2-1] + " ";
                    if(planszaeasy[row1-1][column1-1].equals(planszaeasy[row2-1][column2-1])){
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Cjances: " + chanceh);
                        showeasygame();
                        System.out.println("+ 1 point");
                        ScoreE = ScoreE + 1;
                    }else{
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Cjances: " + chanceh);
                        showeasygame();
                        chancee = chancee - 1;
                        System.out.println("- 1 chance");
                        planszaeasy[row1-1][column1-1] = "_";
                        planszaeasy[row2-1][column2-1] = "_";
                        System.out.println("Score: " + ScoreH);
                        System.out.println("Cjances: " + chanceh);
                        showeasygame();
                    }

                }
            }else{
                System.out.println("GAME OVER");
                System.out.println("Score: " + ScoreH);
                System.out.println("Cjances: " + chanceh);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        while(true){
            System.out.println("Write 'restart' to restart or 'end' to end.");
            String decision = koordynaty.nextLine();
            if(decision.equals("restart")){
                System.out.println("Choose level(easy/hard)");
                String decision1 = koordynaty.nextLine();
                if(decision1.equals("easy")){
                    wordseasy();
                    for(int i = 0; i < 2; i++){
                        for(int j = 0; j < 4; j++){
                            planszaeasy[i][j] = "_";
                        }
                    }
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showeasygame();
                    gra2(kartyez);
                    break;
                }else if(decision1.equals("hard")){
                    wordshard();
                    for(int i = 0; i < 4; i++){
                        for(int j = 0; j < 4; j++){
                            planszaHard[i][j] = "_";
                        }
                    }
                    System.out.println("Score: " + ScoreH);
                    System.out.println("Cjances: " + chanceh);
                    showhardgame();
                    gra1(kartyhard);
                    break;
                }else{
                    System.out.println("Try again");
                }
            }else if(decision.equals("end")){
                System.out.println("Zzzzzzz");
                break;
            }else{
                System.out.println("Try again");
            }
        }

    }
}