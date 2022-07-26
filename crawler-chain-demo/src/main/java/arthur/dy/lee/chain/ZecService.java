package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arthur.li
 * @since 2022/7/26 10:30
 */
@Slf4j
public class ZecService {

    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://api.zcha.in/v2/mainnet/accounts/t1RP5gmbnodM5eaajzQ9DoUChGzEtmnjzSH";

        Map<String, Object> map = new HashMap<>();
        map.put("addr", "1FRtceDPtWUdUtbT49b1phAvojGdGbpQgr");
        String response = HttpUtils.sendGet(url);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String balance = jsonObject.get("balance").toString();

        return new BigDecimal(balance);
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
