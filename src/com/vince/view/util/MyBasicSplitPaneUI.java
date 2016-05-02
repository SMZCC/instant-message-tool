/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vince.view.util;

import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author lgh
 */
public class MyBasicSplitPaneUI extends BasicSplitPaneUI{
    /**
     * Creates the default divider.
     */
    public BasicSplitPaneDivider createDefaultDivider() {
        return new MyBasicSplitPaneDivider(this);
    }
}
