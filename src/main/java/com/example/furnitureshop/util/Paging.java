package com.example.furnitureshop.util;

import com.example.furnitureshop.model.Page;

public class Paging {
    public static final int NUMBER_RECORD_DEFAULT = 6;

    public static Page pageDivision(int numberItem, int pageCurrent){
        int numberRecord = NUMBER_RECORD_DEFAULT, indexStart = 0;
        int numberPage = numberItem % numberRecord == 0 ? numberItem/numberRecord : numberItem/numberRecord + 1;
        if(pageCurrent > 1){
            indexStart = (pageCurrent - 1) * numberRecord;
        }else{
            pageCurrent = 1;
        }

        return new Page(numberPage, numberRecord, indexStart, pageCurrent);
    }
}
