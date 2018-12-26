package co.bugu.apple.agree.service.impl;

import co.bugu.apple.agree.dao.AgreeDao;
import co.bugu.apple.agree.domain.Agree;
import co.bugu.apple.agree.service.IAgreeService;
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
public class AgreeServiceImpl implements IAgreeService {
    @Autowired
    AgreeDao agreeDao;

    private static Logger logger = LoggerFactory.getLogger(AgreeServiceImpl.class);

    private static String ORDER_BY = "update_time DESC";

    @Override
    public long add(Agree agree) {
        //todo 校验参数
        agree.setIsDel(1);

        Date now = new Date();
        agree.setCreateTime(now);
        agree.setUpdateTime(now);
        agree.setUpdateUserId(agree.getCreateUserId());
        agreeDao.insert(agree);
        return agree.getId();
    }

    @Override
    public int updateById(Agree agree) {
        Preconditions.checkNotNull(agree.getId(), "id不能为空");
        agree.setUpdateTime(new Date());
        agree.setUpdateUserId(agree.getCreateUserId());
        agree.setCreateUserId(null);
        return agreeDao.updateById(agree);
    }

    @Override
    public List<Agree> findByCondition(Agree agree) {
        PageHelper.orderBy(ORDER_BY);
        List<Agree> agrees = agreeDao.findByObject(agree);

        return agrees;
    }

    @Override
    public List<Agree> findByCondition(Integer pageNum, Integer pageSize, Agree agree) {
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Agree> agrees = agreeDao.findByObject(agree);

        return agrees;
    }

    @Override
    public PageInfo<Agree> findByConditionWithPage(Integer pageNum, Integer pageSize, Agree agree) {
        PageHelper.startPage(pageNum, pageSize, ORDER_BY);
        List<Agree> agrees = agreeDao.findByObject(agree);

        return new PageInfo<>(agrees);
    }

    @Override
    public Agree findById(Long agreeId) {
        logger.debug("agree findById, 参数 agreeId: {}", agreeId);
        Agree agree = agreeDao.selectById(agreeId);

        return agree;
    }

    @Override
    public int deleteById(Long agreeId, Long operatorId) {
        logger.debug("agree 删除， 参数 agreeId : {}", agreeId);
        Agree agree = new Agree();
        agree.setId(agreeId);
        agree.setIsDel(2);
        agree.setUpdateTime(new Date());
        agree.setUpdateUserId(operatorId);
        int num = agreeDao.updateById(agree);

        logger.debug("将 {} 条 数据删除", num);
        return num;
    }
}
