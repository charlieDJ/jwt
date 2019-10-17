package com.example.boot.service.impl;

import com.example.boot.dao.mapper.OrderMapper;
import com.example.boot.dao.mapper.StockMapper;
import com.example.boot.dao.model.Order;
import com.example.boot.dao.model.Stock;
import com.example.boot.exception.CustomException;
import com.example.boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author dengjia
 * @date 2019/10/17 14:30
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createWrongOrder(int sid) {
        //乐观锁
       /* Stock stock = checkStock(sid);
        saleStockOptimistic(stock);*/


        // 悲观锁
        Stock stock = checkStockPessimistic(sid);
        saleStockPessimistic(stock);

        // 生成订单。可以返回成功，异步生成订单，让客户端轮询订单结果
        int id = createOrder(stock);
        return id;
    }

    private Stock checkStockPessimistic(int sid) {
        Optional<Stock> stockOpt = stockMapper.getByIdPessimistic(sid);
        if (!stockOpt.isPresent()) {
            throw new CustomException("未查询到库存");
        }
        Stock stock = stockOpt.get();
        if (stock.getSale() >= stock.getCount()) {
            throw new CustomException("库存不足");
        }
        return stock;
    }

    /**
     * 悲观锁
     *
     * @param stock
     */
    private void saleStockPessimistic(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        int count = stockMapper.updateByPrimaryKeySelective(stock);
        if (count == 0) {
            throw new CustomException("悲观锁-并发更新库存失败");
        }
    }

    /**
     * 乐观锁扣库存
     *
     * @param stock
     */
    private void saleStockOptimistic(Stock stock) {
        Stock newStock = new Stock();
        newStock.setSale(stock.getSale() + 1);
        newStock.setVersion(stock.getVersion() + 1);
        int count = stockMapper.saleStockOptimistic(stock, newStock);
        if (count == 0) {
            throw new CustomException("乐观锁-并发更新库存失败");
        }
    }

    private int createOrder(Stock stock) {
        Order order = new Order();
        order.setSid(stock.getId())
                .setName(stock.getName());
        orderMapper.insertSelective(order);
        return order.getId();
    }

    private void saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stockMapper.updateByPrimaryKeySelective(stock);
    }

    private Stock checkStock(int sid) {
        Stock stock = stockMapper.selectByPrimaryKey(sid);
        if (stock.getSale() >= stock.getCount()) {
            throw new CustomException("库存不足");
        }
        return stock;
    }


}
