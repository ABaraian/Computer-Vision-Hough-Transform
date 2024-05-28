import java.io.*;
import java.util.Scanner;
public class BaraianA_HoughTransform {
	int numRows;
	int numCols;
	int minVal;
	int maxVal;
	int HoughDist;
	int HoughAngle=180;
	int imgAry[][];
	int CartesianHoughAry[][];
	int PolarHoughAry[][];
	int angleInDegree;
	double angleInRadians;
	int offset;
	
	void loadImage(Scanner S) {
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				imgAry[i][j]=S.nextInt();
			}
		}
	}
	
	void PrettyPrint(BufferedWriter writer) throws IOException {
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				writer.write(imgAry[i][j]+" ");
			}
			writer.write("\n");
		}
	}
	
	void buildHoughSpace() {
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				if(imgAry[i][j]>0) {
					computeSinusoid(i,j);
				}
			}
		}
	}
	
	void computeSinusoid(int x,int y) {
		angleInDegree=0;
		double dist;
		int distInt;
		while(angleInDegree<=179) {
		angleInRadians = (double)(angleInDegree*Math.PI/180);
		dist=CartesianDist(x,y);
		distInt=(int)dist;
		CartesianHoughAry[distInt][angleInDegree]++;
		dist=PolarDist(x,y);
		distInt=(int)dist;
		PolarHoughAry[distInt][angleInDegree]++;
		angleInDegree++;
		}
	}
	
	double CartesianDist(int x,int y) {
		double t= angleInRadians- Math.atan(y/x)- Math.PI/2; 
		double cartesiandistance=Math.sqrt(Math.pow(x, 2)+Math.pow(y,2)) * Math.cos(t) + offset;
		return cartesiandistance;
	}
	double PolarDist(int x, int y) {
		double polardistance = x* Math.cos(angleInRadians) + y* Math.sin(angleInRadians) + offset;
		return polardistance;
	}
	void reformatPrettyPrint(int Ary[][],BufferedWriter writer) throws IOException {
		int max=0;
		 for(int row=0;row<Ary.length;row++){
             for(int col=0;col<Ary[0].length;col++){
                if(max<Ary[row][col]){
                 max=Ary[row][col];
                }
                
             }
         }
		 
		 int r=0,c=0;
		 while(r<Ary.length) {
			 c=0;
			 while(c<Ary[0].length) {
				 if(max<10) {
					 if(Ary[r][c]==0) {
						 writer.write(". ");
					 }
					 else {
						 writer.write(Ary[r][c] + " ");
					 }
				 }
				 else {
					 if(Ary[r][c]==0) {
						 writer.write(".  ");
					 }
					 else if(Ary[r][c]>=10) {
						 writer.write(Ary[r][c]+ " ");
					 }
					 else {
						 writer.write(Ary[r][c]+"  ");
					 }
				 }
				 c++;
			 }
			 writer.write("\n");
			 r++;
		 }
		 writer.write("\n");
		 writer.flush();
		 
	}
	
	
}