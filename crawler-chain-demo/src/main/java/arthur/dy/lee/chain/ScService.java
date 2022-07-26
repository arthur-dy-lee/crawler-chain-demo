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
 * @since 2022/7/26 10:36
 */
@Slf4j
public class ScService {

    //TODO
    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://siastats.info/navigator?search=dd54dbb924d8935084b8849c866cfafc48d413c2c3b9484a8562eb53bbfcaefe7d942f16fab4";

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
