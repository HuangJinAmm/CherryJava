package com.cherryjava.basesys.system.dept.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.common.core.page.AbstractQuery;

/**
 * @author hjamm
 */
public class DeptQuery extends AbstractQuery<SysDeptEntity> {

    private Long deptId;

    private Long parentId;

    public DeptQuery() {
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public QueryWrapper<SysDeptEntity> addQueryCondition() {
        // TODO parentId 这个似乎没使用
        return new QueryWrapper<SysDeptEntity>()
//            .eq(status != null, "status", status)
                .eq(parentId != null, "parent_id", parentId);
//            .like(StrUtil.isNotEmpty(deptName), "dept_name", deptName);
//            .and(deptId != null && isExcludeCurrentDept, o ->
//                o.ne("dept_id", deptId)
//                    .or()
//                    .apply("FIND_IN_SET (dept_id , ancestors)")
//            );
    }
}
