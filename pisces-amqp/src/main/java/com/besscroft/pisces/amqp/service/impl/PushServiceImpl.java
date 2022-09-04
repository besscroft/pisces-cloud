package com.besscroft.pisces.amqp.service.impl;

import com.besscroft.pisces.amqp.config.BarkConfiguration;
import com.besscroft.pisces.amqp.config.ServerChanConfiguration;
import com.besscroft.pisces.amqp.service.PushService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/9/4 13:16
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String pushBark(String sendKey, String message) {
        StringBuffer url = new StringBuffer();
        url.append(BarkConfiguration.pushUrl);
        url.append("/");
        url.append(sendKey);
        url.append("/");
        url.append(message);

        Request request = new Request.Builder()
                .url(url.toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            return response.body().string();
        } catch (IOException e) {
            log.info("push bark 失败:{}", e);
        }
        return "";
    }

    @Override
    public String pushServerChanSimple(String sendKey, String message) {
        StringBuffer url = new StringBuffer();
        url.append(ServerChanConfiguration.pushUrl);
        url.append("/");
        url.append(sendKey);
        url.append(".send");

        Request request = new Request.Builder()
                .url(url.toString())
                .header("title", message)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            return response.body().string();
        } catch (IOException e) {
            log.info("push bark 失败:{}", e);
        }
        return "";
    }

}
