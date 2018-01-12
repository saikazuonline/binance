package currency.Impl;

import java.math.BigDecimal;

import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceOrder;
import com.webcerebrium.binance.datatype.BinanceOrderPlacement;
import com.webcerebrium.binance.datatype.BinanceOrderSide;
import com.webcerebrium.binance.datatype.BinanceOrderType;
import com.webcerebrium.binance.datatype.BinanceSymbol;

import base.GetApi;
import currency.Trade;

public class TradeImpl extends GetApi implements Trade{

	public void buy(String currency, BigDecimal price, BigDecimal quentity) {
		
		BinanceSymbol symbol;
		try {
			symbol = new BinanceSymbol(currency + "BTC");
			BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.BUY);
			placement.setType(BinanceOrderType.LIMIT);
			placement.setPrice(price);
			placement.setQuantity(quentity);
			BinanceOrder order;
			order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
			System.out.println(order.toString());
		} catch (BinanceApiException e) {
			e.printStackTrace();
			System.out.println("------------------------------");
			System.out.println("購入できませんでした。");
			System.out.println("------------------------------");
		}
		
	}
	
	public void sell(String currency, BigDecimal price, BigDecimal quantity){
		BinanceSymbol symbol;
		try {
			symbol = new BinanceSymbol(currency + "BTC");
			BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.SELL);
			placement.setType(BinanceOrderType.LIMIT);
			placement.setPrice(price);
			placement.setQuantity(quantity);
			BinanceOrder order;
			order = api.getOrderById(symbol, api.createOrder(placement).get("orderId").getAsLong());
			System.out.println(order.toString());
		} catch (BinanceApiException e) {
			e.printStackTrace();
			System.out.println("------------------------------");
			System.out.println("売却できませんでした。");
			System.out.println("------------------------------");
		}
	}

}
