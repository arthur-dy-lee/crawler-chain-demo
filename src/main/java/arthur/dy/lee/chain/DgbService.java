package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * @author Arthur.li
 * @since 2022/7/26 10:36
 */
@Slf4j
public class DgbService {

    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://digiexplorer.info/address/DMfWVdupdtqtBbqbxS8JqHBZTKZB7cRPse";

        String response = HttpUtils.sendGet(url);
        Document doc = Jsoup.parse(response);
        Elements elements = doc.getElementsByClass("table data-table");
        Element element = elements.get(1);
        String content = element.html();

        return new BigDecimal(0);
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
