//Artem Frenk
//CS2223 HW1
//Double Trouble
import java.util.Random;
import java.util.Scanner;


class Game {
    int green = 3;
    int yellow = 7;
    int red = 5;
    Random random = new Random();

    public void ShowThingies(){
        System.out.println("Current Thingies: Red = " + red +  ", Green = " + green + ",Yellow =" + yellow  );
    }
    public void P1() {
        Scanner remove = new Scanner(System.in);
        System.out.println("Pick a Color and number of thingies to remove, separated by comma");
        String[] GameOptions = remove.nextLine().split(",");
        if (GameOptions.length != 2) {
            System.out.println("Wrong Format. Maybe you put a space between the comma and the number?");
            return;
        }
        String color = GameOptions[0].trim().toLowerCase();
        int num_to_go;
        try {
            num_to_go = Integer.parseInt(GameOptions[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number of thingies, go again");
            return;
        }

        if (num_to_go < 1) {
            System.out.println("You need to get rid of minimum 1 thingy");
            return;
        }
        switch (color) {
            case "red":
                if (num_to_go <= red) {
                    red -= num_to_go;
                } else {
                    System.out.println("not enough red thingies");
                }
                break;
            case "green":
                if (num_to_go <= green) {
                    green -= num_to_go;
                } else {
                    System.out.println("not enough green thingies");
                }
                break;
            case "yellow":
                if (num_to_go <= yellow) {
                    yellow -= num_to_go;
                } else
                    System.out.println("not enough yellow thingies");
               break;
            default:
                System.out.println("invalid color");
        }
    }
    public boolean CanWin(){
        int NonZeroCol = 0;
        if (red > 0) NonZeroCol++;
        if (green > 0) NonZeroCol++;
        if (yellow > 0) NonZeroCol++;
        return (NonZeroCol == 1);
    }

    public void sillyRobot(){
        System.out.println("Robot go now");
        if (CanWin()) {
            if (red > 0){
                System.out.println("Robot picked " + red + " Red thingies to win the game)");
                red = 0;
            }
            else if (green > 0){
                System.out.println("Robot picked " + green + " Green thingies to win the game)");
                green = 0;
            }
            else if (yellow > 0){
                System.out.println("Robot picked " + yellow + " Yellow thingies to win the game");
                yellow = 0;
            }
            return;
        }
        //priority
        int MinThingies = Integer.MAX_VALUE;
        String TargetColor = "";

        if (red > 0 && red < MinThingies){
            TargetColor = "Red";
            MinThingies = red;
        }
        if (green >0 && green < MinThingies) {
            TargetColor = "Green";
            MinThingies = green;
        }
        if (yellow >0 && yellow < MinThingies) {
            TargetColor = "Yellow";
            MinThingies = yellow;
        }
        int num_to_go = random.nextInt(MinThingies) + 1;
        switch (TargetColor){
            case "Red":
                red -= num_to_go;
                System.out.println("robot removes" + num_to_go + "red thingies");
                break;
            case "Green":
                green -= num_to_go;
                System.out.println("robot removes" + num_to_go + "green thingies");
                break;
            case "Yellow":
                yellow -= num_to_go;
                System.out.println("robot removes" + num_to_go + "yellow thingies");
                break;
        }
    }

    public boolean GameOver(){
        return (green == 0 && yellow == 0 && red == 0);
    }
    public void Winner(String LastPlayer){
        System.out.println("All thingies gone");
        System.out.println(LastPlayer + "Wins the game");
    }
}



public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("Go 1 or 2");
        int order = scanner.nextInt();
        boolean Turn = (order == 1);

        while (!game.GameOver()) {
            game.ShowThingies();

            if (Turn){
                game.P1();
                if (game.GameOver()){
                    game.Winner("Player ");
                    break;
                }
            } else {
                game.sillyRobot();
                if (game.GameOver()){
                    game.Winner("Robot ");
                    break;
                }
            }
            Turn = !Turn;
        }
    }
}
