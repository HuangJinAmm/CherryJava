package com.cherryjava.basesys.common.command;

import cn.hutool.core.collection.CollUtil;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hjamm
 */
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new ApiException(ErrorCode.Business.COMMON_BULK_DELETE_IDS_IS_INVALID);
        }
        // 移除重复元素
        this.ids = new HashSet<>(idList);
    }

    private Set<T> ids;

    public Set<T> getIds() {
        return ids;
    }

    public void setIds(Set<T> ids) {
        this.ids = ids;
    }
}
