package com.halayang.server.role.mapper;

import com.halayang.server.role.po.RolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface RoleMapper extends BaseMapper<RolePO> {

    /**
     * 根据用户id查询用户角色
     *
     * @param userId 用户id
     * @author YangYudi
     * @date 2021/2/20 17:10
     * @return java.util.List<java.lang.String>
     */
    List<String> getRoleByUserId(@Param("userId") String userId);

}
