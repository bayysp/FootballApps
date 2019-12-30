package com.example.kadesubmisidua.view.fragment.childfragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.FavoriteTeamAdapter
import com.example.kadesubmisidua.model.favorite.FavoriteTeam
import com.example.kadesubmisidua.view.activity.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment(private val favoriteTeam : ArrayList<FavoriteTeam>) : Fragment() {

    private lateinit var favoriteTeamAdapter : FavoriteTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteTeamAdapter = FavoriteTeamAdapter(favoriteTeam){
            val intent = Intent (context, DetailTeamActivity::class.java)
            intent.putExtra("id_team",it.teamId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        view.fragmentfavoriteam_rv.layoutManager = GridLayoutManager(context,3)
        view.fragmentfavoriteam_rv.adapter = favoriteTeamAdapter


    }
}
