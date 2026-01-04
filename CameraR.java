public class CameraR{

	private int x;
	private int y;
	private int z;
	
	private double xAng;
	private double yAng;
	private double zAng;

	private double fovHori;
	private double fovVer;


	public CameraR(){
		x = 0;
		y = 0;
		z = 0;

		xAng = 0;
		yAng = 0;
		zAng = 0;
	
		fovHori = 200;	
		fovVer = 135;
	} 

	public CameraR(int xIn, int yIn, int zIn, double xAngIn, double yAngIn, double zAngIn, double fovHoriIn, double fovVerIn){
		this.x = xIn;
		this.y = yIn;
		this.z = zIn;
		this.xAng = xAngIn;
		this.yAng = yAngIn;
		this.zAng = zAngIn;
		this.fovHori = fovHoriIn;
		this.fovVer = fovVerIn;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	public double getXAng(){
		return xAng;
	}
	public double getYAng(){
		return yAng;
	}
	public double getZAng(){
		return zAng;
	}
	public double getFovHori(){
		return fovHori;
	}
	public double getFovVer(){
		return fovVer;
	}
	



}
