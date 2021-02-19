package com.Models.DAO;




import com.Models.Models.Order;
import com.Models.api.DAO.IOrderDAO;

import java.util.*;

public class OrderDAO implements IOrderDAO {

    private List<Order> orders;
    private Map<String, Comparator<Order>> sort;

    public OrderDAO( ) {
        orders = new ArrayList<>();
        this.init();
    }

    private void init(){
        this.sort = new HashMap<>();

        this.sort.put("ByCost", Comparator.comparing(Order::getTotalPrice));
        this.sort.put("ByDateOfExecution", Comparator.comparing(Order::getDateOfExecution));
        this.sort.put("ByStatus", Comparator.comparing(Order::getDateOfExecution));
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(this.orders);
    }

    @Override
    public boolean update(UUID id, Order item) {

        for(Order ord : this.orders){

            if(id.equals(ord.getUiId())) {
                ord = item;

                return true;
            }

        }
        return false;
    }

    public void delete(Order order){

        if(order != null)  this.orders.remove((order));


        }

    @Override
    public boolean delete(UUID id) {

        for(Order ord : this.orders){

            if(id.equals(ord.getUiId())) {
                this.orders.remove(ord);

            }

        }
        return false;
    }

    @Override
    public boolean addEntity(Order entity) {

        if(entity != null){

            this.orders.add(entity);

        return  true;
        }

        return false;
    }

    @Override
    public Order getEntity(UUID id) {

        for(Order ord : this.orders){

            if(id.equals(ord.getUiId())) {
               return ord;
            }
        }
        return null;
    }
}
