package c4.strategy;

import java.util.ArrayList;
import java.util.Collections;

import c4.strategy.example.SortVillageByArea;
import c4.strategy.example.SortVillageById;
import c4.strategy.example.SortVillageByName;
import c4.strategy.example.SortVillageByPopulation;
import c4.strategy.example.Village;
/**
 * 策略模式實例
 */
public class StrategyExample {

	public static void main(String[] args) {
		// 準備三個村莊的資料
		Village appleFarm = new Village(3,"apple farm",32,5.1);
		Village barnField = new Village(1,"barn field",22,1.7);
		Village capeValley = new Village(2, "cape valley",  10  ,10.2);
		
		
		ArrayList<Village> vilages = new ArrayList<>();
		vilages.add(appleFarm);
		vilages.add(barnField);
		vilages.add(capeValley);
		
		System.out.println("沒排序過的資料");
		showList(vilages);
		
		System.out.println("根據ID排序");
		Collections.sort(vilages,new SortVillageById());
		showList(vilages);
		System.out.println("根據名子排序");
		Collections.sort(vilages,new SortVillageByName());
		showList(vilages);
		
		System.out.println("根據人口排序");
		Collections.sort(vilages,new SortVillageByPopulation());
		showList(vilages);
		
		System.out.println("根據面積排序");
		Collections.sort(vilages,new SortVillageByArea());
		showList(vilages);
	}

	// 只是為了把資料印出來看
	public static void showList (ArrayList<Village> list){
		for(Village v : list){
			System.out.println(v);
		}
	}
}


