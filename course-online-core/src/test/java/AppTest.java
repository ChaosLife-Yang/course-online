import com.halayang.common.utils.JacksonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/9 18:33           1.0
 * @program course-online
 * @description
 * @create 2021/1/9 18:33
 */
public class AppTest {
    public static void main(String[] args) {
        String list1 = "[\"/system/admin/user/list\", \"/system/admin/user/saveOrUpdate\"]";
        String list2 = "[\"/system/admin/user/saveOrUpdate\"]";
        String list3 = "[\"/system/admin/user/delete\"]";
        String list4 = "[\"/system/admin/user/save-password\"]";
        List<List<String>> lists = new ArrayList<>();
        lists.add(JacksonUtils.toList(list1, String.class));
        lists.add(JacksonUtils.toList(list2, String.class));
        lists.add(JacksonUtils.toList(list3, String.class));
        lists.add(JacksonUtils.toList(list4, String.class));

        List<String> collect = lists.stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);
    }

}
