package com.example.new_decuisine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    lateinit var rvListReview: RecyclerView
    val list = ArrayList<Review>()
    val adapter = ListReviewAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val review1 = Review("Nusantara","Cumi Cabai Hijau","Devina Hermawan","Sangat nikmat, jadi rindu desa",4)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        rvListReview = view.findViewById<RecyclerView>(R.id.rvReview)
        rvListReview.adapter = adapter
        rvListReview.layoutManager = LinearLayoutManager(context)
    }
}