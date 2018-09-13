/**
 * @Program Simple Select
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest02CalcQuery {
	
	public static void main(String[] args) {
		try {
			Table tableCustomers = new Table();                         // Creating an empty table
			Table tableProducts = new Table();
			Table tableRelationship = new Table();
			
			tableCustomers.loadFromCsvFile("Customers.csv");            //  loading from csv file
			tableProducts.loadFromCsvFile("Products.csv");
			tableRelationship.loadFromCsvFile("Relationship.csv");
			
			tableCustomers.dump("Customers");                           // debug: print all table content
			tableProducts.dump("Products");
			tableRelationship.dump("Relationship");
			
			for (int c=1; c<tableCustomers.size();c++) {               // Loop on Customers
				String customerID   = tableCustomers.getValue(c, 0);
				String customerName = tableCustomers.getValue(c, 1);
				System.out.println( "Customer:" + customerName );
				
				double total = 0;
				Table queryRelationship = tableRelationship.selectWhere(0, customerID); // select from relations where ID=customerID
				for (int r=0; r<queryRelationship.size(); r++){
					String productID = queryRelationship.getValue(r, 1);
					int amount = queryRelationship.getIntValue(r, 2);
					
					Table queryProduct  = tableProducts.selectWhere(0, productID);   // select from products  where ID=productID
					for (int p=0; p<queryProduct.size(); p++){
						String productName = queryProduct.getValue(0, 1);
						Double productPrice = queryProduct.getDoubleValue(0, 4);
						double subTotal = productPrice * amount;
						total=total+subTotal;
						System.out.println( "  Is buying "+amount+" of the product:" + productName + " price:"+productPrice + " subTotal:"+subTotal );
					}
				}
				System.out.println("     TOTAL:" + Table.getMoneyValue(total) );
			}
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest:" + e.getMessage() );
		}
	}
}