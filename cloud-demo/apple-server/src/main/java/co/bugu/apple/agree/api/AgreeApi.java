package co.bugu.apple.agree.api;

import co.bugu.apple.agree.domain.Agree;
import co.bugu.apple.agree.dto.AgreeDto;
import co.bugu.apple.agree.service.IAgreeService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提供接口
 *
 * @author auto-coder
 * @create 2018-07-16 16:30
 */
@RestController
class AgreeApi implements IAgreeApi {
    private static Logger logger = LoggerFactory.getLogger(AgreeApi.class);

    @Autowired
    IAgreeService agreeService;

    @Override
    public RespDto<Long> saveAgree(@RequestBody AgreeDto agreeDto) {
        Agree agree = new Agree();
        BeanUtils.copyProperties(agreeDto, agree);
        Long id = agree.getId();
        if (id == null) {
            id = agreeService.add(agree);
        } else {
            agreeService.updateById(agree);
        }
        return RespDto.success(id);
    }

    @Override
    public RespDto<AgreeDto> findById(Long id) {
        Agree agree = agreeService.findById(id);
        if (agree == null) {
            return RespDto.success(null);
        } else {
            AgreeDto dto = new AgreeDto();
            BeanUtils.copyProperties(agree, dto);
            return RespDto.success(dto);
        }
    }

    @Override
    public RespDto<PageInfo<AgreeDto>> findByConditionWithPage(Integer pageNum, Integer pageSize, @RequestBody AgreeDto agreeDto) {
        Agree agree = new Agree();
        BeanUtils.copyProperties(agreeDto, agree);
        PageInfo<Agree> pageInfo = agreeService.findByConditionWithPage(pageNum, pageSize, agree);
        PageInfo<AgreeDto> res = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, res);
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<AgreeDto> dtos = Lists.transform(pageInfo.getList(), new Function<Agree, AgreeDto>() {
                @Nullable
                @Override
                public AgreeDto apply(@Nullable Agree agree) {
                    AgreeDto dto = new AgreeDto();
                    BeanUtils.copyProperties(agree, dto);
                    return dto;
                }
            });
            res.setList(dtos);
        }
        return RespDto.success(res);
    }

    @Override
    public RespDto<List<AgreeDto>> findByCondition(@RequestBody AgreeDto agreeDto) {
        Agree agree = new Agree();
        BeanUtils.copyProperties(agreeDto, agree);
        List<Agree> list = agreeService.findByCondition(agree);
        if (CollectionUtils.isNotEmpty(list)) {
            List<AgreeDto> dtos = Lists.transform(list, new Function<Agree, AgreeDto>() {
                @Nullable
                @Override
                public AgreeDto apply(@Nullable Agree agree) {
                    AgreeDto dto = new AgreeDto();
                    BeanUtils.copyProperties(agree, dto);
                    return dto;
                }
            });
            return RespDto.success(dtos);
        }
        return RespDto.success();
    }

    @Override
    public RespDto<Boolean> delete(Long id, Long operatorId) {
        Agree agree = new Agree();
        agree.setId(id);
        agreeService.deleteById(id, operatorId);
        return RespDto.success(true);
    }

    @RequestMapping("/set")
    public RespDto<Boolean> set(HttpServletRequest request) {
        request.getSession().setAttribute("name", "allen");
        return RespDto.success(true);
    }

    @RequestMapping("/testGet")
    public RespDto<String> test(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("name");
        logger.info("name:::{}", name);
        return RespDto.success(name);
    }
}