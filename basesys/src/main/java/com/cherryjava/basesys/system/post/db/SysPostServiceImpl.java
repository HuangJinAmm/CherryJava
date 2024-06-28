package com.cherryjava.basesys.system.post.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.basesys.system.user.db.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author hjamm
 * @since 2022-06-16
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPostEntity> implements SysPostService {

    private final SysUserMapper userMapper;

    public SysPostServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param postName 岗位名称
     * @return 结果
     */
    @Override
    public boolean isPostNameDuplicated(Long postId, String postName) {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(postId != null, "post_id", postId)
                .eq("post_name", postName);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isPostCodeDuplicated(Long postId, String postCode) {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(postId != null, "post_id", postId)
                .eq("post_code", postCode);
        return baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean isAssignedToUsers(Long postId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        return userMapper.exists(queryWrapper);
    }


}
