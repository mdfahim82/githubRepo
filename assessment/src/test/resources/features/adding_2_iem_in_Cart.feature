Feature: Add to Cart feature

  Scenario Outline: Adding an item in cart and verifying subtotal
    Given user is on Amazon Landing Page
    When user search "<item1>" in search filed
    Then user select <resultIndex> product
    And user add "<item1>" product to cart
    Then compare price of "<item1>" in product page with cart page
    When user search "<item2>" in search filed
    Then user select <resultIndex> product
    And user add "<item2>" product to cart
    Then compare price of "<item2>" in product page with cart page
    Then compare price all items in product page with cart page
    And compare sub total with product page

    Examples: 
      | item1				|item2    | resultIndex |
      | Headphones	|Keyboard | 1						|