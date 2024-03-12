package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TextView showCountTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view1 = binding.getRoot();

        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        showCountTextView = binding.textviewFirst;

        view1.findViewById(R.id.random_button).setOnClickListener(view -> NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment));

        view1.findViewById(R.id.toast_button).setOnClickListener(view -> {
            Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
            myToast.show();
        });

        view1.findViewById(R.id.count_button).setOnClickListener(view -> countMe(view));


        return view1;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.randomButton.setOnClickListener(view1 -> {
            int currentCount = Integer.parseInt(showCountTextView.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putInt("currentCount", currentCount);
            getParentFragmentManager().setFragmentResult("requestKey", bundle);

            Navigation.findNavController(view1).navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void countMe(View view) {
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();
        // Convert value to a number and increment it
        int count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(Integer.toString(count));
    }

}