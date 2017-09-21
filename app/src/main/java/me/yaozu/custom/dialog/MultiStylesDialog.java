package me.yaozu.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.yaozu.custom.R;

import static me.yaozu.custom.dialog.MultiStylesDialog.MULTI_DIALOG_THEME.HAS_TITLE_AND_CONTENT_TWO;

/**
 * @author : Shiyaozu
 * @version : [V1.0.0]
 * @date : 2017/8/29 0029
 * @desc : [这是一个多风格的自定义Dialog]
 *
 * 注：对view的操作只能在showDialog调用之后进行，否则会出现空指针异常
 */

public class MultiStylesDialog extends Dialog implements View.OnClickListener {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 标题
     */
    private String title;
    /**
     * dialog内容
     */
    private String content;
    /**
     * 单个按钮时，按钮的显示文案
     */
    private String btnNext;
    /**
     * 按钮文案
     */
    private String btn1;

    /**
     * 按钮文案
     */
    private String btn2;

    private MULTI_DIALOG_THEME theme;

    private MultiAlertDialogListener listener;

    private LinearLayout mBtnLayout;
    private LinearLayout mProgressLayout;
    private TextView mProgressTv;
    private TextView mTitle;
    private TextView mContent;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtnNext;
    private ProgressBar mProgressBar;

    /**
     * 弹出框主题
     */
    public enum MULTI_DIALOG_THEME {
        /**
         * 带有标题和内容的，有两个按钮的dialog
         */
        HAS_TITLE_AND_CONTENT_TWO,
        /**
         * 有标题,没内容的，有两个按钮的dialog
         */
        HAS_TITLE_NO_CONTENT_TWO,

        /**
         * 无标题，有内容，有两个按钮
         */
        NO_TITLE_HAS_CONTENT_TWO,

        /**
         * 有标题，没内容，只有一个按钮
         */
        HAS_TITLE_NO_CONTENT_ONE,

        /**
         * 有标题，有内容，只有一个按钮
         */
        HAS_TITLE_AND_CONTENT_ONE,

        /**
         * 无标题，有内容，只有一个按钮
         */
        HAS_CONTENT_NO_TITLE_ONE,

        /**
         * 有标题，无内容，有两个btn的progress dialog
         */
        HAS_TITLE_NO_CONTENT_PROGRESS_TWO,

        /**
         * 有标题，无内容，只有一个btn的progress dialog
         */
        HAS_TITLE_NO_CONTENT_PROGRESS_ONE
    }

    public MultiStylesDialog(@NonNull Context context) {
        this(null,null,null,null,null,null,HAS_TITLE_AND_CONTENT_TWO);
    }

    public MultiStylesDialog(Context context,MULTI_DIALOG_THEME theme){
        this(context,null,null,null,null,null,theme);
    }

