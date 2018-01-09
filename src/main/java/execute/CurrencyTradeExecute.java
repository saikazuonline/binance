package execute;

import currency.CurrencyBuy;
import currency.Impl.CurrencyBuyImpl;

public class CurrencyTradeExecute {
	
	public static void main(String args[]){
		
		CurrencyBuy currencyBuy = new CurrencyBuyImpl();
		
		currencyBuy.execute();
//		currencyBuy.buy("TRX", 0.00001000, 1000);
		currencyBuy.sell("TRX", 0.00001000, 1000);
	}
}
