package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.dao.ItemPriceDAO;
import com.aaxis.microservice.training.demo1.domain.ItemPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class ItemPriceService {

    @Autowired
    private ItemPriceDAO mItemPriceDAO;

    public void initData(){
        List<String> ids = mItemPriceDAO.findAllProductIds();

        for(String productId : ids){
            ItemPrice itemPrice = new ItemPrice();
            itemPrice.setId(productId);
            itemPrice.setPrice(new BigDecimal(new Random().nextDouble() * 1000).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
            mItemPriceDAO.save(itemPrice);
        }
    }

    public ItemPrice findItemPriceById(String pProductId){
        Optional<ItemPrice> optionalItemPrice = mItemPriceDAO.findById(pProductId);
        if(optionalItemPrice.isPresent()){
            return optionalItemPrice.get();
        }
        return null;
    }
}
