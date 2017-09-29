package me.yaozu.custom.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import me.yaozu.custom.MyMarkerView;
import me.yaozu.custom.R;

public class ReportFormActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private int[] mColors = new int[]{
            Color.parseColor("#5abdfc"),    //蓝色
            Color.parseColor("#eb73f6")    //紫色
    };

    /**
     * x轴数据
     */
    protected String[] mMonths = new String[]{
            "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
    };

    /**
     * y轴数据
     * @return
     */
    private ArrayList<Entry> getYValues(){
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            yValues.add(new Entry(i, (float) (Math.random() * 10000f)));
        }
        return yValues;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_form);
        mLineChart = (LineChart) findViewById(R.id.line_chart);

        initChartView();

        /**-------------这里的数据不重要，主要用随机数的方式生成点坐标-------------**/
        //设置模拟数据
        setData(getYValues(), "微信粉丝数");
        /**--------------------------**/
    }

    private void initChartView() {
        setDescription();
        setChartProperty();
        setChartInteractive();
        setXYAxis();
        setLegend();
        setAnim();
    }

    /**
     * 设置图例
     */
    private void setLegend() {
        //图标的下边的指示块  图例
        Legend l = mLineChart.getLegend();//图例
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);//设置图例的位置
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.LINE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度
//        l.setXEntrySpace(40);
    }

    /**
     * 设置描述信息
     */
    private void setDescription() {
//        Description description = new Description();
//        description.setText("会员增长趋势图");
//        description.setTextColor(Color.RED);
//        description.setTextSize(20);
        mLineChart.setDescription(null);//设置图表描述信息
        mLineChart.setNoDataText("没有数据");//没有数据时显示的文字
        mLineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        mLineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        mLineChart.setDrawBorders(false);//禁止绘制图表边框的线
    }

    /**
     * 设置提示
     */
    private void setTip() {
        //自定义的MarkerView对象
        MyMarkerView mv = new MyMarkerView(this, R.layout.mark_view);
        mv.setChartView(mLineChart);
        mLineChart.setMarker(mv);
    }

    /**
     * 动画效果
     */
    private void setAnim() {
//        animateX( int durationMillis) :水平轴动画 在指定时间内 从左到右
//        animateY( int durationMillis) :垂直轴动画 从下到上
//        animateXY( int xDuration, int yDuration) : 两个轴动画，从左到右，从下到上
        mLineChart.animateXY(1000, 1000);
    }

    /**
     * 设置与图表交互方式
     */
    private void setChartInteractive() {
        mLineChart.setTouchEnabled(true); // 设置是否可以触摸
        mLineChart.setDragEnabled(true);// 是否可以拖拽
        mLineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
//        mLineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
//        mLineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
//        mLineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        mLineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
        mLineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        mLineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        mLineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
    }

    /**
     * 设置chart属性
     */
    private void setChartProperty() {
        mLineChart.setDrawBorders(true);    //四周是不是有边框
        mLineChart.setBorderWidth(0.5f); //边框宽度
        mLineChart.setBorderColor(Color.parseColor("#b3b3b3"));    //边框颜色，默认黑色？
    }

    /**
     * 设置X,Y轴属性
     */
    private void setXYAxis() {

        // ------------------------------X轴属性配置 start---------------------
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//是否绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置,在上还是在下
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
//        //设置竖线的显示样式为虚线
//        //lineLength控制虚线段的长度
//        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);    //背景用虚线表格来绘制  给整成虚线
        xAxis.setAxisMinimum(0f);//设置轴的最小值。这样设置将不会根据提供的数据自动计算。
        xAxis.setGranularityEnabled(true);    //粒度
//        xAxis.setGranularity(1f);    //缩放的时候有用，比如放大的时候，我不想把横轴的月份再细分

//        xAxis.setAxisLineWidth(0f);    //设置坐标轴那条线的宽度
        xAxis.setLabelCount(mMonths.length);    //强制有多少个刻度
        xAxis.setTextColor(Color.parseColor("#b3b3b3"));
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(-45f);//设置x轴标签的旋转角度
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }

        });
        // ------------------------------X轴属性配置 end---------------------

        // ------------------------------Y轴属性配置 start---------------------
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
        //坐标轴绘制在图表的内侧
//        leftAxis.setPosition(INSIDE_CHART);
        leftAxis.setTextColor(Color.parseColor("#b3b3b3"));
        //确实没看懂这个是干嘛用的，默认是10f
        //这个玩意好像有坐标轴enable的时候是不可用的
        leftAxis.setSpaceBottom(10f);
        // ------------------------------Y轴属性配置 end---------------------

        //一个chart中包含一个Data对象，一个Data对象包含多个DataSet对象，
        // 每个DataSet是对应一条线上的所有点(相对于折线图来说)
        mLineChart.setData(new LineData());
    }


    /**
     * 根据数据集合，动态构建DataSet，来绘制界面
     */
    private void setData(ArrayList<Entry> entryList, String dataSetName) {

        LineData data = mLineChart.getData();

        if (data != null) {
            int count = data.getDataSetCount();

            LineDataSet set = new LineDataSet(entryList, dataSetName);
            set.setLineWidth(1.5f);
            set.setCircleRadius(3.5f);

            int color = mColors[count % mColors.length];

            set.setColor(color);// 设置折线的颜色
            set.setCircleColor(color); //设置点的颜色
            set.setHighlightEnabled(true);//是否开启点击高亮线
            set.setHighLightColor(color); // 设置高亮的颜色
            set.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set.setHighlightLineWidth(1f);//设置点击交点后显示高亮线宽
            set.setValueTextSize(10f);
            set.setDrawValues(false);    //节点是否显示具体数值
            set.setValueTextColor(color);
            set.enableDashedHighlightLine(10f, 5f, 0f);    //选中某个点的时候高亮显示只是线
            set.setDrawFilled(true);     //填充折线图折线和坐标轴之间
            set.setFillColor(color);    //填充可以设置渐变填充一个Drawable，或者仅仅填充颜色
            set.setAxisDependency(YAxis.AxisDependency.RIGHT);    //如果使用的是右坐标轴必须设置这行

            //设置曲线值的圆点是实心还是空心
            set.setDrawCircleHole(true);
//            set.setDrawVerticalHighlightIndicator(false);//取消纵向辅助线
            set.setDrawHorizontalHighlightIndicator(true);//取消横向辅助线

            data.addDataSet(set);
            data.notifyDataChanged();
            mLineChart.notifyDataSetChanged();
            //这行代码必须放到这里，这里设置的是图表这个视窗能显示，x坐标轴，从最大值到最小值之间
            //多少段，好像这个库没有办法设置x轴中的间隔的大小
            mLineChart.setVisibleXRangeMaximum(6);//x轴默认可见刻度是多少 mMonths.length
            mLineChart.invalidate();
        }
        setTip();
    }

}
