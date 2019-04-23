package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) { 
		Item[] items = {new Item(5,6,1), 
						new Item(6,7,2),
						new Item(2,3,3), 
						new Item(4,5,4), 
						new Item(5,1,5),
						new Item(3,8,6), 
						};
		List<Item> rzeczy = new ArrayList<Item>(Arrays.asList(items));
		Instance instancja = new Instance(rzeczy, 20);
		BruteForce BF = new BruteForce(instancja.getlist(),instancja.getpojemnosc());
		Greedy GA = new Greedy(instancja.getlist(),instancja.getpojemnosc());
		Solution solution = new Solution(BF.getrozw());
		Solution solution2 = new Solution(GA.getrozw());
		solution.wyswwynik(instancja.getlist());
		solution2.wyswwynik(instancja.getlist());
		System.out.print(solution.ResultPL(instancja.getlist()));
	}

	
}
