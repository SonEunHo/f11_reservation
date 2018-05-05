import manager.LoginManager;
import manager.ReserveManager;
import model.Field;
import model.ReserveForm.*;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        LoginManager loginManager = new LoginManager("your id", "your pw");

        if( ! loginManager.login()){
            System.err.println("************[login fail]*************");
            return;
        }

        ReserveManager reserveManager = new ReserveManager();
        Builder builder = null;
        boolean result;
        /**
         * 잠깐! 예약하기 전에 ReserveForm class에 수정 필수 항목들을 본인에 맞게 수정하세요
         * 학번, 전화번호 등등 입력하세요.
         */

        /**
         * EXAMPLE 1
         * 족구장 옆 풋살장 예약하기.
         * 2018-10-18일 20시부터 2시간동안 예약
         * 시간단위는 24시간 단위이며 오후 3시는 15시라고 적야함.
         */
//        builder = new Builder()
//                .date("2018-10-18")
//                .startHour(20)
//                .useHour(2)
//                .field(Field.FOOTSAL4);
//        boolean result = reserveManager.reserve(builder);
//        if(! result)
//            System.err.println("************[reserve fail]*************");

        /**
         * EXAMPLE 2
         * 풀코트 예약하기.
         * 2018-10-18일 10시부터 1시간동안 예약
         * 여기서 10시는 오전 10시를 뜻함.
         */
        builder = new Builder()
                .date("2018-10-18")
                .startHour(10)
                .useHour(1)
                .field(Field.FULL_COURT);
        result = reserveManager.reserve(builder);
        if(! result)
            System.err.println("************[reserve fail]*************");
    }
}
