package co.bugu.apple.worker.api.hystrix;

import co.bugu.apple.worker.api.IWorkerApi;
import co.bugu.apple.worker.dto.WorkerDto;
import co.bugu.common.RespDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务断路器
 *
 * @author auto-coder
 * @create 2018-08-10 09:29
 */
@Component
public class WorkerServiceHystrix implements IWorkerApi {

    @Override
    public RespDto<PageInfo<WorkerDto>> findByConditionWithPage(Integer pageNum, Integer pageSize, WorkerDto workerDto) {
        return RespDto.fail(-10000, "分页查询失败");
    }

    @Override
    public RespDto<List<WorkerDto>> findByCondition(WorkerDto workerDto) {
        return RespDto.fail(-10000, "条件查询失败");
    }

    @Override
    public RespDto<Long> saveWorker(WorkerDto worker) {
        return RespDto.fail(-10000, "保存用户失败");
    }

    @Override
    public RespDto<WorkerDto> findById(Long id) {
        return RespDto.fail(-10000, "通过id查询失败");
    }

    @Override
    public RespDto<Boolean> delete(Long id, Long operatorId) {
        return RespDto.fail(-10000, "删除失败");
    }
}
