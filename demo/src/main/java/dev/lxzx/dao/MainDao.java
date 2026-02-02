package dev.lxzx.dao;

import java.util.List;

import dev.lxzx.entity.MainEntity;

public interface MainDao extends BaseDao<MainEntity, Long> {
    List<MainEntity> findAllByGroupId(Long groupId);
}
