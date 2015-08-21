package Generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapDraw {
	
	int size;
	File image_file;
	BufferedImage bi ;
	int [][] maze;
	
	public MapDraw(int [][] mazec, String png_name) throws IOException {

		this.size=mazec.length;
		this.image_file=new File(png_name);
		this.maze=mazec;
		

		try{
		this.bi = ImageIO.read(image_file);
		} catch(IOException e){
			e.printStackTrace();
		}

	
		
	}//DrawingLib

	public BufferedImage getMap(){
		
		int type=bi.getType();
		BufferedImage final_img=new BufferedImage(bi.getHeight()*size,bi.getWidth()*size,type);
		
		for (int i=0; i< size ; ++i){
			for(int j=0;j<size;++j){
					if(maze[i][j]==1)
						final_img.createGraphics().drawImage(bi, bi.getHeight()*i, bi.getWidth()*j, null); 
						
			}//for j				
		}//for i
		
		try {
			ImageIO.write(final_img, "png", new File("finalImg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return final_img;
		
	}
	
}// class end
