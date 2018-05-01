package manager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class LoginManager {
    private static final String URL = "http://sports.knu.ac.kr/pages/member/login_process.php";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final int RESPONSE_SUCCESS = 200;
    private final String ID;
    private final String PW;

    public LoginManager(String ID, String PW) {
        this.ID = ID;
        this.PW = PW;
    }

    public int login() throws UnsupportedEncodingException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = makeRequest();

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        if( httpResponse.getStatusLine().getStatusCode() != RESPONSE_SUCCESS ){
            System.err.println("login fail !!");
            System.out.println("Check your account or network");
            return httpResponse.getStatusLine().getStatusCode();
        }

        System.out.println("[ login success ]");
        parseCookie(httpResponse);
        httpClient.close();

        return RESPONSE_SUCCESS;
    }

    public HttpPost makeRequest() throws UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.addHeader("Cache-Control","max-age=0");
        httpPost.addHeader("Connection","keep-alive");
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.addHeader("Host","sports.knu.ac.kr");
        httpPost.addHeader("Origin","http://sports.knu.ac.kr");
        httpPost.addHeader("Referer","http://sports.knu.ac.kr/main.html");
        httpPost.addHeader("Upgrade-Insecure-Requests","1");
        httpPost.addHeader("User-Agent",USER_AGENT);

        HttpEntity entity = new StringEntity("id="+ID+"&password="+PW);
        httpPost.setEntity(entity);

        return httpPost;
    }

    public void parseCookie(HttpResponse httpResponse) throws IOException{
        Header[] h_array = httpResponse.getAllHeaders();
        for (Header header : h_array) {
            if(!header.toString().contains("Cookie"))
                continue;
            //Set-Cookie: PHPSESSID=jg6oi4qh5e8kn37knjjvl1boa3; path=/
            String cookie = header.toString().split(":")[1].split(";")[0].trim();
            CookieManager.add("PHPSESSID", cookie);
            System.out.println("cookie:"+cookie);
            break;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String line=null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
