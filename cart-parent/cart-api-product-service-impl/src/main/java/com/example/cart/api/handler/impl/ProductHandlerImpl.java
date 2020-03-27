package com.example.cart.api.handler.impl;

import com.example.cart.annotation.SysLog;
import com.example.cart.api.handler.IProductHandler;
import com.example.cart.base.BaseApiService;
import com.example.cart.base.ResponseBase;
import com.example.cart.model.Product;
import com.example.cart.service.IProductService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 吴玥
 * @Title ProductHandlerImpl
 * @Description
 * @date 2020/1/6
 */
@Slf4j
//@RequestMapping("/apiProduct")
@RestController
public class ProductHandlerImpl extends BaseApiService implements IProductHandler {
    @Autowired
    private IProductService productService;
    @Value("${server.port}")
    private String serverPort;

    /**
     * 测试负载均衡效果
     * @param name
     * @return
     */
    @RequestMapping(value = "/getProduct",name="getProduct")
    @Override
    public ResponseBase<Product> getProduct(@RequestParam("name") String name) {

        return setResultSuccess(serverPort+name);
    }
    /**
     * 查询所有商品项
     * @return
     */
    @SysLog("查询所有商品项")
    @GetMapping(value = "/findAllProduct" ,name = "findAllProduct")
    @Override
    public ResponseBase<PageInfo> findAllProduct(@RequestParam(defaultValue="1",name = "pageNum") int pageNum) {
        PageInfo<Product> pageInfo = productService.findAllProduct(pageNum);
        if(pageInfo.getList()==null){
            return setResultError("商品列表为空");
        }
        log.info("查询所有商品成功");
        return setResult(200,"查询所有商品成功",pageInfo);
    }

    /**
     * 根据商品编号查询单个商品
     * @param pid
     * @return
     */
    @GetMapping(value = "/findProductByPid/{pid}",name = "findProductByPid")
    @Override
    @SysLog("根据商品编号查询单个商品")
    public ResponseBase<Product> findProductByPid( @PathVariable("pid")  int pid) {
        Product product = productService.findProductByPid(pid);
        if(product==null){
            return setResultError("查询失败");
        }
        log.info("查询商品编号"+pid+"成功"+product);
        return setResult(200,"查询商品编号"+pid+"成功",product);

    }
}
