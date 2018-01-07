package preview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

public class View {
	
	
	
	public static void main(String args[]){
		
		Properties properties = new Properties();
	    String file = "tmp/information.properties";
		
		try {
			  InputStream inputStream = new FileInputStream(file);
              properties.load(inputStream);
              inputStream.close();
			
			  BinanceApi api = new BinanceApi();
			  api.setApiKey(properties.getProperty("apiKey"));
			  api.setSecretKey(properties.getProperty("apiSecretKey"));
			  System.out.println(api.balances());
			  
			} catch (BinanceApiException e) {
			  System.out.println( "ERROR: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
