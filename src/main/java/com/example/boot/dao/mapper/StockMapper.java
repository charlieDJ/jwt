package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.model.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author dengjia
 * @date 2019/10/17 14:36
 */
@Repository
public interface StockMapper extends BaseMapper<Stock> {

    /**
     * 乐观锁更新
     *
     * @param stock    老库存，持有version标识
     * @param newStock 即将更新的库存
     * @return 不等于0，说明更新成功
     */
    @Update("update `stock` set sale = #{newStock.sale}, `version` = #{newStock.version} where id = #{stock.id} and `version` = #{stock.version}")
    int saleStockOptimistic(@Param("stock") Stock stock, @Param("newStock") Stock newStock);

    /**
     * 悲观锁查询
     * @param sid 库存主键
     * @return
     */
    @Select("select * from stock where id = #{sid} for update")
    Optional<Stock> getByIdPessimistic(@Param("sid") int sid);
}
