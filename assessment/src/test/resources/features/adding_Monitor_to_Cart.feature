Feature: Add Monitor to Cart feature

  Scenario Outline: Adding an item in cart and verifying subtotal
    Given user is on Amazon Landing Page
    When user search "<item>" in search filed
    Then user select <resultIndex> product
    And user add "<item>" product to cart
    Then compare price of "<item>" in product page with cart page
    And compare sub total with product page

    Examples: 
      | item    | resultIndex |
      | Monitor |           1 |