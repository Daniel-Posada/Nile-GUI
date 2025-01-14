/* Name: Daniel Posada
Course: CNT 4714 – Fall 2024
Assignment title: Project 1 – Event-driven Enterprise Simulation
Date: Sunday September 6, 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NileGUI extends Frame {
	int number = 1;
	double total = 0;
	double priceNum = 0;
	ArrayList<String> cart = new ArrayList<String>();
	public NileGUI() throws IOException {
		Button find=new Button("Find Item");
		Button add=new Button("Add Item");
		Button check=new Button("Check Out");
		Button view=new Button("View Cart");
		Button empty=new Button("Empty Cart");
		Button exit=new Button("Exit");
		
		Label id = new Label("Item ID:");   
		Label quantity = new Label("Item Quantity:");  
		Label details = new Label("Item Details:"); 
		Label subtotal = new Label("Order Subtotal:");
		Label discount = new Label("Discount:");
		Label price = new Label("Price:");
		
		TextField idField = new TextField();
		TextField quantityField = new TextField();
		TextField detailsField = new TextField();
		TextField subtotalField = new TextField();
		TextField discountField = new TextField();
		TextField priceField = new TextField();
		
		TextField[] cartContent = new TextField[5];
        
     //Creates Screen
        setLayout(null);
      	setVisible(true);
      	setTitle("Nile.com | Fall 2023"); 
      	setSize(510,590);
      	setBackground(Color.PINK);
      	
     //Places Title
      	Label title = new Label("- Item 01 -");
        title.setFont(new Font("Futura", Font.PLAIN, 30)); 
        title.setForeground(new Color(255,255,255));
        title.setBounds(190, 40, 200, 30);
        add(title);	
        
    //Places Cart Contents label
        Label contents = new Label("-------------------  Shopping cart has 0 item(s)  -------------------");
        contents.setFont(new Font("Futura", Font.PLAIN, 20)); 
        contents.setForeground(new Color(255,255,255));
        contents.setBounds(40, 220, 500, 30);
        add(contents);	
        
   //Places Divider
        Label divider = new Label("-----------------------------------------------------------------------------------------------------------");
        divider.setFont(new Font("Futura", Font.PLAIN, 20)); 
        divider.setForeground(new Color(255,255,255));
        divider.setBounds(40, 440, 500, 30);
        add(divider);	
      	
      //Styles everything
        style(find);
        style(add);
        style(check);
        style(view);
        style(empty);
        style(exit);
        
        style(id);
        style(quantity);
        style(details);
        style(subtotal);
        style(discount);
        style(price);
      	
      //Places Labels
		id.setBounds(20,90,120,20);
		add(id);
		quantity.setBounds(250,90,120,20);
		add(quantity);
		details.setBounds(20,120,120,20);
		add(details);
		subtotal.setBounds(20,180,120,20);
		add(subtotal);
		
		discount.setBounds(140,150,80,20);
		add(discount);
		price.setBounds(330,150,50,20);
		add(price);
		
	  //Places Fields
		idField.setBounds(150,90,100,20);
		add(idField);
		quantityField.setBounds(380,90,100,20);
		add(quantityField);
		detailsField.setBounds(150,120,330,20);
		add(detailsField);
		disable(detailsField);
		subtotalField.setBounds(150,180,330,20);
		add(subtotalField);
		subtotalField.setEnabled(false);
		
		discountField.setBounds(230, 150,90, 20);
		add(discountField);
		disable(discountField);
		priceField.setBounds(390,150,90,20);
		add(priceField);
		disable(priceField);
		
		
	  //Places Cart Content Fields
		for(int i = 0; i < 5; i++) {
			cartContent[i] = new TextField();
			cartContent[i].setBounds(20,270+(35*i),470,20);
			add(cartContent[i]);
			cartContent[i].setEnabled(false);
		}
				
		
	  //Places Buttons
		add.setBounds(20,480,150,40);
		add(add);
		add.setEnabled(false);
		find.setBounds(180,480,150,40);
		add(find);
		check.setBounds(340,480,150,40);
		add(check);
		check.setEnabled(false);
		view.setBounds(20,530,150,40);
		add(view);
		view.setEnabled(false);
		empty.setBounds(180,530,150,40);
		add(empty);
		exit.setBounds(340,530,150,40);
		add(exit);
		
		
	  //Find Item from list to see if it exists, and if it does, list the details
		find.addActionListener(new ActionListener() { 
			public void actionPerformed (ActionEvent e) {
		    	
				//File Reader Setup
				File input = new File("src/Inventory.csv");
				BufferedReader br = null;
				try { br = new BufferedReader(new FileReader(input)); }
				catch (FileNotFoundException e1) { e1.printStackTrace(); }
				String inventory = "";
				
		    	String[] item = null;
		    	String itemID = idField.getText();
		    	String itemQuantity = quantityField.getText();
		    	JFrame error = new JFrame();
		    	
		    	
		    	//Finds if Item exists
			    	try {
						while((inventory = br.readLine()) != null) {
							item = inventory.split(", ");
							if(item[0].equals(itemID)) {
								break;
							}
							else {
								item = null;
							}
						}
					} catch (IOException e1) {e1.printStackTrace();}
			    	
			    	//Item ID not valid
			    	if(item == null) {
			    		JOptionPane.showMessageDialog(error, "Item ID " + itemID + " not in file.",
			    			"Nile Dot Com - Error", JOptionPane.ERROR_MESSAGE);
			    		
			    		idField.setText("");
			    		quantityField.setText("");
			    	}
			    	
			    	//Item ID Valid
			    	else {
			    		//Item out of Stock
				    	if(item[2].equals("false")) {
				    		JOptionPane.showMessageDialog(error, "Item out of stock.",
				    			"Nile Dot Com - Error", JOptionPane.ERROR_MESSAGE);
				    		idField.setText("");
				    		quantityField.setText("");
				    	}
				    	
				    	//No quantity inputted
				    	else if(itemQuantity.equals("")) {
				    		JOptionPane.showMessageDialog(error, "No quantity specified.",
					    			"Nile Dot Com - Error", JOptionPane.ERROR_MESSAGE);
				    	}
				    	
				    	//Not enough items in stock
				    	else if(Integer.parseInt(itemQuantity) > Integer.parseInt(item[3])) {
				    		JOptionPane.showMessageDialog(error, "Insufficent Stock, only " + item[3] + " available. Please reduce quantity.",
				    			"Nile Dot Com - Error", JOptionPane.ERROR_MESSAGE);
				    		idField.setText("");
				    		quantityField.setText("");
			    		}
				    	
				    	//Everything is valid
			    		else {
				    		detailsField.setText(item[1] + ", $" + item[4]);
				    		
				    		//Calculates Discounted Price
				    		double discountNum = 0;
				    		double quantityNum = Double.parseDouble(itemQuantity);
				    		if(quantityNum >= 5 && quantityNum <= 9)  { discountNum = 10; }
							if(quantityNum >=10 && quantityNum <= 14) { discountNum = 15; }
							if(quantityNum >= 15) 					  { discountNum = 20; }
							
							discountField.setText(((int)discountNum + "%"));
							discountNum /= 100;
							
							//Finds discounted price total
				    		priceNum = (Double.parseDouble(item[4])*quantityNum);
				    		priceNum = priceNum - (priceNum*discountNum);
				    		String priceFormatted = String.format("$%.2f", priceNum);
				    		
				    	
				    		priceField.setText(priceFormatted);
				    		
				    		disable(idField);
					    	disable(quantityField);
					    	detailsField.setBackground(new Color(255,255,255));
					    	discountField.setBackground(new Color(255,255,255));
					    	priceField.setBackground(new Color(255,255,255));
					    	
					    	find.setEnabled(false);
				    		add.setEnabled(true);
			    		}
			    	}
		    }    
		});  
		
		
		//Adds Item to your shopping cart and adds price to your order subtotal
		add.addActionListener(new ActionListener() {    
		    public void actionPerformed (ActionEvent e) {
		    	if(number <= 5) {
			    	JOptionPane.showMessageDialog(null, "Item " + number + " added to your cart.", 
			        		"Nile Dot Com - Item Confirmed",JOptionPane.INFORMATION_MESSAGE);
			    	
			    	//File Reader Setup
			    	File input = new File("src/Inventory.csv");
					BufferedReader br = null;
					try { br = new BufferedReader(new FileReader(input)); }
					catch (FileNotFoundException e1) { e1.printStackTrace(); }
					String inventory = "";
			    	
			    	//Finds item
					String[] item = null;
				    try {
						while((inventory = br.readLine()) != null) {
							item = inventory.split(", ");
							if(item[0].equals(idField.getText())) {
								break;
							}
						}
					} catch (IOException e1) {e1.printStackTrace();}
			    	
				    
				    double quantityNum = Double.parseDouble(quantityField.getText());
				    cart.add(item[0] + ", "+ item[1] + ", " + item[4] + ", " + quantityNum + ", ");
				    
				    //Writes discounts to cart arraylist
				    if(quantityNum <= 4)  { 
			    		cart.set(cart.size()-1, cart.get(cart.size()-1) + "0%, "); 
			    	}
			    	if(quantityNum >= 5 && quantityNum <= 9)  { 
			    		cart.set(cart.size()-1, cart.get(cart.size()-1) + "10%, "); 
			    	}
					if(quantityNum >=10 && quantityNum <= 14) { 
						cart.set(cart.size()-1, cart.get(cart.size()-1) + "15%, ");
					}
					if(quantityNum >= 15)  {
						cart.set(cart.size()-1, cart.get(cart.size()-1) + "20%, ");
					}
					
					//Writes Final price to cart array
					cart.set(cart.size()-1, cart.get(cart.size()-1) + String.format("$%.2f", priceNum));
				    	
					//Rewrite contents title and cart contents
			        contents.setText("-------------------  Shopping cart has " + number + " item(s)  -------------------");
			        cartContent[number-1].setText("SKU: " + item[0] + ", Desc: " + item[1] + ", Price Ea. $" + item[4] + ", Qty: " + (int)quantityNum + ", Total: $" + String.format("$%.2f", priceNum));
					
			        
			        
			    	//Rewrites Item Title
			        number+=1;
			        String padded = String.format("%02d", number);
			        title.setText("- Item " + padded + " -");
			 
			        //Writes Subtotal 
			        total += priceNum;
			        String priceFormatted = String.format("%.2f", total);
			        subtotalField.setText("$"+ priceFormatted);
			    	
			        
			    	enable(idField);
			    	enable(quantityField);
			        disable(detailsField);
			        disable(discountField);
			        disable(priceField);
			        
			        if(number == 6) {
			        	find.setEnabled(false);
			        }
			        else {
			        	find.setEnabled(true);
			        }
			        
			        add.setEnabled(false);
			        view.setEnabled(true);
			        check.setEnabled(true);
			        
			        idField.setText("");
			        quantityField.setText("");
		    	}
		    	else {
		    		JFrame error = new JFrame();
		    		JOptionPane.showMessageDialog(error, "Cannot add more than 5 Items to Cart.",
			    			"Nile Dot Com - Error", JOptionPane.ERROR_MESSAGE);
		    	}
		   }
		}); 

		//Writes all checked out transactions to Transactions file
		check.addActionListener(new ActionListener() {    
		    public void actionPerformed (ActionEvent e) {  
		    	String cartList = "";
		    	for(int i = 0; i < cart.size(); i++) {
		    		cartList = cartList + (i+1) + ") " + cart.get(i) +"\n";
		    	}
		    	
		    	Date date = new Date();
		    	SimpleDateFormat DateFor = new SimpleDateFormat("MMMM dd, yyyy, HH:mm:ss zzz");
		    	String stringDate = DateFor.format(date);
		    	
		    	JOptionPane.showMessageDialog(null, 
		    			"Date: " + stringDate +  "\n"
		    			+ "Number of line items: " + cart.size() + "\n"
		    			+ "Item # / ID / Title / Price / Qty / Disc% / Subtotal: " + "\n"
		    			+ cartList + "\n"
		    			+ "Order Subtotal: $" + String.format("%.2f", total) + "\n"
		    			+ "Tax rate: 6%\n"
		    			+ "Tax Amount: $" + String.format("%.2f", total * 0.06) + "\n"
		    			+ "Order Total: $" + String.format("%.2f", total * 1.06) + "\n"
		    			+ "Thanks for Shopping at Nile Dot Com!", 
		        		"Nile Dot Com - Current Shopping Cart Status",JOptionPane.PLAIN_MESSAGE);   
		    	
		    	//File Writer Setup
				FileWriter file = null;
				BufferedWriter buffer = null;
				try { 
					file = new FileWriter("src/Transactions.csv", true);
					buffer = new BufferedWriter(file);
				} catch (IOException e1) { e1.printStackTrace(); }
				
				//Adds Cart items to transactions file
				try { 
					for(String s: cart) {
						DateTimeFormatter code = DateTimeFormatter.ofPattern("ddMMYYYYHHmmss");  
				    	DateFor = new SimpleDateFormat("HH:mm:ss zzz");
				    	stringDate = DateFor.format(date);
						LocalDateTime now = LocalDateTime.now();  
						buffer.write(code.format(now) + ", " + s + ", " + stringDate + "\n");
					}
						buffer.newLine();
				} catch (IOException e1) { e1.printStackTrace(); }
				
				
				//Closes File
				try { buffer.close();
				} catch (IOException e1) { e1.printStackTrace(); }
				
				
				//Resets GUI
				enable(idField);
		    	enable(quantityField);
		        disable(detailsField);
		        disable(discountField);
		        disable(priceField);
		        
		        find.setEnabled(true);
		        add.setEnabled(false);
		        check.setEnabled(false);
		        view.setEnabled(false);
		        
		        
		        idField.setText("");
		        quantityField.setText("");
		        detailsField.setText("");
		        discountField.setText("");
		        priceField.setText("");
		        subtotalField.setText("");
		        
		        for(int i = 0; i < 5; i++) {
		        	cartContent[i].setText("");
		        }
				
				number = 1;
				total = 0;
				cart =  new ArrayList<String>();
				
				//Rewrites Titles
				String padded = String.format("%02d", number);
		        title.setText("- Item " + padded + " -");
		        contents.setText("-------------------  Shopping cart has 0 item(s)  -------------------");
		   }    
		}); 
		
		
		view.addActionListener(new ActionListener() {    
		    public void actionPerformed (ActionEvent e) {    
		    	
		    	String cartList = "";
		    	for(int i = 0; i < cart.size(); i++) {
		    		cartList = cartList + (i+1) + ") " + cart.get(i) +"\n";
		    	}
		    	JOptionPane.showMessageDialog(null, cartList, 
		        		"Nile Dot Com - Current Shopping Cart Status",JOptionPane.PLAIN_MESSAGE);       
		    }    
		}); 
		
		//Empties the cart and the transactions file
		empty.addActionListener(new ActionListener() {    
		    public void actionPerformed (ActionEvent e) {    		
		    	cart = new ArrayList<String>();  
		    	view.setEnabled(false);
		    	
		    	
		    	//Resets GUI
		    	enable(idField);
		    	enable(quantityField);
		        disable(detailsField);
		        disable(discountField);
		        disable(priceField);
		        
		        find.setEnabled(true);
		        add.setEnabled(false);
		        check.setEnabled(false);
		        
		        idField.setText("");
		        quantityField.setText("");
		        detailsField.setText("");
		        discountField.setText("");
		        priceField.setText("");
		        subtotalField.setText("");
		        
		        for(int i = 0; i < 5; i++) {
		        	cartContent[i].setText("");
		        }
		    	
		        number = 1;
		    	total = 0;
		    	
		    	//Rewrites Titles
		    	String padded = String.format("%02d", number);
		        title.setText("- Item " + padded + " -");
		        contents.setText("-------------------  Shopping cart has 0 item(s)  -------------------");
		    }    
		}); 
		
		//Closes Window and clears transactions file
		exit.addActionListener(new ActionListener() {    
		    public void actionPerformed (ActionEvent e) { 
		    	System.exit(0);
		    	
		    	try (BufferedWriter bf = Files.newBufferedWriter(Path.of("src/Transactions.csv"),
		                StandardOpenOption.TRUNCATE_EXISTING)) {
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		    }    
		}); 
	}
	
	
//Style for Buttons
	public void style(Button b) {
		b.setFont(new Font("Futura", Font.PLAIN, 14));
		b.setForeground(new Color(220, 100, 120));
	}
	
	
//Style for Labels
	public void style(Label l) {
		l.setFont(new Font("Futura", Font.PLAIN, 16));
		l.setForeground(new Color(220, 100, 120));
		l.setAlignment(Label.RIGHT);
	}

	
//Disable textField
	public void disable(TextField t) {
		t.setEnabled(false);
		t.setBackground(new Color(200,200,200));
	}
	
	
//Enable Text Fields
	public void enable(TextField t) {
		t.setEnabled(true);
		t.setBackground(new Color(255,255,255));
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws InterruptedException, IOException {
			NileGUI gui = new NileGUI();
	}
}
