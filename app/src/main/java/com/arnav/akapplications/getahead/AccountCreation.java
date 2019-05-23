package com.arnav.akapplications.getahead;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountCreation extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    public BottomNavigationView bottomNavigationView;

    public Toolbar toolbar;

    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

//        bottomNavigationView = findViewById(R.id.bottom_navigation);

        db = FirebaseFirestore.getInstance();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
//        toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setTitle(null);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Pager pager  = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        DocumentReference reference = db.collection("users").document("static_page");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot = task.getResult();
                tabLayout.getTabAt(0).setText(documentSnapshot.get("info_tab").toString());
                tabLayout.getTabAt(1).setText(documentSnapshot.get("accountCreation_tab").toString());

                Toast.makeText(getApplicationContext(),"Success:Fetching tab",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error:Fetching",Toast.LENGTH_LONG).show();
            }
        });


//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch(menuItem.getItemId())
//                {
//                    case R.id.action_sign_in:
//                        return true;
//                }
//            }
//        });


    }
}