    /**
     * Dialog
     *
     * @param context
     * @param title
     * @param content
     * @param btnNext
     * @param btn1
     * @param btn2
     * @param theme
     */
    public MultiStylesDialog(Context context, String title, String content, String btnNext, String btn1, String btn2, MULTI_DIALOG_THEME theme) {
//        super(context);
        super(context,R.style.DialogTheme);
        this.context = context;
        this.content = content;
        this.title = title;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btnNext = btnNext;
        this.theme = theme;

        //
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muti_styles_dialog_view);
        mProgressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        mProgressTv = (TextView) findViewById(R.id.dialog_percent_tv);
        mBtnLayout = (LinearLayout) findViewById(R.id.dialog_two_btn_container);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mContent = (TextView) findViewById(R.id.dialog_msg);
        mBtn1 = (Button) findViewById(R.id.dialog_btn1);
        mBtn2 = (Button) findViewById(R.id.dialog_btn2);
        mBtnNext = (Button) findViewById(R.id.dialog_btn_next);
        mProgressBar = (ProgressBar) findViewById(R.id.dialog_progress);
        mBtnNext.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        initText();
        setDialogTheme(theme);
    }

    private void initText(){
        setTitle(title);
        setContent(content);
        setBtn1Text(btn1);
        setBtn2Text(btn2);
        setBtnNextText(btnNext);
    }

    /**
     * dialog主题过滤
     *
     * @param theme
     */
    public void setDialogTheme(MULTI_DIALOG_THEME theme) {
        switch (theme) {
            case HAS_TITLE_AND_CONTENT_TWO:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.GONE);
                mBtnLayout.setVisibility(View.VISIBLE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case HAS_TITLE_NO_CONTENT_TWO:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                mBtnLayout.setVisibility(View.VISIBLE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case NO_TITLE_HAS_CONTENT_TWO:
                mTitle.setVisibility(View.GONE);
                mContent.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.GONE);
                mBtnLayout.setVisibility(View.VISIBLE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case HAS_TITLE_AND_CONTENT_ONE:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnLayout.setVisibility(View.GONE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case HAS_TITLE_NO_CONTENT_ONE:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnLayout.setVisibility(View.GONE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case HAS_CONTENT_NO_TITLE_ONE:
                mTitle.setVisibility(View.GONE);
                mContent.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnLayout.setVisibility(View.GONE);
                mProgressLayout.setVisibility(View.GONE);
                break;
            case HAS_TITLE_NO_CONTENT_PROGRESS_ONE:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                mBtnLayout.setVisibility(View.GONE);
                mProgressLayout.setVisibility(View.VISIBLE);
                break;
            case HAS_TITLE_NO_CONTENT_PROGRESS_TWO:
                mTitle.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                mBtnLayout.setVisibility(View.VISIBLE);
                mProgressLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_btn1) {
            if (null != listener) {
                listener.clickBtn1();
            }
            this.dismiss();
        } else if (id == R.id.dialog_btn2) {
            if (null != listener) {
                listener.clickBtn2();
            }
            this.dismiss();
        } else if (id == R.id.dialog_btn_next) {
            if (null != listener) {
                listener.singleClick();
            }
            this.dismiss();
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public MultiStylesDialog setTitle(String title){
        setText(mTitle,title);
        return this;
    }

    /**
     * 设置内容
     * @param content
     */
    public MultiStylesDialog setContent(String content){
       setText(mContent,content);
        return this;
    }
    /**
     * setProgress 设置进度条的进度
     */
    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
        String temp = progress + "%";
        mProgressTv.setText(temp);
    }

    /**
     * setProgress 设置进度条对应的进度文案
     */
    public void setPercentTv(String percentTx) {
        mProgressTv.setText(percentTx);
    }

    /**
     * 设置btn1文本
     * @param text
     */
    public MultiStylesDialog setBtn1Text(String text){
        setText(mBtn1,text);
        return this;
    }
    /**
     * 设置btn2文本
     * @param text
     */
    public MultiStylesDialog setBtn2Text(String text){
        setText(mBtn2,text);
        return this;
    }
    /**
     * 设置BtnNextText文本
     * @param text
     */
    public MultiStylesDialog setBtnNextText(String text){
        setText(mBtnNext,text);
        return this;
    }
    private void setText(TextView textView,String content){
        if (!TextUtils.isEmpty(content)){
            textView.setText(content);
        }
    }

    /**
     * 设置Btn1背景色
     * @param color
     */
    public MultiStylesDialog setBtn1BackgroundColor(int color){
        mBtn1.setBackgroundColor(ContextCompat.getColor(context, color));
        return this;
    }
    public MultiStylesDialog setBtn1BackgroundResource(int resId){
        mBtn1.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置Btn2背景色
     * @param color
     */
    public MultiStylesDialog setBtn2Background(int color){
        mBtn2.setBackgroundColor(ContextCompat.getColor(context, color));
        return this;
    }
    public MultiStylesDialog setBtn2BackgroundResource(int resId){
        mBtn2.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置BtnNext背景色
     * @param color
     */
    public MultiStylesDialog setBtnNextBackground(int color){
        mBtnNext.setBackgroundColor(ContextCompat.getColor(context, color));
        return this;
    }
    public MultiStylesDialog setBtnNextBackgroundResource(int resId){
        mBtnNext.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置标题字体大小
     * @param size
     */
    public MultiStylesDialog setTitleTextSize(float size) {
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        return this;
    }

    /**
     * 设置标题颜色
     * @param color
     */
    public MultiStylesDialog setTitleColor(int color){
        mTitle.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /**
     * 设置btn1字体颜色
     * @param color
     */
    public MultiStylesDialog setBtn1Color(int color){
        mBtn1.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /**
     * 设置btn2字体颜色
     * @param color
     */
    public MultiStylesDialog setBtn2Color(int color){
        mBtn2.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /**
     * 设置mBtnNext字体颜色
     * @param color
     */
    public MultiStylesDialog setBtnNextColor(int color){
        mBtnNext.setTextColor(context.getResources().getColor(color));
        return this;
    }

    /**
     * 设置btn1字体大小
     * @param size
     */
    public MultiStylesDialog setBtn1TextSize(float size){
        mBtn1.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        return this;
    }

    /**
     * 设置btn2字体大小
     * @param size
     */
    public MultiStylesDialog setBtn2TextSize(float size){
        mBtn2.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        return this;
    }

    public MultiStylesDialog showDialog(){
        super.show();
        return this;
    }

    @Override
    public void show() {
        showDialog();
    }

    /**
     * 设置事件监听器
     *
     * @param listener
     */
    public void setOnClickListener(MultiAlertDialogListener listener) {
        this.listener = listener;
    }

    /**
     * dialog监听事件
     */
    public interface MultiAlertDialogListener {

        void singleClick();

        void clickBtn1();

        void clickBtn2();
    }
}
