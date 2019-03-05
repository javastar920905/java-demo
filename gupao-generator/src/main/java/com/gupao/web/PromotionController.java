package com.gupao.web;
import com.gupao.core.Result;
import com.gupao.core.ResultGenerator;
import com.gupao.model.Promotion;
import com.gupao.service.PromotionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @author  ouzhx on 2019/03/05.
*/
@Api(tags="Promotion管理")
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public Result add(Promotion promotion) {
        promotionService.save(promotion);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        promotionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Promotion promotion) {
        promotionService.update(promotion);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("Promotion详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Promotion promotion = promotionService.findById(id);
        return ResultGenerator.genSuccessResult(promotion);
    }

    @ApiOperation("Promotion列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Promotion> list = promotionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
