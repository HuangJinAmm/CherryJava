package com.cherryjava.admin.service.permission.model;

/**
 * @author hjamm
 * 供 DataPermissionChecker使用的 数据条件
 */
public class DataCondition {

    private Long targetDeptId;
    private Long targetUserId;

    public DataCondition() {
    }

    public static class DataConditionBuilder {
        DataCondition dataCondition;

        public DataConditionBuilder() {
            this.dataCondition = new DataCondition();
        }

        public DataConditionBuilder targetDeptId(Long targetDeptId) {
            this.dataCondition.targetDeptId = targetDeptId;
            return this;
        }

        public DataConditionBuilder targetUserId(Long targetUserId) {
            dataCondition.targetDeptId = targetUserId;
            return this;
        }

        public DataCondition build() {
            return dataCondition;
        }
    }

    public DataCondition(Long targetDeptId, Long targetUserId) {
        this.targetDeptId = targetDeptId;
        this.targetUserId = targetUserId;
    }

    public static DataConditionBuilder builder() {
        return new DataConditionBuilder();
    }

    public Long getTargetDeptId() {
        return targetDeptId;
    }

    public void setTargetDeptId(Long targetDeptId) {
        this.targetDeptId = targetDeptId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }
}
