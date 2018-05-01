package model;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import javafx.util.Builder;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Nano.son on 2018. 5. 1.
 */
public class ReserveForm {

    //예약 페이지번호
    private int fc_grno;
    //구장 번호
    private int fc_sqno;

    //신청하고자 하는 날짜
    private String tDate;
    //예약하고자 하는 시작 시간
    private String fc_time;
    //사용시간
    private String use_time;

    private static final String FROM_URL = "/doc/class_info6_reserve.php";
    //신청자 이름
    private static final String APNT_PRSN_NM = "손은호";
    //뭔진 모르지만 계속 1
    private static final String LCTN_DVCD = "1";
    //학번 (자동맵핑됨)
    private static final String MEMB_ID = "2012105054";
    //연락처
    private static final String HP_NO = "01052858268";
    //학부
    private static final String BLNG_NM="컴퓨터학부";
    //신청자 학번
    private static final String EMNO="2012105054";
    //사용 인원
    private static final int USER_QTY= 11;
    //같이 차는 인원들
    private static final String USER_LIST="손은호 외 10명, 손종영, 성지원, 정문주, 최종현, 김정환, 이민재, 신재민, 박상현, 김명훈, 윤원철";
    //행사 계획
    private static final String EVNT_PLAN = "풋살. 뒷정리 깔끔히 하겠습니다.";

    private ReserveForm(Builder builder) {
        this.tDate = builder.tDate;
        this.use_time = builder.use_time;
        this.fc_time = builder.fc_time;
    }

    public static class Builder {
        String tDate;
        String fc_time;
        String use_time;
        private Boolean ready;

        public Builder() {
            ready = false;
        }

        public Builder date(String tDate) {
            this.tDate = tDate;
            check();
            return this;
        }

        public Builder startHour(int startHour) {
            this.fc_time = String.valueOf(startHour);
            check();
            return this;
        }


        public Builder useHour(int useHour) {
            this.use_time = String.valueOf(useHour);
            check();
            return this;
        }

        public void check() {
            if(tDate!=null && fc_time!=null && use_time!=null)
                ready = true;
            else
                ready = false;
        }

        public ReserveForm build() {
            if(ready)
                return new ReserveForm(this);
            throw new InvalidStateException("make sure builder's instance variables");
        }
    }


    public HttpEntity makeEntity(Field field) throws UnsupportedEncodingException {
        //풋살장1,2,3, 대운동장
        //족구장 옆 풋살장
        this.fc_grno = field.fc_grno;
        this.fc_sqno = field.fc_sqno;

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("MEMB_ID="+MEMB_ID)
                .append("&fc_grno="+fc_grno)
                .append("&fc_sqno="+fc_sqno)
                .append("&fc_time="+fc_time)
                .append("&tDate="+tDate)
                .append("&APNT_PRSN_NM="+APNT_PRSN_NM)
                .append("&LCTN_DVCD="+LCTN_DVCD)
                .append("&FROM_URL="+FROM_URL)
                .append("&HP_NO="+HP_NO)
                .append("&BLNG_NM="+BLNG_NM)
                .append("&EMNO="+EMNO)
                .append("&use_time="+use_time)
                .append("&USER_QTY="+USER_QTY)
                .append("&USER_LIST="+USER_LIST)
                .append("&EVNT_PLAN="+EVNT_PLAN);

        HttpEntity httpEntity = new StringEntity(URLEncoder.encode(strBuilder.toString(),"UTF-8"));
//        HttpEntity httpEntity = new StringEntity(strBuilder.toString());
        return httpEntity;
    }
}
