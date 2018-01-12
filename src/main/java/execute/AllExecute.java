package execute;

import java.util.ArrayList;
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
//		List<TradeInfoBean> tradeInfoList = new ArrayList<TradeInfoBean>();
		
		comparison.execute();
		List<TradeInfoBean> tradeInfoList = comparison.comparison();
		
		for(TradeInfoBean tradeInfo : tradeInfoList){
			calculation.execute();
			calculation.setQuentity(tradeInfo.getPrice(), tradeInfo);
			trade.execute();
			trade.buy(tradeInfo.getCurrency(), tradeInfo.getPrice(), tradeInfo.getQuentity());
		}
		
		newCurrencySearch.execute();
		newCurrencySearch.search();
	}
}
