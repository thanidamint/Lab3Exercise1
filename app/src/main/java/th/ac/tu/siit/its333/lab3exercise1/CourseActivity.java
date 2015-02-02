package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CourseActivity extends ActionBarActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonClicked1(View v) {
        TextView coursecode = (TextView)findViewById(R.id.etCode);
        TextView credits = (TextView)findViewById(R.id.etCR);
        RadioGroup rg1 = (RadioGroup)findViewById(R.id.rg1);
        RadioButton rb1 = (RadioButton)findViewById(rg1.getCheckedRadioButtonId());


        Intent res = new Intent();
        res.putExtra("course", coursecode.getText().toString());
        res.putExtra("credits", credits.getText().toString());
        res.putExtra("grades", rb1.getText().toString());
        setResult(RESULT_OK, res);
        finish();
    }
}


