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
        swipeToDo.setOnSwipeToDo(new SwipeToDo.OnSwipeToDo() {
            @Override
            public boolean onSwipeToLeft(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "OnSwipeToLeft", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeToRight(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "OnSwipeToRight", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
