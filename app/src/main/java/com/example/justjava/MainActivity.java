package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberOfCoffees=0;
    int p=0;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText edit =  (EditText) findViewById(R.id.name_field);
        String value=edit.getText().toString();

        CheckBox chocklate = (CheckBox)findViewById(R.id.chocklate);
        boolean haschocklate=chocklate.isChecked();
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean haswhippedcream=whippedCreamCheckBox.isChecked();
        display(numberOfCoffees);
        if(haschocklate && haswhippedcream)
        {
            p=8;

        }

        else if(haswhippedcream)
        {
            p=7;
        }
        else if(haschocklate)
        {
            p=6;
        }
        else
        {
            p=5;
        }

        //displayPrice(numberOfCoffees*5);
        String priceMessage="NAME:-"+value+"\nhas whipped cream?"+haswhippedcream+"\nhas chocklate?"+haschocklate+"\nTOTAL:- $"+ numberOfCoffees*p + "\nthank you!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }
    public void increment(View view) {

        numberOfCoffees=numberOfCoffees+1;
        display(numberOfCoffees);

    }
    public void decrement(View view) {
        if(numberOfCoffees<=0)
        {
            numberOfCoffees=0;
        }
        else
        numberOfCoffees=numberOfCoffees-1;
        display(numberOfCoffees);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}