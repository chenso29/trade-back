package src.main.java.com.trade_accounting.utils.translator;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Repository
public class Translator {

    private static final String myUrl = "https://script.google.com/macros/s/AKfycbz0u0_MusWBrY6NLuiR9ij3s8MidHKMqmYHS_bz-QOZi4CQF0I/exec";

    public Translator() {

    }

    public String translate(String langFrom, String langTo, String text) {
        try {
            String urlStr = myUrl +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
