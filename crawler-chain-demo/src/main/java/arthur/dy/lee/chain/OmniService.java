package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arthur.li
 * @since 2022/7/16 22:29
 */
@Slf4j
@Service
public class OmniService {

    protected static int timeout = 30000;

    public static BigDecimal method1() throws IllegalAccessException {
        String url = "https://api.omniwallet.org/v2/address/addr/";
        Map<String, Object> map = new HashMap<>();
        map.put("addr", "1FRtceDPtWUdUtbT49b1phAvojGdGbpQgr");
        String response = HttpUtils.sendPostForm(url, map);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        JSONObject obj = (JSONObject)jsonObject.get("1FRtceDPtWUdUtbT49b1phAvojGdGbpQgr");
        JSONArray balances = obj.getJSONArray("balance");
        BigDecimal amount=null;
        if (balances != null) {
            for (Object balance : balances) {
                JSONObject json = (JSONObject) balance;
                if ("SP31".equals(json.getStr("symbol"))) {
                    String value = json.getStr("value");
                    amount = new BigDecimal(value);
                    break;
                }
            }
        }else{
            amount= BigDecimal.ZERO;
        }
        return amount;
    }


    public static void main(String[] args) throws IllegalAccessException {
        log.info(method1().toPlainString());

    }

}
