package execute;

import java.util.List;

import bean.TradeInfoBean;
import currency.Comparison;
import currency.Impl.ComparisonImpl;

public class ComparisonExecute {
	
	public static void main(String args[]){
		
		Comparison currencyComparison = new ComparisonImpl();		
		
		// 前回取得した通貨全量と今回の全量を比較し、通知する。
		currencyComparison.execute();
		List<TradeInfoBean> currencyList = currencyComparison.comparison();
		for(TradeInfoBean currency : currencyList){
			System.out.println(currency.getCurrency());
			System.out.println(currency.getPrice());
		}
	}
	
}
