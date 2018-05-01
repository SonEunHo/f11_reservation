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

        LoginManager loginManager = new LoginManager("니들 아이디 입력해라", "니들 비밀번호 입력해라");
        int statusCode = loginManager.login();

        if(statusCode!= 200)
            return;

        ReserveManager reserveManager = new ReserveManager();

        /**
         * EXAMPLE 1
         * 족구장 옆 풋살장 예약하기.
         * 2018-05-28일 17시부터 2시간동안 예약
         * 시간단위는 24시간 단위이며 오후 3시는 15시라고 적야함.
         */
        Builder builder = new Builder()
                .date("2018-05-28")
                .startHour(17)
                .useHour(2);
        reserveManager.reserve(builder.build().makeEntity(Field.FOOTSAL4));

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
