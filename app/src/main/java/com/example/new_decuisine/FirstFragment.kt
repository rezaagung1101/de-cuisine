package com.example.new_decuisine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    lateinit var rvListRecipe: RecyclerView
    lateinit var search: SearchView
    lateinit var button: Button

    val recipeList = ArrayList<Recipe>()
    val adapter = ListRecipeAdapter(recipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //image
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //search
        var img : ImageView = view.findViewById(R.id.profile_image)
        img.setOnClickListener {
            val intent = Intent(context, DeCuisineProfile::class.java)
            context?.startActivity(intent)
        }
        search = view.findViewById(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })

        recipeList.add(Recipe(R.drawable.ayam_betutu,"Spaghetti Bolognese","Kontinental","Devina Hermawan"))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Cottage Pie","Kontinental","Arnold Poernomo")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Rendang","Nusantara","Luvita Ho")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Cumi Cabai Hijau","Nusantara","Arnold Poernomo")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Pho Mie","Oriental","Devina Hermawan")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Tom Yum Goong","Oriental","Devina Hermawan")))
        rvListRecipe = view.findViewById<RecyclerView>(R.id.rvListRecipe)
        rvListRecipe.adapter = adapter
        rvListRecipe.layoutManager = LinearLayoutManager(context)

//        button = view.findViewById(R.id.button)

//        button.setOnClickListener {
//            val intent = Intent(activity, DetailActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun filterList(newText: String?) {
        val filteredList = ArrayList<Recipe>()
        for (item in recipeList){
            if (newText != null) {
                if (item.name.lowercase().contains(newText.lowercase())){
                    filteredList.add(item)
                }
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(activity,"Resep tidak ditemukan", Toast.LENGTH_SHORT).show()
        }else  {
            adapter.setFilteredList(filteredList)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}