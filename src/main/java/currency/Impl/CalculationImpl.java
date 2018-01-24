package currency.Impl;

import java.math.BigDecimal;

import com.webcerebrium.binance.api.BinanceApiException;

import base.GetApi;
import bean.TradeInfoBean;
import currency.Calculation;

public class CalculationImpl extends GetApi implements Calculation {
	
	private final String BTC_USDT = "BTCUSDT";
	
	public void setQuentity(BigDecimal price, TradeInfoBean tradeInfoBean){
		
		try {
			 
			BigDecimal btcusd = api.pricesMap().get(BTC_USDT);
			BigDecimal dollars = trade.getDoller();;
			BigDecimal currencyDollars = BigDecimal.ZERO;
			BigDecimal quentity = BigDecimal.ZERO;
				
			currencyDollars = btcusd.multiply(price);
			quentity = dollars.divide(currencyDollars, 2, BigDecimal.ROUND_HALF_UP);
			
			tradeInfoBean.setQuentity(quentity.setScale(0, BigDecimal.ROUND_HALF_UP));
		} catch (BinanceApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
