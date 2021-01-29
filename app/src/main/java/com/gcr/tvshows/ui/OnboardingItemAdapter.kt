package com.gcr.tvshows.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gcr.tvshows.R
import com.gcr.tvshows.model.OnboardingItem

class OnboardingItemAdapter(private val onboardingItem: List<OnboardingItem>):
    RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container,parent,false)
        return OnboardingItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return onboardingItem.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItem[position])
    }

    inner class OnboardingItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imgOnboarding = view.findViewById<ImageView>(R.id.imgOnboarding)
        private val tvOnboardingTitle = view.findViewById<TextView>(R.id.tvOnboardingTitle)
        private val tvOnboardingDesc = view.findViewById<TextView>(R.id.tvOnboardingDesc)

       fun bind(onboardingItem:OnboardingItem){
            imgOnboarding.setImageResource(onboardingItem.onboardingImage)
            tvOnboardingTitle.text = onboardingItem.title
            tvOnboardingDesc.text = onboardingItem.description
        }
    }
}