package vn.phatbee.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText("Ong Vĩnh Phát \n" +
                "Lớp: 221101ST1A \n" +
                "MSSV: 22110394");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtChan = findViewById(R.id.textViewChan);
        TextView txtLe = findViewById(R.id.textViewLe);
        Button btnSoNgauNhien = findViewById(R.id.btnSoNgauNhien);

        btnSoNgauNhien.setOnClickListener(v -> {
            ArrayList<Integer> listRandom = generateRandomNumbers(30);

            ArrayList<Integer> listChan = new ArrayList<>();
            ArrayList<Integer> listLe = new ArrayList<>();

            classifyNumbers(listRandom, listChan, listLe);
            displayNumbers(listChan, txtChan, "even");
            displayNumbers(listLe, txtLe, "odd");
        });

        EditText editTextVanBan = findViewById(R.id.editTextVanBan);
        TextView textViewDaoNguoc = findViewById(R.id.textViewDaoNguoc);
        Button btnDaoNguoc = findViewById(R.id.btnDaoNguoc);

        btnDaoNguoc.setOnClickListener(v -> {
            String input = editTextVanBan.getText().toString().trim();
            String reversed = reverseString(input);
            textViewDaoNguoc.setText(reversed);
            Toast.makeText(MainActivity.this, "Đảo ngược thành công: " + reversed, Toast.LENGTH_LONG).show();
        });
    }

    private String reverseString(String input){
        List<String> words = Arrays.asList(input.split(" "));
        Collections.reverse(words);
        return String.join(" ", words).toUpperCase();
    }

    private ArrayList<Integer> generateRandomNumbers(int count) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add((int) (Math.random() * 100));
        }
        return list;
    }

    private void classifyNumbers(ArrayList<Integer> listRandom, ArrayList<Integer> listChan, ArrayList<Integer> listLe) {
        for (int number : listRandom) {
            if (number % 2 == 0) {
                listChan.add(number);
            } else {
                listLe.add(number);
            }
        }
    }

    private void displayNumbers(ArrayList<Integer> list, TextView textView, String type) {
        Log.d("MainActivity", "Displaying numbers: " + list.toString());
        if (Objects.equals(type, "even")) {
            textView.setText("Số chẵn: ");
        } else {
            textView.setText("Số lẻ: ");

        }
        for (int number : list) {
            textView.append(number + ", ");
        }
    }


}