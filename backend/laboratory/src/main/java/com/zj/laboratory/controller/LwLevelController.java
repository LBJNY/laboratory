package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwLevel;
import com.zj.laboratory.service.LwLevelService;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("level")
public class LwLevelController {
    @Autowired
    private LwLevelService levelService;

    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public Result<?> getList(){
        List<LwLevel> list=levelService.getList();
        return new Result<>(list);
    }
}
