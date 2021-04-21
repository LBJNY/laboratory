package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwLevel;
import com.zj.laboratory.pojo.LwReviewerType;
import com.zj.laboratory.service.LwReviewerTypeService;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reviewerType")
public class LwReviewerTypeController {
    @Autowired
    private LwReviewerTypeService lwReviewerTypeService;
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public Result<?> getList(){
        List<LwReviewerType> list=lwReviewerTypeService.getList();
        return new Result<>(list);
    }
}
