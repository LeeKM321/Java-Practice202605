package etc.api.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateTimeExample {

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date.getDay());

        System.out.println("======================================================");

        // 현재 날짜 정보 (연, 월, 일)
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        // 현재 시간 정보 (시, 분, 초)
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        // 현재 날짜와 시간 정보 얻기 (연, 월, 일, 시, 분, 초)
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        System.out.println("======================================================");

        // 특정 날짜와 시간 정보 얻기
        LocalDateTime beginDate = LocalDateTime.of(2022, 7, 1, 9, 0, 0);
        System.out.println("beginDate = " + beginDate);

        // 연 정보 추출
        int year = localDateTime.getYear();
        System.out.println("year = " + year);

        // Month라는 열거형 타입 리턴 (문자 형태의 월)
        Month month = localDateTime.getMonth();
        System.out.println("month = " + month);

        // 정수 형태의 월 추출
        int monthValue = localDateTime.getMonthValue();
        System.out.println("monthValue = " + monthValue);

        // 일 추출
        int dayOfMonth = localDateTime.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);

        // 요일 추출
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);

        // 일 년중 몇번째 날인가?
        int dayOfYear = localDateTime.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear);

        // 지금으로부터 3일 뒤
        LocalDateTime d3 = localDateTime.plusDays(3);
        System.out.println("d3 = " + d3);

        // 지금으로부터 1년 2개월 7일 뒤
        LocalDateTime d4 = localDateTime
                .plusYears(1)
                .plusMonths(2)
                .plusDays(7);
        System.out.println("d4 = " + d4);

        // 지금으로부터 9개월 23일 전
        LocalDateTime d5 = localDateTime.minusMonths(9)
                .minusDays(23);
        System.out.println("d5 = " + d5);

        // 사이 날짜 연산
        LocalDate b = LocalDate.of(2020, 12, 30);
        LocalDate f = LocalDate.of(2022, 9, 14);

        long between = ChronoUnit.DAYS.between(b, f);
        System.out.println("between = " + between);

        System.out.println("=======================================================");

        // 날짜 포맷 변경하기
        DateTimeFormatter dtf
                = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일 a hh시 mm분 ss초");

        String formatDate = localDateTime.format(dtf);
        System.out.println("formatDate = " + formatDate);


    }

}












