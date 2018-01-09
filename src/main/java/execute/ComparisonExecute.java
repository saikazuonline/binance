package execute;

import currency.CurrencyComparison;
import currency.NewCurrencySearch;
import currency.Impl.CurrencyComparisonImpl;
import currency.Impl.NewCurrencySearchImpl;

public class ComparisonExecute {
	
	public static void main(String args[]){
		
		CurrencyComparison currencyComparison = new CurrencyComparisonImpl();		
		
		// 前回取得した通貨全量と今回の全量を比較し、通知する。
		currencyComparison.execute();
		currencyComparison.comparison();
	}
	
}
