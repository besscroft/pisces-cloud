package com.besscroft.pisces.amqp.service.impl;

import com.besscroft.pisces.amqp.config.BarkConfiguration;
import com.besscroft.pisces.amqp.config.ServerChanConfiguration;
import com.besscroft.pisces.amqp.service.PushService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * @Description 推送服务实现类
 * @Author Bess Croft
 * @Date 2022/9/4 13:16
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String pushBark(@NonNull String sendKey, @NonNull String message) {
        StringBuilder url = new StringBuilder();
        url.append(BarkConfiguration.pushUrl);
        url.append("/");
        url.append(sendKey);
        url.append("/");
        url.append(message);

        Request request = new Request.Builder()
                .url(url.toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                log.info(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.error("push bark 失败:{}", e);
        }
        return "";
    }

    @Override
    public String pushServerChanSimple(@NonNull String sendKey, @NonNull String message) {
        StringBuilder url = new StringBuilder();
        url.append(ServerChanConfiguration.pushUrl);
        url.append("/");
        url.append(sendKey);
        url.append(".send");

        Request request = new Request.Builder()
                .url(url.toString())
                .header("title", message)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                log.info(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.error("push bark 失败:{}", e);
        }
        return "";
    }

}
