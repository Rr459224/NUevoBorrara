package com.example.listacompra;

import java.util.ArrayList;
import java.util.List;


public class ProductContent {

    //List of all the bikes to be listed in the RecyclerView
    public static List<String> ITEMS = new ArrayList<String>();


    public static class Product {

        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Product(String name) {

            this.name = name;


        }

        @Override
        public String toString() {
            return name;
        }
    }
}