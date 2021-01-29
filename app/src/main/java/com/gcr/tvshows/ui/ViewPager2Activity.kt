package com.gcr.tvshows.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gcr.tvshows.R
import com.gcr.tvshows.model.OnboardingItem
import kotlinx.android.synthetic.main.activity_view_pager2.*

class ViewPager2Activity : AppCompatActivity() {

    private lateinit var adapter: OnboardingItemAdapter
    private lateinit var indicator: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        setUpOnboarding()
        setUpIndicator()
        setCurrentIndicator(0)
    }

    private fun setUpOnboarding() {
        adapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    R.drawable.onboarding_1,
                    "Comienza ahora",
                    "App demo viewpager2"
                ),
                OnboardingItem(
                    R.drawable.onboarding_2,
                    "Nuevas funcionalidades",
                    "Desliza..."
                ),
                OnboardingItem(
                    R.drawable.onboarding_3,
                    "Servicios nuevos",
                    "componentes nunca antes vistos"
                )
            )
        )

        onboardingViewPager.adapter = adapter
        onboardingViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        imgNext.setOnClickListener {
            if(onboardingViewPager.currentItem + 1 < adapter.itemCount){
                onboardingViewPager.currentItem += 1
            }else{
                navigate()
            }
        }
        tvSkip.setOnClickListener {
            navigate()
        }
    }

    private fun navigate(){
        startActivity(Intent(applicationContext,MainActivity::class.java))
        finish()
    }

    private fun setUpIndicator(){
        indicatorsContainer
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for (i in indicators.indices){
            indicators[i] = ImageView((applicationContext))
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position:Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_background
                    ))
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}