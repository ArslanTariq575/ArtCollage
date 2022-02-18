package art;

import java.awt.Color;

/*
 * This class contains methods to create and perform operations on a collage of images.
 * 
 * @author Ana Paula Centeno
 * @author Arslan Tariq
 */ 

public class Collage {

    // The orginal picture
    private Picture originalPicture;

    // The collage picture is made up of tiles.
    // Each tile consists of tileDimension X tileDimension pixels
    // The collage picture has collageDimension X collageDimension tiles
    private Picture collagePicture;

    // The collagePicture is made up of collageDimension X collageDimension tiles
    // Imagine a collagePicture as a 2D array of tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    // Imagine a tile as a 2D array of pixels
    // A pixel has three components (red, green, and blue) that define the color 
    // of the pixel on the screen.
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 150
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public Collage (String filename) {

        collageDimension = 4;
        tileDimension = 150;
        originalPicture = new Picture(filename);
        Color bookBlue = new Color(0, 0, 0);
        collagePicture = new Picture(tileDimension*collageDimension,tileDimension*collageDimension);

        for(int i =0;i<collagePicture.width();i++){
          for(int j =0; j<collagePicture.height();j++){
              collagePicture.set(i, j, bookBlue);
          }
        }
        scale(originalPicture, collagePicture);
       
        
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */    
    public Collage (String filename, int td, int cd) {
        collageDimension = cd;
        tileDimension = td;
        originalPicture = new Picture(filename);
        collagePicture = new Picture(td*cd,cd*td);
        Color bookBlue1 = new Color(0, 0, 0);
        for(int i =0;i<collagePicture.width();i++){
            for(int j =0; j<collagePicture.height();j++){
                collagePicture.set(i, j, bookBlue1);
            }
          }
          scale(originalPicture, collagePicture);

    }


    /*
     * Scales the Picture @source into Picture @target size.
     * In another words it changes the size of @source to make it fit into
     * @target. Do not update @source. 
     *  
     * @param source is the image to be scaled.
     * @param target is the 
     */
    public static void scale (Picture source, Picture target) {
        
       
        int w, h;
        w = target.height();
        h = target.width();
       for (int tcol = 0; tcol < w; tcol++){
        for (int trow = 0; trow < h; trow++) {
        int scol = tcol * source.width() / w; int srow = trow * source.height() / h; Color color = source.get(scol, srow); target.set(tcol, trow, color);
        } 
        
      }

    }

     /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */   
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */    
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    
    public Picture getOriginalPicture() {
        return originalPicture;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    
    public Picture getCollagePicture() {
        return collagePicture;
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */    
    public void showOriginalPicture() {
        originalPicture.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */    
    public void showCollagePicture() {
	    collagePicture.show();
    }

    /*
     * Updates collagePicture to be a collage of tiles from original Picture.
     * collagePicture will have collageDimension x collageDimension tiles, 
     * where each tile has tileDimension X tileDimension pixels.
     */    
    public void makeCollage () {
       // Each Title pix in a pic name "tile"
Picture tile = new Picture(tileDimension,tileDimension);

// Scale orginal pic into tile pic
 scale(originalPicture, tile);

// Making Total tiles in the collage pic
collagePicture = new Picture(collageDimension*tileDimension,collageDimension*tileDimension);

// Putting the pixel into collage pic
int k,p;
int y = 0;
int o = 0;
for(k = 0;k<(tileDimension*collageDimension);k++){
    for(p =0; p<(tileDimension*collageDimension);p++){
        
        if(y== tileDimension ){
            y = 0;
           
        }
        if(o == tileDimension){
            o = 0;
        }
        collagePicture.set(k, p, tile.get(o, y));
        y++;
    }
    if( o== tileDimension){
      
        o = 0;
    }
    if( y== tileDimension){
      
        y = 0;
    }
    
    o++;
}

    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        //String f = component;
        //StdOut.println(component);
        int co = 0;
        if(component.equals("blue")){
            co = 1;
            
        }
         if ( component.equals("green") ){
            co = 2;
        }
        if(component.equals("red")){
            co = 3;
        }

        switch(co){
            case 1 : 
            int k = 0;
        int  p = 0;
       if(collageCol == 0 && collageRow == 0){
           
           for( int r = 0; r<(tileDimension);r++){
               for(int l = 0; l<(tileDimension);l++){
                   Color color = collagePicture.get(r,l);
                   int g  = color.getBlue();
                   collagePicture.set(r,l, new Color(0,0,g));
               } 
           }
       }else{
  
       for(int i = (tileDimension*collageCol);i<((tileDimension*collageCol)+(tileDimension));i++){
           for(int j = (tileDimension*collageRow);j<((tileDimension*collageRow)+(tileDimension));j++){
            int g;
            if(k== tileDimension ){
                k = 0;
                
            }
            Color color = collagePicture.get(i,j);
            g  = color.getBlue();
            collagePicture.set(i,j, new Color(0,0,g));
            
              k++; 
           }
           if(p== tileDimension ){
            p = 0;
           
           }
         
           p++;
       }
      
    }
            break;
            case 2: 
             k = 0;
             p = 0;
       if(collageCol == 0 && collageRow == 0){
           
           for( int r = 0; r<(tileDimension);r++){
               for(int l = 0; l<(tileDimension);l++){
                   Color color = collagePicture.get(r,l);
                   int g  = color.getGreen();
                   collagePicture.set(r,l, new Color(0,g,0));
               } 
           }
       }else{
  
       for(int i = (tileDimension*collageCol);i<((tileDimension*collageCol)+(tileDimension));i++){
           for(int j = (tileDimension*collageRow);j<((tileDimension*collageRow)+(tileDimension));j++){
            int g;
            if(k== tileDimension ){
                k = 0;
                
            }
            Color color = collagePicture.get(i,j);
            g  = color.getGreen();
            collagePicture.set(i,j, new Color(0,g,0));
            
              k++; 
           }
           if(p== tileDimension ){
            p = 0;
           
           }
         
           p++;
       }
    }
            break;
            case 3 : 
             k = 0;
             p = 0;
       if(collageCol == 0 && collageRow == 0){
           
           for( int r = 0; r<(tileDimension);r++){
               for(int l = 0; l<(tileDimension);l++){
                   Color color = collagePicture.get(r,l);
                   int g  = color.getRed();
                   collagePicture.set(r,l, new Color(g,0,0));
               } 
           }
       }else{
  
       for(int i = (tileDimension*collageCol);i<((tileDimension*collageCol)+(tileDimension));i++){
           for(int j = (tileDimension*collageRow);j<((tileDimension*collageRow)+(tileDimension));j++){
            int g;
            if(k== tileDimension ){
                k = 0;
                
            }
            Color color = collagePicture.get(i,j);
            g  = color.getRed();
            collagePicture.set(i,j, new Color(g,0,0));
            
              k++; 
           }
           if(p== tileDimension ){
            p = 0;
           
           }
         
           p++;
         }
        }
        break;

        } 
        
        

       
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        
        // Making a pic for Replace picture that is given
       Picture replac = new Picture(filename);

       // Making a blank size to pixel size
       Picture Ars = new Picture(tileDimension,tileDimension);

       // Scaling to fit the pic replace pic into tile pic size
       scale(replac, Ars);
       

       int k = 0;
       int p = 0;
       if(collageCol == 0 && collageRow == 0){
           for( int r = 0; r<Ars.width();r++){
               for(int l = 0; l<Ars.height();l++){
                   collagePicture.set(r, l, Ars.get(r, l));
               }
           }
       }else{

       for(int i = (tileDimension*collageCol);i<((tileDimension*collageCol)+(tileDimension));i++){
           
           for(int j = (tileDimension*collageRow);j<((tileDimension*collageRow)+(tileDimension));j++){
            
            if(k== tileDimension ){
                k = 0;
               
            }
            
            collagePicture.set(i, j, Ars.get(p, k));
              k++; 
           }
           if(p== tileDimension ){
            p = 0;
           
           }
         
           p++;
       }
    }

    }


    /*
     * Grayscale tile at (collageCol, collageRow)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void grayscaleTile (int collageCol, int collageRow) {

        int k = 0;
        int  p = 0;
       if(collageCol == 0 && collageRow == 0){
           for( int r = 0; r<(tileDimension);r++){
               for(int l = 0; l<(tileDimension);l++){
                Color color = collagePicture.get(r,l);
                 Color  g  = toGray(color);
                 collagePicture.set(r,l,g);
               } 
           }
       }else{

       for(int i = (tileDimension*collageCol);i<((tileDimension*collageCol)+(tileDimension));i++){
           
           for(int j = (tileDimension*collageRow);j<((tileDimension*collageRow)+(tileDimension));j++){
            
            if(k== tileDimension ){
                k = 0;
               
            }
            Color color = collagePicture.get(i,j);
                 Color g  = toGray(color);
                 collagePicture.set(i,j, g);
              k++; 
           }
           if(p== tileDimension ){
            p = 0;
           
           }
         
           p++;
       }
    }
        
       
    }

    /**
     * Returns the monochrome luminance of the given color as an intensity
     * between 0.0 and 255.0 using the NTSC formula
     * Y = 0.299*r + 0.587*g + 0.114*b. If the given color is a shade of gray
     * (r = g = b), this method is guaranteed to return the exact grayscale
     * value (an integer with no floating-point roundoff error).
     *
     * @param color the color to convert
     * @return the monochrome luminance (between 0.0 and 255.0)
     */
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b) return r;   // to avoid floating-point issues
        return 0.299*r + 0.587*g + 0.114*b;
    }

    /**
     * Returns a grayscale version of the given color as a {@code Color} object.
     *
     * @param color the {@code Color} object to convert to grayscale
     * @return a grayscale version of {@code color}
     */
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color)));   // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }

    /*
     * Closes the image windows
     */
    public void closeWindow () {
        if ( originalPicture != null ) {
            originalPicture.closeWindow();
        }
        if ( collagePicture != null ) {
            collagePicture.closeWindow();
        }
    }
}
