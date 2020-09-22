package com.example.cbmm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;
    private ImageView addFavoriteButton;
    private ImageView backButton;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    private static boolean ALREADY_ADDED_TO_FAVORITE = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        backButton = findViewById(R.id.product_details_back_btn);
        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addFavoriteButton = findViewById(R.id.add_favorite_button);
        productDetailsViewPager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.product_1st);
        productImages.add(R.drawable.product_2nd);
        productImages.add(R.drawable.product_3rd);
        productImages.add(R.drawable.product_4th);

        ProductimagesAdapter productimagesAdapter = new ProductimagesAdapter(productImages);
        productImagesViewPager.setAdapter(productimagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ALREADY_ADDED_TO_FAVORITE){

                    ALREADY_ADDED_TO_FAVORITE = false;
                    addFavoriteButton.setColorFilter(Color.rgb(154, 154, 154));

                }
                else {

                    ALREADY_ADDED_TO_FAVORITE = true;
                    addFavoriteButton.setColorFilter(Color.rgb(255, 135, 44));

                }

            }
        });

        productDetailsViewPager.setAdapter(new ProductDescriptionAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}