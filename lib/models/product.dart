class Product {
  const Product({
    required this.id,
    required this.title,
    required this.price,
    required this.description,
    required this.category,
    required this.image,
  });

  final int id;
  final String title;
  final double price;
  final String description;
  final String category;
  final String image;

  factory Product.fromJson(Map<dynamic, dynamic> json) {
    return switch (json) {
      {
        'id': int id,
        'title': String title,
        'price': double price,
        'description': String description,
        'category': String category,
        'image': String image,
      } =>
        Product(
          id: id,
          title: title,
          price: price,
          description: description,
          category: category,
          image: image,
        ),
      _ => throw const FormatException('Failed to load product.'),
    };
  }

  @override
  String toString() {
    return "$id $title";
  }

}