

package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int price = calculatePrice();
        //createOrderSummary(price);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.submitOrder_subject, displayName()));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(calculatePrice()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * Calculates the price of the order.
     *
     *  is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int totalPrice=0;
        totalPrice += quantity*5;
        if(displayBoolWipCream()){
            totalPrice += quantity;
        }
        if(displayBoolChoco()){
            totalPrice +=quantity*2;
        }
        return totalPrice;
    }

    public void increment(View view) {
        if (quantity<100) {
            quantity++;
        }
        else if(quantity>= 100){
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.hundred_cups);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
            displayQuantity(quantity);
        //displayPrice(quantity * 5);
    }

    public void decrement(View view) {
        if (quantity>0) {
            quantity--;
        }
        else if(quantity<= 100){
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.zero_cups);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        displayQuantity(quantity);
        //displayPrice(quantity * 5);

    }
    public String createOrderSummary(int price){
        String name=getString(R.string.Order_summary_name, displayName())+ "\n";
        String whippedCreamString = getString(R.string.add_whipped_cream)  + displayBoolWipCream()+ "\n";
        String chocoString = getString(R.string.add_chocolate) + displayBoolChoco()+ "\n";
        String quant = getString(R.string.summary_quantity) + quantity + "\n";
        String priceMessage = getString(R.string.total) + price + "\n"+ getString(R.string.thank_you)+"\n" ;
        return name + whippedCreamString+ chocoString+ quant+ priceMessage;

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * this code is for whipped cream checkbox
     */
    private boolean displayBoolWipCream() {
        CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.WhippedCream_CB);
        return whippedCreamCB.isChecked();
    }
    private boolean displayBoolChoco() {
        CheckBox ChocolateCB = (CheckBox) findViewById(R.id.Chocolate_CB);
        return  ChocolateCB.isChecked();
    }
    private String displayName(){
        EditText name =  (EditText) findViewById(R.id.name_edit_text);
        return name.getText().toString();
    }
}