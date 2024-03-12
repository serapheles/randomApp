package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
//        System.out.println(savedInstanceState.toString());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
            Based on the example found at https://developer.android.com/guide/fragments/communicate
            I'd like to try and make this cleaner, but this took way more effort than it should have.
         */
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {

            int count = bundle.getInt("currentCount");

            String countText = getString(R.string.random_heading, count);
            TextView headerView = view.getRootView().findViewById(R.id.textview_header);
            headerView.setText(countText);

            Random random = new Random();
            int randomNumber = 0;
            if (count > 0) {
                randomNumber = random.nextInt(count + 1);
            }

            TextView randomView = view.getRootView().findViewById(R.id.textview_random);
            randomView.setText(Integer.toString(randomNumber));
        });

        binding.buttonSecond.setOnClickListener(v -> NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}