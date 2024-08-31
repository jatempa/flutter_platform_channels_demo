package com.example.my_app.helper;

import com.example.my_app.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parse {

    public static List<HashMap<String, Object>> productsToMap(List<Product> productList) {
        List<HashMap<String, Object>> products = new ArrayList<>();

        for (Product product: productList) {
            HashMap<String, Object> productMap = new HashMap<String, Object>();
            productMap.put("id", product.getId());
            productMap.put("title", product.getTitle());
            productMap.put("price", product.getPrice());
            productMap.put("description", product.getDescription());
            productMap.put("category", product.getCategory());
            productMap.put("image", product.getImage());
            products.add(productMap);
        }

        return products;
    }
}
