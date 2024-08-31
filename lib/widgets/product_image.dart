import 'package:flutter/material.dart';

class ProductImage extends StatelessWidget {
  final String? url;

  const ProductImage({super.key, required this.url});

  @override
  Widget build(BuildContext context) {
    if (url != null) {
      return ConstrainedBox(
        constraints: const BoxConstraints(
          minHeight: 60,
          minWidth: 60,
          maxHeight: 60,
          maxWidth: 60
        ),
        child: Image.network(url!),
      );
    }

    return const SizedBox();
  }
}