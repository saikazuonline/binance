package execute;

import java.util.List;

import bean.TradeInfoBean;
import currency.Calculation;
import currency.Comparison;
import currency.Trade;
import currency.NewCurrencySearch;
import currency.Impl.CalculationImpl;
import currency.Impl.ComparisonImpl;
import currency.Impl.TradeImpl;
import currency.Impl.NewCurrencySearchImpl;

public class AllExecute {
	public static void main(String args[]){

		NewCurrencySearch newCurrencySearch = new NewCurrencySearchImpl();
		Calculation calculation = new CalculationImpl();
		Comparison comparison = new ComparisonImpl();
		Trade trade = new TradeImpl();
		
		comparison.execute();
		List<TradeInfoBean> tradeInfoList = comparison.comparison();
		
		for(TradeInfoBean tradeInfo : tradeInfoList){
			calculation.execute();
			calculation.setQuentity(tradeInfo.getPrice(), tradeInfo);
			trade.execute();
			trade.buy(tradeInfo.getCurrency(), tradeInfo.getPrice(), tradeInfo.getQuentity());
			System.out.println("------------------------------");
			System.out.println("新通貨: " + tradeInfo.getCurrency());
			System.out.println("価値: " + tradeInfo.getPrice());
			System.out.println("購入量: " + tradeInfo.getQuentity());
			System.out.println("------------------------------");
		}
		
		newCurrencySearch.execute();
		newCurrencySearch.search();
		System.out.println("------------------------------");
		System.out.println("XMLを更新しました。");
		System.out.println("------------------------------");
	}
}
