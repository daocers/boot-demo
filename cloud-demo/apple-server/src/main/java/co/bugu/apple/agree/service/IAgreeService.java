package co.bugu.apple.agree.service;

import co.bugu.apple.agree.domain.Agree;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * service接口
 *
 * @author auto-coder
 * @create 2018-07-16 16:30
 */
public interface IAgreeService {

    /**
     * 新增
     *
     * @param agree
     * @return
     */
    long add(Agree agree);

    /**
     * 通过id更新
     *
     * @param agree
     * @return
     */
    int updateById(Agree agree);

    /**
     * 条件查询
     *
     * @param agree
     * @return
     */
    List<Agree> findByCondition(Agree agree);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param agree     查询条件
     * @return
     */
    List<Agree> findByCondition(Integer pageNum, Integer pageSize, Agree agree);

    /**
     * 条件查询 分页
     *
     * @param pageNum  页码，从1 开始
     * @param pageSize 每页多少条
     * @param agree     查询条件
     * @return
     */
    PageInfo<Agree> findByConditionWithPage(Integer pageNum, Integer pageSize, Agree agree);

    /**
     * 通过id查询
     *
     * @param agreeId
     * @return
     */
    Agree findById(Long agreeId);

    /**
     * 删除指定id的记录 软删除，设置删除标志
     *
     * @param agreeId
     * @return
     */
    int deleteById(Long agreeId, Long operatorId);
}
