package takoyaki.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import takoyaki.model.DataDto;

@Mapper
@Repository
public interface DataMapper {
    DataDto selectCustomer(DataDto dataDto);
    void completeCustomer(DataDto dataDto);
    void insertCustomer(DataDto dataDto);
    void deleteCustomer(DataDto dataDto);
    void changeMenu(DataDto dataDto);
}
