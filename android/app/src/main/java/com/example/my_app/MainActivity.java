package com.example.my_app;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "custom_counter";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);

    MethodChannel methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);

    methodChannel.setMethodCallHandler(
      (call, result) -> {
        try {
          if (call.method.equals("incrementCounter")) {
            int counter = call.argument("counter");
            counter++;
            result.success(counter);
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
