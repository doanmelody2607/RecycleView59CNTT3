package vn.edu.ntu.ngocdoan.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.ngocdoan.model.Product;

public class CartController extends Application implements ICartController
{
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> shoppingCart = new ArrayList<>();

    public CartController()
    {
        listProduct.add(new Product("Xoài cát", 60000, "Xoài cát Phú Yên F100"));
        listProduct.add(new Product("Khoai lang", 45000, "Khoai lang tím giống Nhật"));
        listProduct.add(new Product("Me thái", 65000, "Me Thái Lan nhập khẩu"));
        listProduct.add(new Product("Ổi", 60000, "Ổi đào Ninh Thuận F10"));
        listProduct.add(new Product("Mận tím", 50000, "Mận tím Tây Bắc"));
        listProduct.add(new Product("Táo đỏ", 60000, "Táo đỏ Quảng Bình F10"));
        listProduct.add(new Product("Sầu riêng", 90000, "Sầu riêng Khánh Sơn loại 1"));
    }

    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public ArrayList<Product> getShoppingCart()
    {
        return shoppingCart;
    }


    @Override
    public boolean addToShoppingCart(Product p)
    {
        if (!shoppingCart.contains(p))
        {
            shoppingCart.add(p);
            return true;
        }
        return false;
    }

    @Override
    public void clearShoppingCart()
    {
        shoppingCart.clear();
    }
}
