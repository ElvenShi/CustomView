package me.yaozu.custom.pdf;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import me.yaozu.custom.R;
import me.yaozu.custom.loadingview.LoadingDialog;

public class PDFPreviewActivity extends AppCompatActivity implements DownloadFile.Listener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private PdfViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    private RelativeLayout rootView;
    private LoadingDialog dialog;
    private ProgressBar progressBar;
    private TextView back;
    private TextView title;
    private int pageCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_preview);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        back = (TextView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title.setText("文档");
        back.setOnClickListener(this);
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
        progressBar.setVisibility(View.VISIBLE);
        showLoadingDialog(false);
        /**
         * 创建RemotePDFViewPager 对象
         */
        remotePDFViewPager =  new PdfViewPager(this, "http://file.chmsp.com.cn/colligate/file/00100000224821.pdf", this);
        remotePDFViewPager.addOnPageChangeListener(this);
    }

    /**
     * 下载成功回调
     * @param url
     * @param destinationPath
     */
    @Override
    public void onSuccess(String url, String destinationPath) {
        progressBar.setVisibility(View.GONE);
        adapter = new PDFPagerAdapter(this, "00100000224821.pdf");
        remotePDFViewPager.setAdapter(adapter);
        pageCount = adapter.getCount();
        rootView.addView(remotePDFViewPager);
        setPage(0);
//        setContentView(remotePDFViewPager);
        cancelLoadingDialog();
    }

    /**
     * 下载失败回调
     * @param e
     */
    @Override
    public void onFailure(Exception e) {
        progressBar.setProgress(100);
        progressBar.setVisibility(View.GONE);
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
        progressBar.setMax(total);
        progressBar.setProgress(progress);
        if (progress == total){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
        remotePDFViewPager.removeAllViews();
        rootView.removeAllViews();
        cancelLoadingDialog();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void setPage(int page){
        page++;
        title.setText("文档"+"("+pageCount+"/"+page+")");
    }
    @Override
    public void onClick(View v) {
        finish();
    }
}
