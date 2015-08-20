package Generator;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




import javax.imageio.ImageIO;

public class MapDraw {

	int size;
	File image_file;
	BufferedImage bi ;

	public MapDraw(int [][] maze, String png_name) throws IOException {

		this.size=maze.length;
		this.image_file=new File("test.png");
		
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
