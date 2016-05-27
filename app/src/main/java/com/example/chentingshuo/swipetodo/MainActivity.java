package com.example.chentingshuo.swipetodo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeToDo swipeToDo = new SwipeToDo(this);
        swipeToDo.setBackgroundColor(getResources().getColor(R.color.cyan_dark));
        setContentView(swipeToDo);
        swipeToDo.setOnSwipeToLeftCircle(new SwipeToDo.OnSwipeToLeftCircle() {
            @Override
            public boolean onSwipe(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "OnSwipeToLeftCircle", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        swipeToDo.setOnSwipeToRightCircle(new SwipeToDo.OnSwipeToRightCircle() {
            @Override
            public boolean onSwipe(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "OnSwipeToRightCircle", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
