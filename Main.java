 import java.lang.annotation.Target;
 import java.util.Scanner;




class Game {
    int order;
    int green = 3;
    int yellow = 7;
    int red = 5;
    public Game(int order){
        this.order = order;}

    public void ShowThingies(){
        System.out.println("Current Number of Thingies: Red = " + red +  ", Green = " + green + ",Yellow =" + yellow  );
    }
    public void P1(){
        Scanner remove = new Scanner(System.in);
        System.out.println();
        System.out.println("Pick a Color and number of thingies to remove, separated by comma");
        String[] GameOptions = remove.nextLine().split(",");
        if (GameOptions.length != 2){
            System.out.println("Wrong Format. Maybe you put a space between the comma and the number?");
            return;
        }
        String color = GameOptions[0].trim();
        int num_to_go;
        try {
            num_to_go = Integer.parseInt(GameOptions[1].trim());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Number of thingies, go again");
            return;
        }

        if (num_to_go > 0){
            if (color.equalsIgnoreCase("red")){
                if (num_to_go <= red){
                    red -= num_to_go;
                }
                else {System.out.println("Not enough red thingies to remove");}}
            else if (color.equalsIgnoreCase("green")){
                if (num_to_go <= green){
                    green -= num_to_go;}
                else {
                    System.out.println("Not enough green thingies to remove");}}
            else if (color.equalsIgnoreCase("yellow")) {
                if (num_to_go <= yellow){
                    yellow -= num_to_go;}
                else {
                    System.out.println("Not enough yellow thingies to remove");}}
            else{
                System.out.println("Wrong Color, go again");
                }
            }
        else{System.out.println("Num must be positive");}}

    public void sillyRobot(){
        System.out.println("Robot go now");
        int MinThingies = Math.min(red, Math.min(green, yellow));
        String TargetColor = "";

        //prio

        if (red == MinThingies){
            TargetColor = "Red";
        } else if (green == MinThingies) {
            TargetColor = "Green";
        } else {
            TargetColor = "Yellow";
        }
        int num_to_go = (int) (Math.random() * MinThingies) + 1;
        if (TargetColor.equals("Red")) {
            red -= num_to_go;
            System.out.println("Robot removes " + num_to_go + " Red thingies.");
        } else if (TargetColor.equals("Green")) {
            green -= num_to_go;
            System.out.println("Robot removes " + num_to_go + " Green thingies.");
        } else {
            yellow -= num_to_go;
            System.out.println("Robot removes " + num_to_go + " Yellow thingies.");
        }
    }

    public boolean GameOver(){
        return (green <= 0 && yellow <= 0 && red <= 0);

    }
}



public class Main {
    public static void main (String[] args) {
        int order = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Go 1 or 2");
        if (scanner.hasNextInt()){
            order = scanner.nextInt();
        }
        else{
            System.out.println("Invalid input");
            return ;
        }


        Game game = new Game(order);
        while (!game.GameOver()) {
            game.ShowThingies();

            if (game.order == 1){
                game.P1();
                if (game.GameOver()){
                    break;
                }
                game.sillyRobot();
            } else if (game.order == 2){
                game.sillyRobot();
                if (game.GameOver()){
                    break;
                }
                game.P1();
            }
        }
        System.out.println("Game over. Final State");
        game.ShowThingies();
    }
}
