package arthur.dy.lee.chain;

import arthur.dy.lee.tool.HttpUtils;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Arthur.li
 * @since 2022/7/26 10:36
 */
@Slf4j
public class ScService {

    //TODO
    public static BigDecimal getBalance() throws IllegalAccessException {
        String url = "https://siastats.info/navigator-api/unspent_outputs/dd54dbb924d8935084b8849c866cfafc48d413c2c3b9484a8562eb53bbfcaefe7d942f16fab4";

        BigDecimal result = BigDecimal.ZERO;
        String response = HttpUtils.sendGet(url);
        JSONArray jsonArray = JSONUtil.parseArray(response);
        if (jsonArray == null || jsonArray.size() == 0) {
            return BigDecimal.ZERO;
        }

        for(int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = JSONUtil.parseObj(jsonArray.get(i).toString());
            String amount = jsonObject.get("hastings").toString();
            result = result.add(new BigDecimal(amount));
        }
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        log.info(getBalance().toPlainString());
    }
}
