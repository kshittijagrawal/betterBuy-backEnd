# BetterBuy Backend API Documentation

## User Microservice

### Register User

- **Endpoint:** `POST /user/register`
- **Description:** Registers a new user account.
- **Request Body:**
  ```json
  {
    "emailId": "string",
    "firstName": "string",
    "lastName": "string",
    "password": "string",
    "dob": "string",
    "address": "string",
    "mobile": "string"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Login User

- **Endpoint:** `POST /user/login`
- **Description:** Authenticates a user.
- **Request Body:**
  ```json
  {
  "emailId": "string",
  "password": "string"  
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Add to Cart

- **Endpoint:** `POST /user/addToCart/{userId}`
- **Description:** Adds a product to the user's shopping cart.
- **Request Body:**
  ```json
  {
  "productId": "string",
  "merchantId": "string",
  "itemCount": "integer"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

  
### View Cart

- **Endpoint:** `GET /user/viewCart/{userId}`
- **Description:** Retrieves the user's shopping cart.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "CartList": [
    {
      "merchantId": "string",
      "productId": "string",
      "image": "string",
      "productName": "string",
      "merchantName": "string",
      "itemCount": "integer",
      "price": "number"
    }
            ]
  }

### Checkout

- **Endpoint:** `POST /user/checkOut/{userId}`
- **Description:** Completes the purchase by processing the payment and order confirmation.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Delete Cart

- **Endpoint:** `DELETE /user/deleteCart/{userId}`
- **Description:** Clears the user's shopping cart.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Remove from Cart

- **Endpoint:** `DELETE /user/removeFromCart/{userId}`
- **Description:** Removes a specific item from the user's shopping cart.
- **Request Body:**
  ```json
  {
  "productId": "string",
  "merchantId": "string",
  "itemCount": "integer"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### View Purchase History

- **Endpoint:** `GET /user/viewHistory/{userId}`
- **Description:** Retrieves the user's purchase history.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "HistoryList": [
    {
      "DateOfPurchase": "string",
      "merchantId": "string",
      "productId": "string",
      "image": "string",
      "productName": "string",
      "merchantName": "string",
      "itemCount": "integer",
      "price": "number"
    }
                ]
  }

### Delete Item from Cart

- **Endpoint:** `DELETE /user/deleteFromCart/{productId}/{merchantId}/{userId}`
- **Description:** Deletes a specific item from the user's cart.
- **Request Body:**
  ```json
  {
  "productId": "string",
  "merchantId": "string",
  "itemCount": "integer"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }


## Merchant Microservice

### Insert or Update Merchant

- **Endpoint:** `POST /merchant/insertorupdate`
- **Description:** Inserts or updates a merchant.
- **Request Body:**
  ```json
  {
    "merchantEmail": "string",
    "merchantName": "string",
    "merchantAddress": "string",
    "merchantContactNo": "string",
    "merchantUserName": "string",
    "merchantPassword": "string"
  }
- **Response Body:**
  ```sh
  Boolean true / false

### Delete Merchant

- **Endpoint:** `DELETE /merchant/deletemerchant/{merchantId}`
- **Description:** Deletes a merchant.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```sh
  String "deleted successfully" "unsuccessful deletion"

### Merchant Login

- **Endpoint:** `POST /merchant/login`
- **Description:** Authenticates a merchant.
- **Request Body:**
  ```json
  {
  "merchantId": "string",
  "merchantPassword": "string"
  }
- **Response Body:**
  ```sh
  Boolean true / false

### Get Merchant Name

- **Endpoint:** `GET /merchant/getMerchantName/{merchantId}`
- **Description:** Retrieves the name of a merchant.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "merchantName": "string"
  }


## Product Microservice

### Find Product

- **Endpoint:** `GET /product/findOneProduct/{productId}`
- **Description:** Retrieves details of a single product.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "productId": "string",
  "productName": "string",
  "brand": "string",
  "category": "string",
  "description": "string",
  "productUsp": "string",
  "merchants": [
    {
      "merchantId": "string",
      "stock": "integer",
      "stockSold": "integer",
      "productReview": "string",
      "image": "string",
      "price": "number"
    }
              ]
  }

### Add Merchant Product

- **Endpoint:** `POST /product/addmerchantproduct/{productId}`
- **Description:** Adds a product to a merchant's inventory.
- **Request Body:**
  ```json
  {
  "merchantId": "string",
  "stock": "integer",
  "stockSold": "integer",
  "image": "string",
  "price": "number"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Delete Product

- **Endpoint:** `DELETE /product/delProduct/{productId}/{merchantId}`
- **Description:** Deletes a product.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Get Products by Category

- **Endpoint:** `GET /product/getProductsByCategory/{category}`
- **Description:** Retrieves products based on a category.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "SearchResultsList": [
    {
      "productId": "string",
      "productName": "string",
      "brand": "string",
      "category": "string",
      "description": "string",
      "image": "string",
      "productUsp": "string",
      "merchantId": "string",
      "stock": "integer",
      "stocksold": "integer",
      "price": "number",
      "productReview": "string"
    }
                      ]
  }

### Add or Update Product

- **Endpoint:** `POST /product/addOrUpdate`
- **Description:** Adds or updates a product.
- **Request Body:**
  ```json
  {
  "productId": "string",
  "productName": "string",
  "brand": "string",
  "category": "string",
  "description": "string",
  "productUsp": "string",
  "merchants": [
    {
      "merchantId": "string",
      "stock": "integer",
      "stocksold": "integer",
      "productReview": "string",
      "image": "string",
      "price": "number"
    }
              ]
  }
- **Response Body:**
  ```sh
  Boolean true / false

### Edit Merchant Product

- **Endpoint:** `POST /product/editmerchantproduct/{productId}`
- **Description:** Edits a product in a merchant's inventory.
- **Request Body:**
  ```json
  {
  "merchantId": "string",
  "stock": "integer",
  "stocksold": "integer",
  "image": "string",
  "price": "number"
  }
- **Response Body:**
  ```json
  {
  "isValid": true
  }

### Get Products

- **Endpoint:** `GET /product/getProducts`
- **Description:** Retrieves all products.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "SearchResultsList": [
    {
      "productId": "string",
      "productName": "string",
      "brand": "string",
      "category": "string",
      "description": "string",
      "image": "string",
      "productUsp": "string",
      "merchantId": "string",
      "stock": "integer",
      "stocksold": "integer",
      "price": "number",
      "productReview": "string"
    }
                      ]
  }

### Find Merchant Product

- **Endpoint:** `GET /product/findMerchantProduct/{productId}/{merchantId}`
- **Description:** Retrieves details of a specific product for a merchant.
- **Request Body:**
  ```json
  {}
- **Response Body:**
  ```json
  {
  "productId": "string",
  "productName": "string",
  "brand": "string",
  "category": "string",
  "description": "string",
  "image": "string",
  "productUsp": "string",
  "merchantId": "string",
  "stock": "integer",
  "stocksold": "integer",
  "price": "number",
  "productReview": "string"
  }


__*Please note that the above API endpoints are placeholders and may need to be adjusted according to your specific implementation.*__
