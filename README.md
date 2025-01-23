# Nile-GUI
## Overview
This is a java application that is supposed to emulate an online shopping cart using Eclipse and the Java Swing library for the GUI. <br>
## Home Page
This is the clean home page with no values inputted yet. At the top you have all the values inputted and outputted for the indivual item you're looking for. The middle section is your "Shopping Cart" where it lists all the items you've successfully added. And at the bottom there are six buttons, each of which we'll discuss the specifics of later.<br><br>
At the top you have to input your desired item ID followed by the quantity of the item. Before inputting these values the "Item Details", "Price", and "Discount" text labels are disabled.<br><br>
Also at this point only the "Find Item", "Empty Cart" and "Exit" buttons are enabled. The "Empty Cart" buttons clears any item information in your cart, and the "Exit" button closes the program. <br><br>
<img src = "MainPage.png" width=500> <br><br>
## Find Item
The "Find Item" button is used to find an item using the type ID to cross reference it with the stock database's item IDs and find a match. <br>
If you try and find a valid item but the Item ID you gave does not match any from the list, then it will output an error.<br><br>
<img src = "ItemNotFound.png" width=500> <br><br>
If you try to find a valid item but the given amount is higher than what is in stock, it will output an error.<br><br>
<img src = "LowStock.png" width=500> <br><br>
If you try and find a valid item but the item is not in stock, it will output an error. <br><br>
<img src = "NoStock.png" width=500> <br><br>
This is an example of a successfully found item. When an item is found, depending on the amount of product you want, it will display the total price of your order as well as apply to it a percentage discount (Although it is not picutured here).  Also the "Add Item" button is enabled, which allows you to add this item to your cart. <br><br>
<img src = "FindItem.png" width=500> <br><br>
## Item Added
When the "Add Item" is clicked, the item summary will appear in the next open slot of the shopping cart section. You are allowed to have five items total in your cart. <br><br>
<img src = "ItemAdded.png" width=500> <br><br>
## Cart
If you click the "View Cart" button a popup containing a more detailed and complete version of the shopping cart will show up.<br><br>
<img src = "cart.png" width=500> <br><br>
## Receipt
Finally when you're ready to check out you can press the 'Check Out" button which will compile all the purchases you made into a comprehensive reciept. It contains your items as well as the checkout time, number of items, and the total price with all the discounts and taxes applied. <br><br>
<img src = "Receipt.png" width=500> <br><br>
