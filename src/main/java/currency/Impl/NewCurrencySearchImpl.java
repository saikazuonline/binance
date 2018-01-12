package currency.Impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;

import base.GetApi;
import currency.NewCurrencySearch;

public class NewCurrencySearchImpl extends GetApi implements NewCurrencySearch {
	
	public void search(){
		
		try {
				
				// Documentインスタンスの生成
		        DocumentBuilder documentBuilder = null;
				documentBuilder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
				
				Document document = documentBuilder.newDocument();
				// XML文書の作成
				Element coin = document.createElement("bitcoin");
		        document.appendChild(coin);
				
				JsonArray blanceList = api.balances();
				for(JsonElement blanceElement : blanceList){
					// JsonObjectを取得
					JsonObject jsonObjectBlance = blanceElement.getAsJsonObject();
					// Elementに変換
					JsonElement jsonElementBlance = jsonObjectBlance.get("asset");
					// Stringに変換
					String strBlance = jsonElementBlance.toString();
					
					Element value = document.createElement("coin");
			        value.appendChild(document.createTextNode(strBlance));
			        coin.appendChild(value);
				}
				
				// XMLファイルの作成
				File file = new File("BitCoin.xml");
				write(file, document);
			  
			} catch (BinanceApiException e) {
				System.out.println( "ERROR: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private boolean write(File file, Document document) {

        // Transformerインスタンスの生成
        Transformer transformer = null;
        try {
             TransformerFactory transformerFactory = TransformerFactory
                       .newInstance();
             transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
             e.printStackTrace();
             return false;
        }

        // Transformerの設定
        transformer.setOutputProperty("indent", "yes"); //改行指定
        transformer.setOutputProperty("encoding", "Shift_JIS"); // エンコーディング

        // XMLファイルの作成
        try {
             transformer.transform(new DOMSource(document), new StreamResult(
                       file));
        } catch (TransformerException e) {
             e.printStackTrace();
             return false;
        }

        return true;
   }
}
