
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

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
        //int quantity = 2;
        int price = calculatePrice();
        createOrderSummary(price);
    }


    /**
     * Calculates the price of the order.
     *
     *  is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        return (quantity * 5);
    }

    public void increment(View view) {
        //int quantity = 2;
        quantity++;
        displayQuantity(quantity);
        displayPrice(quantity * 5);
    }

    public void decrement(View view) {
        //int quantity = 2;
        quantity--;
        displayQuantity(quantity);
        displayPrice(quantity * 5);

    }
    public void createOrderSummary(int price){
        String name="Your name is "+ displayName()+ "\n";
        String whippedCreamString = "Add whipped cream? :" + displayBoolWipCream()+ "\n";
        String chocoString = "Add whipped cream? :" + displayBoolChoco()+ "\n";
        String quant = "Quantity: " + quantity + "\n";
        String priceMessage = "Total = $" + price + "\nThank You!\n" ;
        displayMessage(name + whippedCreamString+ chocoString+ quant+ priceMessage);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int displayQuantity) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(displayQuantity));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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