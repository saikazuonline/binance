package execute;

import currency.CurrencyComparison;
import currency.NewCurrencySearch;
import currency.Impl.CurrencyComparisonImpl;
import currency.Impl.NewCurrencySearchImpl;

public class Init {
	
	public static void main(String args[]){
	
		NewCurrencySearch currencySearch = new NewCurrencySearchImpl();
		CurrencyComparison currencyComparison = new CurrencyComparisonImpl();
		
		// Binanceの通貨 全取得しXMLに書き込み
		currencySearch.execute();
		currencySearch.search();
	
	}
}
