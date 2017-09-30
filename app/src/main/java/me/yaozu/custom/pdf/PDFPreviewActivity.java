package me.yaozu.custom.pdf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import me.yaozu.custom.R;
import me.yaozu.custom.loadingview.LoadingDialog;

public class PDFPreviewActivity extends AppCompatActivity implements DownloadFile.Listener{

    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    private RelativeLayout rootView;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_preview);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        initPDF();
    }

    private void showLoadingDialog(boolean cancelable){
        dialog = new LoadingDialog(this,"正在加载...");
        dialog.setDialogCancelable(cancelable);
        dialog.show();
    }
    private void cancelLoadingDialog(){
        if (dialog != null){
            dialog.cancel();
        }
    }
    private void initPDF(){
        showLoadingDialog(false);
        /**
         * 创建RemotePDFViewPager 对象
         */
        remotePDFViewPager =  new RemotePDFViewPager(this, "http://file.chmsp.com.cn/colligate/file/00100000224821.pdf", this);
    }

    /**
     * 下载成功回调
     * @param url
     * @param destinationPath
     */
    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, "00100000224821.pdf");
        remotePDFViewPager.setAdapter(adapter);
        rootView.addView(remotePDFViewPager);
//        setContentView(remotePDFViewPager);
        cancelLoadingDialog();
    }

    /**
     * 下载失败回调
     * @param e
     */
    @Override
    public void onFailure(Exception e) {
        cancelLoadingDialog();
        Toast.makeText(this, "文档加载失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 下载中回调
     * @param progress
     * @param total
     */
    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
        rootView.removeAllViews();
        cancelLoadingDialog();
    }
}
