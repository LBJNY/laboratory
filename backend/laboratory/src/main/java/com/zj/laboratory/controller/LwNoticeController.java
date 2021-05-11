package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.LwNotice;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.service.LwNoticeService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class LwNoticeController {
    @Autowired
    private LwNoticeService lwNoticeService;
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwNotice>> getByPage(@RequestBody Page<LwNotice> page) {
        Page<LwNotice> result = lwNoticeService.getByPage(page);
        return new Result<>(result);
    }
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwNotice> get(@PathVariable Long id) {
        LwNotice lwNotice = lwNoticeService.get(id);
        return new Result<>(lwNotice);
    }
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        lwNoticeService.delete(id);
        return new Result<>("删除成功");
    }

    /**
     * 查询前十条通知
     * @param page
     * @return
     */
    public Result<?> getTopNoticeList(Page<LwNotice> page){
        Page<LwNotice> result = lwNoticeService.getTopNoticeList(page);
        return new Result<>(result);
    }
}
