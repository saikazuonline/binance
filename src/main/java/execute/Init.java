package execute;

import currency.NewCurrencySearch;
import currency.Impl.NewCurrencySearchImpl;

public class Init {
	
	public static void main(String args[]){
	
		NewCurrencySearch currencySearch = new NewCurrencySearchImpl();
		
		// Binanceの通貨 全取得しXMLに書き込み
		currencySearch.execute();
		 currencySearch.search(); 
	
	}
}
