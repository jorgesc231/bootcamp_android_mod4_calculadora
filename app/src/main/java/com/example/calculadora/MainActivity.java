package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.calculadora.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    int numero1, numero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding.checkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> binding.btRegistro.setEnabled(isChecked));

        binding.btSuma.setOnClickListener(v -> {
            if (checkParameters()) binding.tvResult.setText(String.format(Locale.FRANCE, "%d", numero1 + numero2));
        });

        binding.btResta.setOnClickListener(v -> {
            if (checkParameters()) binding.tvResult.setText(String.format(Locale.FRANCE, "%d", numero1 - numero2));
        });

        binding.btMulti.setOnClickListener(v -> {
            if (checkParameters()) binding.tvResult.setText(String.format(Locale.FRANCE, "%d", numero1 * numero2));
        });

        binding.btDiv.setOnClickListener(v -> {
            if (checkParameters()) {
                if (numero2 > 0) binding.tvResult.setText(String.format(Locale.FRANCE, "%.2f", numero1 / (float) numero2));
                else {
                    binding.tvResult.setText("División por 0");
                    Toast.makeText(this, "No se puede dividir por 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btExit.setOnClickListener(v -> {
            // Cerrar app
            finish();   // won't exit the application, it just kills the activity
            System.exit(0);
        });
    }

    Boolean checkParameters() {
        String num1_string = binding.inputPrimero.getEditText().getText().toString();
        String num2_string = binding.inputSegundo.getEditText().getText().toString();

        boolean result = true;

        if (!num1_string.isEmpty()) {
            binding.inputPrimero.setError(null);
            try {
                numero1 = Integer.parseInt(num1_string);
            } catch (Exception e) {
                binding.inputPrimero.setError("Ingresa un numero valido");

                result = false;
            }
        } else {
            binding.inputPrimero.setError("Debes ingresar un numero!");

            result = false;
        }

        if (!num2_string.isEmpty()) {
            binding.inputSegundo.setError(null);
            try {
                numero2 = Integer.parseInt(num2_string);
            } catch (Exception e) {
                binding.inputSegundo.setError("Ingresa un numero valido");

                result = false;
            }
        } else {
            binding.inputSegundo.setError("Debes ingresar un numero!");

            result = false;
        }

        return result;
    }
}