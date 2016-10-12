# 狀態模式 Status Pattern

####目的：
將物件的狀態封裝成類別，讓此物件隨著狀態改變時能有不同的行為。
  
####鬥士就是生命越低越有戰鬥力
很多事物的行為模式會隨著狀態而改變，例如說毛毛蟲只能在地上爬，之後變成蛹就入定了，破蛹而出變蝴蝶不但可以爬還可以飛，一般來說
我們會用if else或是switch case，不過根據書上的說法，有些人不喜歡看到一大堆if esle或switch case這種判斷式，因此就將會改變的狀態
封裝成類別，這樣可以減少一些判斷式，也可以讓類別的責任由狀態類別分擔。   
  
鬥士是一種很有趣的冒險者，他的戰鬥能力會隨著生命值HP的下降而提升，首先一開始HP>70%時候一切正常，
HP降低到70%以下時，會進入狂怒的狀態，如果HP進一步降到30%以下，則進入背水一戰狀態，以上狀態都回復生命，
如果HP降到0的時候，鬥士就會進入無法戰鬥的狀態，而且無法恢復生命值。以下WarriorPlain實作上面敘述，假設
move()方法內的if else有十幾種，而且每個條件都要執行數十行的程式，這種情況下就可以考慮使用狀態模式。  
  
```
/**
 * 鬥士類別(Context)
 */
public class WarriorPlain {
	private int hp ; 		// 生命值(直接以0~100表示)

	public WarriorPlain(){
		 // 一開始為滿HP狀態
		this.hp = 100 ;
	}
	
	/**
	 * 治療-恢復HP
	 * @param time
	 */
	public void heal(int heal){
		// 無法戰鬥的時候不能接受治療
		if(hp == 0) {
			hp = 0;
		} else {
			this.hp +=  heal;
		}
		if(hp > 100) {
			hp = 100;
		}
	}
	
	/**
	 * 受傷-減少hp
	 * @param damage
	 */
	public void getDamage(int damage){
		this.hp -= damage;
		if(hp < 0) {
			hp = 0;
		}
	}
	
	/**
	 * 將產生怪物的策略交給Status處理
	 */
	public void move(){
		if(hp == 0){
			System.out.println("無法戰鬥");
			//...麻煩自行想像下面還有幾十行才能玩成狀態設定
			return ;
		}
		if(hp > 70) {
			System.out.println("一般狀態");
			//...麻煩自行想像下面還有幾十行才能玩成狀態設定
		} else if (hp < 30) {
			System.out.println("背水一戰狀態");
			//...麻煩自行想像下面還有幾十行才能玩成狀態設定
		} else {
			System.out.println("狂怒狀態");
			//...麻煩自行想像下面還有幾十行才能玩成狀態設定			
		}
		//.....這邊假設 if else 總共還有十幾種不同的狀態
	}
}  
```  
  
改用狀態模式，鬥士為Context類別，各種生命值之下的狀態為State，當Context產生變化時，State也隨之變化，因此Context的行為模式就改變了。
     
類別圖：  
![Warrior](image/state.gif)  
```
/**
 * 鬥士類別(Context)
 */
public class Warrior {
	private int hp ; 		// 生命值(直接以0~100表示)
	private State state; 	// 目前狀態

	public Warrior(){
		 // 一開始為滿HP狀態
		this.hp = 100 ;
		state = new NormalState();
	}
	
	/**
	 * 治療-恢復HP
	 * @param time
	 */
	public void heal(int heal){
		this.hp +=  heal;
		if(hp > 100) {
			hp = 100;
		}
	}
	
	/**
	 * 受傷-減少hp
	 * @param damage
	 */
	public void getDamage(int damage){
		this.hp -= damage;
		if(hp < 0) {
			hp = 0;
		}
	}
	
	/**
	 * 將產生怪物的策略交給Status處理
	 */
	public void move(){
		state.move(this);
	}

	public void setState(State state){
		this.state = state;
	}
	public int getHP(){
		return this.hp;
	}
}



/**
 * 隨著生命值變化的狀態(State)
 */
public interface State {
	/**
	 * 狀態不同，行為模式不同(傳入warrior所以狀態可以取得warrior的資料)
	 * @param warrior 
	 */
	void move(Warrior warrior);
}


/**
 * 隨著時間變化的狀態(State)，HP > 70% ， 一般狀態　
 */
public class NormalState implements State{
	/**
	 * 狀態不同，行為模式不同(傳入warrior所以狀態可以取得warrior的資料)
	 * @param warrior 
	 */
	@Override 	
	public void move(Warrior warrior) {
		if(warrior.getHP() > 70){
			System.out.println("HP=" + warrior.getHP() + " , no buff ");	
		} else {
			warrior.setState(new FuryState());
			warrior.move();
		}
	}
}


/**
 * 隨著時間變化的狀態(State)，HP < 70% ， 狂怒狀態　
 */
public class FuryState implements State{
	/**
	 * 狀態不同，行為模式不同(傳入warrior所以狀態可以取得warrior的資料)
	 * @param warrior 
	 */
	@Override 	
	public void move(Warrior warrior) {
		int hp = warrior.getHP();
		if( hp > 70){
			warrior.setState(new NormalState());
			warrior.move();
		} else if (hp <= 30) {
			warrior.setState(new DesperateState());
			warrior.move();
		} else {
			System.out.println("HP=" + warrior.getHP() + " ,狂怒狀態 傷害增加30%");	
		}
	}
}


/**
 * 隨著時間變化的狀態(State)，HP小於30%，背水一戰狀態
 */
public class DesperateState implements State{
	/**
	 * 狀態不同，行為模式不同(傳入warrior所以狀態可以取得warrior的資料)
	 * @param warrior 
	 */
	@Override 	
	public void move(Warrior warrior) {
		int hp = warrior.getHP();
		if(hp == 0){
			warrior.setState(new UnableState());
			warrior.move();
		} else if ( hp > 30 ) {
			warrior.setState(new FuryState());
			warrior.move();
		} else {
			System.out.println("HP=" + warrior.getHP() + " ,背水一戰 傷害增加50%, 防禦增加50%");	
		} 
	}
}

/**
 * 隨著時間變化的狀態(State)，HP = 0% ， 無法戰鬥狀態　
 */
public class UnableState implements State{
	/**
	 * 狀態不同，行為模式不同(傳入warrior所以狀態可以取得warrior的資料)
	 * @param warrior 
	 */
	@Override 	
	public void move(Warrior warrior) {
		System.out.println("HP=" + warrior.getHP() + " , 無法戰鬥");	
	}
}

```  

