package com.example.demomonsterhunter.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demomonsterhunter.R
import com.example.demomonsterhunter.data.network.Resource
import com.example.demomonsterhunter.databinding.FragmentArmorListBinding
import com.example.demomonsterhunter.viewModels.ArmorViewModel
import com.example.demomonsterhunter.views.adapters.ArmorListAdapter

class ArmorListFragment : Fragment() {

    private var _binding: FragmentArmorListBinding? = null

    private val mViewModel by viewModels<ArmorViewModel>()

    private val mAdapter: ArmorListAdapter = ArmorListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArmorListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        if (mViewModel.armorLists.toList().isEmpty()) {
            getArmorList()
        } else {
            lifecycleScope.launchWhenResumed {
                mAdapter.submitList(mViewModel.armorLists.toList())
            }
        }
    }

    private fun getArmorList() {
        mViewModel.getArmorList().observe(viewLifecycleOwner, {resource ->
            resource?.let {
                when(it.status) {
                    Resource.Status.SUCCESS ->{
                        _binding?.progress?.visibility = View.GONE
                        lifecycleScope.launchWhenResumed {
                            mAdapter.submitList(resource.data?.data)
                        }
                        mViewModel.armorLists = resource.data?.data?.asSequence() ?: sequenceOf()
                    }
                    Resource.Status.ERROR -> {
                        _binding?.progress?.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.data?.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING -> {
                        _binding?.progress?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.armor_list)
            rvArmor.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvArmor.adapter = mAdapter
            etSearchView.addTextChangedListener {test->
                val query = test?.toString() ?: ""
                if (query.isEmpty()) {
                    lifecycleScope.launchWhenResumed {
                        mAdapter.submitList(mViewModel.armorLists.toList())
                    }
                } else {
                    val list = mViewModel.armorLists.filter { it.name?.contains(query) ?: false }
                    lifecycleScope.launchWhenResumed {
                        mAdapter.submitList(list.toList())
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}