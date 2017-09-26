package me.yaozu.custom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.yaozu.custom.dialog.MultiStylesDialog;
import me.yaozu.custom.loadingview.LoadingDialog;
import me.yaozu.custom.toast.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1();
            }
        });
        findViewById(R.id.toast_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showBottomToast(MainActivity.this,"bottom toast");
            }
        });
        findViewById(R.id.toast_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showCenterToast(MainActivity.this,"center toast");
            }
        });
        findViewById(R.id.anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        findViewById(R.id.loading_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog(null,false);
            }
        });
        findViewById(R.id.loading_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showLoadingDialog("加载中...",true);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProgressActivity.class));
            }
        });
    }

    private void showLoadingDialog(String message,boolean cancelable){
        LoadingDialog dialog = new LoadingDialog(MainActivity.this,message);
        dialog.setDialogCancelable(cancelable);
        dialog.show();
    }

    private void showDialog1(){
        MultiStylesDialog dialog = new MultiStylesDialog(this,"标题","这是一个含有标题和内容，同时带有两个按钮的dialog",null,"取消","确定", MultiStylesDialog.MULTI_DIALOG_THEME.HAS_TITLE_AND_CONTENT_TWO);
        dialog.setCancelable(true);//back键消失
        dialog.setCanceledOnTouchOutside(true);//点击其他地方消失
        dialog.setOnClickListener(new MultiStylesDialog.MultiAlertDialogListener() {
            @Override
            public void singleClick() {

            }

            @Override
            public void clickBtn1() {
                Toast.makeText(MainActivity.this, "clickBtn1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickBtn2() {
                Toast.makeText(MainActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void showDialog2(){
        MultiStylesDialog dialog = new MultiStylesDialog(this,MultiStylesDialog.MULTI_DIALOG_THEME.NO_TITLE_HAS_CONTENT_TWO);
        dialog.setOnClickListener(new MultiStylesDialog.MultiAlertDialogListener() {
            @Override
            public void singleClick() {

            }

            @Override
            public void clickBtn1() {
                Toast.makeText(MainActivity.this, "clickBtn1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickBtn2() {
                Toast.makeText(MainActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();dialog.setBtn1Text("取消").setBtn2Text("确定");
    }
}
