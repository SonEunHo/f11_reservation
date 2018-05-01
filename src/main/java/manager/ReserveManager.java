package manager;

import model.ReserveForm;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class ReserveManager {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "https://sports.knu.ac.kr/pages/register/facility_reserve.php";
    private static final String REFERER = "https://sports.knu.ac.kr/doc/class_info6_reserve.php";

    public int reserve(HttpEntity httpEntity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = makeRequest();
        httpPost.setEntity(httpEntity);


        System.out.println("httpPost:"+httpPost);
        Header[] headers = httpPost.getAllHeaders();
        for(Header h : headers)
            System.out.println(h);
        System.out.println("entity:");
        String line = "";
        BufferedReader r = new BufferedReader(new InputStreamReader(httpPost.getEntity().getContent()));
        while((line = r.readLine())!=null)
            System.out.println(line);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        int responseCode = response.getStatusLine().getStatusCode();
        if(responseCode != 200)
            return responseCode;

        Header[] h_array = response.getAllHeaders();
        for(Header header : h_array) {
            System.out.println(header.toString());
        }

        return 200;
    }

    public HttpPost makeRequest() {
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.addHeader("Cache-Control","max-age=0");
        httpPost.addHeader( "Connection","keep-alive");
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie",CookieManager.getCookie("PHPSESSID"));
        httpPost.addHeader("Host","sports.knu.ac.kr");
        httpPost.addHeader("Origin","http://sports.knu.ac.kr");
        httpPost.addHeader("Referer",REFERER);
        httpPost.addHeader("Upgrade-Insecure-Requests","1");
        httpPost.addHeader("User-Agent",USER_AGENT);

        return httpPost;
    }
}

