package com.example.kadesubmisidua.view.fragment.childfragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.FavoriteAdapter
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import com.example.kadesubmisidua.view.activity.DetailPreviousActivity
import kotlinx.android.synthetic.main.fragment_favorite_previous.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritePreviousFragment(private val itemFavorite : ArrayList<FavoriteMatch>) : Fragment() {

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favorite_previous, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteAdapter(itemFavorite){
            val intent = Intent(context,DetailPreviousActivity::class.java)
            intent.putExtra("id_event",it.eventId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        }

        view.fragmentfavoriteprevious_rv.layoutManager = LinearLayoutManager(context)
        view.fragmentfavoriteprevious_rv.adapter = favoriteAdapter
    }

}
