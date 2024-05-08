package com.batchdentilab.batchdentilab.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class TranslatorAPI {
    public String translate(String langTo, String description) throws IOException {
        String apiUrl =
                "https://script.google.com/" +
                        "macros/" +
                        "s/" +
                        "AKfycby1MdQmI9T6AzKYnVxZtlVfIveb6AF2p_vcfZEkuRunIQVGi2s4s5mSvrfy0IR496s1/exec" +
                        "?q=" + URLEncoder.encode(description, StandardCharsets.UTF_8) +
                        "&target=" + langTo;

        log.info("apiUrl = " + apiUrl);
        URL mappedUrl = new URL(apiUrl);

        StringBuilder translatedDesc = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) mappedUrl.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null){
            log.info("inputLine =" + inputLine);
            translatedDesc.append(inputLine);
        }
        bufferedReader.close();

        log.info("translatedDesc = " + translatedDesc.toString());
        return translatedDesc.toString();
    }
}
