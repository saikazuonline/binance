package currency.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceSymbol;

import base.GetApi;
import bean.TradeInfoBean;
import currency.Comparison;

public class ComparisonImpl extends GetApi implements Comparison{
	
	private List<String> beforeList;
	private List<String> afterList;
	private static final String XML_File = "BitCoin.xml";

	public List<TradeInfoBean> comparison() {
		
		List<String> beList = getBeforeCurrency();
		List<String> afList = getAfterCurrency();
		List<TradeInfoBean> tradeInfoList = new ArrayList<TradeInfoBean>();
		boolean comparisonFlg = false;
		boolean hit;
		
		for(String afCurrency : afList){
			hit = false;
			for(String beCurrency: beList){
				if(StringUtils.equals(afCurrency, beCurrency)){
					hit = true;
				}
			}
			if(hit == false){
				try {
					String repCurrency = afCurrency.replace("\"", "");
					BigDecimal price = api.pricesMap().get(repCurrency + "BTC");
					TradeInfoBean tradeInfo = new TradeInfoBean();
					tradeInfo.setCurrency(afCurrency);
					tradeInfo.setPrice(price);
					tradeInfoList.add(tradeInfo);
					comparisonFlg = true;
				} catch (BinanceApiException e) {
					e.printStackTrace();
				}
			}
		}
		if(comparisonFlg == false){
			System.out.println("新しく追加された通貨はありません。");
		} else {
			System.out.println("新しく追加された通貨があります。");
		}

		return tradeInfoList;
		
	}
	
	private List<String> getBeforeCurrency(){
		
		beforeList = new ArrayList<String>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(XML_File);
			Element root = document.getDocumentElement();
			
			NodeList nodeList = root.getChildNodes();
			for(int i=0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				beforeList.add(node.getTextContent());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return beforeList;
	}
	
	private List<String> getAfterCurrency(){
		afterList = new ArrayList<String>();
		JsonArray blanceList = null;
		try {
			blanceList = api.balances();
			for(JsonElement blanceElement : blanceList){
				// JsonObjectを取得
				JsonObject jsonObjectBlance = blanceElement.getAsJsonObject();
				// Elementに変換
				JsonElement jsonElementBlance = jsonObjectBlance.get("asset");
				// リストに格納
				afterList.add(jsonElementBlance.toString());
			}
		} catch (BinanceApiException e) {
			e.printStackTrace();
		}

		return afterList;
	}

}
