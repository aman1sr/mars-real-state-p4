

package com.example.android.marsrealestate.overview

import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter : ListAdapter<MarsProperty,
        PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    class MarsPropertyViewHolder {

    }

}