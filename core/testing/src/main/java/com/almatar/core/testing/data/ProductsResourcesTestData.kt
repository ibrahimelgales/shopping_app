package com.almatar.core.testing.data

import com.almatar.core.data.model.Product


val productResourcesTestData: List<Product> = listOf(
    Product(
        id = 1,
        name = "Chicken",
        quantity = 5,
        description = "Chicken with onion",
        isProductBought = true,
    ),
    Product(
        id = 2,
        name = "Beef",
        quantity = 123,
        description = "Beef with mushroom",
        isProductBought = false,
    ),
    Product(
        id = 3,
        name = "Fol",
        quantity = 0,
        description = "fol with tomato",
        isProductBought = false,
    ),
    Product(
        id = 4,
        name = "Falafel",
        quantity = 12,
        description = "Falafel with tomato",
        isProductBought = false,
    ),

    )
