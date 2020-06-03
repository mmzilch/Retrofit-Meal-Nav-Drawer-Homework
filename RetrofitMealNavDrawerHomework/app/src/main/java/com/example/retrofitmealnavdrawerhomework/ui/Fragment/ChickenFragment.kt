package com.example.retrofitmealnavdrawerhomework.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmealnavdrawerhomework.R
import com.example.retrofitmealnavdrawerhomework.ui.Adapter.MealAdapter
import com.example.retrofitmealnavdrawerhomework.ui.ViewModel.ChickenViewModel
import kotlinx.android.synthetic.main.fragment_chicken.*

class ChickenFragment : Fragment() {

    private lateinit var mealAdapter: MealAdapter
    private lateinit var chickenViewModel: ChickenViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chicken, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        mealAdapter = MealAdapter()
        rcyChicken.apply {
            adapter = mealAdapter
            layoutManager = viewManager
            observeViewModel()
        }

    }

    private fun observeViewModel() {
        chickenViewModel = ViewModelProvider(this).get(ChickenViewModel::class.java)

        chickenViewModel.mealResult.observe(
            viewLifecycleOwner, Observer {
                rcyChicken.visibility = View.VISIBLE
                tvChickenError.visibility = View.GONE
                mealAdapter.updateMealList(it)
                //mealAdapter.data
                Log.d("UpdateList>>>",it.toString())
            }
        )

        chickenViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it)
                {
                    tvChickenError.visibility = View.VISIBLE
                    rcyChicken.visibility = View.GONE
                }

                else
                {
                    tvChickenError.visibility = View.GONE
                    rcyChicken.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        chickenViewModel.loadChicken("chicken")
    }

}
