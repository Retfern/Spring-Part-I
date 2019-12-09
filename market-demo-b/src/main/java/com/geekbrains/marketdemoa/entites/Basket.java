package com.geekbrains.marketdemoa.entites;

import com.geekbrains.marketdemoa.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class Basket {

    private Map<Item, Integer> itemList;
    private ItemRepository itemRepository;

    @Autowired
    private void setProductRepository (ItemRepository itemRepository) {
        this.itemRepository=itemRepository;
    }

    @PostConstruct
    public void basketInit () {
        itemList = new HashMap<Item, Integer>();
    }

    public Map<Item, Integer> getItemList() {
        return itemList;
    }

    public void addItem (Long id, Integer sum) {
        if (!itemList.isEmpty()){
            boolean res = false;
            for (Item i: itemList.keySet()) {
                if(i.getId()==id) {
                    itemList.put(i, sum);
                    res = true;
                    break;
                }
            }
            if (!res) {
                itemList.put(itemRepository.getOne(id), sum);
            }
        }else {
            itemList.put(itemRepository.getOne(id), sum);
        }
        System.out.println("Size " + itemList.size());
    }

    public void deleteItem (Long id) {
        for (Item i: itemList.keySet()) {
            if(i.getId()==id) {
                itemList.remove(i);
            }
        }
        System.out.println("Basket " + itemList);
    }

    public Integer getPriceOrder () {
        Integer price = 0;
        for (Item i: itemList.keySet()) {
            price = price + i.getPrice()*itemList.get(i);
        }
        System.out.println("getPriceOrder " + price);
        return price;
    }
}
