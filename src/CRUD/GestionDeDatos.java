package CRUD;
import java.io.*;
import java.util.*;

import Producto.Producto;
public class GestionDeDatos {
	static String direc ="data.txt";
    //GuardarDatos de CLIENTES
    public static void GuardarDatos(Vector<Producto> datos) throws FileNotFoundException,IOException{
        File file = new File(direc);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        DataOutputStream outStream = new DataOutputStream(fileOutputStream);
        
        outStream.writeInt(datos.size());
        
        for(int i =0;i<datos.size();i++){
            outStream.writeUTF(datos.get(i).GetReferencia());
            outStream.writeUTF(datos.get(i).GetParticipacionProduccion());
        }

        outStream.flush();
        outStream.close();
    }
    //CargarDatos de CLIENTES
    public static Vector<Producto> CargarDatos() throws FileNotFoundException,IOException{
        File file = new File(direc);
        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream InStream = new DataInputStream(fileInputStream);
        
        int capacidad = InStream.readInt();
        
        Vector<Producto> data = new Vector<Producto>();
        
        for(int i =0;i<capacidad;i++){
            String referencia =InStream.readUTF();
            String participacionProduccion =InStream.readUTF();

            Producto nuevoProduct = new Producto (referencia ,participacionProduccion);
        	data.add(nuevoProduct);
        }
        
        InStream.close();
        return data;
    }
}
