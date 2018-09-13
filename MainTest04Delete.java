/**
 * @Program update insert
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest04Delete {
	
	public static void main(String[] args) {
		try {
			Table tableCustomers = new Table();
			tableCustomers.loadFromCsvFile("Customers.csv");
			
			tableCustomers.dump("Original Customers");        // view original table

			tableCustomers.deleteWhere(1, "Bob");    // Note: it is marked as a deleted. (Logical Deletion)
			
			tableCustomers.dump("Customers with logical delete");        // view original table
			
			Table customers3 = tableCustomers.clone();               // Eliminates the registry
			
			customers3.dump("Customer3 without de deleted registry");
			
			customers3.saveToCsvFile("customers3.csv");
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest:" + e.getMessage() );
		}
	}
}