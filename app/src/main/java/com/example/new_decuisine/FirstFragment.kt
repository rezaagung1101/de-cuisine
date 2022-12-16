package com.example.new_decuisine

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import de.hdodenhof.circleimageview.CircleImageView
import  io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
    //
    private val LOGS = 5
    lateinit var subscription : CompositeDisposable
    //
    lateinit var rvListRecipe: RecyclerView
    lateinit var search: SearchView
    lateinit var button: Button
    lateinit var image: CircleImageView
    val recipeList = ArrayList<Recipe>()
    val adapter = ListRecipeAdapter(recipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        search = view.findViewById(R.id.searchView) // continued in reactive fiture , code row 72
        add(recipeList)
        rvListRecipe = view.findViewById<RecyclerView>(R.id.rvListRecipe)
        rvListRecipe.adapter = adapter
        rvListRecipe.layoutManager = LinearLayoutManager(context)
        image = view.findViewById(R.id.profile_image)
        image.setOnClickListener {
            val intent = Intent(activity, DeCuisineProfile::class.java)
            startActivity(intent)
        }

        /// REACTIVE
        subscription = CompositeDisposable()
        val observable_search = Observable.create(ObservableOnSubscribe<String> { subscriber ->
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }

            })

        })
            .debounce(1000, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ text ->
                Log.d("search", "subscriber: $text")
                filterList(text)
            },
                {
                    Log.e("search","Erorr : $it")
                },
                {
                    Log.d("search","Complete")
                }
            )

        subscription.add(observable_search)

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

    fun add(list: ArrayList<Recipe>){
        list.add(Recipe(R.drawable.spagetti,"Spaghetti Bolognese","Kontinental","Devina Hermawan","250 g spaghetti kering\n" +
                "2.5 liter air\n" +
                "\n" +
                "◉ Saus Bolognese:\n" +
                "3 sdm mentega\n" +
                "50 g bawang bombay, cincang halus\n" +
                "3 siung bawang putih, cincang kasar\n" +
                "100 g jamur kancing, cincang\n" +
                "300 g daging sapi cincang\n" +
                "2 sdm tomat pasta\n" +
                "3 buah tomat merah, parut\n" +
                "1 sdt organo kering\n" +
                "2 sdt basil kering\n" +
                "2 lembar bayleaf\n" +
                "1/2 sdt merica hitam, memarkan\n" +
                "1 sdt gula pasir\n" +
                "2 sdt garam\n" +
                "250 ml air rebusan pasta\n" +
                "\n" +
                "◉ Taburan:\n" +
                "50 g keju parmesan, serut tipis\n" +
                "10 lembar daun basil segar"))
        list.add(Recipe(R.drawable.cottage_pie,"Cottage Pie","Kontinental","Arnold Poernomo","◉ Mashed potato:\n" +
                "5 butir kentang kupas rebus dan haluskan\n" +
                "100 ml susu cair\n" +
                "1/2 sdt pala bubuk\n" +
                "1/2 sdt bawang putih bubuk\n" +
                "Garam lada secukup nya\n" +
                "2 sdm butter atau margarine\n" +
                "1 cup keju cheddar parut\n" +
                "\n" +
                "◉ Filling:\n" +
                "500 gr daging sapi giling\n" +
                "2 batang celery potong kecil2\n" +
                "2 buah wortel kupas dan potong kecil\n" +
                "1/2 butir bawang bombay di cincang kasar\n" +
                "1 sdm tomato paste\n" +
                "1 cup atau 250 ml beef stock (kaldu sapi)\n" +
                "5 butir bawang putih cincang halus\n" +
                "1 sdm Worcestershire sauce (kecap inggris)\n" +
                "1 sdm butter\n" +
                "1 sdm olive oil\n" +
                "2 lembar bay leaves\n" +
                "Garam lada secukup nya"))
        list.add((Recipe(R.drawable.rendang,"Rendang","Nusantara","Luvita Ho","• 500 gr daging\n" +
                "• 1 batang serai geprek\n" +
                "• 3 lembar daun jeruk\n" +
                "• 2 lembar daun salam\n" +
                "• 1 sdt kaldu sapi dan garam\n" +
                "• 1/2 sdt jintan bubuk\n" +
                "• 1/2 sdt merica bubuk\n" +
                "• 1 ruas lengkuas geprek\n" +
                "• 2 cm kayu manis\n" +
                "• 1 sdt gula merah\n" +
                "• Jeruk nipis\n" +
                "• 500 ml santan dari 1/2 kelapa\n" +
                "\n" +
                "◉ Bumbu halus:\n" +
                "• 4 siung bawang putih\n" +
                "• 6 butir bawang merah\n" +
                "• 2 butir kemiri\n" +
                "• 1/2 sdt ketumbar\n" +
                "• 3 cabai merah besar (buang bijinya)\n" +
                "• 2 cm kunyit\n" +
                "• 3 cm jahe")))
        list.add((Recipe(R.drawable.cumi,"Cumi Cabai Hijau","Nusantara","Arnold Poernomo","750 gram cumi ukuran sedang.\n" +
                "20 Cabai hijau.\n" +
                "10 cabai rawit hijau.\n" +
                "4 siung bawang merah.\n" +
                "3 siung bawang putih. \n" +
                "1/4 buah bawang bombay. \n" +
                "Jahe secukupnya.\n" +
                "Lengkuas secukupnya.\n" +
                "Daun salam secukupnya.\n" +
                "Garam secukupnya.\n" +
                "Kaldu bubuk secukupnya.\n" +
                "Gula sesuai selera")))
        list.add((Recipe(R.drawable.phobo,"Pho Mie","Oriental","Devina Hermawan","◉ Daging dan Kuah\n" +
                "500 gram iga atau tulang sapi\n" +
                "500 gram daging sapi khas dalam\n" +
                "4 liter air atau sesuai selera untuk kuah\n" +
                "1/2 sendok makan garam\n" +
                "4 sendok makan kecap ikan\n" +
                "1 sendok teh kaldu sapi bubuk\n" +
                "1/2 sendok teh lada bubuk\n" +
                "1 sendok makan gula pasir\n" +
                "\n" +
                "◉Bahan Bumbu Cemplung\n" +
                "1 batang kayu manis\n" +
                "3 buah bunga lawang\n" +
                "2 butir cengkeh\n" +
                "3 butir kapulaga\n" +
                "1 ruas jahe, memarkan\n" +
                "1 buah bawang bombay, potong kasar\n" +
                "\n" +
                "◉Bahan Isian\n" +
                "Daging sapi yang untuk membuat kuah\n" +
                "500 gram mie pipih untuk kwetiau basah, rebus sebentar dalam air mendidih\n" +
                "1 buah bawang bombay, iris panjang halus\n" +
                "150 gram tauge segar secukupnya")))
        list.add((Recipe(R.drawable.tomyam,"Tom Yum Goong","Oriental","Devina Hermawan","1 liter Kaldu Ayam\n" +
                "5 gram Daun Jeruk\n" +
                "2 gram Bawang Putih\n" +
                "secukupnya Bawang Merah\n" +
                "5 cm Lengkuas (Laos)\n" +
                "4 batang Serai\n" +
                "12 ekor Udang Galah\n" +
                "100 gram Jamur Kancing (Champignon, Portabello)\n" +
                "8 buah Cabai (Cabe) Rawit Hijau\n" +
                "2 sendok makan Sambal Merah\n" +
                "2 buah Tomat\n" +
                "1 cangkir Santan\n" +
                "secukupnya Garam\n" +
                "3 sendok makan Lime Juice\n" +
                "1 sendok makan Fish sauce\n" +
                "secukupnya Daun Ketumbar")))
    }

    companion object {
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