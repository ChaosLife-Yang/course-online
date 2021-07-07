import com.halayang.common.utils.JwtUtils;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/22 8:57           1.0
 * @program course-online
 * @description
 * @create 2021/3/22 8:57
 */
public class Test {

    public static void main(String[] args) {
        String token = JwtUtils.getJwtToken("656656", "yudi");
        System.out.println(token);
    }

}
