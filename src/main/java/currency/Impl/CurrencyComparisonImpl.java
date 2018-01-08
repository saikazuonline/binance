package currency.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

import base.GetApi;
import currency.CurrencyComparison;

public class CurrencyComparisonImpl extends GetApi implements CurrencyComparison{
	
	private List<String> beforeList;
	private List<String> afterList;
	private static final String XML_File = "BitCoin.xml";
	private List<String> hitCurrencyList;

	public void comparison() {
		
		List<String> beList = getBeforeCurrency();
		List<String> afList = getAfterCurrency();
		boolean comparisonFlg = false;
		boolean hit;
		
		for(String afCurrency : afList){
			hit = false;
			for(String beCurrency: beList){
				if(StringUtils.equals(beCurrency, afCurrency)){
					hit = true;
				}
			}
			if(hit == false){
				System.out.println(afCurrency + "の通貨が追加されています。");
				comparisonFlg = true;
			}
		}
		
		if(comparisonFlg == false){
			System.out.println("新しく追加された通貨はありません。");
		} else {
			System.out.println("新しく追加された通貨があります。");
		}

		
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
