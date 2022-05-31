package com.radjapov.pr11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.print.PrintHelper;

import java.io.FileNotFoundException;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        if (PrintHelper.systemSupportsPrint()) {
            button.setText("print me");
        } else {
            button.setEnabled(false);
            button.setText("testtest");
        }
    }

    @Override
    public void onClick(final View v) {
        final Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/jpeg");
        startActivityForResult(intent, 85);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == 85 && resultCode == RESULT_OK) {
            final PrintHelper helper = new PrintHelper(this);
            helper.setColorMode(PrintHelper.COLOR_MODE_MONOCHROME);
            helper.setScaleMode(PrintHelper.SCALE_MODE_FIT);
            try {
                helper.printBitmap("test", data.getData());
            } catch (final FileNotFoundException e) {
            }
        }
    }
}
