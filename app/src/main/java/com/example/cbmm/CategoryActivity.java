package com.example.cbmm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private ImageView backButton;
    private TextView categoryTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryTitle = findViewById(R.id.product_details_title);
        backButton = findViewById(R.id.product_details_back_btn);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        categoryTitle.setText(title);
        //getSupportActionBar().setTitle(title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_activity_recyclerview);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();

        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.strip_ad,"#000000"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(2,"Deal of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.strip_ad,"#000000"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(3,"Hot",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.strip_ad,"#000000"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        ///////////////////////////////////////////////////////



    }
}