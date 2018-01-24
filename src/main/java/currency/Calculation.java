package currency;

import java.math.BigDecimal;

import bean.TradeInfoBean;

public interface Calculation {
	
	public void execute();
	
	public void setQuentity(BigDecimal price, TradeInfoBean tradeInfoBean);

}
