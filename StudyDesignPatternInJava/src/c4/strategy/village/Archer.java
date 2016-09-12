package c4.strategy.village;
/**
 * 弓箭手
 * @author Yan
 *
 */
public class Archer extends Adventurer {

	@Override
	public String getType() {
		System.out.println("我是弓箭手");
		return  this.getClass().getSimpleName();
	}
}
