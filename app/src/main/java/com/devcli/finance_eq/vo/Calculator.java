package com.devcli.finance_eq.vo;

import java.io.Serializable;

/**
 * Created by prashantkoshta on 12/8/16.
 */

public class Calculator implements Serializable {
    public String type;
    public String action;
    public String pagename;
    public String pageurl;

    @Override
    public String toString() {
        return this.type;
    }
}
