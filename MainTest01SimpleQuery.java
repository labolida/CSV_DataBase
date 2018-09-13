/**
 * @Program Simple Select
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest01SimpleQuery {
	
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
			
			for (int r=0; r<tableRelationship.size();r++) {             // Loop on each registry of the relationship table
				String customerID = tableRelationship.getValue(r, 0);
				String productID  = tableRelationship.getValue(r, 1);
				
				// Get a new TableResultSet of a query
				Table queryCustomer = tableCustomers.selectWhere(0, customerID); // select * from customers where ID=customerID
				Table queryProduct  = tableProducts.selectWhere(0, productID);   // select * from products  where ID=productID
				
				String customerName = queryCustomer.getValue(0, 1);
				String productName = queryProduct.getValue(0, 1);
				
				System.out.println( "The customer:" + customerName + " is buying the product:" + productName );
			}
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest:" + e.getMessage() );
		}
	}
}