
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
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
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + displayName());
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
            totalPrice += quantity*1;
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
            CharSequence text = "You cannot order more than 100 cups of coffees!";
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
            CharSequence text = "You cannot order 0 cups of coffees!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        displayQuantity(quantity);
        //displayPrice(quantity * 5);

    }
    public String createOrderSummary(int price){
        String name="Your name is "+ displayName()+ "\n";
        String whippedCreamString = "Add whipped cream? :" + displayBoolWipCream()+ "\n";
        String chocoString = "Add whipped cream? :" + displayBoolChoco()+ "\n";
        String quant = "Quantity: " + quantity + "\n";
        String priceMessage = "Total = $" + price + "\nThank You!\n" ;
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
        Boolean checkedState = whippedCreamCB.isChecked();
        return checkedState;
    }
    private boolean displayBoolChoco() {
        CheckBox ChocolateCB = (CheckBox) findViewById(R.id.Chocolate_CB);
        Boolean checkedState = ChocolateCB.isChecked();
        return checkedState;
    }
    private String displayName(){
        EditText name =  (EditText) findViewById(R.id.name_edit_text);
        return name.getText().toString();
    }
}