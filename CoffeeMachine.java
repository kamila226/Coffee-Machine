package machine;
import java.util.Scanner;

public class CoffeeMachine {
    enum Status {
        CHOOSE_ACTION, CHOOSE_COFFEE, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUPS, TURNED_OFF
    }
    Status state = Status.CHOOSE_ACTION;
    public void askAction() {
        state = Status.CHOOSE_ACTION;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
    public void printRemaining() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", Available.water);
        System.out.printf("%d ml of milk%n", Available.milk);
        System.out.printf("%d g of coffee beans%n", Available.coffee);
        System.out.printf("%d disposable cups%n", Available.cups);
        System.out.printf("$%d of money%n", Available.money);
        state = Status.CHOOSE_ACTION;
        askAction();
    }
    public void take() {
        System.out.printf("I gave you $%d%n", Available.money);
        Available.money = 0;
        state = Status.CHOOSE_ACTION;
        askAction();
    }
    public void makeCoffee(String input) {
        switch (input) {
            case "1" -> Available.makeCoffee(Recipes.espresso);
            case "2" -> Available.makeCoffee(Recipes.latte);
            case "3" -> Available.makeCoffee(Recipes.cappuccino);
            case "back" -> askAction();
            default -> {
                System.out.println("Error!");
                return;
            }
        }
        askAction();
    }

    public void chooseAction(String choice) {
        switch (choice) {
            case "buy" -> {
                state = Status.CHOOSE_COFFEE;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
            }
            case "fill" -> {
                state = Status.FILL_WATER;
                System.out.println("Write how many ml of water you want to add:");
            }
            case "take" -> take();
            case "remaining" -> printRemaining();
            case "exit" -> state = Status.TURNED_OFF;
            default -> {
                System.out.println("Incorrect input");
                askAction();
            }
        }
    }

    public void processInput(String input) {
        switch (state) {
            case CHOOSE_ACTION:
                chooseAction(input);
                break;
            case CHOOSE_COFFEE:
                makeCoffee(input);
                break;
            case FILL_WATER:
                Available.water += Integer.parseInt(input);
                state = Status.FILL_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case FILL_MILK:
                Available.milk += Integer.parseInt(input);
                state = Status.FILL_COFFEE;
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case FILL_COFFEE:
                Available.coffee += Integer.parseInt(input);
                state = Status.FILL_CUPS;
                System.out.println("Write how many disposable cups you want to add:");
                break;
            case FILL_CUPS:
                Available.cups += Integer.parseInt(input);
                askAction();
                break;
            case TURNED_OFF:
                break;
            default:
                System.out.println("Incorrect Status");
                askAction();
        }
    }
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        Scanner scan = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String input = scan.nextLine();
        machine.processInput(input);
        while(machine.state != Status.TURNED_OFF) {
            input = scan.nextLine();
            machine.processInput(input);
        }

    }
}
