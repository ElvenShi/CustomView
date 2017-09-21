package me.yaozu.custom.loadingview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import me.yaozu.custom.R;


/**
 * 类描述：加载中提示框
 * 创建人：shiyaozu
 * 创建时间：2016/6/8 20:09
 */
public class LoadingDialog extends ProgressDialog {

    private TextView mTxt;
    private String title;

    public LoadingDialog(Context context, int theme) {
        super(context, R.style.LoadingDialog);
    }

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    /**
     *
     * @param context
     * @param title 提示性文字
     */
    public LoadingDialog(Context context, String title) {
        super(context, R.style.LoadingDialog);
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        mTxt = (TextView) findViewById(R.id.loading_dialog_txt);
        if (!TextUtils.isEmpty(title))
            mTxt.setText(title);
        else
            mTxt.setVisibility(View.GONE);
        //默认点击外部，不取消
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    /**
     * back键取消显示
     */
    private boolean cancelable = true;

    public void setDialogCancelable(boolean flag) {
        this.cancelable = flag;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (cancelable){
            this.cancel();
        }
    }
}
