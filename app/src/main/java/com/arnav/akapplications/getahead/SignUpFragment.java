package com.arnav.akapplications.getahead;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SignUpFragment extends Fragment {

    TextView createAccountTextView,passwordTextView,capitalTextView,numberTextView,eightCharTextView;
    EditText fullNameEditText, nickNameEditText, emailEditText, passwordEditText, phoneNum, pinCode;

    private Spinner country, states;

    RadioButton nickNameRadioButton;
    LinearLayout linearLayout;
    private String[] countries, canada, america;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.sign_up_fragment,container,false);


        countries = getResources().getStringArray(R.array.country_arrays);
        canada = getResources().getStringArray(R.array.canada);
        america = getResources().getStringArray(R.array.america);


        createAccountTextView = view.findViewById(R.id.headingTextView);
        passwordTextView = view.findViewById(R.id.passwordText);
        capitalTextView = view.findViewById(R.id.capitalLetterTextView);
        numberTextView = view.findViewById(R.id.numberTextView);
        eightCharTextView = view.findViewById(R.id.eightCharTextView);

        fullNameEditText = view.findViewById(R.id.nameEditText);
        nickNameEditText = view.findViewById(R.id.nickNameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);


        phoneNum = view.findViewById(R.id.edtTxt_phoneNumber);
        pinCode = view.findViewById(R.id.edtTxt_pinCode);
        country = view.findViewById(R.id.spin_country);
        states = view.findViewById(R.id.spin_province);

        nickNameRadioButton = view.findViewById(R.id.nicknameRadioButton);

        linearLayout = view.findViewById(R.id.linearLayout);
        linearLayout.setElevation((float)15.0);

        updateCountrySpinnerItems();
        
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //In case we need the string selection from the string array values
                //We can also use get the value by using states.getPrompt()
                //String selection = countries[position];

                if (position == 0) {
                    states.setAdapter(null);
                } else if (position == 1) {
                    updateStateSpinnerItems(canada);
                } else {
                    updateStateSpinnerItems(america);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //pass
            }

        });
    }

    private void updateCountrySpinnerItems() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.country_arrays));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        country.setAdapter(dataAdapter);
    }

    private void updateStateSpinnerItems(String[] states) {

        this.states.setAdapter(null);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, states);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.states.setAdapter(dataAdapter);
    }
}
