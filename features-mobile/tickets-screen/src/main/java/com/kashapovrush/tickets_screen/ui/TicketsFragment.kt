package com.kashapovrush.tickets_screen.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.adapter.tickets.TicketsAdapter
import com.kashapovrush.common.viewModel.CommonViewModel
import com.kashapovrush.tickets_screen.R
import com.kashapovrush.tickets_screen.databinding.FragmentTicketsBinding
import com.kashapovrush.tickets_screen.di.TicketsComponentProvider
import com.kashapovrush.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsFragment : Fragment() {

    private lateinit var binding: FragmentTicketsBinding
    private lateinit var viewModel: CommonViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var ticketAdapter: TicketsAdapter

    override fun onAttach(context: Context) {
        (requireActivity().application as TicketsComponentProvider).getTicketsComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.routeText.text = arguments?.getString(EXTRA_ROUTE)
        binding.infoText.text = " ${arguments?.getString(EXTRA_DATE)}, 1 пассажир"

        setRecyclerView()

        viewModel = ViewModelProvider(this, viewModelFactory)[CommonViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getTickets()
        }

        viewModel.tickets.observe(viewLifecycleOwner) {
            ticketAdapter.submitList(it)
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setRecyclerView() {
        ticketAdapter = TicketsAdapter()
        with(binding.rvTickets) {
            adapter = ticketAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {

        const val EXTRA_ROUTE = "route"
        const val EXTRA_DATE = "date"

        fun newInstance(route: String, date: String) = TicketsFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_ROUTE, route)
                putString(EXTRA_DATE, date)
            }
        }
    }
}