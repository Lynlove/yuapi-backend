package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.springbootinit.utils.SqlUtils;
import com.yupi.yuapicommon.model.entity.UserInterfaceInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
* @author lyn
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-05-26 17:16:10
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{
    @Override
    public void validInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {

        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(userInterfaceInfo.getId() <= 0 || userInterfaceInfo.getUserId() <=0, ErrorCode.PARAMS_ERROR);

        }
        // 有参数则校验
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余");
        }
    }

    /**
     * 获取查询包装类
     *
     * @param userInterfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest) {

        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (userInterfaceInfoQueryRequest == null) {
            return queryWrapper;
        }
        Long id = userInterfaceInfoQueryRequest.getId();
        Long userId = userInterfaceInfoQueryRequest.getUserId();
        Long interfaceInfoId = userInterfaceInfoQueryRequest.getInterfaceInfoId();
        Integer status = userInterfaceInfoQueryRequest.getStatus();
        String sortField = userInterfaceInfoQueryRequest.getSortField();
        String sortOrder = userInterfaceInfoQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(ObjectUtils.isNotEmpty(interfaceInfoId), "interfaceInfoId", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断(其实这里还应该校验存不存在，这里就不用校验了，因为它不存在，也更新不到那条记录)
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 使用 UpdateWrapper 对象来构建更新条件
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        // 在 updateWrapper 中设置了两个条件：interfaceInfoId 等于给定的 interfaceInfoId 和 userId 等于给定的 userId。
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);
        // setSql 方法用于设置要更新的 SQL 语句。这里通过 SQL 表达式实现了两个字段的更新操作：
        // leftNum=leftNum-1和totalNum=totalNum+1。意思是将leftNum字段减一，totalNum字段加一。
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        // 最后，调用update方法执行更新操作，并返回更新是否成功的结果
        return this.update(updateWrapper);
    }
    /**
     * 校验用户是否还有调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeValid(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId", interfaceInfoId);
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("status", 0);
        queryWrapper.gt("leftNum", 0);
        UserInterfaceInfo userInterfaceInfo = this.getOne(queryWrapper);
        return userInterfaceInfo != null;
    }

}




