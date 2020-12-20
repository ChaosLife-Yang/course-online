package com.halayang.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum CourseChargeEnum {
    /**
     * 课程是否收费
     */
    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;

    private String desc;

    CourseChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    static class Container {
        private static Map<String, CourseChargeEnum> map = new HashMap<>(2);

        static {
            map.put(CHARGE.getCode(), CHARGE);
            map.put(FREE.getCode(), FREE);
        }

        public CourseChargeEnum getCourseChargeEnum(String key) {
            return map.get(key);
        }
    }


}
