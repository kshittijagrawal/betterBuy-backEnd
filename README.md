# BetterBuy Backend

Welcome to the BetterBuy Backend repository! This project is a microservices-based backend implementation for an e-commerce website called `BetterBuy`. It utilizes the Spring Boot framework and consists of five microservices, including user, merchant, product, gateway, and Solr search.

## Microservices Architecture

The BetterBuy Backend follows a microservices architecture to achieve scalability, maintainability, and flexibility. Here are the details of the five microservices:

1. User Microservice: Handles user-related functionality such as user registration, authentication, and user profile management. User operations include:
   - Register: Allows users to create a new account.
   - Login: Authenticates users based on the hashed data stored in the database.
   - Checkout: Completes the purchase by processing the payment and order confirmation.
   - View Cart: Retrieves the current user's shopping cart.
   - Delete Cart: Clears the user's entire shopping cart.
   - Delete from Cart: Removes a specific item from the user's shopping cart.
   - Add to Cart: Adds a product to the user's shopping cart.

2. Merchant Microservice: Manages merchant-related operations, including merchant registration, product listing, order management, and inventory control. Merchant operations include:
   - Login: Authenticates merchants based on the hashed data stored in the database.
   - Register: Allows merchants to create a new account.
   - Add Product: Enables merchants to add a new product to their inventory.
   - Update Product: Allows merchants to update the details of a specific product in their inventory.
   - Delete Product: Removes a product from the merchant's inventory.

3. Product Microservice: Responsible for product-related functionalities such as product catalog management, product details retrieval, and product reviews.

4. Gateway Microservice: Serves as the entry point to the BetterBuy system, routing requests from clients to the appropriate microservices.

5. Solr Search Microservice: Integrates with Apache Solr to provide a robust and efficient search functionality for BetterBuy. It allows users to search for products based on keywords, categories, or other relevant parameters.


## Prerequisites

Before running the BetterBuy Backend, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 11 or higher
- Maven
- Docker (optional for running microservices in containers)

## API Documentation

blah-blah

## Contact

If you have any questions, suggestions, or feedback, please feel free to reach out to the project maintainers at [email protected]

Happy coding!
