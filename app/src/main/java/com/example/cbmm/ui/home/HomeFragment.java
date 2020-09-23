package com.example.cbmm.ui.home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cbmm.CategoryAdapter;
import com.example.cbmm.CategoryModel;
import com.example.cbmm.GridProductLayoutAdapter;
import com.example.cbmm.HomePageAdapter;
import com.example.cbmm.HomePageModel;
import com.example.cbmm.HorizontalProductScrollAdapter;
import com.example.cbmm.HorizontalProductScrollModel;
import com.example.cbmm.R;
import com.example.cbmm.SliderAdapter;
import com.example.cbmm.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {





    public HomeFragment() {
        // Required empty public constructor
    }


    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);


        List <CategoryModel> categoryModelList = new ArrayList<CategoryModel>();

        categoryModelList.add(new CategoryModel("Link","Home"));
        categoryModelList.add(new CategoryModel("Link","Gadget"));
        categoryModelList.add(new CategoryModel("Link","Clothing"));
        categoryModelList.add(new CategoryModel("Link","Furniture"));
        categoryModelList.add(new CategoryModel("Link","Mobile"));
        categoryModelList.add(new CategoryModel("Link","Computer"));
        categoryModelList.add(new CategoryModel("Link","Game"));
        categoryModelList.add(new CategoryModel("Link","Shoes"));
        categoryModelList.add(new CategoryModel("Link","Electronics"));
        categoryModelList.add(new CategoryModel("Link","Car"));
        categoryModelList.add(new CategoryModel("Link","Glasses"));
        categoryModelList.add(new CategoryModel("Link","Bike"));
        categoryModelList.add(new CategoryModel("Link","T-Shirts"));
        categoryModelList.add(new CategoryModel("Link","Pants"));

        categoryAdapter = new CategoryAdapter(categoryModelList);

        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();



        //////////////////////////////////Banner Slider Codes

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();



        sliderModelList.add(new SliderModel(R.drawable.banner3,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner4,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner2,"#077AE4"));



        sliderModelList.add(new SliderModel(R.drawable.banner1,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner3,"#077AE4"));



        sliderModelList.add(new SliderModel(R.drawable.banner4,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#077AE4"));




        /////////////////////////////////Banner Slider Codes



        ///////////////////////Horizontal Product Layout


        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.galaxy_s4, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.laptops, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobiles, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.shoess, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.glasses, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.hats, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.tshirts, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.female_dresses, "Galaxy S4", "SD Processor","BDT 12000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.watches, "Galaxy S4", "SD Processor","BDT 12000/-"));


        ///////////////////////Horizontal Product Layout


        ///////////////////////////////////////////////////////


        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();

        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.strip_ad,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.strip_ad,"#000000"));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        ///////////////////////////////////////////////////////


        return view;
    }


}