package co.bugu.tes.order.api;

import co.bugu.common.RespDto;
import co.bugu.tes.order.domain.Order;
import co.bugu.tes.order.service.IOrderService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据api
 *
 * @author auto-coder
 * @create 2019-01-16 17:47
 */
@RestController
@RequestMapping("/order/api")
public class OrderApi {
    private Logger logger = LoggerFactory.getLogger(OrderApi.class);

    @Autowired
    IOrderService orderService;

    /**
     * 条件查询
     *
     * @param
     * @return
     * @author auto-coder
     * @date 2019-01-16 17:47
     */
    @RequestMapping(value = "/findByCondition")
    public RespDto<PageInfo<Order>> findByCondition(Integer pageNum, Integer pageSize, @RequestBody Order order) {
        try {
            logger.debug("条件查询， 参数: {}", JSON.toJSONString(order, true));
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            List<Order> list = orderService.findByCondition(pageNum, pageSize, order);
            PageInfo<Order> pageInfo = new PageInfo<>(list);
            logger.info("查询到数据： {}", JSON.toJSONString(pageInfo, true));
            return RespDto.success(pageInfo);
        } catch (Exception e) {
            logger.error("findByCondition  失败", e);
            return RespDto.fail();
        }
    }

    /**
     * 保存
     *
     * @param order
     * @return co.bugu.common.RespDto<java.lang.Boolean>
     * @author auto-coder
     * @date 2019-01-16 17:47
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespDto<Boolean> saveOrder(@RequestBody Order order) {
        try {
            Long orderId = order.getId();
            if(null == orderId){
                logger.debug("保存， saveOrder, 参数： {}", JSON.toJSONString(order, true));
                orderId = orderService.add(order);
                logger.info("新增 成功， id: {}", orderId);
            }else{
                orderService.updateById(order);
                logger.debug("更新成功", JSON.toJSONString(order, true));
            }
            return RespDto.success(orderId != null);
        } catch (Exception e) {
            logger.error("保存 order 失败", e);
            return RespDto.fail();
        }
    }


    /**
     * 获取详情
     *
     * @param id
     * @return co.bugu.common.RespDto<co.bugu.tes.order.domain.Order>
     * @author auto-coder
     * @date 2019-01-16 17:47
     */
    @RequestMapping(value = "/findById")
    public RespDto<Order> findById(Long id) {
        try {
            logger.info("findById, id： {}", id);
            Order order = orderService.findById(id);
            return RespDto.success(order);
        } catch (Exception e) {
            logger.error("获取详情失败", e);
            return RespDto.fail();
        }
    }

    /**
     * 删除，软删除，更新数据库删除标志
     *
     * @param
     * @return
     * @author auto-coder
     * @date 2019-01-16 17:47
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespDto<Boolean> delete(Long id, Long operatorId) {
        try {
            logger.debug("准备删除， 参数: {}", id);
            Preconditions.checkArgument(id != null, "id不能为空");
            int count = orderService.deleteById(id, operatorId);

            return RespDto.success(count == 1);
        } catch (Exception e) {
            logger.error("删除 失败", e);
            return RespDto.fail();
        }
    }
}

