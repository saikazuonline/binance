package currency.Impl;

import java.math.BigDecimal;

import com.webcerebrium.binance.api.BinanceApiException;

import base.GetApi;
import bean.TradeInfoBean;
import currency.Calculation;

public class CalculationImpl extends GetApi implements Calculation {
	
	public void setQuentity(BigDecimal price, TradeInfoBean tradeInfoBean){
		
		try {
			 
			BigDecimal BTCUSD = api.pricesMap().get("BTCUSDT");
			BigDecimal TRX = price;
			BigDecimal DOLLARS = trade.getDoller();;
			BigDecimal TRXDollars = BigDecimal.ZERO;
			BigDecimal quentity = BigDecimal.ZERO;
				
			TRXDollars = BTCUSD.multiply(TRX);
			quentity = DOLLARS.divide(TRXDollars);
				
			System.out.println(TRXDollars);
			System.out.println(String.format("%.0f", quentity));
			tradeInfoBean.setQuentity(quentity);
		} catch (BinanceApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
