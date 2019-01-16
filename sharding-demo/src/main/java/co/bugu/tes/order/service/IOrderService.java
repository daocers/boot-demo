package co.bugu.tes.order.service;

import co.bugu.tes.order.domain.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * service接口
 *
 * @author auto-coder
 * @create 2019-01-16 17:47
 */
public interface IOrderService {

    /**
     * 新增
     *
     * @param order
     * @return
     */
    long add(Order order);

    /**
     * 通过id更新
     *
     * @param order
     * @return
     */
    int updateById(Order order);

    /**
     * 条件查询
     *
     * @param order
     * @return
     */
    List<Order> findByCondition(Order order);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param order     查询条件
     * @return
     */
    List<Order> findByCondition(Integer pageNum, Integer pageSize, Order order);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param order     查询条件
     * @return
     */
    PageInfo<Order> findByConditionWithPage(Integer pageNum, Integer pageSize, Order order);

    /**
     * 通过id查询
     *
     * @param orderId
     * @return
     */
    Order findById(Long orderId);

    /**
     * 删除指定id的记录 软删除，设置删除标志
     *
     * @param orderId
     * @return
     */
    int deleteById(Long orderId, Long operatorId);

}
