import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class Table {

	private final int MAXREG = 80 ;
	private final int MAXFIE = 80 ;
	private String[][] table = new String[MAXREG][MAXFIE]; // [registry][field]
	private int size = 0;
	
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
	
	/** Return a SubTable (ResultSet) with all registries where fieldPosition is equal to a valueParameter */
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
			throw new Exception("Error at getWhere():" + e.getMessage());
		}
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