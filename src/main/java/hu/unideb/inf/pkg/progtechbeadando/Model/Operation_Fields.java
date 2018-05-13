/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.pkg.progtechbeadando.Model;

import java.util.ArrayList;
import java.util.Random;

public class Operation_Fields {
     
    
    public static int[][] kezdoMatrix(int n, int m) {
        int[][] aktualisMatrix = new int[n][m];
             return aktualisMatrix ;
    }
    public static void add(int aktualisMatrix[][]){
        int nullak = 0;
        ArrayList<Number> indexsor = new ArrayList<>();
        ArrayList<Number> indexoszlop = new ArrayList<>();
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){
                
                if(aktualisMatrix[i][j] == 0 ){
                    nullak++;
                    indexsor.add(i);
                    indexoszlop.add(j);
                }
            }
        }
        if(nullak!=0){ 
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(indexsor.size());
            int index2 = randomGenerator.nextInt(indexoszlop.size());
            int valasztottindexsor = (int) indexsor.get(index);
            int valasztottindexoszlop = (int) indexoszlop.get(index2);
            while(aktualisMatrix[valasztottindexsor][valasztottindexoszlop]==9){
                 index = randomGenerator.nextInt(indexsor.size());
                 index2= randomGenerator.nextInt(indexoszlop.size());
                 valasztottindexsor=(int) indexsor.get(index);
                 valasztottindexoszlop=(int) indexoszlop.get(index2);
            }
            aktualisMatrix[valasztottindexsor][valasztottindexoszlop]=9; 
            
        }
      
    }
    public static int[][] konfiguracio(String i){
        int[][] aktualisMatrix=null;
        int x = 0;
        
        if("kezdő".equals(i)){
            aktualisMatrix =kezdoMatrix(8,8);
            while(x<10){
                add(aktualisMatrix);
                x++;
            }
        }
        return aktualisMatrix;
    }
    public static void indexeles(int aktualisMatrix[][]){
        int nullak = 0;
        ArrayList<Number> indexsor;
        indexsor = new ArrayList<>();
        ArrayList<Number> indexoszlop;
        indexoszlop= new ArrayList<>();
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){
                
                if(aktualisMatrix[i][j] == 9 ){
                    nullak++;
                    indexsor.add(i);
                    indexoszlop.add(j);
                }
            }
        }
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){
                if(aktualisMatrix[i][j] == 9 ){
                    switch (i) {
                        case 0:
                            boolean egyezikElsoSor= aktualisMatrix[i][j]!=aktualisMatrix[i+1][j];
                            if(egyezikElsoSor==true ){
                                aktualisMatrix[i+1][j]++;}
                                switch(j){
                                    case 0:
                                        if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j+1]){
                                            aktualisMatrix[i+1][j+1]++;
                                        }
                                        break;
                                    case 7:
                                        if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j-1]){
                                            aktualisMatrix[i+1][j-1]++;
                                        }
                                        break;
                                    default:
                                        if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j+1]){
                                            aktualisMatrix[i+1][j+1]++;}
                                        if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j-1]){
                                            aktualisMatrix[i+1][j-1]++;}
                                        break;}
                            break;
                        case 7:
                            boolean egyezikUtolsoSor= aktualisMatrix[i][j]!=aktualisMatrix[i-1][j];
                            if(egyezikUtolsoSor==true && aktualisMatrix[i-1][j]!=9){
                                aktualisMatrix[i-1][j]++;}
                            switch(j){
                                case 0:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j+1]){
                                        aktualisMatrix[i-1][j+1]++;
                                    }
                                    break;
                                case 7:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j-1]){
                                        aktualisMatrix[i-1][j-1]++;
                                    }
                                    break;
                                default:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j+1]){
                                        aktualisMatrix[i-1][j+1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j-1]){
                                        aktualisMatrix[i-1][j-1]++;}
                                    break;
                            }
                            break;
                        default:
                            if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j]){
                                aktualisMatrix[i-1][j]++;}
                            if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j]){
                                aktualisMatrix[i+1][j]++;}
                            switch(j){
                                case 0:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j+1]){
                                        aktualisMatrix[i-1][j+1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j+1]){
                                        aktualisMatrix[i+1][j+1]++;}
                                    break;
                                case 7:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j-1]){
                                        aktualisMatrix[i-1][j-1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j-1]){
                                        aktualisMatrix[i+1][j-1]++;}                                  
                                    break;
                                default:
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j-1]){
                                        aktualisMatrix[i-1][j-1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i-1][j+1]){
                                        aktualisMatrix[i-1][j+1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j+1]){
                                        aktualisMatrix[i+1][j+1]++;}
                                    if(aktualisMatrix[i][j]!=aktualisMatrix[i+1][j-1]){
                                        aktualisMatrix[i+1][j-1]++;}
                                    break;
                            }                            
                        break;
                    }
                    switch(j){
                        case 0:
                            boolean egyezikElsoOszlop=aktualisMatrix[i][j]!=aktualisMatrix[i][j+1];
                            if(egyezikElsoOszlop==true && aktualisMatrix[i][j+1]!=9){
                                aktualisMatrix[i][j+1]++;}
                            break;
                        case 7:
                            boolean egyezikUtolsoOszlop=aktualisMatrix[i][j]!=aktualisMatrix[i][j-1];
                            if(egyezikUtolsoOszlop==true && aktualisMatrix[i][j-1]!=9)
                                aktualisMatrix[i][j-1]++;  
                            break;
                        default:
                            if(aktualisMatrix[i][j]!=aktualisMatrix[i][j-1]){
                                aktualisMatrix[i][j-1]++;}
                            if(aktualisMatrix[i][j]!=aktualisMatrix[i][j+1]){
                                aktualisMatrix[i][j+1]++;}
                            break;
                    }
                }
            }
        }
    }
 
}


