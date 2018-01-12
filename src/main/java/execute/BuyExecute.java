package execute;

import java.math.BigDecimal;

import currency.Trade;
import currency.Impl.TradeImpl;

public class BuyExecute {
	public static void main(String args[]){
		
		Trade currencyBuy = new TradeImpl();
		
		BigDecimal price = new BigDecimal("0.00001000");
		BigDecimal quentity = new BigDecimal("1000");
		
		currencyBuy.execute();
		currencyBuy.buy("TRX", price, quentity);
	}
}
