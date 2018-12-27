package co.bugu.apple.worker.service.impl;

import co.bugu.apple.worker.dao.WorkerDao;
import co.bugu.apple.worker.domain.Worker;
import co.bugu.apple.worker.service.IWorkerService;
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
 * @create 2018-07-16 16:30
 */
@Service
public class WorkerServiceImpl implements IWorkerService {
    @Autowired
    WorkerDao workerDao;

    private static Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    private static String ORDER_BY = "update_time DESC";

    @Override
    public long add(Worker worker) {

        Date now = new Date();
        worker.setCreateTime(now);
        worker.setUpdateTime(now);
        worker.setUpdateUserId(worker.getCreateUserId());
        workerDao.insert(worker);
        return worker.getId();
    }

    @Override
    public int updateById(Worker worker) {
        Preconditions.checkNotNull(worker.getId(), "id不能为空");
        worker.setUpdateTime(new Date());
        worker.setUpdateUserId(worker.getCreateUserId());
        worker.setCreateUserId(null);
        return workerDao.updateById(worker);
    }

    @Override
    public List<Worker> findByCondition(Worker worker) {
        PageHelper.orderBy(ORDER_BY);
        List<Worker> workers = workerDao.findByObject(worker);

        return workers;
    }

    @Override
    public List<Worker> findByCondition(Integer pageNum, Integer pageSize, Worker worker) {
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Worker> workers = workerDao.findByObject(worker);

        return workers;
    }

    @Override
    public PageInfo<Worker> findByConditionWithPage(Integer pageNum, Integer pageSize, Worker worker) {
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Worker> workers = workerDao.findByObject(worker);

        return new PageInfo<>(workers);
    }

    @Override
    public Worker findById(Long workerId) {
        logger.debug("worker findById, 参数 workerId: {}", workerId);
        Worker worker = workerDao.selectById(workerId);

        return worker;
    }

    @Override
    public int deleteById(Long workerId, Long operatorId) {
        logger.debug("worker 删除， 参数 workerId : {}", workerId);
        Worker worker = new Worker();
        worker.setId(workerId);
        worker.setUpdateTime(new Date());
        worker.setUpdateUserId(operatorId);
        int num = workerDao.updateById(worker);

        logger.debug("将 {} 条 数据删除", num);
        return num;
    }
}
