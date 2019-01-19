package com.potalika.potalika;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    /////////Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;



    //////////Banner Slider

    ///////strip Ad
   /// private ImageView stripAdImage;
    ///private ConstraintLayout stripAdContainer;
    /////Strip Ad

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_home, container, false );

        categoryRecyclerView = view.findViewById ( R.id.category_recyclerview );
        LinearLayoutManager layoutManager = new LinearLayoutManager ( getActivity () );
        layoutManager.setOrientation ( LinearLayoutManager.HORIZONTAL );
        categoryRecyclerView.setLayoutManager ( layoutManager );


        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel> ( );
        categoryModelList.add ( new CategoryModel ( "link" ,"Home") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Electronic") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Food") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Cack") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Movers") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Furniture") );
        categoryModelList.add ( new CategoryModel ( "link" ,"Parcel") );


        categoryAdapter = new CategoryAdapter ( categoryModelList );
        categoryRecyclerView.setAdapter ( categoryAdapter );
        categoryAdapter.notifyDataSetChanged ();


        //////////Banner Slider

        bannerSliderViewPager = view.findViewById ( R.id.banner_slider_view_pager );

        sliderModelList = new ArrayList<SliderModel> (  );
        ///last image
        sliderModelList.add ( new SliderModel ( R.drawable.img_checkbox_uncheck,"#077AE4" ) );
        sliderModelList.add ( new SliderModel ( R.drawable.my_account,"#077AE4" ) );

        sliderModelList.add ( new SliderModel ( R.drawable.img_email,"#077AE4" ) );
        sliderModelList.add ( new SliderModel ( R.drawable.home_icon,"#077AE4" ) );
        sliderModelList.add ( new SliderModel ( R.drawable.cart_black ,"#077AE4") );
        sliderModelList.add ( new SliderModel ( R.drawable.cart_white ,"#077AE4") );
        sliderModelList.add ( new SliderModel ( R.drawable.bell,"#077AE4") );
        sliderModelList.add ( new SliderModel ( R.drawable.add_profile_image,"#077AE4" ) );
        sliderModelList.add ( new SliderModel ( R.drawable.img_checkbox_uncheck ,"#077AE4") );
        sliderModelList.add ( new SliderModel ( R.drawable.banner,"#077AE4" ) );

/////////////////first two image
        sliderModelList.add ( new SliderModel ( R.drawable.img_email ,"#077AE4") );
        sliderModelList.add ( new SliderModel ( R.drawable.home_icon,"#077AE4" ) );

        SliderAdapter sliderAdapter = new SliderAdapter ( sliderModelList );
        bannerSliderViewPager.setAdapter ( sliderAdapter );
        bannerSliderViewPager.setClipToPadding ( false );
        bannerSliderViewPager.setPageMargin ( 20 );

        bannerSliderViewPager.setCurrentItem ( currentPage );

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper ();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener ( onPageChangeListener );

        startBannerSlideShow ();

        bannerSliderViewPager.setOnTouchListener ( new View.OnTouchListener () {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                pageLooper ();
                stopBannerSlideShow ();
                if (motionEvent.getAction () == MotionEvent.ACTION_UP){

                    startBannerSlideShow ();
                }
                return false;
            }
        } );


        ////////Banner Slider


        //////Strp Ad

        ////stripAdImage = view.findViewById ( R.id.strip_ad_image );
        ///stripAdContainer = view.findViewById ( R.id.strip_ad_container );
        ///stripAdImage.setImageResource ( R.drawable.banner );
        ////stripAdImage.setBackgroundColor ( Color.parseColor ( "#000000" ) );






        /////Strip Ad
        return view;
    }
    //////Banner Slide

    private void pageLooper(){
        if(currentPage == sliderModelList.size () -2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem ( currentPage,false );
        }
        if(currentPage == 1){
            currentPage = sliderModelList.size () -3;
            bannerSliderViewPager.setCurrentItem ( currentPage,false );
        }



    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler (  );
        final Runnable update = new Runnable () {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size ()){
                    currentPage =1;
                }
                bannerSliderViewPager.setCurrentItem ( currentPage++, true );
            }
        };
        timer = new Timer (  );
        timer.schedule ( new TimerTask () {
            @Override
            public void run() {
                handler.post ( update );
            }
        }, DELAY_TIME,PERIOD_TIME);
    }
    private void stopBannerSlideShow(){
        timer.cancel ();
    }

////////////////
}
