/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tg.codeart.geco.ng.web.reports;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pellage
 */
public class RemplisseurListe{
    
    @SuppressWarnings("empty-statement")
    public static <T> List<T> remplireListeSensSomme(List<Object[]> liste, T objet){
        int taille = liste.size();
        int pas = taille / 4;
        List<T> resultat1 = new ArrayList();
        List<T> resultat2 = new ArrayList();
        List<T> resultat3 = new ArrayList();
        List<T> resultat4 = new ArrayList();
        ConstructeurObjetEtat obj1 = new ConstructeurObjetEtat(liste.subList(0, pas),
                objet.getClass(), resultat1);
        ConstructeurObjetEtat obj2 = new ConstructeurObjetEtat(liste.subList(pas, pas*2),
                objet.getClass(), resultat2);
        ConstructeurObjetEtat obj3 = new ConstructeurObjetEtat(liste.subList(pas*2, pas*3),
                objet.getClass(), resultat3);
        ConstructeurObjetEtat obj4 = new ConstructeurObjetEtat(liste.subList(pas*3, taille),
                objet.getClass(), resultat4);
        obj1.start();
        obj2.start();
        obj3.start();
        obj4.start();
        
        while(!obj1.getState().equals(Thread.State.TERMINATED) ||
                !obj2.getState().equals(Thread.State.TERMINATED) ||
                !obj3.getState().equals(Thread.State.TERMINATED) ||
                !obj4.getState().equals(Thread.State.TERMINATED));
        
        resultat1.addAll(resultat2);
        resultat1.addAll(resultat3);
        resultat1.addAll(resultat4);
        return resultat1;
    }
    
    @SuppressWarnings("empty-statement")
    public static <T extends EtatAvecSomme> List<T> 
        remplireListeAvecSomme(List<Object[]> liste, T objet) throws NoSuchMethodException,
                InstantiationException, IllegalAccessException, InvocationTargetException{
        int taille = liste.size();
        int pas = taille / 4;
        System.out.println("TAILLE : "+taille);
        System.out.println("pas : "+pas);
        List<T> resultat1 = new ArrayList();
        List<T> resultat2 = new ArrayList();
        List<T> resultat3 = new ArrayList();
        List<T> resultat4 = new ArrayList();
        Constructor con = objet.getClass().getConstructor();
        ConstructeurObjetEtatAvecSomme obj1 = new ConstructeurObjetEtatAvecSomme(liste.subList(0, pas),
                resultat1, (EtatAvecSomme)con.newInstance());
        ConstructeurObjetEtatAvecSomme obj2 = new ConstructeurObjetEtatAvecSomme(liste.subList(pas, pas*2),
                resultat2, (EtatAvecSomme)con.newInstance());
        ConstructeurObjetEtatAvecSomme obj3 = new ConstructeurObjetEtatAvecSomme(liste.subList(pas*2, pas*3),
                resultat3, (EtatAvecSomme)con.newInstance());
        ConstructeurObjetEtatAvecSomme obj4 = new ConstructeurObjetEtatAvecSomme(liste.subList(pas*3, taille),
                resultat4, (EtatAvecSomme)con.newInstance());
        obj1.start();
        obj2.start();
        obj3.start();
        obj4.start();
        
        while(!obj1.getState().equals(Thread.State.TERMINATED) ||
                !obj2.getState().equals(Thread.State.TERMINATED) ||
                !obj3.getState().equals(Thread.State.TERMINATED) ||
                !obj4.getState().equals(Thread.State.TERMINATED));
        
        resultat1.addAll(resultat2);
        resultat1.addAll(resultat3);
        resultat1.addAll(resultat4);
        Map<String, BigDecimal> mape = obj1.getSommeDesChamps();
                Map<String, BigDecimal> mape2 = obj2.getSommeDesChamps();
                Map<String, BigDecimal> mape3 = obj3.getSommeDesChamps();
                Map<String, BigDecimal> mape4 = obj4.getSommeDesChamps();
                Object[] cles = mape.keySet().toArray();
                BigDecimal tmp;
                int compteur;
                int tailleMape = cles.length;
                String cle;
                for (compteur = 0; compteur < tailleMape; compteur++) {
                    cle = cles[compteur].toString();
                    tmp = mape.remove(cle);
                    tmp = tmp.add(mape2.get(cle));
                    tmp = tmp.add(mape3.get(cle));
                    tmp = tmp.add(mape4.get(cle));
                    mape.put(cle, tmp);
                }
                resultat1.add((T)objet.construireTotal(mape));
                System.out.println("TAILLE FINAL "+resultat1.size());
        return resultat1;
    }
}
