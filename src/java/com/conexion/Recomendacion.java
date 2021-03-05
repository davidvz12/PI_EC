/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.util.ArrayList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author BRYAN MENDOZA EL PATRON
 */
public class Recomendacion {
    
    public static double[] recomendacion(ArrayList<Usuario> entrenamiento,ArrayList<Usuario> prueba)throws Exception {
  
     double [] comidas= new double [entrenamiento.size()];
     double [] calificacion= new double[entrenamiento.size()];
     double [] categoria= new double[entrenamiento.size()];
     double [] establecimiento=new double[entrenamiento.size()];
     for (int i = 0; i < entrenamiento.size(); i++) {
            comidas[i]=entrenamiento.get(i).getPlato();
            categoria[i]=entrenamiento.get(i).getCategoria();
            calificacion[i]=entrenamiento.get(i).getCalificacion();
            establecimiento[i]=entrenamiento.get(i).getEstablecimiento();
     }
     //encabezado de mi dataset
        Attribute atrplatos= new Attribute("platos");
        Attribute atrcompras= new Attribute("categorias");
        Attribute atrestablecimiento= new Attribute("establecimiento");
        Attribute atrcalificacion= new Attribute("calificacion");
 
        FastVector atts = new FastVector();
        atts.addElement(atrplatos);
        atts.addElement(atrcompras);
        atts.addElement(atrestablecimiento);
        atts.addElement(atrcalificacion);
        //creacion del dataset sin datos 
        Instances dataset = new Instances("Dataset", atts, 0);
        // Rellenamos datos dentro de nuestro dataset
        for (int i = 0; i <comidas.length ; i++) {
            Instance instancia= new Instance(4);
            instancia.setValue(atrplatos,comidas[i]);
            instancia.setValue(atrcompras,categoria[i]);
            instancia.setValue(atrestablecimiento, establecimiento[i]);
            instancia.setValue(atrcalificacion,calificacion[i]);
            dataset.add(instancia);
        }
        System.out.println(dataset);
         //Dataset de nuevos platos plato categoria calificación
        double [] comidasdes= new double [prueba.size()];
        double [] calificaciondes= new double[prueba.size()];
        double [] establecimientodesc= new double[prueba.size()];
        double [] compradodesc= new double[prueba.size()];
        for (int i = 0; i < prueba.size(); i++) {
            comidasdes[i]=prueba.get(i).getPlato();
            compradodesc[i]=prueba.get(i).getCategoria();
            establecimientodesc[i]=prueba.get(i).getEstablecimiento();
            calificaciondes[i]=prueba.get(i).getCalificacion();
     }    
        Attribute atrplatosdesc= new Attribute("platos");
        Attribute atrcomprasdesc= new Attribute("categoria");
        Attribute atrestablecimientodesc= new Attribute("establecimiento");
        Attribute atrcalificaciondesc= new Attribute("calificacion");
        
        FastVector attsd = new FastVector();
        attsd.addElement(atrplatosdesc);
        attsd.addElement(atrcomprasdesc);
        attsd.addElement(atrestablecimientodesc);
        attsd.addElement(atrcalificaciondesc);
        Instances datasetdesc = new Instances("Datasetdesc", attsd, 0);
        // Rellenamos datos.
        
        for (int i = 0; i <comidasdes.length ; i++) {
            Instance instan= new Instance(4);
            instan.setValue(atrplatosdesc,comidasdes[i]);
            instan.setValue(atrcomprasdesc,compradodesc[i]);
            instan.setValue(atrestablecimientodesc,establecimientodesc[i]);
            instan.setValue(atrcalificaciondesc,calificaciondes[i]);
            datasetdesc.add(instan);
        }
        System.out.println(datasetdesc);
        
        if (dataset.classIndex() == -1) {
            System.out.println("reset index...");
            dataset.setClassIndex(dataset.numAttributes() - 1);
        }  
        System.out.println(dataset);
        SMOreg regresion= new SMOreg();
        regresion.buildClassifier(dataset);
        LinearRegression linearregresion= new LinearRegression();
        linearregresion.buildClassifier(dataset);
        
       datasetdesc.setClassIndex(datasetdesc.numAttributes()-1);
       List<String> platosrecomender=new ArrayList<>();
       List<String> establecimientosrecomender=new ArrayList<>();
       double [] recomendar= new double [prueba.size()];
       for (int i = 0; i < datasetdesc.numInstances(); i++) {
            Instance newInst=datasetdesc.instance(i);
            double regres=regresion.classifyInstance(newInst);
            double prediccion2=linearregresion.classifyInstance(newInst);
            System.out.println("plato: " +String.valueOf(comidasdes[i])+" calificacion "+String.valueOf(prediccion2)+ " modelo "+String.valueOf(regres));
            if(regres > 4.5){
                recomendar[i]=comidasdes[i];
                System.out.println("plato recomendado : "+String.valueOf(recomendar[i]));
                platosrecomender.add(String.valueOf(recomendar[i]));
                establecimientosrecomender.add(String.valueOf(establecimientodesc[i]));
            }
        }
           double[] recomendacionfinal= new double[platosrecomender.size()];
           for (int j = 0; j < platosrecomender.size(); j++) {
               recomendacionfinal[j]=Double.valueOf(platosrecomender.get(j));
               //recomendacionfinal[j][1]=Double.valueOf(establecimientosrecomender.get(j));
           }
  return recomendacionfinal;
 }
    
}
