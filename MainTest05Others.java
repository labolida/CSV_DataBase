/**
 * @Program update insert
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest05Others {
	
	public static void main(String[] args) {
		try {
			Table table = new Table();
			table.loadFromCsvFile("BigTable.csv");
			table.dump("Original table");
			
			table.deleteWhere(2, "XXX");
			
			table.dump("Customers with logical delete");        // view original table
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest:" + e.getMessage() );
		}
	}
}