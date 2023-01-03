package machine;

public class Available {

    protected static int water = 400;
    protected static int milk = 540;
    protected static int coffee = 120;
    protected static int cups = 9;
    protected static int money = 550;
    public static void useIngredients(int waterUsed, int milkUsed, int coffeeUsed) {
        water -= waterUsed;
        milk -= milkUsed;
        coffee -= coffeeUsed;
        cups -= 1;
    }
    public static void addMoney(int income) {
        money += income;
    }

    protected static void makeCoffee(int[] recipe) {
        int[] available = {water, milk, coffee};
        String[] ingredients = {"water", "milk", "coffee"};
        for (int i = 0; i < 3; i++) {
            if (recipe[i] > available[i]) {
                System.out.printf("Sorry, not enough %s%n", ingredients[i]);
                break;
            } else if (cups < 1){
                System.out.println("Sorry, not enough cups");
                break;
            } else {
                System.out.println("I have enough resources, making you a coffee!");
                useIngredients(recipe[0], recipe[1], recipe[2]);
                addMoney(recipe[3]);
                break;
            }
        }
    }

}
