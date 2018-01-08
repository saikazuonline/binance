package execute;

import currency.NewCurrencySearch;
import currency.Impl.NewCurrencySearchImpl;

public class Do {
	
	public static void main(String args[]){
	
		NewCurrencySearch currencySearch = new NewCurrencySearchImpl();
		
		currencySearch.search();
	
	}
}
