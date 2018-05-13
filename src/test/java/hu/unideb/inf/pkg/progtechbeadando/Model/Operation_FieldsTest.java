/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.pkg.progtechbeadando.Model;


import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.add;
import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.indexeles;
import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.kezdoMatrix;
import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.konfiguracio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juhász Gyula
 */
public class Operation_FieldsTest {
    
    @Test
    public void testkezdoMatrix(){
        int[][] testaktualisMatrix=new int[8][8];
        int[][] aktualisMatrix = kezdoMatrix(8,8);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertTrue(testaktualisMatrix[i][j]==aktualisMatrix[i][j]);

            }
        }
    
    }
    
    @Test
    public void testadd(){
        int[][] testMatrix = new int[8][8];
        int nullak=0;
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){                
                if(testMatrix[i][j] == 0 ){
                    nullak++;
                }
            }
        }
        assertTrue(nullak==64);
        add(testMatrix);
        int nullak2=0;
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){                
                if(testMatrix[i][j] == 0 ){
                    nullak2++;
                }
            }
        }
        assertTrue(nullak2==63);
    }
    
    @Test
    public void testkonfiguracio(){
        int[][] konfigMatrix = null;
        konfigMatrix=konfiguracio("kezdő");
        int[][]testMatrix= new int[8][8];
        int test=0;
        int darab=0;
        int testdarab=0;
        while(test<10){
            test++;
            add(testMatrix);
        }
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(konfigMatrix[i][j]==0){
                    darab++;
                }
            }
        }
    
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){      
                if(testMatrix[i][j]==0){
                    testdarab++;
                }
            }
        }
        
        assertTrue(darab==testdarab);
    }
    
    @Test
    public void testIndexeles(){
        int[][] testMatrix={{0,0,0,0,0,0,0,9},{0,0,0,0,0,9,0,0},{0,0,9,0,0,0,0,9},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{9,0,0,0,0,0,0,9},{9,0,0,0,0,0,0,9},{0,0,0,0,0,0,0,0}};
        int[][] testMatrix2={{9,0,0,0,0,0,0,0},{0,0,0,0,0,9,0,0},{0,0,0,0,0,0,0,9},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{9,0,0,0,0,0,0,9},{0,0,0,0,0,0,0,9},{9,0,0,0,0,0,0,9}};
        indexeles(testMatrix);
        indexeles(testMatrix2);
        int[][] eredmenyMatrix={{0,0,0,0,1,1,2,9},{0,1,1,1,1,9,3,2},
            {0,1,9,1,1,1,2,9},{0,1,1,1,0,0,1,1},{1,1,0,0,0,0,1,1},{9,2,0,0,0,0,2,9},
            {9,2,0,0,0,0,2,9},{1,1,0,0,0,0,1,1}};
        int[][] eredmenyMatrix2={{9,1,0,0,1,1,1,0},{1,1,0,0,1,9,2,1},{0,0,0,0,1,1,2,9},{0,0,0,0,0,0,1,1},{1,1,0,0,0,0,1,1},{9,1,0,0,0,0,2,9},{2,2,0,0,0,0,3,9},{9,1,0,0,0,0,2,9}};

        
        for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){                
                assertTrue(testMatrix[i][j] == eredmenyMatrix[i][j]);
            }
        }
                for(int i = 0;i<8;i++){
            for(int j= 0;j<8;j++){                
                assertTrue(testMatrix2[i][j] == eredmenyMatrix2[i][j]);
            }
        }
        
    }
}

