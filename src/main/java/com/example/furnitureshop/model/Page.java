package com.example.furnitureshop.model;

public class Page {
    private int numberPage;
    private int numberRecord;
    private int indexStart;
    private int pageCurrent;

    public Page() {
    }

    public Page(int numberPage, int numberRecord, int indexStart, int pageCurrent) {
        this.numberPage = numberPage;
        this.numberRecord = numberRecord;
        this.indexStart = indexStart;
        this.pageCurrent = pageCurrent;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public int getNumberRecord() {
        return numberRecord;
    }

    public void setNumberRecord(int numberRecord) {
        this.numberRecord = numberRecord;
    }

    public int getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(int indexStart) {
        this.indexStart = indexStart;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }
}
