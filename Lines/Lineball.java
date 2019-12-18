//================================================================
//name:    Bui Ngoc Hai,Tran Minh Hung,Vu Dinh Tuan,Dam Van Chung
//class:   K51CD nhom 3
//project: Bai tap lon 2
//================================================================

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
    public int ball[][]= new int[MaxCell][MaxCell]; // Mang the hien trang thai cua bang 
    public int balltmp[][]= new int[MaxCell][MaxCell]; // Mang luu lai trang thai cua bang sau moi~ buoc 	  	     	
   
    public point[]PathBall=new point [MaxCell*MaxCell] ; //Mang luu duong di cua bong
    public int []nextcolor=new int[3];//Mang luu 3 mau se~ xuat hien
    public int []nextcolortmp=new int[3];//Mang luu lai 3 mau se~ xuat hien sau moi~ buoc
    public int nCountPath;
    public double MarkResult;// Tong diem
    public double MarkResultTemp;// Luu lai tong diem sau moi buoc   
    boolean gameover;	
    
	//------------------------------------------------------------------
    public Lineball() {   	
        //... 		
    }

    public void StartGame(){
            // Khoi tao mang ban dau    
             	
    	    for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)         	            		     
                    ball[i][j]=0;  
                           	    	                                              	 
           	MarkResult=0;  
           	gameover=false;	  	    	
                 	    
            // Dat vao 6 qua bong dau tien	
            int i,j;
           	point [] FreeCell = new point[3]; 
           	Random random = new Random();           	        
 	  	
           	// Dat vao 3 bong to	
				if (RandomBall(3,FreeCell))					
				   for (int k=0; k < 3; k++){
					
              		   i = FreeCell[k].x;
					   j = FreeCell[k].y;											
					   ball[i][j]=random.nextInt(MaxColor)+1;
				   }			    
			       else System.out.println("Game over!");
                   			
            // Dat vao 3 bong nho
  			if (RandomBall(3,FreeCell))					
				   for (int k=0; k < 3; k++){
					
              		   i = FreeCell[k].x;
					   j = FreeCell[k].y;											
					   ball[i][j]=random.nextInt(MaxColor)+MaxColor+1;
				   }			    
			       else System.out.println("Game over!");
	 
		    //luu lai trang thai cua bang
		   for ( i=0;i<MaxCell;i++)
           	    for (j=0;j<MaxCell;j++)          	            		                         
                    balltmp[i][j]=ball[i][j];
		   // Sinh random 3 mau se~ xuat hien           	    	      				           		 	           		            		 	
           new3color();	  
           for (int k=0; k < 3; k++)
                nextcolortmp[k]=nextcolor[k];	         		 	
    }    
    //-------------------------------------------------------------------
    public void new3color() {  //tao 3 mau sap xuat hien	        
        Random random = new Random();   
        for (int k=0; k < 3; k++)
        	nextcolor[k]=random.nextInt(MaxColor)+1;	
    }
    public void new3Balls(){    
            // Chuyen  bong to thanh bong nho
    		 for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)
           	    	if(ball[i][j]>MaxColor)           	      		     
                      ball[i][j]-=(MaxColor);   
   				             	      		  
   			// va ve~them 3 bong nho tuong voi 3 mau trong nextcolor[3]	             	      		                                   	   
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
			// Sinh random 3 mau se~ xuat hien	       				           		 	           		            		 	
           new3color();
    }

  //-------------------------------------------------------------------
    	
     public boolean RandomBall(int nBall,point [] ResultBall ) {//Ham` chon n o ngau nhien
    	int ncountFreeBall=0;//So o con` trong'
    	point [] CheckCell=new point [MaxCell*MaxCell];// Luu cac o chua co bong
    	boolean [] BoolCheckCell=new boolean [MaxCell*MaxCell];//Danh dau' cac o chua co bong 
    	
    	for (int i=0;i<MaxCell;i++)
           	for (int j=0;j<MaxCell;j++)
           		 if ((ball[i][j])==0){// Neu o chua co bong
           		 
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
        	
        	  x=random.nextInt(ncountFreeBall);//chon ngau nhien 1 o trong'
        	  if (BoolCheckCell[x]){
        	  	
        	  	  BoolCheckCell[x]=false;
        	  	  ResultBall[nCount++]=CheckCell[x];
        	  	
        	     }        	  	        	
        }	
        return true;			            		         	   
	}
    //--------------------------------------------------------------------
    //lui lai trang thai truoc cua bang
    public void Undo(){
    	
    	   for (int i=0;i<MaxCell;i++)
           	    for (int j=0;j<MaxCell;j++)         	            		     
                    ball[i][j]=balltmp[i][j];  
        	                             	    	 
           for (int k=0; k < 3; k++)
           nextcolor[k]=nextcolortmp[k];
           
           MarkResult = MarkResultTemp;
    }
    	
    //-------------------------------------------------------------------     
    //luu trang thai truoc cua bang
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
    	
    //-------------------------------------------------------------------       
         
       
     public boolean cutBall(){
    	int NumCutBall = 0;//So bong bi cut
    	int nBall;
    	boolean CheckBall[][]=new boolean[MaxCell][MaxCell]; 
    	point[]TempBall=new point [MaxCell];    		   	
    	point[]CellBall=new point [MaxCell*MaxCell];//Mang luu lai toa do cac bong bi cut
    	int i, j,nRow, nCol, nCount;
    	
    	for (i =0; i < MaxCell; i++)
			for (j=0; j < MaxCell; j++)
			    CheckBall[i][j] = false;
			    
		for (nRow=0; nRow < MaxCell; nRow++)
			for (nCol=0; nCol < MaxCell; nCol++)	    
				if (ball[nRow][nCol] > 0 && !CheckBall[nRow][nCol]){
						
				     	nBall = ball[nRow][nCol];
						//Xet' hang` doc
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

						//Xet' hang` ngang
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
						
						//Xet hang cheo' trai' 
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
						//Xet/ hang` cheo' phai 
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
    
   
    //-------------------------------------------------------------------   
    //Luu lai duong di	 
    public void FindPath(point p, point [][] PathBallTemp)
		{
			if(p.x!=-1 && p.y!=-1)
		       if (PathBallTemp[p.x][p.y] != new point(-1,-1))
				FindPath(PathBallTemp[p.x][p.y],PathBallTemp);												
			PathBall[nCountPath++]=p;
		}	     	
    //-------------------------------------------------------------------
    //tham khao tai trang web http://my.opera.com/hodawa/blog/viettrochoiline
    public boolean Loang(int si, int sj, int fi, int fj){ // Loang de tim duong di tu (si,sj)-->(fi,fj);
     
     	int [] di = {-1, 1, 0, 0};
	    int [] dj = {0 , 0,-1, 1};
	    int i, j, k, nCount;
	    point pStart, pFinish, pCurrent;
	    point [][] Query = new point[ 2][ MaxCell * MaxCell ];//2 hang doi de loang
	    point [][] PathBallTemp = new point[ MaxCell][MaxCell ];//Mang luu cac o da di qua
	    boolean [][]ballCheck=new boolean[MaxCell][MaxCell];//Mang danh dau ca o da xet
	 
		pStart = new point(si, sj);//O bat dau
		pFinish = new point(fi, fj);//O ket thuc
		
		//Cho pSart vao` hang doi
		int nQuery = 1;		
		Query[0][0] = pStart;
		
		//Danh dau cac o da~ co bong
		for (i=0; i < MaxCell; i++)
			for (j=0; j < MaxCell; j++)
				if (ball[i][j]>0 && ball[i][j]<8)
			    	ballCheck[i][j] = true; 
			        else ballCheck[i][j] = false; 	
					
		ballCheck[pStart.x][pStart.y] = true;
		if (ballCheck[fi][fj])  
			return false;
		//Loang de tim duong di	
		PathBallTemp[si][sj] = new point(-1,-1);
		while (nQuery > 0)
			{
				nCount = 0;
				for (int nLast=0; nLast < nQuery; nLast++)
				{
					pCurrent = Query[0][nLast ];				
					//Tim xung quanh 4 huong' cua o (i, j) xem co huong nao` co' the di duoc khong ?
					for (k=0; k < 4; k++)
					{
						i = pCurrent.x + di[k];
						j = pCurrent.y + dj[k];
						if (i >= 0 && i < MaxCell && j >=0 & j < MaxCell && !ballCheck[i][j]){	
													
							Query[1][nCount++] = new point( i, j);
							ballCheck[i][j] = true;
							PathBallTemp[i][j] = new point(pCurrent.x, pCurrent.y);
							//Tim tay o dich, thi dung tim kiem
							if (ballCheck[fi][fj]){										
								nCountPath = 0;	
								FindPath(new point(fi,fj),PathBallTemp);					
								return true;
							}
							
						}
					}
				}	
				//Bo cac' ptu cua Query[1] vao Query[0] de tiep' tuc loang
				for (k=0; k < nCount; k++)
					Query[0][k ] = Query[1][k ];
				nQuery = nCount;				
			}												
	    
		return false;	
     }
//    public void Test(){// Test ham loang  
//       // Dat vao them  bong de test 
//       int i,j;
//       point [] FreeCell = new point[20]; 
//       Random random = new Random();           	        
// 	  	                  		
//	   if (RandomBall(20,FreeCell))					
//		   for (int k=0; k < 20; k++){
//					
//               i = FreeCell[k].x;
//			   j = FreeCell[k].y;											
//		       ball[i][j]=random.nextInt(MaxColor)+1;
//		  }	
//		  			    			                      			 			
//   	  DrawBall();
//   	  CutBall();
//   	  System.out.println("------CutBall------");
//   	  DrawBall();		           
//      if(Loang(1,1,8,8)){
//       System.out.println("Co duong di tu [1,1]-->[8,8] do dai:" +(nCountPath-1));         
//       for (int k=1;k<nCountPath;k++)
//       	   System.out.print("[" + PathBall[k].x + "," + PathBall[k].y +"]--->");
//       	   System.out.print("[" + PathBall[nCountPath-1].x + "," + PathBall[nCountPath-1].y +"]");	    
//     }   
//     else	
//       System.out.println("ko co duong di:");    
//    }
    //-------------------------------------------------------------------
//    	 
//    public static void main(String[] args) {
//    	Lineball test = new Lineball();
//    	test.StartGame();    	    	  
//    	test.Test();	            	    		           			    	
//    }
}