import java.io.*;

public class Prueba {
    
	private static int longuitudLlave = 20;
    
    /*-----------------------------------------------------------------
     / Test functions
     /-----------------------------------------------------------------*/
    
	private static void crearArchivo() {
        
		try {
            
			File file = new File( "Depositos.Info" );
			RandomAccessFile raf = new RandomAccessFile( file, "rw" );
			Archivo archivo = new Archivo( raf );
            
			Registro registro;
            /*
			registro = new Registro( "Sucursal 3", 3, "Cliente 3", 300.0 );
			archivo.insertar( registro );
			registro = new Registro( "Sucursal 2", 2, "Cliente 2", 200.0 );
			archivo.insertar( registro );
			registro = new Registro( "Sucursal 1", 1, "Cliente 1", 100.0 );
			archivo.insertar( registro );
			registro = new Registro( "Sucursal 0", 0, "Cliente 0", 0.0 );
			archivo.insertar( registro );
			*/
			
			registro = new Registro("Puebla", 10, "John", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Amozoc", 11, "Beto", 200.0);
            archivo.insertarFin(registro);
            registro = new Registro("Texas", 12, "Tipis", 400.0);
            archivo.insertarFin(registro);

            registro = new Registro("BC", 12, "Jose", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Guea", 10, "Veronica", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Tlaxcala", 10, "Jesus", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Florida", 10, "David", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Poza Rica", 10, "Sara", 100.0);
            archivo.insertarFin(registro);
            registro = new Registro("Noruega", 10, "Raul", 100.0);
            archivo.insertarFin(registro);

            //System.out.println(archivo.buscar("Puebla","John"));
            //archivo.buscar("Florida", "David");
			archivo.borrar("Puebla" , "John");
			raf.close();
            
		} catch( IOException e ) {
            
			System.out.println( "IOException:" );
			e.printStackTrace();
		}
	}
    
	private static void mostrarArchivo() {
        
		try {
            
			File file = new File( "Depositos.Info" );
			RandomAccessFile raf = new RandomAccessFile( file, "rw" );
            
			Archivo archivo = new Archivo( raf );
			archivo.imprimirRegistros();
            
			raf.close();
            
		} catch( IOException e ) {
            
			System.out.println( "IOException:" );
			e.printStackTrace();
		}
	}
    
	public static void main( String[] args ) {
        
		crearArchivo();
		mostrarArchivo();
	}
}
