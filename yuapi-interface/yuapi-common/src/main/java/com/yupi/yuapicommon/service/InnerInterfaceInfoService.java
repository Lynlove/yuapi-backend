package com.yupi.yuapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yuapicommon.model.entity.InterfaceInfo;


/**
* @author lyn
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-05-23 16:35:19
*/
public interface InnerInterfaceInfoService{
    /**
     * 从数据库中查询模拟接口是否存在（请求路径，请求方法，请求参数）
     * @param Path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String Path, String method);
}
