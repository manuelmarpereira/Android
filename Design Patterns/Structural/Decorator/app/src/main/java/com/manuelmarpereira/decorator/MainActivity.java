package com.manuelmarpereira.decorator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnPizza, btnSandwich, btnHamburger, btnDessert, btnChips, btnDrink;
    TextView txtMenuName, txtMenuPrice, txtExtraName, txtTotal;
    Meal meal;

    //default prices for menus
    Double pizzaPrice=4.0, hamburgerPrice=5.0, sandwichPrice=3.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtExtraName = (TextView) findViewById(R.id.txt_extra_name);
        txtMenuName = (TextView) findViewById(R.id.txt_menu_name);
        txtMenuPrice = (TextView) findViewById(R.id.txt_menu_price);
        txtTotal = (TextView) findViewById(R.id.txt_total);


        btnHamburger = (Button) findViewById(R.id.btn_hamburger);
        btnHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkMenu(btnHamburger);
                meal = new Hamburger("Hamburger",hamburgerPrice);
                updateInvoiceMenu();

            }
        });

        btnPizza = (Button) findViewById(R.id.btn_pizza);
        btnPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkMenu(btnPizza);
                meal = new Pizza("Pizza",pizzaPrice);
                updateInvoiceMenu();

            }
        });

        btnSandwich= (Button) findViewById(R.id.btn_sandwich);
        btnSandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkMenu(btnSandwich);
                meal = new Sandwich("Sandwich",sandwichPrice);
                updateInvoiceMenu();

            }
        });


        btnDrink= (Button) findViewById(R.id.btn_drink);
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meal = new Drink(meal);
                updateInvoiceExtra();

            }
        });


        btnDessert= (Button) findViewById(R.id.btn_dessert);
        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meal = new Dessert(meal);
                updateInvoiceExtra();


            }
        });

        btnChips= (Button) findViewById(R.id.btn_chips);
        btnChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meal = new Chips(meal);
                updateInvoiceExtra();

            }
        });

    }


    private void checkMenu(Button btnMenu){

        unCheckAll();
        btnMenu.setBackgroundColor(Color.GRAY);

    }

    private void unCheckAll(){
        //reset menu buttons background color
        btnPizza.setBackgroundResource(android.R.drawable.btn_default);
        btnSandwich.setBackgroundResource(android.R.drawable.btn_default);
        btnHamburger.setBackgroundResource(android.R.drawable.btn_default);

    }

    private void updateInvoiceMenu(){

        txtMenuName.setText(meal.getName());
        txtMenuPrice.setText(meal.price().toString()+ " Euros");
        txtTotal.setText(meal.price().toString());

    }

    private void updateInvoiceExtra(){

        txtExtraName.setText(meal.getName());
        txtTotal.setText(meal.price().toString()+" Euros");
    }
}

