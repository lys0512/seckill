package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnums;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by luoyisu on 2018/2/12.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);
        //list.jsp + model = ModelAndView
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){

        if (seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax接口，返回json
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody  //将返回数据封装为json
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId){

        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execution(
            @PathVariable("seckillId") Long seckillId,
            @CookieValue(value = "killPhone",required = false) Long phone,
            @PathVariable("md5") String md5){
        if (phone == null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;
        try {
            //通过存储过程获取
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            //SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        } catch (SeckillClosedException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnums.END);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        } catch (RepeatKillException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnums.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        } catch (SeckillException e) {
            logger.error(e.getMessage(),e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnums.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }

}
