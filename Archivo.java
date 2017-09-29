import java.io.*;

public class Archivo {
    
	private RandomAccessFile raf = null;
	private int posicionBorrado;

	public Archivo( RandomAccessFile raf ) {
		this.raf = raf;
	}
    
    /*-----------------------------------------------------------------
    / inserta un registro al inicio del archivo
    /-----------------------------------------------------------------*/
    
	public void insertar( Registro registro ) throws IOException {
        
		insertarEn( 0, registro );
	}
    
	public void imprimirRegistros() throws IOException {
        
		Registro registro = new Registro();
		int length = (int) (raf.length() / registro.length());
        
		System.out.println( "Número de registros: " + length );
		raf.seek( 0 );
        
		for( int i = 0; i < length; i++ ) {
            
			registro.read( raf );
            
			System.out.println( "( " + registro.getSucursal() + ", "
                                     + registro.getNumero() + ", "
                                     + registro.getNombre() + ", "
                                     + registro.getSaldo() + " )" );
		}
	}
    
    /*-----------------------------------------------------------------
    / desplaza registros para insertar un registro en la posicion p
    /-----------------------------------------------------------------*/
    
	private void insertarEn( int p, Registro registro ) throws IOException {
		//Metodo de Expanción, recorre todos hasta el final y lo pone en 0
        
		int n = (int) (raf.length() / registro.length());
        
		for( int i = n-1; i >= p; i -- ) {    // desplazamiento de registros
            
			Registro temp = new Registro();
            
			raf.seek( i * temp.length() );		//Lee el primer byte del ultimo registro (n-1)
			temp.read( raf );
            
			raf.seek( (i+1) * temp.length() );
			temp.write( raf );
		}
        
		raf.seek( p * registro.length() );   // inserta el nuevo registro
		registro.write( raf );
	}

	/*
	 *Inserta el registro en la ultima posición (Crear)
	 */

	public void insertarFin(Registro registro) throws IOException {
		//int n = (int) (raf.length() / registro.length());
		raf.seek(raf.length());
		//System.out.println(raf.length());
		registro.write(raf);
	}

	/*
	 *Eliminar
	 *public void borrar(Registro registro) throws IOException{
		if() {
			
		}
	 *}
	 */


	public void borrar(String nombreSucursal, String nombreCliente) throws IOException{
		Registro registro = new Registro(nombreSucursal, nombreCliente);

		int tam = (int) (raf.length() / registro.length());
		
		if (buscar(nombreSucursal, nombreCliente) == true && posicionBorrado != tam-1){
			for( int i = posicionBorrado; i < tam; i ++ ) {
				Registro temp = new Registro();
				raf.seek( (i+1) * temp.length() );
				temp.read( raf );
				raf.seek( i*temp.length() );
				temp.write( raf );
			}
		}

		imprimirRegistros();
	}

	/*
	 * Buscar/recuperar
	 */

	public boolean buscar(String nombreSucursal, String nombreCliente) throws IOException{
		Registro registro = new Registro(nombreSucursal, nombreCliente);
		boolean found = false;
		int tam = (int)(raf.length() / registro.length());
		int i = 0;
		while(i < tam){
			Registro temp = new Registro();
			raf.seek( i* registro.length());
			temp.read(raf);

			boolean sameSuc = temp.getSucursal().equals(registro.getSucursal());
			boolean sameName = temp.getNombre().equals(registro.getNombre());
			
			if(sameSuc == true && sameName == true && temp.getBorrado() == 0){
				posicionBorrado = i;
				found = true;
				System.out.println(found + " " + posicionBorrado);
				return found;
			}
			i++;
		}
		System.out.println(found + " " + posicionBorrado);
		return found;
	}

}