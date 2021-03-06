package com.squareup.timessquare.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.List;
import java.io.*;
import android.content.Context;

import static android.widget.Toast.LENGTH_SHORT;

public class SampleTimesSquareActivity extends Activity {
  private static final String TAG = "SampleTimesSquareActivi";
  private CalendarPickerView calendar;
  private AlertDialog theDialog;
  private CalendarPickerView dialogView;
  private final Set<Button> modeButtons = new LinkedHashSet<Button>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_calendar_picker);

    Calendar start = Calendar.getInstance();
    start.add(Calendar.YEAR, -3);

    Calendar end = Calendar.getInstance();
    end.add(Calendar.YEAR, 3);

    calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
    calendar.setCustomDayView(new DefaultDayViewAdapter());
    Calendar today = Calendar.getInstance();
    //ArrayList<Date> dates = new ArrayList<Date>();
    //dates.add(today.getTime());
    ArrayList<Date> dates= new ArrayList<Date>();

    String filename = "hoi";
    try {
      FileInputStream in = openFileInput(filename);
      ObjectInputStream ois = new ObjectInputStream(in);
      dates = (ArrayList<Date>) (ois.readObject());
    }
    catch (Exception e)
    {
      e.printStackTrace();

    }

    calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
    calendar.init(start.getTime(), end.getTime()) //
            .inMode(SelectionMode.MULTIPLE) //
            .withSelectedDates(dates);

    initButtonListeners();

  }

  private void initButtonListeners() {
/*
    final Button single = (Button) findViewById(R.id.button_single);
    final Button multi = (Button) findViewById(R.id.button_multi);
    final Button range = (Button) findViewById(R.id.button_range);
    final Button displayOnly = (Button) findViewById(R.id.button_display_only);
    final Button dialog = (Button) findViewById(R.id.button_dialog);
    final Button customized = (Button) findViewById(R.id.button_customized);
    final Button decorator = (Button) findViewById(R.id.button_decorator);
    final Button hebrew = (Button) findViewById(R.id.button_hebrew);
    final Button arabic = (Button) findViewById(R.id.button_arabic);
    final Button customView = (Button) findViewById(R.id.button_custom_view);
    modeButtons.addAll(Arrays.asList(single, multi, range, displayOnly, decorator, customView));


    range.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        setButtonsEnabled(range);

        calendar.setCustomDayView(new DefaultDayViewAdapter());
        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();
        today.add(Calendar.DATE, 3);
        dates.add(today.getTime());
        today.add(Calendar.DATE, 5);
        dates.add(today.getTime());
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(new Date(), nextYear.getTime()) //
            .inMode(SelectionMode.RANGE) //
            .withSelectedDates(dates);
      }
    });

    displayOnly.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        setButtonsEnabled(displayOnly);

        calendar.setCustomDayView(new DefaultDayViewAdapter());
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(new Date(), nextYear.getTime()) //
            .inMode(SelectionMode.SINGLE) //
            .withSelectedDate(new Date()) //
            .displayOnly();
      }
    });

    dialog.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        String title = "I'm a dialog!";
        showCalendarInDialog(title, R.layout.dialog);
        dialogView.init(lastYear.getTime(), nextYear.getTime()) //
            .withSelectedDate(new Date());
      }
    });

    customized.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        showCalendarInDialog("Pimp my calendar!", R.layout.dialog_customized);
        dialogView.init(lastYear.getTime(), nextYear.getTime())
            .withSelectedDate(new Date());
      }
    });

    decorator.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        setButtonsEnabled(decorator);

        calendar.setCustomDayView(new DefaultDayViewAdapter());
        calendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new SampleDecorator()));
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
            .inMode(SelectionMode.SINGLE) //
            .withSelectedDate(new Date());
      }
    });

    hebrew.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        showCalendarInDialog("I'm Hebrew!", R.layout.dialog);
        dialogView.init(lastYear.getTime(), nextYear.getTime(), new Locale("iw", "IL")) //
                .withSelectedDate(new Date());
      }
    });

    arabic.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        showCalendarInDialog("I'm Arabic!", R.layout.dialog);
        dialogView.init(lastYear.getTime(), nextYear.getTime(), new Locale("ar", "EG")) //
            .withSelectedDate(new Date());
      }
    });

    customView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        setButtonsEnabled(customView);

        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.setCustomDayView(new SampleDayViewAdapter());
        calendar.init(lastYear.getTime(), nextYear.getTime())
                .inMode(SelectionMode.SINGLE)
                .withSelectedDate(new Date());
      }
    });
*/
  findViewById(R.id.done_button).setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {

        String filename = "hoi";
        FileOutputStream outputStream;

        try {
          outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
          ObjectOutputStream oos = new ObjectOutputStream(outputStream);
          oos.writeObject (calendar.getSelectedDates());
          oos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }

        finish();
        System.exit(0);      }
    });
  }


/*
  private void showCalendarInDialog(String title, int layoutResId) {
    dialogView = (CalendarPickerView) getLayoutInflater().inflate(layoutResId, null, false);
    theDialog = new AlertDialog.Builder(this) //
        .setTitle(title)
        .setView(dialogView)
        .setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
          }
        })
        .create();
    theDialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override public void onShow(DialogInterface dialogInterface) {
        Log.d(TAG, "onShow: fix the dimens!");
        dialogView.fixDialogDimens();
      }
    });
    theDialog.show();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    boolean applyFixes = theDialog != null && theDialog.isShowing();
    if (applyFixes) {
      Log.d(TAG, "Config change: unfix the dimens so I'll get remeasured!");
      dialogView.unfixDialogDimens();
    }
    super.onConfigurationChanged(newConfig);
    if (applyFixes) {
      dialogView.post(new Runnable() {
        @Override public void run() {
          Log.d(TAG, "Config change done: re-fix the dimens!");
          dialogView.fixDialogDimens();
        }
      });
    }


  }
   */
}
