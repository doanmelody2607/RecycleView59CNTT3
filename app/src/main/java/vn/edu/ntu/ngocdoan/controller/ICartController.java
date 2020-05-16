package vn.edu.ntu.ngocdoan.controller;

import java.util.ArrayList;

import vn.edu.ntu.ngocdoan.model.Product;

public interface ICartController
{
    public ArrayList<Product> getListProduct();
    public boolean addToShoppingCart(Product p);
    public ArrayList<Product> getShoppingCart();
    public void clearShoppingCart();
}
