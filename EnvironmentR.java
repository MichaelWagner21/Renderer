import java.awt.Color;

//this 3d space will have negative coordinates, which is why some values are multiplied
//by 2 and indexes are offset by the coordinate maxes (because an array can't have negative indexes)
public class EnvironmentR {
    Color[][][] envColors;

    // Bound of x value indexes are [-xMax, yMax] etc. for y and z
    int xMax;
    int yMax;
    int zMax;


    public EnvironmentR(int xMaxIn, int yMaxIn, int zMaxIn){
        //one index is added for 0
        this.envColors = new Color[xMaxIn*2+1][yMaxIn*2+1][zMaxIn*2+1];
        this.xMax = xMaxIn;
        this.yMax = yMaxIn;
        this.zMax = zMaxIn;
    }
    public void setColor(Color c, int x, int y, int z){
        envColors[x+xMax][y+yMax][z+zMax] = c;
    }
    
    
    
    
    
    public String toString(){
        String returnString = "";
        for (Color[][] x:this.envColors){
            returnString+="slice:\n";
            for (Color[] y:x){
                for (Color z:y){
                    if (z == null){
                        returnString += "0 ";
                    }
                    else {
                        returnString += "1 ";
                    }
                }
                returnString+="\n";
            }
            returnString+="\n";
            returnString+="\n";
        }

        return returnString;
    }
}
