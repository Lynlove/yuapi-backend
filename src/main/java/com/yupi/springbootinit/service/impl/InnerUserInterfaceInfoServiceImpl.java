package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.yuapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 调用注入的 UserInterfaceInfoService 的 invokeCount 方法
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }


    /**
     * 校验用户是否还有调用次数
      * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeValid(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeValid(interfaceInfoId, userId);
    }
}
