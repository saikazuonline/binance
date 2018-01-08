package base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.webcerebrium.binance.api.BinanceApi;

public class GetApi {

	protected BinanceApi api = new BinanceApi();
	
	public void execute(){
	
		Properties properties = new Properties();
		String propertyFile = "tmp/information.properties";
	
		try {
			
			InputStream inputStream = new FileInputStream(propertyFile);
			properties.load(inputStream);
			inputStream.close();
		
			api.setApiKey(properties.getProperty("apiKey"));
			api.setSecretKey(properties.getProperty("apiSecretKey"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
