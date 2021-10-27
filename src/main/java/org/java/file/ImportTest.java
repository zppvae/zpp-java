package org.java.file;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.Data;

import java.util.List;

public class ImportTest {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("/Users/mac/111.xlsx");
        List<Temp> all = reader.readAll(Temp.class);

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ipc_channel_category_config(first_category_id,first_category_name,second_category_id,second_category_name,category_type)values");
        all.stream().forEach(item -> {
            sb.append("(");
            sb.append(item.getFirst_category_id() + ",");
            sb.append("'" + item.getFirst_category_name() + "',");
            sb.append(item.getSecond_category_id() + ",");
            sb.append("'" + item.getSecond_category_name() + "',");
            sb.append(item.getCategory_type());
            sb.append("),");
        });
        System.out.println(sb.toString());
    }

    @Data
    public static class Temp {
        private Integer first_category_id;

        private String first_category_name;

        private Integer second_category_id;

        private String second_category_name;

        private Integer category_type;
    }
}
