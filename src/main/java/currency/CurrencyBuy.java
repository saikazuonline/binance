package currency;

public interface CurrencyBuy {
	
	public void execute();
	
	public void buy(String currency, double price, int quentity);
	
	public void sell(String currency, double price, int quentity);
	
}
