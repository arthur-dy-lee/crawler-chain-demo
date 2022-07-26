package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class XecService {

    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://explorer.bitcoinabc.org/address/1CBHdFBQcJQC8vnhWHkdJrKxC2YKgZfcS6";

        String response = HttpUtils.sendGet(url);
        if (StringUtils.isBlank(response)) {
            return null;
        }
        Document doc = Jsoup.parse(response);
        if (doc == null) {
            return null;
        }
        Elements elements = doc.getElementsByClass("json bg-light");
        if (elements == null || elements.get(0) == null) {
            return null;
        }

        Element element = elements.get(1);
        String content = element.toString();
        content = content.replace("<code class=\"json bg-light\">", "")
                .replace("</code>", "")
                .replace("<pre>", "")
                .replace("</pre>", "")
                .trim();

        JSONObject jsonObject = JSONUtil.parseObj(content);
        String balance = jsonObject.get("balanceSat").toString();

        return new BigDecimal(balance);
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
