package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.framework.common.entity.White;
import com.besscroft.pisces.admin.mapper.WhiteMapper;
import com.besscroft.pisces.admin.service.WhiteService;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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

    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<White> getWhiteListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWhite(@NonNull White white) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.WHITE));
        Assert.isTrue(this.baseMapper.insert(white) > 0, "新增白名单失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWhite(@NonNull White white) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.WHITE));
        Assert.isTrue(this.baseMapper.updateById(white) > 0, "更新白名单失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWhite(@NonNull Long whiteId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.WHITE));
        Assert.isTrue(this.baseMapper.deleteById(whiteId) > 0, "删除白名单失败！");
    }

    @Override
    public List<WhiteDictDto> getWhiteDict() {
        List<WhiteDictDto> whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        if (CollectionUtils.isEmpty(whiteDictDtoList)) {
            synchronized (this) {
                whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
                if (CollectionUtils.isEmpty(whiteDictDtoList)) {
                    List<White> whiteList = this.list();
                    if (CollectionUtils.isEmpty(whiteList)) return whiteDictDtoList;
                    List<WhiteDictDto> dictDtoList = whiteList.stream().map(white -> {
                        WhiteDictDto dto = new WhiteDictDto();
                        dto.setTitle(white.getTitle());
                        dto.setPath(white.getPath());
                        return dto;
                    }).collect(Collectors.toList());
                    redisTemplate.opsForValue().set(SystemDictConstants.WHITE, dictDtoList);
                }
            }
        }
        return whiteDictDtoList;
    }

}
