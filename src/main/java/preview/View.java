package preview;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

public class View {
	public static void main(String args[]){
		try {
			  BinanceApi api = new BinanceApi();
			  api.setApiKey("");
			  api.setSecretKey("");
			  System.out.println(api.balances());
			  
			} catch (BinanceApiException e) {
			  System.out.println( "ERROR: " + e.getMessage());
			}
	}
}
