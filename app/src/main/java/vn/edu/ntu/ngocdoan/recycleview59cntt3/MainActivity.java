package vn.edu.ntu.ngocdoan.recycleview59cntt3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
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

public class MainActivity extends AppCompatActivity
{
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
    }

    private void addViews()
    {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));
        ICartController controller = (ICartController) getApplication();
        listProduct = controller.getListProduct();
        adapter = new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
    }

    // Lớp cài đặt cho việc hiển thị của một Product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName, txtPrice, txtDescription;
        ImageButton imBtnCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
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
            Toast.makeText(MainActivity.this,
                    "Bạn đưa sản phẩm: " + p.getName() + " vào giỏ hàng",
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
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
