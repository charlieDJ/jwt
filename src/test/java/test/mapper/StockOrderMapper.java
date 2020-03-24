package mapper;

import model.StockOrder;
import model.StockOrderExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StockOrderMapper extends Mapper<StockOrder> {
    long countByExample(StockOrderExample example);

    int deleteByExample(StockOrderExample example);

    List<StockOrder> selectByExample(StockOrderExample example);

    int updateByExampleSelective(@Param("record") StockOrder record, @Param("example") StockOrderExample example);

    int updateByExample(@Param("record") StockOrder record, @Param("example") StockOrderExample example);
}