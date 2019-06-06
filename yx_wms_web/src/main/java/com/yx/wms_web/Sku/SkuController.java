package com.yx.wms_web.Sku;

import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.model.Sku.Sku;
import com.yx.wms_service.Sku.SkuService;
import com.yx.wms_web.Base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value={"/Sku"})
public class SkuController extends BaseController {
    @Autowired
    private SkuService skuService;

    //商品首页
    @RequestMapping(value={"","/"})
    public String Sku() {
        return "Sku/sku";
    }

    //获取商品列表
    @ResponseBody
    @RequestMapping(value = "/GetSkuList",method = RequestMethod.POST)
    public GridResponse<Sku> GetSkuList(@RequestBody GridRequest request) {
        return skuService.GetSkuList(GetGroupId(), request);
    }

    //获取商品详细信息
    @ResponseBody
    @RequestMapping("/GetSkuInfo")
    public Sku GetSkuInfo(String skuId) {
        return skuService.GetSkuInfo(skuId);
    }

    //保存商品信息
    @ResponseBody
    @RequestMapping("/SaveSku")
    public int SaveSku(@RequestBody Sku sku) {
        if(StringUtils.isBlank(sku.getSkuId())) {
            sku.setGroupId(GetGroupId());
            return skuService.InsertSku(sku);
        }
        else{
            return skuService.UpdateSku(sku);
        }
    }

    //修改数据状态
    @ResponseBody
    @RequestMapping("/UpdateDataValidate")
    public int UpdateDataValidate(String skuId, int validate) {
        return skuService.UpdateDataValidate(skuId, validate);
    }

    private static Double percent = 0.99;

    @ResponseBody
    @RequestMapping("/GetInfo")
    public Set<Set<Integer>> GetInfo() {
        Double[] weights = { 3.0, 5.0, 2.0, 6.0, 4.0, 5.0, 6.0, 8.0 };
        Integer[] singlePlan = { 0, 0, 0, 0, 0, 0, 0, 0 };
        return xx(weights, singlePlan, 12.1);
    }

    public Set<Set<Integer>> xx(Double[] weights, Integer[] singlePlan, Double maxWeight) {
        double minWeight = maxWeight * percent;
        Set<Set<Integer>> currentPlanSet = new HashSet<Set<Integer>>();// currentPlanSet当前方案集-(包括单个方案)
        int num = weights.length-1;
        int singlePlanLength = singlePlan.length - 1;
        Double currentWeight = 0.0;

        while(singlePlan[0] < num) {
            // 一个终结条件，也就是满足当前计划条件的时候
            Set<Integer> singlePlanSet = new HashSet<Integer>(Arrays.asList(singlePlan));
            singlePlan[singlePlanLength]++;
            currentWeight = xx(weights,singlePlanSet);
            if (currentWeight.compareTo(maxWeight)<=0 && currentWeight.compareTo(minWeight)>=0) {
                currentPlanSet.add(singlePlanSet);//加入当前计划
            }
            if (singlePlan[singlePlanLength] > singlePlanLength) {
                singlePlan=xx(singlePlan,singlePlanLength,singlePlanLength);
            }
        }
        return currentPlanSet;
    }

    public Integer[] xx(Integer[] singlePlan,Integer para,int singlePlanLength) {
        if (singlePlan[para] > singlePlanLength) {
            singlePlan[para] = 0;
            singlePlan[para-1] += 1;
            return xx(singlePlan,para-1,singlePlanLength);
        }
        return singlePlan;
    }

    public Double xx(Double[] weights, Set<Integer> singlePlan) {
        Double sumWeight = 0.0;
        for (Integer integer : singlePlan) {
            sumWeight += weights[integer];
        }
        return sumWeight;
    }
}
