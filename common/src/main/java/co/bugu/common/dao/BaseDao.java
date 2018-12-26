package co.bugu.common.dao;

import java.util.List;

/**
 * mybatis 通用接口类
 *
 * @Author daocers
 * @Date 2018/12/26:14:09
 * @Description:
 */
public interface BaseDao<T> {
    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * insert，如果字段为空，略去空字段
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    T selectById(Long id);

    /**
     * 通过主键更新单条数据
     *
     * @param record
     * @return
     */
    int updateById(T record);

    /**
     * 通过条件查询
     *
     * @param record
     * @return
     */
    List<T> findByObject(T record);
}
