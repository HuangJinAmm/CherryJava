package com.cherryjava.basesys.system.user.db;

/**
 * 如果Entity的字段和复杂查询不匹配时   自定义类来接收
 *
 * @author hjamm
 */
public class SearchUserDO extends SysUserEntity {

    private String deptName;
    private String deptLeader;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    @Override
    public String toString() {
        return "SearchUserDO{" +
                "deptName='" + deptName + '\'' +
                ", deptLeader='" + deptLeader + '\'' +
                '}';
    }
}
