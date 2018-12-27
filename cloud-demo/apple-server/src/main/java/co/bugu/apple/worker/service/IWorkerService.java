package co.bugu.apple.worker.service;

import co.bugu.apple.worker.domain.Worker;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * service接口
 *
 * @author auto-coder
 * @create 2018-07-16 16:30
 */
public interface IWorkerService {

    /**
     * 新增
     *
     * @param worker
     * @return
     */
    long add(Worker worker);

    /**
     * 通过id更新
     *
     * @param worker
     * @return
     */
    int updateById(Worker worker);

    /**
     * 条件查询
     *
     * @param worker
     * @return
     */
    List<Worker> findByCondition(Worker worker);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param worker     查询条件
     * @return
     */
    List<Worker> findByCondition(Integer pageNum, Integer pageSize, Worker worker);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param worker     查询条件
     * @return
     */
    PageInfo<Worker> findByConditionWithPage(Integer pageNum, Integer pageSize, Worker worker);

    /**
     * 通过id查询
     *
     * @param workerId
     * @return
     */
    Worker findById(Long workerId);

    /**
     * 删除指定id的记录 软删除，设置删除标志
     *
     * @param workerId
     * @return
     */
    int deleteById(Long workerId, Long operatorId);
}
