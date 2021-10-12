package takoyaki.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import takoyaki.model.DataDto;

import java.util.List;

@Mapper
@Repository
public interface DataMapper {
    DataDto selectCustomer(String phone);
    void completeCustomer(String phone);
    void insertCustomer(DataDto dataDto);
    void deleteCustomer(String phone);
    void changeMenu(DataDto dataDto);
    int selectCount();
    List<DataDto> selectAll();
    int selectWaitingCount(String phone);
}
