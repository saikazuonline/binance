package execute;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import currency.Impl.TradeImpl;
import currency.Trade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by kazuki on 3/14/2018 AD.
 */
public class TradeSupport {
    public static void main(String args[]){
        try {

            /** START UIにて入力 TODO */
            String currency = "ETH";
            BigDecimal quentity = new BigDecimal(0.0);
            BigDecimal max = new BigDecimal(1.03);
            BigDecimal min = new BigDecimal(0.97);
            /** END UI似て入力 */

            // インスタンス生成
            BinanceApi api = new BinanceApi();
            Trade trade = new TradeImpl();

            // BTC建てでsymbol作成
            String symbol = currency + "BTC";
            // symbolをキーに値段を取得
            BigDecimal price = api.pricesMap().get(symbol);

            // buyメソッドを使うためのexecute実施
            trade.execute();
            // 購入
//            trade.buy(symbol, price, quentity);

            // 上限値算出
            BigDecimal upperLimit = price.multiply(max);
            // 上限値算出
            BigDecimal lowerLimit = price.multiply(min);

            // BigDecimal型からString型へ変換
            String strPrice = price.toString(); 
            // 「.」を「,」に変換　。(「.」はsplitできないため)
            String strReplace = strPrice.replace(".",",");
            // 「,」をターゲットにsprit
            String[] split = strReplace.split(",", 0);
            int decimal = split[1].length();

            // TODO XMLにupperLimitとlowerLimitを書きつけ。
            xmlWrite(upperLimit.setScale(decimal, BigDecimal.ROUND_HALF_UP),
                    lowerLimit.setScale(decimal, BigDecimal.ROUND_HALF_UP));

        } catch(BinanceApiException e) {
            System.out.println( "ERROR: " + e.getMessage());
        }


    }

    private static void xmlWrite(BigDecimal upperLimit, BigDecimal lowerLimit){

        // Documentインスタンスの生成
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();

        // XML文書の作成
        Element info = document.createElement("info");
        document.appendChild(info);
        Element max = document.createElement("max");
        max.appendChild(document.createTextNode(upperLimit.toString()));
        info.appendChild(max);
        Element min = document.createElement("min");
        min.appendChild(document.createTextNode(lowerLimit.toString()));
        info.appendChild(min);

        // XMLファイルの作成
        File file = new File("data/Info.xml");
        write(file, document);

    }

    public static boolean write(File file, Document document) {

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
