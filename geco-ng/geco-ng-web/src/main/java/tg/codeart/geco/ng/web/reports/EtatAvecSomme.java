/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.reports;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pellage
 */
public interface EtatAvecSomme {

    public List<String> listeDesChapsASommer();
    public Object construireTotal(Map<String, BigDecimal> mape);
}
