package com.example.new_decuisine


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondFragment : Fragment() {
    lateinit var rvListReview: RecyclerView
    val list = ArrayList<Review>()
    val adapter = ListReviewAdapter(list)
    lateinit var btnTambah: Button

    companion object{
        const val CAT = "category"
        const val RESEP = "resep"
        const val CREATOR = "creator"
        const val REVIEW = "ingridients"
        const val RATING = "rate"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val review1 = Review("Nusantara","Cumi Cabai Hijau","Devina Hermawan","Sangat nikmat, jadi rindu desa",3.2)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        list.add(review1)
        val data = arguments
        if(data!=null){
            val creator = data!!.getString(CREATOR)
            val cat = data!!.getString(CAT)
            val review = data!!.getString(REVIEW)
            val rate = data!!.getDouble(RATING)
            val resep = data!!.getString(RESEP)
//            list.add(Review(cat!!,resep!!,creator!!, review!!,rate!!))
        }
        rvListReview = view.findViewById<RecyclerView>(R.id.rvReview)
        rvListReview.adapter = adapter
        rvListReview.layoutManager = LinearLayoutManager(context)

        btnTambah = view.findViewById(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val intent = Intent(activity, FormReview::class.java)
            startActivity(intent)
        }
    }
}