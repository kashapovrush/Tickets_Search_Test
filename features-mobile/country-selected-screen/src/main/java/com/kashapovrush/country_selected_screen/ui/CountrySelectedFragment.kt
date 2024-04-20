package com.kashapovrush.country_selected_screen.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashapovrush.common.adapter.offers_tickets.OffersTicketsAdapter
import com.kashapovrush.common.constants.Constants
import com.kashapovrush.common.viewModel.CommonViewModel
import com.kashapovrush.country_selected_screen.databinding.FragmentCoutrySelectedBinding
import com.kashapovrush.country_selected_screen.di.CountrySelectedComponentProvider
import com.kashapovrush.utils.ViewModelFactory
import com.kashapovrush.utils.preferenceManager.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CountrySelectedFragment : Fragment() {

    private lateinit var calendar: Calendar
    private lateinit var dateFrom: DatePickerDialog.OnDateSetListener
    private lateinit var dateTo: DatePickerDialog.OnDateSetListener
    private lateinit var binding: FragmentCoutrySelectedBinding
    private var dateFromTransfer = EMPTY_TEXT
    private var dateToTransfer = EMPTY_TEXT
    private var date = EMPTY_TEXT

    private lateinit var offersTicketsAdapter: OffersTicketsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private lateinit var viewModel: CommonViewModel


    override fun onAttach(context: Context) {
        (requireActivity().application as CountrySelectedComponentProvider).getCountrySelectedComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoutrySelectedBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCalendarView()
        binding.etFrom.setText(preferencesManager.getString(Constants.SAVE_INPUT_FROM))
        binding.etTo.setText(arguments?.getString(Constants.SAVE_INPUT_TO))
        setRecyclerView()

        viewModel = ViewModelProvider(this, viewModelFactory)[CommonViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getOffersTickets()
        }

        viewModel.offersTickets.observe(viewLifecycleOwner) {
            offersTicketsAdapter.submitList(it)
        }

        binding.etFrom.setOnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.etFrom.right - binding.etFrom.compoundDrawables[drawableRight].bounds.width())) {
                    var textFromTemp = binding.etFrom.text
                    var textToTemp = binding.etTo.text
                    binding.etTo.text = textFromTemp
                    binding.etFrom.text = textToTemp
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.btnDateFrom.setOnClickListener {
            createDatePicker(dateFrom)

        }

        binding.btnDateTo.setOnClickListener {
            createDatePicker(dateTo)

        }

        binding.btnToTickets.setOnClickListener {
            val cityFrom = binding.etFrom.text.toString()
            val cityTo = binding.etTo.text.toString()
            val dateTo: String = binding.dateTo.text.toString()


            if (dateFromTransfer == EMPTY_TEXT) {
                dateFromTransfer = "24 февраля"
            }
            if (dateTo != "обратно") {
                date = "$dateFromTransfer- $dateToTransfer"
            } else {
                date = dateFromTransfer
            }
            (requireActivity() as ClickListenerFromCountrySelected).clickListenerToTickets(
                route = "$cityFrom- $cityTo",
                date = date
            )

            dateFromTransfer = EMPTY_TEXT
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun createDatePicker(date: DatePickerDialog.OnDateSetListener) {
        val datePicker = DatePickerDialog(
            requireContext(),
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        if (date != dateFrom) {
            datePicker.setOnCancelListener {
                with(binding) {
                    dateTo.text = "обратно"
                    imagePlus.visibility = View.VISIBLE
                    dayTo.visibility = View.GONE
                }


            }

        }
        datePicker.show()

    }

    private fun setCalendarView() {
        calendar = Calendar.getInstance()

        dateFrom = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            update(calendar, dateFrom)

        }


        dateTo = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            update(calendar, dateTo)
        }
    }

    private fun update(calendar: Calendar, date: OnDateSetListener) {
        val russianLocale = Locale("ru")
        val formatDate = "dd MMM "
        val formatDay = "EEE"
        val format = "dd MMMM"
        val sdfDate = SimpleDateFormat(formatDate, russianLocale)
        val sdfDay = SimpleDateFormat(formatDay, russianLocale)
        val sdf = SimpleDateFormat(format, russianLocale)

        when (date) {
            dateFrom -> {
                binding.dateFrom.text = sdfDate.format(calendar.time)
                binding.dayFrom.text = sdfDay.format(calendar.time)
                dateFromTransfer = sdf.format(calendar.time)
            }

            dateTo -> {
                with(binding) {
                    dateTo.text = sdfDate.format(calendar.time)
                    dayTo.text = sdfDay.format(calendar.time)
                    imagePlus.visibility = View.GONE
                    dayTo.visibility = View.VISIBLE
                    dateToTransfer = sdf.format(calendar.time)
                }
            }
        }
    }

    private fun setRecyclerView() {
        offersTicketsAdapter = OffersTicketsAdapter()
        with(binding.rvOffersTickets) {
            adapter = offersTicketsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    interface ClickListenerFromCountrySelected {

        fun clickListenerToTickets(route: String, date: String)
    }


    companion object {

        const val EMPTY_TEXT = ""

        fun newInstance(value: String) = CountrySelectedFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.SAVE_INPUT_TO, value)
            }
        }
    }
}