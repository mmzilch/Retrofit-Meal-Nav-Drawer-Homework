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
import com.example.retrofitmealnavdrawerhomework.ui.ViewModel.SeafoodViewModel
import kotlinx.android.synthetic.main.fragment_chicken.*
import kotlinx.android.synthetic.main.fragment_seafood.*

class SeafoodFragment : Fragment() {

    private lateinit var mealAdapter: MealAdapter
    private lateinit var seafoodViewModel: SeafoodViewModel
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_seafood, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        mealAdapter = MealAdapter()
        rcySeafood.apply {
            adapter = mealAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        seafoodViewModel = ViewModelProvider(this).get(SeafoodViewModel::class.java)

        seafoodViewModel.mealResult.observe(
            viewLifecycleOwner, Observer {
                rcySeafood.visibility = View.VISIBLE
                tvSeafoodError.visibility = View.GONE
                mealAdapter.updateMealList(it)
                //mealAdapter.data
                Log.d("UpdateList>>>",it.toString())
            }
        )

        seafoodViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it)
                {
                    tvSeafoodError.visibility = View.VISIBLE
                    rcySeafood.visibility = View.GONE
                }

                else
                {
                    tvSeafoodError.visibility = View.GONE
                    rcySeafood.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        seafoodViewModel.loadSeafood("seafood")
    }
}