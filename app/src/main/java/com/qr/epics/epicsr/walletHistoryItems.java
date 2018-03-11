package com.qr.epics.epicsr;

/**
 * Created by Raja on 11-Jan-18.
 */

public class walletHistoryItems
{
    String ss;
    String sv;

    public walletHistoryItems(String aSsa, String aSva) {
        ss = aSsa;
        sv = aSva;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String aSva) {
        sv = aSva;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String aSsa) {
        ss = aSsa;
    }
}
