package execute;

import java.math.BigDecimal;

import bean.TradeInfoBean;
import currency.Calculation;
import currency.Impl.CalculationImpl;

public class CalculateExecute {
	
	public static void main(String args[]){
		
		Calculation calculation = new CalculationImpl();
		TradeInfoBean tradeInfoBean = new TradeInfoBean();
		calculation.execute();
		// TRX 50$ 
		calculation.setQuentity(BigDecimal.ZERO, tradeInfoBean);	
	}	
}
