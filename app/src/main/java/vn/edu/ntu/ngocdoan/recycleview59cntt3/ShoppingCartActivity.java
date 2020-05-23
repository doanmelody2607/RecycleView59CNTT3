package vn.edu.ntu.ngocdoan.recycleview59cntt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.ngocdoan.controller.ICartController;
import vn.edu.ntu.ngocdoan.model.Product;

public class ShoppingCartActivity extends AppCompatActivity
{
    TextView txtCartInfo, txtTotal;
    Button btnSubmit, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        addViews();
        addEvents();
    }

    private void addViews()
    {
        txtCartInfo = findViewById(R.id.txtCartInfo);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        txtTotal = findViewById(R.id.txtTotal);
        viewCartInfo();
    }

    private void addEvents() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICartController controller = (ICartController) ShoppingCartActivity.this.getApplication();
                controller.clearShoppingCart();
                txtCartInfo.setText("Không còn mặt hàng nào trong giỏ");
                txtTotal.setText("Tổng tiền: 0 vnd");
                Toast.makeText(ShoppingCartActivity.this,
                        "Đã xóa các mặt hàng trong giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void viewCartInfo()
    {
        int s = 0;
        ICartController controller = (ICartController) ShoppingCartActivity.this.getApplication();
        ArrayList<Product> listProducts = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for (Product p: listProducts)
        {
            builder.append(p.getName() + "\t\t\t" + p.getPrice() + " vnd\n");
            s += p.getPrice();
        }
        txtTotal.setText("Tổng tiền: " + new Integer(s).toString() + " vnd");
        if (builder.toString().length() > 0)
            txtCartInfo.setText(builder.toString());
        else
            txtCartInfo.setText("Không có mặt hàng nào trong giỏ hàng");

    }
}
