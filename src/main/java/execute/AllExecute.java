package execute;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bean.TradeInfoBean;
import currency.Calculation;
import currency.Comparison;
import currency.Trade;
import currency.NewCurrencySearch;
import currency.Impl.CalculationImpl;
import currency.Impl.ComparisonImpl;
import currency.Impl.TradeImpl;
import currency.Impl.NewCurrencySearchImpl;

public class AllExecute {
	static int set_hour=1;//時間を設定
	public static void main (String[] args){
		Timer tim = new Timer();
		tim.schedule(new Count(set_hour), 0,1000);
	}
}

class Count extends TimerTask{
	int count=0;
	int set_hour;
	public Count(int h){
		set_hour=h*1800;//設定時間*3600秒（１時間）
	}
	
	@Override
	public void run() {//1秒 ごとに実行
	if(count!=0){
		count--;
	}else{
		new Get_and_Write().start();
		count=set_hour;
	}
	//カウント
	System.out.println("<count>"+count/3600+":"+count%3600/60+":"+count%60);
	}
}
	
class Get_and_Write extends Thread{//データを取得、ファイル書き出しClass
	public void run(){
	//try catch 等好きなように記述
	autoBuy();
}
	
	private void autoBuy(){
		NewCurrencySearch newCurrencySearch = new NewCurrencySearchImpl();
		Calculation calculation = new CalculationImpl();
		Comparison comparison = new ComparisonImpl();
		Trade trade = new TradeImpl();
		
		comparison.execute();
		List<TradeInfoBean> tradeInfoList = comparison.comparison();
		
		for(TradeInfoBean tradeInfo : tradeInfoList){
			calculation.execute();
			calculation.setQuentity(tradeInfo.getPrice(), tradeInfo);
			trade.execute();
			trade.buy(tradeInfo.getCurrency(), tradeInfo.getPrice(), tradeInfo.getQuentity());
			System.out.println("------------------------------");
			System.out.println("新通貨: " + tradeInfo.getCurrency());
			System.out.println("価値: " + tradeInfo.getPrice());
			System.out.println("購入量: " + tradeInfo.getQuentity());
			System.out.println("仮想通貨を購入致しました。");
			System.out.println("------------------------------");
		}
		
		newCurrencySearch.execute();
		newCurrencySearch.search();
		System.out.println("------------------------------");
		System.out.println("XMLを更新しました。");
		System.out.println("------------------------------");
	}
}
