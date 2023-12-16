package Producto;

public class Producto{
    private String referencia;
    private String participacionProduccion;
    
    
    public Producto (){
        referencia="";
        participacionProduccion="";
    }
    public Producto (String referencia,String participacionProduccion){
        this.referencia = referencia;
        this.participacionProduccion = participacionProduccion;
    }
	public void SetReferencia(String referencia){
		this.referencia = referencia;
	}
	public void SetParticipacionProduccion(String participacionProduccion){
		this.participacionProduccion = participacionProduccion;
	}
	public String GetReferencia(){
		return referencia;
	}
	public String GetParticipacionProduccion(){
		return participacionProduccion;
	}   
    
}