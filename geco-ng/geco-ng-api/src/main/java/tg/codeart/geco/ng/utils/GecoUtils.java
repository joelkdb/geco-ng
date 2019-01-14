/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.utils;

/**
 *
 * @author joelkdb
 */
public class GecoUtils {
    
    /**
     * Méthode renvoyant les codes des classes-entités générés sous un format
     * @param prefixe
     * @param count
     * @return
     */
    public static String generateProjectCode(String prefixe, int count){
        return prefixe+String.format("%05d", count);
    }
}
