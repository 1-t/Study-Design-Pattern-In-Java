﻿#策略模式與簡單工廠模式有什麼不同?

###先看圖  

####簡單工廠模式  
![Simple Factory](image/simpleFactory.gif) 

####策略模式  
![Fight Strategy](image/strategy.gif)  
  
這問題我困擾了很久，簡單工廠模式跟策略模式的類別圖看起來根本是一模一樣的，用法似乎也是一樣  
***訓練營提供不同的冒險者讓我們可以去進行不同的任務 vs 冒險者選擇不同的攻擊策略來攻擊怪物***
	
上面這兩句怎麼看都只是把名詞換掉而已，在Google大神找遍了中英文各家解釋，一般來說會得到這樣的答案：  
* 簡單工廠模式是用來建立物件的模式，關注物件如何被產生  
* 策略模式是一種行為模式，關注的是行為的封裝  
  
***訓練營提供不同的冒險者讓我們可以去進行不同的任務 vs 冒險者選擇不同的攻擊策略來攻擊怪物***  

這時候就必須承認自己愛鑽牛角尖又天資不夠聰穎，無法從上面這兩句話看出這兩個模式有什麼差異???   
  
關鍵在於使用的時機，看下面的程式碼，簡單工廠模式只負責創立物件，因此只要跟訓練營Factory說，給我一個弓手Product，給我一個鬥士Product，訓練營就會給你一個相對應的冒險者，致於這個冒險者要去燒殺擄掠還是解救村民，訓練營表示不關我的事情，我也不想知道，我只負責訓練冒險者；現在換到策略模式，冒險者根據不同的情境來產生攻擊策略，因此我們關注的點變成在策略Strategy本身，而不是使用策略的冒險者。選擇什麼樣的策略現在才是真正重要的事情，至於這個策略怎麼來的，我們並不在乎。  
  
兩者的差別在於工廠模式中的工廠類別並不會去使用產品，因為工廠模式只關注在如何產生建立物件；在策略模式中的環境類別則是使用外部傳入的策略類別，因此我們必須知道傳入策略的實際內容才行。  
  
做完總結後，發現自己的結論跟網路上找到的那兩句是一樣的，有時候學習就是這麼一回事，不是自己的經歷、沒自己思考過，即使明明解答就在面前，還式無法領會。  
  

* * *  
  
簡單工廠模式  
```
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
```


策略模式  
```
@Test
public void test(){
	Adventurer ad = new Adventurer();
	
	// 史萊姆用一般攻擊就可以
	System.out.println("出現史萊姆>>>");
	ad.choiceStrategy(new NormalAttack());
	ad.attack();
	System.out.println();
	
	// 利害的敵人要用厲害的招式打他
	System.out.println("非常非常巨大的史萊姆>>>");
	ad.choiceStrategy(new UseSkill());
	ad.attack();
	System.out.println();
}	
```