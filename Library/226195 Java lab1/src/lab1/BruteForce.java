package lab1;

import java.util.Arrays;
import java.util.List;


/**
 * Klasa rozwiazuje problem plecakowy algorytmem przegladu zupelnego,
 * nalezy podac liste przedmiotow oraz maksymalna pojemnosc plecaka
 */
public class BruteForce {
	
	/**
	 * najlepsze znalezione rozwiazanie, tablica jedynek i zer, gdzie zero
	 * w odpowiednim miejscu oznacza iz przedmiot ten jest zawarty w plecaku
	 */
	int bestrozw[];
	/**
	 * @param lista - Lista przedmiotow typu klasy Item
	 * @param maxweight - maksymalna waga jako moze przechowywac plecak
	 */
	public BruteForce() {
		
	}
	public BruteForce(List<Item> lista, int maxweight) {
		int totalweight = 0;
		float totalvalue = 0;
		float maxvalue = 0;
		int wPlecaku[] = new int[lista.size()];
		int bestrozw[] = new int[lista.size()];
		
		for(int i=wPlecaku.length-1;i>=0;i--)
		{
			if(wPlecaku[i] == 1)
			{
				wPlecaku[i] = 0;
			}
			else
			{
				wPlecaku[i] = 1;
				for(int j = 0;j<lista.size();j++)
				{
					if (wPlecaku[j]==1)
					{
						totalweight += lista.get(j).getWeight();
						totalvalue += lista.get(j).getValue();
					}
				}
				if (totalvalue > maxvalue && totalweight <= maxweight)
				{
					bestrozw=Arrays.copyOf(wPlecaku,wPlecaku.length);
					maxvalue = totalvalue;
				}
				totalweight = 0;
				totalvalue = 0;
				i=wPlecaku.length;
			}
		}
		this.bestrozw = bestrozw;
	}
	
	public static int[] DoAlg(List<Item> lista, int maxweight) {
		int totalweight = 0;
		float totalvalue = 0;
		float maxvalue = 0;
		int wPlecaku[] = new int[lista.size()];
		int bestrozw[] = new int[lista.size()];
		
		for(int i=wPlecaku.length-1;i>=0;i--)
		{
			if(wPlecaku[i] == 1)
			{
				wPlecaku[i] = 0;
			}
			else
			{
				wPlecaku[i] = 1;
				for(int j = 0;j<lista.size();j++)
				{
					if (wPlecaku[j]==1)
					{
						totalweight += lista.get(j).getWeight();
						totalvalue += lista.get(j).getValue();
					}
				}
				if (totalvalue > maxvalue && totalweight <= maxweight)
				{
					bestrozw=Arrays.copyOf(wPlecaku,wPlecaku.length);
					maxvalue = totalvalue;
				}
				totalweight = 0;
				totalvalue = 0;
				i=wPlecaku.length;
			}
		}
		return bestrozw;
	}

	/**
	 * Getter zwracajacy wynik algorytmu przechowywanego w tablicy bestrozw
	 * @return najlepsze rozwi¹zanie algorytmu
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
		return "Algorytm sprawdza wszystkie mozliwe spososby wlozenia rzeczy do plecaku";
	}

}
