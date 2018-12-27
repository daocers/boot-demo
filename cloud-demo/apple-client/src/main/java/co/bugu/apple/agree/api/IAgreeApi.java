package co.bugu.apple.agree.api;

import co.bugu.apple.agree.api.hystrix.AgreeServiceHystrix;
import co.bugu.apple.agree.dto.AgreeDto;
import co.bugu.common.RespDto;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 接口
 *
 * @author auto-coder
 * @create 2018-07-16 16:30
 */
@FeignClient(value = "apple-server", fallback = AgreeServiceHystrix.class)
public interface IAgreeApi {

    /**
     * 保存（更新和新增）
     *
     * @param agree
     * @return 保存成功，返回数据库id，保存失败，返回失败信息
     * @author auto-coder
     * @date 2018-07-16 16:30
     */
    @RequestMapping(value = "/apple/agree/api/v1/save", method = RequestMethod.POST)
    RespDto<Long> saveAgree(@RequestBody AgreeDto agree);


    /**
     * 获取详情
     *
     * @param id
     * @return com.woao.common.RespDto<com.woao.tes.agree.domain.Agree>
     * @author auto-coder
     * @date 2018-07-16 16:30
     */
    @RequestMapping(value = "/apple/agree/api/v1/findById")
    RespDto<AgreeDto> findById(@RequestParam("id") Long id);


    /**
     * 条件查询,返回分页信息
     *
     * @param pageNum  第几页，从1开始
     * @param pageSize 每页展示多少
     * @param agreeDto  查询条件
     * @return
     * @author auto-coder
     * @date 2018-07-16 16:30
     */
    @RequestMapping(value = "/apple/agree/api/v1/findByConditionWithPage")
    RespDto<PageInfo<AgreeDto>> findByConditionWithPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody AgreeDto agreeDto);

    /**
     * 条件查询，查询全部
     *
     * @param agreeDto 查询条件
     * @return
     * @author auto-coder
     * @date 2018-07-16 16:30
     */
    @RequestMapping(value = "/apple/agree/api/v1/findByCondition")
    RespDto<List<AgreeDto>> findByCondition(@RequestBody AgreeDto agreeDto);


    /**
     * 删除，软删除，更新数据库删除标志
     *
     * @param id
     * @return
     * @author auto-coder
     * @date 2018-07-16 16:30
     */
    @RequestMapping(value = "/apple/agree/api/v1/delete", method = RequestMethod.POST)
    RespDto<Boolean> delete(@RequestParam("id") Long id, @RequestParam("operatorId") Long operatorId);

}
