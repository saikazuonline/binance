package currency;

import java.util.List;

import bean.TradeInfoBean;

public interface Comparison {
	
	public void execute();
	
	public List<TradeInfoBean> comparison();
	
}
