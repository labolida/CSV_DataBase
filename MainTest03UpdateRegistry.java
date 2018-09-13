/**
 * @Program update insert
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest03UpdateRegistry {
	
	public static void main(String[] args) {
		try {
			Table tableCustomers = new Table();
			tableCustomers.loadFromCsvFile("Customers.csv");
			tableCustomers.dump("Customers");                    // view original table

			
			/* UPDATE */
			Table query = tableCustomers.selectWhere(1, "Bill"); // Pointer reference
			query.setValue(0, 2, "Addrrss Street 45678");        // update a field from an existing registry
			
			tableCustomers.dump("Customers");                    // view original table
			
			
			/* INSERT */
			tableCustomers.setRegistry("999;Leonardo;MyAddress;MyEmail;".split(";"));
			
			tableCustomers.dump("Customers");                    // view original table
			
			tableCustomers.saveToCsvFile("Customers2.csv");
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest:" + e.getMessage() );
		}
	}
}