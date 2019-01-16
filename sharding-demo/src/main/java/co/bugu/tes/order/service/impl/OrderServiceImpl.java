package co.bugu.tes.order.service.impl;

import co.bugu.common.enums.DelFlagEnum;
import co.bugu.tes.order.dao.OrderDao;
import co.bugu.tes.order.domain.Order;
import co.bugu.tes.order.service.IOrderService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author auto-coder
 * @create 2019-01-16 17:47
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderDao orderDao;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static String ORDER_BY = "update_time DESC";

    @Override
    public long add(Order order) {
        //todo 校验参数
        order.setIsDel(DelFlagEnum.NO.getCode());

        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        orderDao.insert(order);
        return order.getId();
    }

    @Override
    public int updateById(Order order) {
        logger.debug("order updateById, 参数： {}", JSON.toJSONString(order, true));
        Preconditions.checkNotNull(order.getId(), "id不能为空");
        order.setUpdateTime(new Date());
        return orderDao.updateById(order);
    }

    @Override
    public List<Order> findByCondition(Order order) {
        logger.debug("order findByCondition, 参数： {}", JSON.toJSONString(order, true));
        PageHelper.orderBy(ORDER_BY);
        List<Order> orders = orderDao.findByObject(order);

        logger.debug("查询结果， {}", JSON.toJSONString(orders, true));
        return orders;
    }

    @Override
    public List<Order> findByCondition(Integer pageNum, Integer pageSize, Order order) {
        logger.debug("order findByCondition, 参数 pageNum: {}, pageSize: {}, condition: {}", new Object[]{pageNum, pageSize, JSON.toJSONString(order, true)});
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Order> orders = orderDao.findByObject(order);

        logger.debug("查询结果， {}", JSON.toJSONString(orders, true));
        return orders;
    }

    @Override
    public PageInfo<Order> findByConditionWithPage(Integer pageNum, Integer pageSize, Order order) {
        logger.debug("order findByCondition, 参数 pageNum: {}, pageSize: {}, condition: {}", new Object[]{pageNum, pageSize, JSON.toJSONString(order, true)});
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Order> orders = orderDao.findByObject(order);

        logger.debug("查询结果， {}", JSON.toJSONString(orders, true));
        return new PageInfo<>(orders);
    }

    @Override
    public Order findById(Long orderId) {
        logger.debug("order findById, 参数 orderId: {}", orderId);
        Order order = orderDao.selectById(orderId);

        logger.debug("查询结果： {}", JSON.toJSONString(order, true));
        return order;
    }

    @Override
    public int deleteById(Long orderId, Long operatorId) {
        logger.debug("order 删除， 参数 orderId : {}", orderId);
        Order order = new Order();
        order.setId(orderId);
        order.setIsDel(DelFlagEnum.YES.getCode());
        order.setUpdateTime(new Date());
        order.setUpdateUserId(operatorId);
        int num = orderDao.updateById(order);

        logger.debug("将 {} 条 数据删除", num);
        return num;
    }

}
