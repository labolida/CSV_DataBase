/**
 * @Program Performance
 * @author www.leonardo.labolida.com
 * KISS : Keep It Simple ;-)  as possible 
 */
public class MainTest05Performance {
	
	public static void main(String[] args) {
		try {
			
			Table table = new Table();
			table.loadFromCsvFile("BigTable.csv");
			
			/* CREATE A NEW BIG TABLE */
			/*
			String words[] = {"Leonardo","Labolida","new","old","door","window","gate"};
			for (int r=0;r<800;r++) {
				StringBuffer buff = new StringBuffer("");
				buff.append( words[(r)%words.length] + ";" );
				for (int f=0;f<79;f++) {
					buff.append(" Reg ");
					buff.append(r);
					buff.append(" Field ");
					buff.append(f);
					buff.append(";");
				}
				table.setRegistry(buff.toString().split(";"));
			}
			table.saveToCsvFile("BigTable.csv");
			System.exit(0);
			*/
			
			table.deleteWhere(0, "gate");
			table = table.clone();
			table.saveToCsvFile("BigTableNoGate.csv");
		} 
		catch (Exception e) {
			System.out.println( "Error at MainTest05Performance:" + e.getMessage() );
		}
	}
}