package me.yaozu.custom.pdf;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

/**
 * @author : Shiyaozu
 * @version : [V1.0.0]
 * @date : 2017/10/13 0013
 * @desc : [相关类/方法]
 */

public class PdfViewPager extends RemotePDFViewPager {
    public PdfViewPager(Context context, String pdfUrl, DownloadFile.Listener listener) {
        super(context, pdfUrl, listener);
    }

    public PdfViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
