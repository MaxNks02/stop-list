package uz.davrbank.stoplist.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.davrbank.stoplist.dao.dto.StopListDto;
import uz.davrbank.stoplist.dao.model.StopListEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StopListMapper extends BaseMapper<StopListEntity, StopListDto>{
}
