package main.Task3.Fourth.DAO;


import main.Task3.Fourth.Models.Order;
import main.Task3.Fourth.api.DAO.IOrderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDAO implements IOrderDAO {

    private List<Order> orders;

    public OrderDAO( List<Order> orders) {
        this.orders = orders;
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
