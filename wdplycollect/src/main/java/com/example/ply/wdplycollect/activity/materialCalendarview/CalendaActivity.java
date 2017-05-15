package com.example.ply.wdplycollect.activity.materialCalendarview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;
import com.example.ply.wdplycollect.util.CommonUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 日历
 * Created by ply on 2017/5/15.
 */

public class CalendaActivity extends Activity implements OnDateSelectedListener,View.OnClickListener {
    @BindView(R.id.rl_root)
    LinearLayout rl_root;
    @BindView(R.id.tv_click)
    TextView tv_click;
    private PopupWindow calenderPop;
    private TextView tv_pop_title;
    private MaterialCalendarView materialCalendar;
    private TextView tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenda);
        ButterKnife.bind(this);
        tv_click.setOnClickListener(this);

//        for (int i=0;i<2;i++) {
//            CalendarDay calendarDayEve = CalendarDay.from(new Date(dateBeanEve.date * 1000));
//            calendarDays.add(calendarDayEve);
//        }

    }

    private void showCalenderPop(List<CalendarDay> days) {
        if (calenderPop == null) {
            View customView = getLayoutInflater().inflate(R.layout.item_calender_learnplan, null, false);
            materialCalendar = (MaterialCalendarView) customView.findViewById(R.id.materialCalendar);
            materialCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
            materialCalendar.setOnDateChangedListener(this);
            tv_pop_title = (TextView) customView.findViewById(R.id.tv_pop_title);
            tv_confirm = (TextView) customView.findViewById(R.id.tv_confirm);
            tv_confirm.setOnClickListener(this);
            calenderPop = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        materialCalendar.setDatesSelected(days);
        tv_pop_title.setText("共" + (days == null ? 0 : days.size()) + "天");

        if (!calenderPop.isShowing()) {
            calenderPop.showAtLocation(rl_root, Gravity.CENTER,0,0);
            calenderPop.update();
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        List<CalendarDay> selectedDates = materialCalendar.getSelectedDates();
        tv_pop_title.setText("共" + selectedDates.size() + "天");
    }
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, CalendaActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click:
                CommonUtils.showToast(this,"点击");
                showCalenderPop(null);
                break;
        }
    }
}
