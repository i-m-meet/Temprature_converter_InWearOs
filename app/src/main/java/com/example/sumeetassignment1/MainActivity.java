package com.example.sumeetassignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sumeetassignment1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isCelsiusSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Default Color of buttons is blue
        defaultButtonColors();

        // Set Click Listeners
        binding.buttonCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCelsiusSelected = true;
                // To change color of selected button from blue to dark_blue
                updateButtonColors();
                //Conversion function called
                convertTemperature();
            }
        });

        binding.buttonFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCelsiusSelected = false;
                updateButtonColors();
                convertTemperature();
            }
        });
    }

    //Convert temperature on the basis of selected button
    private void convertTemperature() {
        String tempInput = binding.editTextTemperature.getText().toString();
        if (!tempInput.isEmpty()) {
            try {
                double inputTemp = Double.parseDouble(tempInput);
                if (isCelsiusSelected) {
                    // Convert Celsius to Fahrenheit
                    double convertedTemp = (inputTemp * 9 / 5) + 32;
                    binding.textViewConvertedTemp.setText(String.format("%.2f ℉", convertedTemp));
                } else {
                    // Convert Fahrenheit to Celsius
                    double convertedTemp = (inputTemp - 32) * 5 / 9;
                    binding.textViewConvertedTemp.setText(String.format("%.2f ℃", convertedTemp));
                }
            } catch (NumberFormatException e) {
                // Display an error message if input is not a valid number
                Toast.makeText(this, "Please enter a valid numeric temperature", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Empty output text view if no input is given
            binding.textViewConvertedTemp.setText("");
        }
    }

    //Default Color of buttons
    private void defaultButtonColors() {
        binding.buttonCelsius.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.blue));
        binding.buttonFahrenheit.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.blue));
    }
    //Update button colors on selecting
    private void updateButtonColors() {
        if (isCelsiusSelected) {
            //Set celsius button to dark blue and fahrenheit button blue
            binding.buttonCelsius.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_blue));
            binding.buttonFahrenheit.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.blue));
        } else {
            //Set celsius button to  blue and fahrenheit button dark blue
            binding.buttonFahrenheit.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_blue));
            binding.buttonCelsius.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.blue));
        }
    }
}

