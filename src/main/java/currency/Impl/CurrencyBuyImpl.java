package currency.Impl;

import java.math.BigDecimal;

import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceOrder;
import com.webcerebrium.binance.datatype.BinanceOrderPlacement;
import com.webcerebrium.binance.datatype.BinanceOrderSide;
import com.webcerebrium.binance.datatype.BinanceOrderType;
import com.webcerebrium.binance.datatype.BinanceSymbol;

import base.GetApi;
import currency.CurrencyBuy;

public class CurrencyBuyImpl extends GetApi implements CurrencyBuy{

	public void buy(String currency, double price, int quantity) {
		
		BinanceSymbol symbol;
		try {
			symbol = new BinanceSymbol(currency + "BTC");
			BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.BUY);
			placement.setType(BinanceOrderType.LIMIT);
			placement.setPrice(BigDecimal.valueOf(price));
			placement.setQuantity(BigDecimal.valueOf(quantity));
			BinanceOrder order;
			order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
			System.out.println(order.toString());
		} catch (BinanceApiException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sell(String currency, double price, int quantity){
		BinanceSymbol symbol;
		try {
			symbol = new BinanceSymbol(currency + "BTC");
			BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.SELL);
			placement.setType(BinanceOrderType.LIMIT);
			placement.setPrice(BigDecimal.valueOf(price));
			placement.setQuantity(BigDecimal.valueOf(quantity));
			BinanceOrder order;
			order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
			System.out.println(order.toString());
		} catch (BinanceApiException e) {
			e.printStackTrace();
		}
	}

}
