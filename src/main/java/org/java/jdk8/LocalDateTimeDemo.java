package org.java.jdk8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author zpp
 * @date 2018/10/12 16:49
 */
public class LocalDateTimeDemo {

    public static void main(String[] args){
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//        LocalDate localDate = LocalDate.now();
//        System.out.println("今天的日期："+localDate);
//        System.out.println("year: "+localDate.getYear());
//        System.out.println("month: "+localDate.getMonthValue());
//        System.out.println("day: "+localDate.getDayOfMonth());
//
//        LocalDate date = LocalDate.of(2020,2,6);
//        System.out.println("自定义日期: "+date);

        /**
         * 判断2个日期是否相等
         */
//        LocalDate date1 = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2020,2,5);
//        if(date1.equals(date2)){
//            System.out.println("时间相等");
//        }else{
//            System.out.println("时间不等");
//        }

        /**
         * 检查周期性事件，如生日
         */
//        LocalDate date1 = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2020,3,4);
//        MonthDay birthday = MonthDay.of(date2.getMonth(),date2.getDayOfMonth());
//        MonthDay currentMonthDay = MonthDay.from(date1);
//
//        if(currentMonthDay.equals(birthday)){
//            System.out.println("是你的生日");
//        }else{
//            System.out.println("你的生日还没有到");
//        }

//        LocalTime time = LocalTime.now();
//        System.out.println("获取当前的时间,不含有日期:"+time);

//        LocalTime time = LocalTime.now();
//        LocalTime newTime = time.plusHours(3);
//        System.out.println("三个小时后的时间为:"+newTime);

//        LocalDate today = LocalDate.now();
//        System.out.println("今天的日期为:"+today);
//        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
//        System.out.println("一周后的日期为:"+nextWeek);

//        LocalDate today = LocalDate.now();
//        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
//        System.out.println("一年前的日期 : " + previousYear);
//        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
//        System.out.println("一年后的日期:"+nextYear);

        /**
         * 获取当时的时间戳
         */
//        Clock clock = Clock.systemUTC();
//        System.out.println("Clock : " + clock.millis());
//        // Returns time based on system clock zone
//        Clock defaultClock = Clock.systemDefaultZone();
//        System.out.println("Clock : " + defaultClock.millis());

        /**
         * 判断日期是早于还是晚于另一个日期
         */
//        LocalDate today = LocalDate.now();
//        LocalDate tomorrow = LocalDate.of(2020,3,4);
//        if(tomorrow.isAfter(today)){
//            System.out.println("之后的日期:"+tomorrow);
//        }
//        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
//        if(yesterday.isBefore(today)){
//            System.out.println("之前的日期:"+yesterday);
//        }

        /**
         * 把本时区的时间转换成另一个时区的时间。
         */
//        ZoneId america = ZoneId.of("America/New_York");
//        LocalDateTime localtDateAndTime = LocalDateTime.now();
//        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
//        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


//        YearMonth currentYearMonth = YearMonth.now();
//        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
//        YearMonth creditCardExpiry = YearMonth.of(2020, Month.FEBRUARY);
//        System.out.printf("信用卡到期日： %s %n", creditCardExpiry);
//        System.out.println("当月的天数："+creditCardExpiry.lengthOfMonth());


        /**
         * 检查闰年
         */
//        LocalDate today = LocalDate.now();
//        if(today.isLeapYear()){
//            System.out.println("This year is Leap year");
//        }else {
//            System.out.println("2020 is not a Leap year");
//        }


        /**
         * 计算两个日期之间的天数和月数
         */
//        LocalDate today = LocalDate.now();
//        LocalDate java8Release = LocalDate.of(2019, 12, 11);
//        Period periodToNextJavaRelease = Period.between(today, java8Release);
//        System.out.println("Months left between today and Java 8 release : "
//                + periodToNextJavaRelease.getMonths() );


        /**
         * 当前的时间戳
         */
//        Instant timestamp = Instant.now();
//        System.out.println("What is value of this instant " + timestamp.toEpochMilli());


//        String dayAfterTommorrow = "20200205";
//        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
//                DateTimeFormatter.BASIC_ISO_DATE);
//        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);


        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:"+str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str,format2);
        System.out.println("日期类型:"+date2);
    }
}
