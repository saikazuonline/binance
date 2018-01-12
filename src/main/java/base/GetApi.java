package base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import com.webcerebrium.binance.api.BinanceApi;

import bean.TradeInfoBean;

public class GetApi {

	protected BinanceApi api = new BinanceApi();
	protected TradeInfoBean trade = new TradeInfoBean();
	
	public void execute(){
	
		Properties properties = new Properties();
		String propertyFile = "tmp/information.properties";
	
		try {
			
			InputStream inputStream = new FileInputStream(propertyFile);
			properties.load(inputStream);
			inputStream.close();
		
			api.setApiKey(properties.getProperty("apiKey"));
			api.setSecretKey(properties.getProperty("apiSecretKey"));
			
			trade.setBtcusd(new BigDecimal(properties.getProperty("BTCUSD")));
			trade.setDoller(new BigDecimal(properties.getProperty("DOLLER")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
