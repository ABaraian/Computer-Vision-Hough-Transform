import java.io.*;
import java.util.Scanner;
public class BaraianA_Main {
	public static void main(String[] args)throws IOException{
		File inputFile = new File(args[0]);
		File outFile = new File(args[1]);
		Scanner inputFileScanner = new Scanner(inputFile);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile,true));
		BaraianA_HoughTransform HoughSpace = new BaraianA_HoughTransform();
		
		
		HoughSpace.numRows=inputFileScanner.nextInt();
		HoughSpace.numCols=inputFileScanner.nextInt();
		HoughSpace.minVal=inputFileScanner.nextInt();
		HoughSpace.maxVal=inputFileScanner.nextInt();
		HoughSpace.HoughAngle=180;
		HoughSpace.HoughDist= (int) (2 * Math.sqrt(Math.pow(HoughSpace.numRows,2)+Math.pow(HoughSpace.numCols,2)));
		HoughSpace.imgAry= new int[HoughSpace.numRows][HoughSpace.numCols];
		HoughSpace.CartesianHoughAry = new int [HoughSpace.HoughDist][HoughSpace.HoughAngle];
		HoughSpace.PolarHoughAry = new int[HoughSpace.HoughDist][HoughSpace.HoughAngle];
		for(int i=0;i<HoughSpace.numRows;i++) {
			for(int j=0;j<HoughSpace.numCols;j++) {
				HoughSpace.imgAry[i][j]=0;
			}
		}
		for(int i=0;i<HoughSpace.HoughDist;i++) {
			for(int j=0;j<HoughSpace.HoughAngle;j++) {
				HoughSpace.CartesianHoughAry[i][j]=0;
				HoughSpace.PolarHoughAry[i][j]=0;
			}
		}
		
		HoughSpace.offset=(int) Math.sqrt(Math.pow(HoughSpace.numRows,2 )+Math.pow(HoughSpace.numCols, 2));
		
		HoughSpace.loadImage(inputFileScanner);
		writer.write("Img Array\n");
		HoughSpace.reformatPrettyPrint(HoughSpace.imgAry, writer);
		HoughSpace.buildHoughSpace();
		
		writer.write("Cartesian Hough Array\n");
		HoughSpace.reformatPrettyPrint(HoughSpace.CartesianHoughAry, writer);
		writer.write("Polar Hough Array\n");
		HoughSpace.reformatPrettyPrint(HoughSpace.PolarHoughAry, writer);
		writer.close();
	}
	
	
}
