package com.gupao.service.impl;

import com.gupao.dao.PromotionMapper;
import com.gupao.model.Promotion;
import com.gupao.service.PromotionService;
import com.gupao.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author  ouzhx on 2019/03/05.
 */
@Service
@Transactional
public class PromotionServiceImpl extends AbstractService<Promotion> implements PromotionService {
    @Resource
    private PromotionMapper promotionMapper;

}
