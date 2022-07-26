package arthur.dy.lee.tool;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpUtils {

    protected static int timeout = 30000;

    public static String sendGet(String requestUrl, String headerKey, String headerValue)
            throws IllegalAccessException {
        HttpResponse httpResponse = null;
        log.info("HttpUtils.sendGet requestUrl={}", requestUrl);
        try {
            HttpRequest httpRequest = null;
            if (StringUtils.isNotEmpty(headerKey) && StringUtils.isNotEmpty(headerValue)) {
                httpRequest = HttpUtil.createGet(requestUrl).header(headerKey, headerValue);
            } else {
                httpRequest = HttpUtil.createGet(requestUrl);
            }
            httpRequest.setReadTimeout(timeout);
            httpRequest.setConnectionTimeout(timeout);

            httpResponse = httpRequest.execute();
        } catch (Exception e) {
            log.error("HttpUtils.sendGet requestUrl={},httpResponse={}", requestUrl, JSONUtil.toJsonStr(httpResponse));
            throw e;
        }
        log.info("invoke HttpUtils.sendGet return ===> httpResponse={}, body={}", JSONUtil.toJsonStr(httpResponse),
                httpResponse.body());

        if (httpResponse.getStatus() == 404) {
            throw new IllegalAccessException(
                    "invoke url=" + requestUrl + " get 404! httpResponse=" + JSONUtil.toJsonStr(httpResponse));
        }
        return httpResponse.body();
    }

    public static String sendGetNoLog(String requestUrl) throws IllegalAccessException {
        HttpResponse httpResponse = null;
        log.info("HttpUtils.sendGet requestUrl={}", requestUrl);
        try {
            HttpRequest httpRequest = null;
            httpRequest = HttpUtil.createGet(requestUrl);
            httpRequest.setReadTimeout(timeout);
            httpRequest.setConnectionTimeout(timeout);
            httpResponse = httpRequest.execute();
        } catch (Exception e) {
            log.error("HttpUtils.sendGet requestUrl={},httpResponse={}", requestUrl, JSONUtil.toJsonStr(httpResponse));
            throw e;
        }
        if (httpResponse.getStatus() == 404) {
            throw new IllegalAccessException(
                    "invoke url=" + requestUrl + " get 404! httpResponse=" + JSONUtil.toJsonStr(httpResponse));
        }
        return httpResponse.body();
    }

    public static String sendGet(String requestUrl) throws IllegalAccessException {
        return sendGet(requestUrl, null, null);
    }

    /**
     * 发送POST请求
     *
     * @param requestUrl 地址
     * @return
     */
    public static String sendPost(String requestUrl, String body) throws IllegalAccessException {
        HttpResponse httpResponse = null;
        log.info("HttpUtils.sendPost requestUrl={}, body={}", requestUrl, body);
        try {
            HttpRequest httpRequest = HttpUtil.createPost(requestUrl)
                    .header("Content-Type", "application/x-www-form-urlencoded");
            ;
            httpRequest.setReadTimeout(timeout);
            httpRequest.setConnectionTimeout(timeout);
            httpRequest.body(body, ContentType.JSON.getValue());
            //            httpRequest.body("addr", "1FRtceDPtWUdUtbT49b1phAvojGdGbpQgr");
            httpResponse = httpRequest.execute();
        } catch (Exception e) {
            log.error("HttpUtils#sendPost requestUrl={}, body={}, httpResponse={}", requestUrl, body,
                    JSONUtil.toJsonStr(httpResponse));
            throw e;
        }
        log.info("invoke HttpUtils.sendPost httpResponse={}", JSONUtil.toJsonStr(httpResponse));
        if (httpResponse.getStatus() == 404) {
            throw new IllegalAccessException(
                    "invoke url=" + requestUrl + " get 404! httpResponse=" + JSONUtil.toJsonStr(httpResponse));
        }
        return httpResponse.body();
    }

    public static String sendPostForm(String url, Map<String, Object> parameters) throws IllegalAccessException {

        // 添加请求头信息
        Map<String, String> heads = new HashMap<>();
        heads.put("Content-Type", "application/x-www-form-urlencoded");

        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpRequest.post(url)
                    .headerMap(heads, false)
                    .form(parameters)
                    .timeout(timeout)
                    .execute();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        log.info("invoke HttpUtils.sendPost httpResponse={}", JSONUtil.toJsonStr(httpResponse));
        if (httpResponse.getStatus() == 404) {
            throw new IllegalAccessException(
                    "invoke url=" + url + " get 404! httpResponse=" + JSONUtil.toJsonStr(httpResponse));
        }
        return httpResponse.body();

    }
}
