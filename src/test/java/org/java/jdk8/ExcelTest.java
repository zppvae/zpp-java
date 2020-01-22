package org.java.jdk8;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author zpp
 * @date 2019/6/26 13:46
 */
@Slf4j
public class ExcelTest {

    @Data
    class DateVO{
        private Date start;

        private Date end;

        private long timeDiff;

        private double hourDiff;
    }

    @Data
    class Staff {
        private String time;

        private double hourDiff;

        Staff(){}

        Staff(String time,double hourDiff){
            this.time = time;
            this.hourDiff = hourDiff;
        }

    }

    @Test
    public void test(){
        long startTime = System.currentTimeMillis();
        String importPath = "F:/筛选后的/";

        List<String> list = new ArrayList<>();
        list.add("kq.xlsx");

        for (String s : list) {
            ExcelReader reader = ExcelUtil.getReader(importPath + s);
            List<Map<String,Object>> readAll = reader.readAll();

            String fliterName = "黄婷婷";
            Map<String,DateVO> map = new HashMap<>();
            for (int i = 0; i < readAll.size();i++) {
                Map<String,Object> tmp = readAll.get(i);
                String name = tmp.get("姓名")+"";
                if (!name.equals(fliterName)) {
                    continue;
                }
                String dateStr = tmp.get("日期时间")+"";
                Date valDate = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
                String key = DateUtil.formatDate(valDate);

                DateVO d = map.get(key);
                if (d == null) {
                    d = new DateVO();
                    d.setStart(valDate);
                    d.setEnd(valDate);
                    map.put(key,d);
                } else {
                    if (valDate.getTime() > d.getEnd().getTime()) {
                        long timeDiff = DateUtil.between(d.getStart(),valDate,DateUnit.SECOND);
                        d.setTimeDiff(timeDiff);
                        d.setHourDiff(NumberUtil.div(timeDiff,3600,2));
                        d.setEnd(valDate);
                        map.put(key,d);
                    }
                }
            }
            reader.close();

            Set<String> keys = map.keySet();
            Iterator<String> it = keys.iterator();
            List<Staff> staffs = new LinkedList<>();
            while (it.hasNext()) {
                String key = it.next();
                DateVO tmp = map.get(key);
                staffs.add(new Staff(key,tmp.getHourDiff()));
            }

            // 通过工具类创建writer
            ExcelWriter writer = ExcelUtil.getWriter("F:/筛选后的/export/" + s);
            writer.write(staffs, true);
            writer.close();
        }

        log.info("数据处理完成，时间：{}",System.currentTimeMillis() - startTime);
    }

    @Test
    public void testDate(){
        Date d1 = DateUtil.parse("2017-4-1 09:18:15", "yyyy-MM-dd HH:mm:ss");
        Date d2 = DateUtil.parse("2017-4-1 16:14:38", "yyyy-MM-dd HH:mm:ss");
        log.info("{}",d1.getTime());
        log.info("{}", DateUtil.formatDate(d1));
        log.info("{}",DateUtil.between(d1,d2,DateUnit.HOUR) );
    }

}
