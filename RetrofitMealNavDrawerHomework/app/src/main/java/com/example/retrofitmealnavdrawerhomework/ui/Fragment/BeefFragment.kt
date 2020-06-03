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
import com.example.retrofitmealnavdrawerhomework.ui.ViewModel.BeefViewModel
import kotlinx.android.synthetic.main.fragment_beef.*

class BeefFragment : Fragment() {

    private lateinit var mealAdapter: MealAdapter
    private lateinit var beefViewModel: BeefViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_beef, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity, 2)
        mealAdapter = MealAdapter()
        rcyBeef.apply {
            adapter = mealAdapter
            layoutManager = viewManager
            observeViewModel()
        }

    }

    private fun observeViewModel() {
        beefViewModel = ViewModelProvider(this).get(BeefViewModel::class.java)

        beefViewModel.mealResult.observe(
            viewLifecycleOwner, Observer {
                rcyBeef.visibility = View.VISIBLE
                tvBeefError.visibility = View.GONE
                mealAdapter.updateMealList(it)
                //mealAdapter.data
                Log.d("UpdateList>>>", it.toString())
            }
        )

        beefViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it) {
                    tvBeefError.visibility = View.VISIBLE
                    rcyBeef.visibility = View.GONE
                } else {
                    tvBeefError.visibility = View.GONE
                    rcyBeef.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        beefViewModel.loadBeef("beef")
    }
}
