package com.example.my_app;

import androidx.annotation.NonNull;

import com.example.my_app.helper.Parse;
import com.example.my_app.models.Product;
import com.example.my_app.services.DemoService;

import java.util.List;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "api_demo";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);

    MethodChannel methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);

    methodChannel.setMethodCallHandler(
      (call, result) -> {
        try {
          if (call.method.equals("fetchProducts")) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            DemoService service = retrofit.create(DemoService.class);

            Call<List<Product>> response = service.fetchAllProducts();

            response.enqueue(new Callback<List<Product>>() {
              @Override
              public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                result.success(Parse.productsToMap(response.body()));
              }

              @Override
              public void onFailure(Call<List<Product>> call, Throwable throwable) {

              }
            });
          } else {
            result.error("Error", "The invoked method does not exist", null);
          }
        } catch (Exception e) {
          result.error("UNAVAILABLE", e.getMessage(), null);
        }
      }
    );
  }
}
