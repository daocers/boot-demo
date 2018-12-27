package co.bugu.apple.worker.api;

import co.bugu.apple.worker.domain.Worker;
import co.bugu.apple.worker.dto.WorkerDto;
import co.bugu.apple.worker.service.IWorkerService;
import co.bugu.common.RespDto;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 提供接口
 *
 * @author auto-coder
 * @create 2018-07-16 16:30
 */
@RestController
class WorkerApi implements IWorkerApi {
    private static Logger logger = LoggerFactory.getLogger(WorkerApi.class);

    @Autowired
    IWorkerService workerService;

    @Override
    public RespDto<Long> saveWorker(@RequestBody WorkerDto workerDto) {
        Worker worker = new Worker();
        BeanUtils.copyProperties(workerDto, worker);
        Long id = worker.getId();
        if (id == null) {
            id = workerService.add(worker);
        } else {
            workerService.updateById(worker);
        }
        return RespDto.success(id);
    }

    @Override
    public RespDto<WorkerDto> findById(Long id) {
        Worker worker = workerService.findById(id);
        if (worker == null) {
            return RespDto.success(null);
        } else {
            WorkerDto dto = new WorkerDto();
            BeanUtils.copyProperties(worker, dto);
            return RespDto.success(dto);
        }
    }

    @Override
    public RespDto<PageInfo<WorkerDto>> findByConditionWithPage(Integer pageNum, Integer pageSize, @RequestBody WorkerDto workerDto) {
        Worker worker = new Worker();
        BeanUtils.copyProperties(workerDto, worker);
        PageInfo<Worker> pageInfo = workerService.findByConditionWithPage(pageNum, pageSize, worker);
        PageInfo<WorkerDto> res = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, res);
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<WorkerDto> dtos = Lists.transform(pageInfo.getList(), new Function<Worker, WorkerDto>() {
                @Nullable
                @Override
                public WorkerDto apply(@Nullable Worker worker) {
                    WorkerDto dto = new WorkerDto();
                    BeanUtils.copyProperties(worker, dto);
                    return dto;
                }
            });
            res.setList(dtos);
        }
        return RespDto.success(res);
    }

    @Override
    public RespDto<List<WorkerDto>> findByCondition(@RequestBody WorkerDto workerDto) {
        Worker worker = new Worker();
        BeanUtils.copyProperties(workerDto, worker);
        List<Worker> list = workerService.findByCondition(worker);
        if (CollectionUtils.isNotEmpty(list)) {
            List<WorkerDto> dtos = Lists.transform(list, new Function<Worker, WorkerDto>() {
                @Nullable
                @Override
                public WorkerDto apply(@Nullable Worker worker) {
                    WorkerDto dto = new WorkerDto();
                    BeanUtils.copyProperties(worker, dto);
                    return dto;
                }
            });
            return RespDto.success(dtos);
        }
        return RespDto.success();
    }

    @Override
    public RespDto<Boolean> delete(Long id, Long operatorId) {
        Worker worker = new Worker();
        worker.setId(id);
        workerService.deleteById(id, operatorId);
        return RespDto.success(true);
    }
}