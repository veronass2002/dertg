package com.example.spor.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spor.FilmInfo;
import com.example.spor.MyHelper;
import com.example.spor.R;
import com.example.spor.databinding.FragmentMainBinding;
import com.example.spor.filmsAdapter;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public FragmentMainBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MyHelper.fillTrend ();
        MyHelper.fillNew();
        MyHelper.fillForyou ();

        Picasso.get().load("http://cinema.areas.su/up/images/magicians.png").placeholder(R.drawable.errimg).fit ().into(binding.imageView3);


        RecyclerView recyclerViewTrend = binding.scrollView2.findViewById(R.id.recTrend);
        filmsAdapter adapterTrend = new filmsAdapter(getLayoutInflater(), MyHelper.InTrend);
        recyclerViewTrend.setAdapter(adapterTrend);

        RecyclerView recyclerViewNew = binding.scrollView2.findViewById(R.id.recNew);
        filmsAdapter adapterNew = new filmsAdapter(getLayoutInflater(), MyHelper.New);
        recyclerViewNew.setAdapter(adapterNew);

        RecyclerView recyclerViewForyou = binding.scrollView2.findViewById(R.id.recForyou);
        filmsAdapter adapterForyou = new filmsAdapter(getLayoutInflater(), MyHelper.ForYou);
        recyclerViewForyou.setAdapter(adapterForyou);

        binding.textView4.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent ( getContext (), FilmInfo.class);
                startActivity (intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}