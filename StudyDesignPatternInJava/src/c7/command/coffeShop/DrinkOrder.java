package c7.command.coffeShop;

/**
 * 飲料訂單(ConcreteCommand)
 */
public class DrinkOrder extends Order {
	public DrinkOrder(KitchenWoker receiver) {
		super(receiver);
		super.name = "drinkOrder";
	}
}