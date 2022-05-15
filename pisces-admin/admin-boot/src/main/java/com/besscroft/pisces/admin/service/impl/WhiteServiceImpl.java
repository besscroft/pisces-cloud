package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.entity.White;
import com.besscroft.pisces.admin.mapper.WhiteMapper;
import com.besscroft.pisces.admin.service.WhiteService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/5/14 18:21
 */
@Service
@RequiredArgsConstructor
public class WhiteServiceImpl extends ServiceImpl<WhiteMapper, White> implements WhiteService {

    private final SecurityUtils securityUtils;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<White> getWhiteListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addWhite(White white) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        white.setCreator(currentAdmin.getUsername());
        white.setUpdater(currentAdmin.getUsername());
        int i = this.baseMapper.insert(white);
        redisTemplate.delete(SystemDictConstants.WHITE);
        return i > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWhite(White white) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        white.setUpdater(currentAdmin.getUsername());
        white.setUpdateTime(LocalDateTime.now());
        int i = this.baseMapper.updateById(white);
        redisTemplate.delete(SystemDictConstants.WHITE);
        return i > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteWhite(Long whiteId) {
        int i = this.baseMapper.updateDelById(whiteId);
        redisTemplate.delete(SystemDictConstants.WHITE);
        return i > 0;
    }

    @Override
    public List<WhiteDictDto> getWhiteDict() {
        List<WhiteDictDto> whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        if (CollectionUtils.isEmpty(whiteDictDtoList)) {
            synchronized (this) {
                List<White> whiteList = this.baseMapper.selectList(new QueryWrapper<>());
                List<WhiteDictDto> dictDtoList = whiteList.stream().map(white -> {
                    WhiteDictDto dto = new WhiteDictDto();
                    dto.setTitle(white.getTitle());
                    dto.setPath(white.getPath());
                    return dto;
                }).collect(Collectors.toList());
                redisTemplate.opsForValue().set(SystemDictConstants.WHITE, dictDtoList);
            }
        }
        return whiteDictDtoList;
    }

}
