package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONArray;
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
public class DcrService {

    //TODO
    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://dcrdata.decred.org/api/address/Dsp56bA1uPNhCuDy9bKyt5rwnYk4aQguW8h/amountflow/month";

        Map<String, Object> map = new HashMap<>();
        map.put("addr", "1FRtceDPtWUdUtbT49b1phAvojGdGbpQgr");
        String response = HttpUtils.sendGet(url);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        JSONArray array = jsonObject.getJSONArray("net");
        BigDecimal balance = BigDecimal.ZERO;
        for (int i = 0; i < array.size(); i++) {
            balance = balance.add(new BigDecimal(array.get(i).toString()));
        }
        return balance;
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
