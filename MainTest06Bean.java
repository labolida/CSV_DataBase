import java.util.ArrayList;
import java.util.List;

/**
 * @Program Beans
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest06Bean {
	
	public static void main(String[] args) {
		try {
			Table table = new Table();
			table.loadFromCsvFile("BigTable.csv");
			
			//Creating a ArrayList of beans from our CSV_Table.
			
			List<Bean> list = new ArrayList<Bean>();
			
			for (int r=0;r<table.size();r++) {
				Bean bean = new MainTest06Bean().new Bean();
				bean.setName   ( table.getValue(r,0) );
				bean.setAge    ( table.getValue(r,1) );
				bean.setAddress( table.getValue(r,2) );
				list.add(bean);
			}
			for (Bean b: list) {
				System.out.println( "Name:"+ b.getName() );
				System.out.println( " Age:"+ b.getAge() );
				System.out.println( " Address:"+ b.getAddress() );
			}
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest06Bean:" + e.getMessage() );
		}
	}
	
	// INNER CLASS
	private class Bean{
		private String name = null;
		private String age = null;
		private String address = null;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		
	}
	
}