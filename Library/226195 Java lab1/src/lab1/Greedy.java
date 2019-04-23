package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Klasa roziwazuje problem plecakowy algorytmem przegladu zupelnego,
 * nalezy podac liste przedmiotow oraz maksymalna pojemnosc plecaka
 */
public class Greedy {
	int bestrozw[];
	/**

	 * @param lista - Lista przedmiotow typu klasy Item
	 * @param maxweight - maksymalna waga jako moze przechowywac plecak
	 */
	public Greedy(List<Item> lista,int maxweight) {
		int totalweight = 0;
		float totalvalue = 0;
		List<Item> posortowanalista = new ArrayList<Item>(lista);
		Collections.sort(posortowanalista, new ItemGreedyComparator());
		int bestrozw[] = new int[posortowanalista.size()];
		
		for(int i=0;i<posortowanalista.size();i++)
		{
			if (totalweight + posortowanalista.get(i).getWeight() <= maxweight)
			{
				totalweight += posortowanalista.get(i).getWeight();
				totalvalue += posortowanalista.get(i).getValue();
				bestrozw[posortowanalista.get(i).getNr()-1]=1;
			}
		}
		this.bestrozw = bestrozw;
	}
	public Greedy() {
		
	}
	/**
	 * Getter zwracajacy wynik algorytmu przechowywanego w tablicy bestrozw
	 * @return najlepsze znalezione rozwi¹zanie algorytmu
	 */
	public int[] getrozw() {
		return bestrozw;
	}
	/**
	 * funkcja zwraca string z opisem algorytmu
	 * @return opis algorytmu
	 */
	public String opis()
	{
		return "Algorytm zachlanny - Priorytetr wyboru to stosunek wartosci od masy";
	}

}

/**
 * Klasa która porównuje dwa przedmioty aby znalezc ktory ma lepszy stosunek 
 * wartosci do wagi
 * @param item1 pierwszy przedmiot
 * @param item2 drugi przedmiot
 * @return Zwraca 0 jezeli pierwszy przedmiot jest lepszy lub rowny, 1 jezeli drugi
 */
class ItemGreedyComparator implements Comparator<Item>{
	public int compare(Item item1, Item item2) {
		return (int) (item1.getValue()/item1.getWeight() - item1.getValue()/item1.getWeight());
	}
}
