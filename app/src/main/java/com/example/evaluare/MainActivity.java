package com.example.evaluare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAmount;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button buttonConvert;
    private TextView textViewResult;
    private Map<String, Double> currencyRates = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        currencyRates.put("USD", 0.24);
        currencyRates.put("EUR", 0.21);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        buttonConvert.setOnClickListener(v -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(editTextAmount.getText().toString());
            String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
            String toCurrency = spinnerToCurrency.getSelectedItem().toString();

            double rateFrom = currencyRates.getOrDefault(fromCurrency, 1.0);
            double rateTo = currencyRates.getOrDefault(toCurrency, 1.0);

            double convertedAmount = (amount / rateFrom) * rateTo;

            textViewResult.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    }