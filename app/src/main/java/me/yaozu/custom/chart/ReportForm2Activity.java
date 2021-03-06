package me.yaozu.custom.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.yaozu.custom.MyMarkerView;
import me.yaozu.custom.R;

public class ReportForm2Activity extends AppCompatActivity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_form);
        mLineChart = (LineChart) findViewById(R.id.line_chart);
        setChart();
        bindData();
        setX();
        setY();
        interactive();
        setLegend();
        setAnim();
    }

    /**
     * 描述信息
     */
    private void setChart() {
        //创建描述信息
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        mLineChart.setDescription(description);//设置图表描述信息
        mLineChart.setNoDataText("没有数据");//没有数据时显示的文字
        mLineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        mLineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        mLineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        ArrayList<Entry> values1 = new ArrayList<>();
//        ArrayList<Entry> values2 = new ArrayList<>();

        values1.add(new Entry(4, 10));
        values1.add(new Entry(6, 15));
        values1.add(new Entry(9, 20));
        values1.add(new Entry(12, 5));
        values1.add(new Entry(15, 30));
        values1.add(new Entry(18, 31));
        values1.add(new Entry(21, 32));
        values1.add(new Entry(23, 33));
        values1.add(new Entry(25, 34));
        values1.add(new Entry(26, 34));
        values1.add(new Entry(27, 34));
        values1.add(new Entry(28, 35));
        values1.add(new Entry(29, 35));

//        values2.add(new Entry(3, 110));
//        values2.add(new Entry(6, 115));
//        values2.add(new Entry(9, 130));
//        values2.add(new Entry(12, 85));
//        values2.add(new Entry(15, 90));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
        LineDataSet set2;

        //判断图表中原来是否有数据
        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
//            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
//            set2.setValues(values2);
            //刷新数据
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "测试数据1");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });
//            if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.webview_progressbar_bg);
//                set1.setFillDrawable(drawable);//设置范围背景填充
//            } else {
                set1.setFillColor(Color.BLACK);
//            }

            //设置数据2
//            set2 = new LineDataSet(values2, "测试数据2");
//            set2.setColor(Color.GRAY);
//            set2.setCircleColor(Color.GRAY);
//            set2.setLineWidth(1f);
//            set2.setCircleRadius(3f);
//            set2.setValueTextSize(10f);

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
//            dataSets.add(set2);
            //创建LineData对象 属于mLineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            mLineChart.setData(data);
            //绘制图表
            mLineChart.invalidate();
        }
    }

    /**
     * 设置X轴的显示效果
     */
    private void setX() {
        //获取此图表的x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符
    }

    /**
     * 设置Y轴效果
     */
    private void setY() {
        /**
         * Y轴默认显示左右两个轴线
         */
        //获取右边的轴线
        YAxis rightAxis = mLineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = mLineChart.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);
    }

    /**
     * 设置与图表交互
     */
    private void interactive() {
        mLineChart.setTouchEnabled(true); // 设置是否可以触摸
        mLineChart.setDragEnabled(true);// 是否可以拖拽
        mLineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        mLineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        mLineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
        mLineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        mLineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        mLineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        mLineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        mLineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
    }

    /**
     * 设置图例
     */
    private void setLegend() {
        Legend l = mLineChart.getLegend();//图例
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);//设置图例的位置
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度
    }
    /**
     * 设置提示
     */
    private void setTip(){
        //自定义的MarkerView对象
        MyMarkerView mv = new MyMarkerView(this, R.layout.mark_view);
        mv.setChartView(mLineChart);
        mLineChart.setMarker(mv);
    }
    private void setAnim() {
//        animateX( int durationMillis) :水平轴动画 在指定时间内 从左到右
//        animateY( int durationMillis) :垂直轴动画 从下到上
//        animateXY( int xDuration, int yDuration) : 两个轴动画，从左到右，从下到上
        mLineChart.animateXY(1000, 1000);
    }
}
