package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.yuapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author lyn
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2024-05-26 17:16:10
* @Entity com.yupi.springbootinit.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    // select interfaceInfoId, sum(totalNum) as totalNum
    //from user_interface_info
    //group by interfaceInfoId
    //order by totalNum desc
    //limit 3;
    // 返回前limit条数据
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




