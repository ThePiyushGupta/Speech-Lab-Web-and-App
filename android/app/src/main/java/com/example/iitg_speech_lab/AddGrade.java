package com.example.iitg_speech_lab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddGrade extends AppCompatActivity {
    public static ArrayList<EditText> TextBoxes = new ArrayList<EditText>();
    public static int cnt;
    public static int cnt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        String CourseInfo = getIntent().getStringExtra("courseInfo");
        String AssignmentID = getIntent().getStringExtra("assignmentID");
        String GroupID = getIntent().getStringExtra("groupID");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference assignsRef = db.collection("Courses").document(CourseInfo).collection("Assignments").document(AssignmentID).collection("Groups").document(GroupID);
        assignsRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot group = task.getResult();
                            ArrayList<Map<Object, Object>> arr = new ArrayList<Map<Object, Object>>();
                            arr = (ArrayList<Map<Object, Object>>) group.get("StudentList");
                            final Integer Counter = arr.size();
                            Integer Count = 0;
                            for (Map<Object, Object> mp : arr) {
                                Count++;
                                final Long Gradey = (Long) mp.get("Grade");
                                DocumentReference studref = (DocumentReference) mp.get("StudentID");
                                studref.get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    LinearLayout myLayout = (LinearLayout) findViewById(R.id.myLayout);
                                                    DocumentSnapshot usr = task.getResult();
                                                    String UserName = (String) usr.get("Username");
                                                    String FullName = (String) usr.get("FullName");
                                                    TextView tv = new TextView(AddGrade.this);
                                                    EditText et = new EditText(AddGrade.this);
//                                                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) tv.getLayoutParams();
//                                                    if (params1!=null) {
//                                                        params1.height = getResources().getDimensionPixelSize(R.dimen.height_of_dynamically);
//                                                    }
//                                                    else {
//                                                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,R.dimen.height_of_dynamically);
//                                                    }
//                                                    tv.setLayoutParams(params1);
//                                                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) et.getLayoutParams();
//                                                    if (params2!=null) {
//                                                        params2.height = getResources().getDimensionPixelSize(R.dimen.height_of_dynamically);
//                                                    }
//                                                    else {
//                                                        params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,R.dimen.height_of_dynamically);
//                                                    }
//                                                    et.setLayoutParams(params2);
                                                    tv.setTextSize(18);
                                                    tv.setText(FullName.toUpperCase());
                                                    tv.setTag(UserName);
                                                    et.setTag(UserName);

                                                    et.setText(Long.toString(Gradey));
                                                    TextBoxes.add(et);
                                                    myLayout.addView(tv);
                                                    myLayout.addView(et);
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }
    public static ArrayList<Map<Object,Object>> push = new ArrayList<Map<Object,Object>>();
    public static ArrayList<Map<Object,Object>> data = new ArrayList<Map<Object,Object>>();
    public void Add_Grade_Group(final View view) {
        int validate = 0;
        int check  = 0;
        for (int i=0;i<TextBoxes.size();i++) {
            if(TextBoxes.get(i).getText().toString().matches("[0-9]+")) {
                int papa;
                papa = 0;
            }
            else {
                check = 1;
            }
        }
        if (check == 1) {
            validate = 1;
        }
        if (validate == 0) {
            final String CourseInfo = getIntent().getStringExtra("courseInfo");
            final String AssignmentID = getIntent().getStringExtra("assignmentID");
            final String GroupID = getIntent().getStringExtra("groupID");
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference assignsRefer = db.collection("Courses").document(CourseInfo).collection("Assignments").document(AssignmentID).collection("Groups").document(GroupID);
            assignsRefer.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot course = task.getResult();
                                ArrayList<Map<Object, Object>> mps = new ArrayList<Map<Object, Object>>();
                                mps = (ArrayList<Map<Object, Object>>) course.get("StudentList");
    //                            final int counter= 0;
                                System.out.println("khf ie gfiegfuiegfuigewufgewugffiuegfyuewgfigewfuiewuifgewuifgewugfgfigewifgewiufgewui");
                                cnt = 0;
                                cnt2 = 0;
                                final int lengthy = mps.size();
                                for (Map<Object, Object> mp : mps) {
                                    final Long Gradey = (Long) mp.get("Grade");

                                    final DocumentReference usr = (DocumentReference) mp.get("StudentID");
                                    usr.get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task2) {
                                                    if (task2.isSuccessful()) {
                                                        cnt++;
                                                        //System.out.println("sasA");
                                                        DocumentSnapshot user = task2.getResult();
                                                        String UserName = (String) user.get("Username");
                                                        for (int i = 0; i < TextBoxes.size(); i++) {
                                                            if (TextBoxes.get(i).getTag().toString().equals(UserName)) {
                                                                final String num = TextBoxes.get(i).getText().toString();
                                                                final Map<Object, Object> mp = new HashMap<>();
                                                                mp.put("Grade", Long.parseLong(num));
                                                                mp.put("StudentID", usr);
                                                                push.add(mp);
                                                                //System.out.println("sasA");
                                                                FirebaseFirestore dc = FirebaseFirestore.getInstance();
                                                                DocumentReference assn = dc.collection("Courses").document(CourseInfo);
                                                                //System.out.println("sasA");
                                                                assn.get()
                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                //System.out.println("sasA");
                                                                                if (task.isSuccessful()) {
                                                                                    //System.out.println("sasA");
                                                                                    DocumentSnapshot xd = task.getResult();
                                                                                    ArrayList<Map<Object, Object>> mpd = new ArrayList<Map<Object, Object>>();
                                                                                    mpd = (ArrayList<Map<Object, Object>>) xd.get("StudentList");
                                                                                    //System.out.println("sasA");
                                                                                    final int lengthy2 = mpd.size();
                                                                                    System.out.print(lengthy2);
                                                                                    System.out.println("sasA");


                                                                                    for (Map<Object, Object> mp : mpd) {
                                                                                        cnt2++;
                                                                                        Long TotGrade = (Long) mp.get("Grade");
                                                                                        DocumentReference usermain = (DocumentReference) mp.get("StudentID");
                                                                                        Map<Object, Object> mppo = new HashMap<>();

                                                                                        if (usermain.equals(usr)) {
                                                                                            TotGrade = TotGrade + Long.parseLong(num) - Gradey;
                                                                                            mppo.put("StudentID", usr);
                                                                                            mppo.put("Grade", TotGrade);
                                                                                            data.add(mppo);
                                                                                        }
                                                                                        if (cnt2 == lengthy * lengthy2) {
                                                                                            FirebaseFirestore db3 = FirebaseFirestore.getInstance();
                                                                                            DocumentReference assignsRef = db3.collection("Courses").document(CourseInfo);
                                                                                            for (Map<Object, Object> mpx : mpd) {
                                                                                                int x = 0;
                                                                                                for (int i = 0; i < data.size(); i++) {

                                                                                                    if (data.get(i).get("StudentID").equals(mpx.get("StudentID"))) {
                                                                                                        x = 1;
                                                                                                    }
                                                                                                }
                                                                                                if (x == 0) {
                                                                                                    System.out.print(mpx);
                                                                                                    System.out.println("sdgew gfiegfui gfigggrffgifigrfgrugf egiewg");
                                                                                                    data.add(mpx);
                                                                                                }
                                                                                            }
                                                                                            Map<String, Object> mpdggg = new HashMap<>();
                                                                                            mpdggg.put("StudentList", data);
                                                                                            System.out.print(mpdggg);
                                                                                            System.out.println("cbbravishankar ravi shankar j d gdgdheududf");
                                                                                            assignsRef.update(mpdggg);
                                                                                            data.clear();
                                                                                            mpdggg.clear();
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        });
                                                                if (cnt == lengthy) {
                                                                    FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                                                                    DocumentReference assignsRef = db2.collection("Courses").document(CourseInfo).collection("Assignments").document(AssignmentID).collection("Groups").document(GroupID);
                                                                    Map<String, Object> mpdg = new HashMap<>();
                                                                    mpdg.put("StudentList", push);
                                                                    System.out.print(mpdg);
                                                                    System.out.println("cfgfgfggaergegrgbb j d gdgdheududf");
                                                                    assignsRef.update(mpdg);
                                                                    mpdg.clear();
                                                                    push.clear();
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                            });


                                }
    //
    //
                            }
                        }
                    });
            Toast.makeText(AddGrade.this, "Grades Updated", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(AddGrade.this, "Grades Should Be Numbers", Toast.LENGTH_SHORT).show();
        }
    }
}
