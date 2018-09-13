import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class Table {

	private final int MAXREG = 800 ;  // If you need more registers, increase it here.
	private final int MAXFIE = 80  ;  // If you need more fields(columns), increase it here.
	private String[][] table = new String[MAXREG][MAXFIE]; // [registry][field]
	private int size = 0;
	private final String DELETED = new String((char)1+"");
	
	
	/** LOAD FROM FILE */
	public void loadFromCsvFile( String filename) throws Exception {
		try {
			File file = new File(filename);
			FileInputStream fis = new FileInputStream( file );
			byte content[] = new byte[(int)file.length()];
			fis.read(content);
			fis.close();
			String regs[] = new String(content).split("\n");
			this.size=regs.length;
			for (int r=0; r<regs.length; r++) {
				table[r] = regs[r].split(";");
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error at loadFromCsvFile():" + e.getMessage() );
		}
	}
	/** SAVE TO FILE */
	public void saveToCsvFile( String filename) throws Exception {
		try {
			StringBuffer buff = new StringBuffer();
			for (int r=0; r<size; r++) {
				for (int f=0; f<table[r].length; f++) {
					buff.append( table[r][f] + ";" );
				}
				buff.append("\n");
			}
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream( file );
			fos.write(new String(buff).getBytes());
			fos.close();
		} 
		catch (Exception e) {
			throw new Exception("Error at saveToCsvFile():" + e.getMessage() );
		}
	}	
	
	/** Size **/
	public int size() {
		return this.size;
	}
	
	/** SET the Value from registryNumber + fieldNumber **/
	public void setValue( int registryNumber , int fieldNumber , String value ) {
		table[registryNumber][fieldNumber]=value;
	}
	/** GET the Value from registryNumber + fieldNumber **/
	public String getValue( int registryNumber , int fieldNumber ) {
		return table[registryNumber][fieldNumber];
	}
	/** GET the integer Value from registryNumber + fieldNumber **/
	public int getIntValue( int registryNumber , int fieldNumber ) {
		return Integer.parseInt( table[registryNumber][fieldNumber] );
	}
	/** GET the double Value from registryNumber + fieldNumber **/
	public double getDoubleValue( int registryNumber , int fieldNumber ) {
		return Double.parseDouble(table[registryNumber][fieldNumber].replace(",", "."));
	}
	/** GET formated money String (Extra tool) **/
	public static String getMoneyValue( Double value ) {
		return new DecimalFormat("#.00").format(value);
	}
	
	
	
	/** GET registry **/
	public String[] getRegistry( int i ) {
		return table[i];
	}	
	/** SET registry **/
	public void setRegistry( String registry[] ) {
		table[ this.size ]  = registry ;
		this.size++;
	}	
	
	/** Return a SubTable (ResultSet) with all registers where fieldPosition is equal to a valueParameter */
	public Table selectWhere( int numField , String value ) throws Exception {
		try {
			Table resultset = new Table();
			for (int r=0; r<this.size(); r++) {
				if ( table[r][numField].equals(value) ) {
					resultset.setRegistry(table[r]);
				}
			}
			return resultset;
		}
		catch (Exception e) {
			throw new Exception("Error at selectWhere():" + e.getMessage());
		}
	}
	
	/** LogicDelete all registers where fieldPosition is equal to a valueParameter. 
	    To force a physical deletion, use the clone() after delete(). */
	public void deleteWhere( int numField , String value ) throws Exception {
		try {
			for (int r=0; r<this.size(); r++) {
				if ( table[r][numField].equals(value) ) {
					table[r][0]=DELETED;
				}
			}
		}
		catch (Exception e) {
			throw new Exception("Error at deleteWhere():" + e.getMessage());
		}
	}
	
	/** Clone this table **/
	public Table clone(){
		Table clone = new Table();
		for (int r=0; r<size; r++) {
			String registry = new String();
			for (int f=0; f<table[r].length; f++) {
				registry=registry+table[r][f]+";" ;
			}
			if (table[r][0]!=DELETED) {
				clone.setRegistry(registry.split(";"));
			}
		}
		return clone;
	}
	
	/** DEBUG: HOW ALL CONTENT **/
	public void dump( String message){
		System.out.print("\nDUMPING "+message+"\n");
		for (int r=0; r<size; r++){
			for (int f=0; f<table[r].length; f++){
				System.out.print( table[r][f] + " | ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
}