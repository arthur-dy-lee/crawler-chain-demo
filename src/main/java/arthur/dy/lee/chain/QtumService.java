package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Arthur.li
 * @since 2022/7/26 10:30
 */
@Slf4j
public class QtumService {

    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://explorer.qtum.org/insight-api/addr/QXKxT6XDLFG8YgmER2H8VRJDQ6fXNrHzLa/?noTxList=1";

        String response = HttpUtils.sendGet(url);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String balance = jsonObject.get("balance").toString();

        return new BigDecimal(balance);
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
