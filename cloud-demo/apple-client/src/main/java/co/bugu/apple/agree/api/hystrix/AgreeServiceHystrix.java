package co.bugu.apple.agree.api.hystrix;

import co.bugu.apple.agree.api.IAgreeApi;
import co.bugu.apple.agree.dto.AgreeDto;
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
public class AgreeServiceHystrix implements IAgreeApi {

    @Override
    public RespDto<PageInfo<AgreeDto>> findByConditionWithPage(Integer pageNum, Integer pageSize, AgreeDto agreeDto) {
        return RespDto.fail(-10000, "分页查询失败");
    }

    @Override
    public RespDto<List<AgreeDto>> findByCondition(AgreeDto agreeDto) {
        return RespDto.fail(-10000, "条件查询失败");
    }

    @Override
    public RespDto<Long> saveAgree(AgreeDto agree) {
        return RespDto.fail(-10000, "保存用户失败");
    }

    @Override
    public RespDto<AgreeDto> findById(Long id) {
        return RespDto.fail(-10000, "通过id查询失败");
    }

    @Override
    public RespDto<Boolean> delete(Long id, Long operatorId) {
        return RespDto.fail(-10000, "删除失败");
    }
}
