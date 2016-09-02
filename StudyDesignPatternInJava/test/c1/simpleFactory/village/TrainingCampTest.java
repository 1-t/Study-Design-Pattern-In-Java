package c1.simpleFactory.village;

import org.junit.Test;

import org.junit.Assert;
/**
 * 冒險者訓練營測試
 */
public class TrainingCampTest {
	@Test
	public void test(){
		System.out.println("==========簡單工廠模式測試==========");
		
		//訓練營訓練冒險者
		Adventurer memberA = TrainingCamp.trainAdventurer("archer");
		Adventurer memberB = TrainingCamp.trainAdventurer("warrior");
		
		//看看是不是真的訓練出我們想要的冒險者
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");
		//memberB應該是Warrior不是Knight，因此這邊會報錯
		// Assert.assertEquals(memberB.getType(), "Knight");
	}
}
