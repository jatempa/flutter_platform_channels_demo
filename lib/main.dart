import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:my_app/models/product.dart';
import 'package:my_app/widgets/product_image.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = MethodChannel('api_demo');
  List<Product> products = [];

  Future<List<Product>> _fetchProducts() async {
    try {
      final productList = await platform.invokeMethod('fetchProducts');
      for (var productItem in productList) {
        products.add(Product.fromJson(productItem));
      }
    } on PlatformException catch (e) {
      debugPrint("$e");
    }

    return products;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: FutureBuilder(
        future: _fetchProducts(),
        builder:(context, snapshot) {
          if (!snapshot.hasData) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }

          return _buildList(products);
        },
      ),
    );
  }

  Widget _buildList(List<Product>  products) {
    return ListView.builder(
      itemCount: products.length,
      itemBuilder: (context, index) {
        final product = products[index];
        return Card(
          elevation: 0,
          color: Colors.white,
          child: ListTile(
            leading: ProductImage(url: product.image),
            title: Text(product.title),
            subtitle: Text(product.category),
            trailing: Text(product.price.toStringAsFixed(2)),
          ),
        );
      },
    );
  }
}
