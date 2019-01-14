/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tg.codeart.geco.ng.web.reports;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pellage
 */
public class ConstructeurObjetEtatAvecSomme<T extends EtatAvecSomme> extends Thread {
    private List listeElement;
    private Class resultatClass;
    private List<T> listeResultat;
    private Map<String, BigDecimal> sommeDesChamps;
    
    public ConstructeurObjetEtatAvecSomme(List liste, List resultat, T temoin){
        this.listeResultat = resultat == null ? new ArrayList() : resultat;
        this.listeElement = liste;
        this.resultatClass = temoin.getClass();
        this.listeResultat = resultat;
        this.sommeDesChamps = new HashMap();
        for(String tmp : temoin.listeDesChapsASommer()){
            this.sommeDesChamps.put(tmp, BigDecimal.ZERO);
        }
    }
    
    public Map<String, BigDecimal> getSommeDesChamps(){
        return this.sommeDesChamps;
    }
    
    public List getListeElement() {
        return listeElement;
    }

    public void setListeElement(List listeElement) {
        this.listeElement = listeElement;
    }

    public Class getResultatClass() {
        return resultatClass;
    }

    public void setResultatClass(Class resultatClass) {
        this.resultatClass = resultatClass;
    }

    public List<T> getListeResultat() {
        return listeResultat;
    }

    public void setListeResultat(List<T> listeResultat) {
        this.listeResultat = listeResultat;
    }
    
    @Override
    public void run(){
        Class[] t = new Class[1];
        t[0] = Object[].class;
        int tail = this.listeElement.size();
        try{
            Constructor construct = this.resultatClass.getConstructor(t);
            T element;
            for(int i = 0;i<tail;i++){
                element = (T)construct.newInstance(this.listeElement.get(i));
                this.listeResultat.add(element);
                this.effectuerLesSommes(element);
//                if((i % 5) == 0){
//                    try{
//                        Thread.sleep(10);
//                    }
//                    catch(InterruptedException e){
//                        System.err.println(e.getMessage());
//                    }
//                }
            }
        }
        catch(NoSuchMethodException e){
            System.err.println("Exception : "+e.getMessage());
            e.printStackTrace();
        }
        catch(InstantiationException s){
            System.err.println("Exception : "+s.getMessage());
            s.printStackTrace();
        }
        catch(IllegalAccessException i){
            System.err.println("Exception : "+i.getMessage());
            i.printStackTrace();
        }
        catch(InvocationTargetException k){
            System.err.println("Exception : "+k.getMessage());
            k.printStackTrace();
        }
        catch(IllegalArgumentException d){
            System.err.println("Exception : "+d.getMessage());
            d.printStackTrace();
        }
        catch(Exception ex){
            System.err.println("Exception : "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void effectuerLesSommes(T element){
        Object[] cles = this.sommeDesChamps.keySet().toArray();
        BigDecimal tmp, tmp2;
        String chaine, nomGetter, cle;
        NumberFormat format = NumberFormat.getInstance();
        int tailleMap = this.sommeDesChamps.size();
        int compteur;
        try{
            for(compteur = 0;compteur<tailleMap;compteur++){
                cle = cles[compteur].toString();
                tmp = this.sommeDesChamps.remove(cle);
                nomGetter = "get"+cle.substring(0, 1).toUpperCase()+cle.substring(1);
                chaine = (String)this.resultatClass.getMethod(nomGetter).invoke(element);
                tmp2 = (chaine == null || chaine.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(format.parse(chaine).toString());
                this.sommeDesChamps.put(cle, tmp2.add(tmp));
            }
        }
        catch(IllegalAccessException i){
            System.err.println("Exception : "+i.getMessage());
            i.printStackTrace();
        }
        catch(IllegalArgumentException d){
            System.err.println("Exception : "+d.getMessage());
            d.printStackTrace();
        }
        catch(Exception ex){
            System.err.println("Exception : "+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
