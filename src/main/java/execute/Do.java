package execute;

import currency.CurrencyComparison;
import currency.NewCurrencySearch;
import currency.Impl.CurrencyComparisonImpl;
import currency.Impl.NewCurrencySearchImpl;

public class Do {
	
	public static void main(String args[]){
		
		NewCurrencySearch currencySearch = new NewCurrencySearchImpl();
		CurrencyComparison currencyComparison = new CurrencyComparisonImpl();
		
		// 前回取得した通貨全量と今回の全量を比較し、通知する。
		currencyComparison.execute();
		currencyComparison.comparison();
		// Binanceの通貨 全取得しXMLに書き込み
		currencySearch.execute();
		currencySearch.search();
	
	}
	
}
