package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.converter.DepartConverterMapper;
import com.besscroft.pisces.admin.domain.dto.DepartDictDto;
import com.besscroft.pisces.admin.domain.dto.DepartDto;
import com.besscroft.pisces.admin.entity.Depart;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.DepartMapper;
import com.besscroft.pisces.admin.service.DepartService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:39
 */
@Service
@RequiredArgsConstructor
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    private final SecurityUtils securityUtils;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<DepartDto> getDepartListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        List<DepartDto> departDtos = new ArrayList<>();
        List<Depart> departList = this.baseMapper.selectAllByQueryKey(queryKey);
        if (!CollectionUtils.isEmpty(departList)) {
            departDtos = DepartConverterMapper.INSTANCE.DepartToDepartDtoList(departList);
            // ????????????
            departDtos = getDepartDtos(departDtos);
        }
        return departDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDepart(Long departId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.DEPART));
        return this.baseMapper.updateDelById(departId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDepart(Depart depart) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        depart.setCreator(currentAdmin.getUsername());
        depart.setUpdater(currentAdmin.getUsername());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.DEPART));
        return this.baseMapper.insert(depart) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDepart(Depart depart) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        depart.setUpdater(currentAdmin.getUsername());
        depart.setUpdateTime(LocalDateTime.now());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.DEPART));
        return this.baseMapper.updateById(depart) > 0;
    }

    @Override
    public List<DepartDictDto> getDepartDict() {
        List<DepartDictDto> departDictDtoList = (List<DepartDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.DEPART);
        if (!CollectionUtils.isEmpty(departDictDtoList)) {
            return departDictDtoList;
        }
        synchronized (this) {
            List<Depart> departList = this.baseMapper.selectList(new QueryWrapper<>());
            departDictDtoList = departList.stream().map(depart -> {
                DepartDictDto departDictDto = new DepartDictDto();
                departDictDto.setDepartId(depart.getId());
                departDictDto.setDepartName(depart.getName());
                return departDictDto;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(SystemDictConstants.DEPART, departDictDtoList);
            return departDictDtoList;
        }
    }

    /**
     * ?????????????????????
     * @param departDtoList ??????
     * @return ??????
     */
    private List<DepartDto> getDepartDtos(List<DepartDto> departDtoList) {
        List<DepartDto> parentDeparts = departDtoList.stream().filter(departDto -> departDto.getParentId() == 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentDeparts)) {
            return parentDeparts;
        }
        List<DepartDto> departDtos = departDtoList.stream().filter(departDto -> departDto.getParentId() != 0).collect(Collectors.toList());
        parentDeparts.forEach(departDto -> {
            List<DepartDto> childDepart = getChildDepart(departDto.getId(), departDtos);
            departDto.setChildren(childDepart);
        });
        return parentDeparts;
    }

    /**
     * ????????????
     * @param departId ?????? id
     * @param departDtoList ???????????????
     * @return ??????
     */
    private List<DepartDto> getChildDepart(Long departId, List<DepartDto> departDtoList) {
        List<DepartDto> departDtos = departDtoList.stream().filter(departDto -> departDto.getParentId() == departId).collect(Collectors.toList());
        departDtos.forEach(departDto -> {
            List<DepartDto> childDepart = getChildDepart(departDto.getId(), departDtoList);
            departDto.setChildren(childDepart);
        });
        return departDtos;
    }

}
