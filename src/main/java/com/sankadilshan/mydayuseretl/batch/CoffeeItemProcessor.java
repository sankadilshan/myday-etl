package com.sankadilshan.mydayuseretl.batch;

import com.sankadilshan.mydayuseretl.dto.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {


    @Override
    public Coffee process(final Coffee coffee) throws Exception {
        final String brand = coffee.getBrand().toUpperCase();

        final Coffee transformedCoffeeItem = new Coffee(brand, coffee.getOrigin(),coffee.getCharacteristic());
        log.info("Converting ( "+ coffee +" ) into ( " + transformedCoffeeItem + " )");
        return transformedCoffeeItem;
    }
}
