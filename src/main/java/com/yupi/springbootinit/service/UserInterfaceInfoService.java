package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yuapicommon.model.entity.UserInterfaceInfo;

/**
* @author lyn
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-05-26 17:16:10
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add
     */
    void validInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 获取查询包装类
     *
     * @param userInterfaceInfoQueryRequest
     * @return
     */
    QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest);


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 校验用户是否还有调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    public boolean invokeValid(long interfaceInfoId, long userId);
}
