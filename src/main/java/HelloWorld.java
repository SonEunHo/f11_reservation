import manager.LoginManager;
import manager.ReserveManager;
import model.Field;
import model.ReserveForm.*;

import java.net.URLEncoder;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception{

        String str="fc_qrno=컴퓨터학부";
        byte[] t = str.getBytes("UTF-8");
        String temp = new String(t);
        System.out.println(temp);
        System.out.println(t.length);
        System.out.println(URLEncoder.encode(str,"UTF-8"));

        LoginManager loginManager = new LoginManager("laedi0701", "8807di");
        int statusCode = loginManager.login();

        ReserveManager reserveManager = new ReserveManager();

        /**
         * EXAMPLE 1
         * 풋살장 1번 예약하기.
         * 2018-05-09일 17시부터 2시간동안 예약
         */
        Builder builder = new Builder()
                .date("2018-05-08")
                .startHour(15)
                .useHour(1);
        reserveManager.reserve(builder.build().makeEntity(Field.FOOTSAL2));

        /**
         * EXAMPLE 2
         * 풀코트 예약하기.
         * 2018-05-09일 10시부터 1시간동안 예약
         */
//        builder = new Builder()
//                .date("2018-05-09")
//                .startHour(10)
//                .useHour(1);
//        reserveManager.reserve(builder.build().makeEntity(Field.FULL_COURT));
    }
}
