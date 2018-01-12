package currency;

import java.math.BigDecimal;

public interface Trade {
	
	public void execute();
	
	public void buy(String currency, BigDecimal price, BigDecimal quantity);
	
	public void sell(String currency, BigDecimal price, BigDecimal quantity);
	
}
