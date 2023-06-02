package Lines;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Lineball{

 	public class point{
		   public int x,y;//Toa do		  
		   public point(int xx,int yy){
		   	      x=xx;
		   	      y=yy;		   
		   }
	}	
    public final int MaxCell = 9;
    public final int MaxColor = 7;    
    public int ball[][]= new int[MaxCell][MaxCell]; 
    public int balltmp[][]= new int[MaxCell][MaxCell];  	  	     	
   
    public point[]PathBall=new point [MaxCell*MaxCell] ; 
    public int []nextcolor=new int[3];
    public int []nextcolortmp=new int[3];
    public int nCountPath;
    public double MarkResult;
    public double MarkResultTemp;
    boolean gameover;	
    
	
    public Lineball() {   	
        		
    }

    public void StartGame(){
            
             	
    	    for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)         	            		     
                    ball[i][j]=0;  
                           	    	                                              	 
           	MarkResult=0;  
           	gameover=false;	  	    	
                 	    
            
            int i,j;
           	point [] FreeCell = new point[3]; 
           	Random random = new Random();           	        
 	  	
           	
				if (RandomBall(3,FreeCell))					
				   for (int k=0; k < 3; k++){
					
              		   i = FreeCell[k].x;
					   j = FreeCell[k].y;											
					   ball[i][j]=random.nextInt(MaxColor)+1;
				   }			    
			       else System.out.println("Game over!");
                   			
          
  			if (RandomBall(3,FreeCell))					
				   for (int k=0; k < 3; k++){
					
              		   i = FreeCell[k].x;
					   j = FreeCell[k].y;											
					   ball[i][j]=random.nextInt(MaxColor)+MaxColor+1;
				   }			    
			       else System.out.println("Game over!");
	 
		  
		   for ( i=0;i<MaxCell;i++)
           	    for (j=0;j<MaxCell;j++)          	            		                         
                    balltmp[i][j]=ball[i][j];
		             	    	      				           		 	           		            		 	
           new3color();	  
           for (int k=0; k < 3; k++)
                nextcolortmp[k]=nextcolor[k];	         		 	
    }    
    
    public void new3color() {  	        
        Random random = new Random();   
        for (int k=0; k < 3; k++)
        	nextcolor[k]=random.nextInt(MaxColor)+1;	
    }
    public void new3Balls(){    
            
    		 for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)
           	    	if(ball[i][j]>MaxColor)           	      		     
                      ball[i][j]-=(MaxColor);   
   				             	      		  
   			           	      		                                   	   
    	    int i,j;
           	point [] FreeCell = new point[3]; 
            Random random = new Random(); 		
    	  	if (RandomBall(3,FreeCell))					
				  for (int k=0; k < 3; k++){
					
              		  i = FreeCell[k].x;
					  j = FreeCell[k].y;											
					 ball[i][j]=nextcolor[k]+MaxColor;
				  }			    
			      else gameover=true;
			       				           		 	           		            		 	
           new3color();
    }

  
    	
     public boolean RandomBall(int nBall,point [] ResultBall ) {
    	int ncountFreeBall=0;
    	point [] CheckCell=new point [MaxCell*MaxCell];
    	boolean [] BoolCheckCell=new boolean [MaxCell*MaxCell];
    	
    	for (int i=0;i<MaxCell;i++)
           	for (int j=0;j<MaxCell;j++)
           		 if ((ball[i][j])==0){
           		 
           		     CheckCell[ncountFreeBall]=new point(i,j);
                     BoolCheckCell[ncountFreeBall]=true;           		                      	
           		 	 ncountFreeBall++;           		 
           		 	 	
           	     }	 
           	     else
                     BoolCheckCell[ncountFreeBall]=false;           		                      	           	     		
        if (ncountFreeBall<nBall) return false; 
        	 
        Random random = new Random();
        int x;
        int nCount=0;
        while (nCount < nBall){
        	
        	  x=random.nextInt(ncountFreeBall);
        	  if (BoolCheckCell[x]){
        	  	
        	  	  BoolCheckCell[x]=false;
        	  	  ResultBall[nCount++]=CheckCell[x];
        	  	
        	     }        	  	        	
        }	
        return true;			            		         	   
	}
    
    public void Undo(){
    	
    	   for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)         	            		     
                    ball[i][j]=balltmp[i][j];  
        	                             	    	 
           for (int k=0; k < 3; k++)
           nextcolor[k]=nextcolortmp[k];
           
           MarkResult = MarkResultTemp;
    }
    	
    
    public void saveUndo(){
    	
    	   for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)
           	    	if (ball[i][j]>2*MaxColor)         	    
           	    	    balltmp[i][j]=ball[i][j]-2*MaxColor; 
           	    	else	         	            		     
                        balltmp[i][j]=ball[i][j]; 
          for (int k=0; k < 3; k++)
                nextcolortmp[k]=nextcolor[k];
		  
		  MarkResultTemp = MarkResult;          	                           
                    	                              	    	 
    }
    	
    
         
       
     public boolean cutBall(){
    	int NumCutBall = 0;
    	int nBall;
    	boolean CheckBall[][]=new boolean[MaxCell][MaxCell]; 
    	point[]TempBall=new point [MaxCell];    		   	
    	point[]CellBall=new point [MaxCell*MaxCell];
    	int i, j,nRow, nCol, nCount;
    	
    	for (i =0; i < MaxCell; i++)
			for (j=0; j < MaxCell; j++)
			    CheckBall[i][j] = false;
			    
		for (nRow=0; nRow < MaxCell; nRow++)
			for (nCol=0; nCol < MaxCell; nCol++)	    
				if (ball[nRow][nCol] > 0 && !CheckBall[nRow][nCol]){
						
				     	nBall = ball[nRow][nCol];
						
						i = nRow;
						j = nCol;
						while (i > 0 && ball[ i-1][j] == nBall)
							i--;						
						nCount = 0;						
						while (i < MaxCell && ball[i][j] == nBall){
							
							CheckBall[i][j] = true;
							TempBall[nCount++] = new point(i ,j);
							i++;
							
						}
						if (nCount >= 5){				
							for (i=0; i < nCount; i++)
								CellBall[NumCutBall++] = TempBall[i];

							MarkResult+=(nCount-4)*nCount;
														
						   }		

						
						i = nRow;
						j = nCol;
						while (j > 0 && ball[i][j-1] == nBall)
							j--;						
						nCount = 0;						
						while (j < MaxCell && ball[i][j] == nBall){
							
							CheckBall[i][j] = true;
							TempBall[nCount++] = new point(i ,j);
							j++;
							
						}
						if (nCount >= 5){		
							for (i=0; i < nCount; i++)
								CellBall[NumCutBall++] = TempBall[i];

							MarkResult+=(nCount-4)*nCount;
		
						}                      
						
						
						i = nRow;
						j = nCol;
						while (i > 0 && j > 0 && ball[i-1][j-1] == nBall){
							
							i--;
							j--;
													
						}
						nCount = 0;						
						while (i < MaxCell &&  j < MaxCell && ball[i][j] == nBall){
							
							CheckBall[i][j] = true;
							TempBall[nCount++] = new point(i ,j);
							i++;
							j++;
							
						}
						if (nCount >= 5){							
							for (i=0; i < nCount; i++)
								CellBall[NumCutBall++] = TempBall[i];													
							
							MarkResult+=(nCount-4)*nCount;
		
						   }		
						
						i = nRow;
						j = nCol;
						while (i > 0 && j+1 < MaxCell && ball[i-1][j+1] == nBall){
							
							i--;
							j++;
													
						}
						nCount = 0;						
						while (i < MaxCell &&  j >= 0 && ball[i][j] == nBall){
							
							CheckBall[i][j] = true;
							TempBall[nCount++] = new point(i ,j);
							i++;
							j--;
							
						}
						if (nCount >= 5){							
							for (i=0; i < nCount; i++)
								CellBall[NumCutBall++] = TempBall[i];																				
							
							MarkResult+=(nCount-4)*nCount;
		
						}                      
			
				}
					for (i=0; i < NumCutBall; i++)
				        ball[CellBall[i].x][CellBall[i].y ] = 0;
				    if (NumCutBall>0) return true;
				    	else return false;    	
    	
    }  
    
   
    
    public void FindPath(point p, point [][] PathBallTemp)
		{
			if(p.x!=-1 && p.y!=-1)
		       if (PathBallTemp[p.x][p.y] != new point(-1,-1))
				FindPath(PathBallTemp[p.x][p.y],PathBallTemp);												
			PathBall[nCountPath++]=p;
		}	     	
   
    public boolean Loang(int si, int sj, int fi, int fj){ 
     
     	int [] di = {-1, 1, 0, 0};
	    int [] dj = {0 , 0,-1, 1};
	    int i, j, k, nCount;
	    point pStart, pFinish, pCurrent;
	    point [][] Query = new point[ 2][ MaxCell * MaxCell ];
	    point [][] PathBallTemp = new point[ MaxCell][MaxCell ];
	    boolean [][]ballCheck=new boolean[MaxCell][MaxCell];
	 
		pStart = new point(si, sj);
		pFinish = new point(fi, fj);
		
		
		int nQuery = 1;		
		Query[0][0] = pStart;
		
		
		for (i=0; i < MaxCell; i++)
			for (j=0; j < MaxCell; j++)
				if (ball[i][j]>0 && ball[i][j]<8)
			    	ballCheck[i][j] = true; 
			        else ballCheck[i][j] = false; 	
					
		ballCheck[pStart.x][pStart.y] = true;
		if (ballCheck[fi][fj])  
			return false;
	
		PathBallTemp[si][sj] = new point(-1,-1);
		while (nQuery > 0)
			{
				nCount = 0;
				for (int nLast=0; nLast < nQuery; nLast++)
				{
					pCurrent = Query[0][nLast ];				
					
					for (k=0; k < 4; k++)
					{
						i = pCurrent.x + di[k];
						j = pCurrent.y + dj[k];
						if (i >= 0 && i < MaxCell && j >=0 & j < MaxCell && !ballCheck[i][j]){	
													
							Query[1][nCount++] = new point( i, j);
							ballCheck[i][j] = true;
							PathBallTemp[i][j] = new point(pCurrent.x, pCurrent.y);
							
							if (ballCheck[fi][fj]){										
								nCountPath = 0;	
								FindPath(new point(fi,fj),PathBallTemp);					
								return true;
							}
							
						}
					}
				}	
				
				for (k=0; k < nCount; k++)
					Query[0][k ] = Query[1][k ];
				nQuery = nCount;				
			}												
	    
		return false;	
     }

}