package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    TextView tvGradePoint;
    TextView tvCredit;
    TextView tvGPAA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        tvGradePoint = (TextView)findViewById(R.id.tvGP);
        tvCredit = (TextView)findViewById(R.id.tvCR);
        tvGPAA = (TextView)findViewById(R.id.tvGPA);

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
        Toast t = Toast.makeText(this,"Hello",Toast.LENGTH_SHORT);
        t.show();
        Log.d("Call", "OnCreate");
    }

    public void buttonClicked(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button4:
                String ss = "List of Courses:\n";
                for(int n = 0; n<listCodes.size();n++){
                    ss += listCodes.get(n) + " (" + listCredits.get(n) + " credits) = " + lookUpGradePoint(listGrades.get(n))+ "\n";
                }
                Intent i = new Intent(this, CourseListActivity.class);
                i.putExtra("ss", ss);
                startActivity(i);
                break;

            case R.id.button2:
                Intent j = new Intent(this, CourseActivity.class);
                //j.putExtra("ss", ss);
                startActivityForResult(j, 89);
                break;

            case R.id.button:
                listCodes = new ArrayList<String>();
                listCredits = new ArrayList<Integer>();
                listGrades = new ArrayList<String>();

                tvGradePoint.setText("0.0");
                tvCredit.setText("0");
                tvGPAA.setText("...GPA...");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity

        Log.d("Call", "onActivityResult");

        if (requestCode == 89) {
            if (resultCode == RESULT_OK) {
                //String result = data.getStringExtra("retValue");
                listCodes.add(data.getStringExtra("course"));
                listCredits.add(Integer.parseInt(data.getStringExtra("credits")));
                listGrades.add(data.getStringExtra("grades"));


                //Toast t = Toast.makeText(this,listGrades.size(),Toast.LENGTH_SHORT);
                //t.show();
            }
            else if (resultCode == RESULT_CANCELED) {

            }
            tvGradePoint.setText(calcGradePoint() + "");
            tvCredit.setText(calcCredits() + "");
            tvGPAA.setText(calcGradePoint()/calcCredits() + "");

            Toast t = Toast.makeText(this,Integer.toString(listGrades.size()),Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public double calcGradePoint(){
        double sum = 0.0;

        for(int i = 0; i< listGrades.size()-1; i++){
            sum += lookUpGradePoint(listGrades.get(i)) * listCredits.get(i);
        }




        return sum;
    }

    public double lookUpGradePoint(String Grade){
        double gradePoint=0.0;
        switch(Grade){
            case "A":
                gradePoint = 4.0;
                break;
            case "B+":
                gradePoint = 3.5;
                break;
            case "B":
                gradePoint = 3.0;
                break;
            case "C+":
                gradePoint = 2.5;
                break;
            case "C":
                gradePoint = 2.0;
                break;
            case "D+":
                gradePoint = 1.5;
                break;
            case "D":
                gradePoint = 1.0;
                break;
            default:
                gradePoint = 0.0;
                break;

        }

        return gradePoint;
    }

    public double calcCredits(){
        int sum = 0;

        for(int i = 0; i< listCredits.size()-1; i++){
            sum += listCredits.get(i);
        }

        return sum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
