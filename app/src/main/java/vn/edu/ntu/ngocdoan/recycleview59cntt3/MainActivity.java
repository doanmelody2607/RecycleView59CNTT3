package vn.edu.ntu.ngocdoan.recycleview59cntt3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.ngocdoan.controller.ICartController;
import vn.edu.ntu.ngocdoan.model.Product;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    ImageView imvCart;
    TextView txtQuantity;
    ICartController controller;
    //ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addViews();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.notifyDataSetChanged();
        if (txtQuantity != null)
            txtQuantity.setText(controller.getCartQuantity());
    }

    private void addViews()
    {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));
        controller = (ICartController) getApplication();
        listProduct = controller.getListProduct();
        adapter = new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        MenuItem cartMenu = menu.findItem(R.id.mnu_cart);
        txtQuantity = cartMenu.getActionView().findViewById(R.id.txtQuantity);
        imvCart = cartMenu.getActionView().findViewById(R.id.imvCart);

        txtQuantity.setText(controller.getCartQuantity());
        imvCart.setOnClickListener(this);
        txtQuantity.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
//            case R.id.mnu_cart:
//                callShoppingCartActivity();
//                break;
            case R.id.mnu_close:
                finish();
        }
        return super.onOptionsItemSelected(item);

    }

    private void callShoppingCartActivity()
    {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        callShoppingCartActivity();
    }

    // Lớp cài đặt cho việc hiển thị của một Product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName, txtPrice, txtDescription;
        ImageButton imBtnCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDescription = this.itemView.findViewById(R.id.txtDescription);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            imBtnCart.setOnClickListener(this);
        }

        public void bind(Product p)
        {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDescription.setText(p.getDescription());
            imBtnCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }

        @Override
        public void onClick(View v)
        {
            if(!controller.addToShoppingCart(p))
                Toast.makeText(MainActivity.this,
                        "SP " + p.getName() + " đã có trong giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            else
                txtQuantity.setText(controller.getCartQuantity());
                Toast.makeText(MainActivity.this,
                        "Đã thêm sp " + p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
        }
    }

    // Lớp adapter kết nối RecycleView và dữ liệu
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        // Tạo một ViewHolder để hiển thị dữ liệu
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = getLayoutInflater();
            //Chuyển Layout đã thiết kế bằng xml thành một đối tượng View
            View view = layoutInflater.inflate(R.layout.product_item,
                    parent, false);
            return new ProductViewHolder(view);
        }

        // Kết nối một mục dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position)
        {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}
