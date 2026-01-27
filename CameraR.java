import java.awt.Color;


public class CameraR extends PhysicalObjectR{

	
	private double fovHori;
	private double fovVer;

	private int screenWidth;
	private int screenHeight;

	public Color[][] projection;


	public CameraR(int screenWidthIn, int screenHeightIn){
		this.x = 0;
		this.y = 0;
		this.z = 0;

		this.xAng = 0;
		this.yAng = 0;
		this.zAng = 0;
	
		this.fovHori = 200;	
		this.fovVer = 135;
		
		this.screenWidth = screenWidthIn;
		this.screenHeight = screenHeightIn;

	
	} 

	public CameraR(int xIn, int yIn, int zIn, 
				   double xAngIn, double yAngIn, double zAngIn, 
				   double fovHoriIn, double fovVerIn, 
				   int screenWidthIn, int screenHeightIn){
		this.x = xIn;
		this.y = yIn;
		this.z = zIn;
		this.xAng = xAngIn;
		this.yAng = yAngIn;
		this.zAng = zAngIn;
		this.fovHori = fovHoriIn;
		this.fovVer = fovVerIn;
		this.screenWidth = screenWidthIn;
		this.screenHeight = screenHeightIn;
	}

	
	public double getFovHori(){
		return fovHori;
	}
	public double getFovVer(){
		return fovVer;
	}
	
	public String toString(){
		return "DEBUG: \n x: "+ String.valueOf(this.x) +
					  "\n y: "+ String.valueOf(this.y) +
					  "\n z: "+ String.valueOf(this.z) +
					  "\n xAng: " + String.valueOf(this.xAng) +
					  "\n yAng: " + String.valueOf(this.yAng) +
					  "\n zAng: " + String.valueOf(this.zAng) +
					  "\n fovHori: "+String.valueOf(this.fovHori) +
					  "\n fovVer: "+ String.valueOf(this.fovVer) +
					  "\n screenWidth: "+String.valueOf(this.screenWidth) +
					  "\n screenHeight: "+String.valueOf(this.screenHeight);
	}

	public void camUpdate(){
		//TODO: This
	}
	public void updateProjection(){

	}


}
