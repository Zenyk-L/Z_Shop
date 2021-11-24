package com.z.shop.service;

import com.z.shop.entity.Bucket;
import com.z.shop.shared.AbstractCRUD;

import java.util.List;

public interface BucketService extends AbstractCRUD<Bucket> {
    List<Bucket> findByUserId(Integer userId);
}
