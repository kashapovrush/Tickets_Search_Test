package com.kashapovrush.main_screen.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashapovrush.common.adapter.offers.OffersAdapter
import com.kashapovrush.common.adapter.popular.PopularAdapter
import com.kashapovrush.common.constants.Constants
import com.kashapovrush.common.model.Popular
import com.kashapovrush.common.viewModel.CommonViewModel
import com.kashapovrush.main_screen.R
import com.kashapovrush.main_screen.databinding.FragmentMainBinding
import com.kashapovrush.main_screen.ui.di.MainComponentProvider
import com.kashapovrush.utils.ViewModelFactory
import com.kashapovrush.utils.preferenceManager.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var popularAdapter: PopularAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private lateinit var viewModel: CommonViewModel

    private var inputTextCache = EMPTY_TEXT

    private var list = listOf(
        Popular("Стамбул"), Popular("Сочи"), Popular("Пхукет")
    )

    override fun onAttach(context: Context) {
        (requireActivity().application as MainComponentProvider).getMainComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResult(
            Constants.NAVIGATION_STATE, bundleOf(
                Constants.STATE to Constants.MAIN_STATE
            )
        )

        inputTextCache = preferencesManager.getString(Constants.SAVE_INPUT_FROM) ?: EMPTY_TEXT
        binding.etFrom.setText(inputTextCache)

        viewModel = ViewModelProvider(this, viewModelFactory)[CommonViewModel::class.java]

        setRecyclerView()

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getOffers()
        }


        viewModel.offers.observe(viewLifecycleOwner) {
            offersAdapter.submitList(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error $it", Toast.LENGTH_SHORT).show()
        }


        binding.etFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if (!input.matches("[а-яА-Я]*".toRegex())) {
                    binding.etFrom.setText(input.replace(Regex("[^а-яА-Я]"), EMPTY_TEXT))
                    binding.etFrom.setSelection(binding.etFrom.text.length)
                }


            }

            override fun afterTextChanged(s: Editable?) {

                preferencesManager.putString(Constants.SAVE_INPUT_FROM, s.toString())
            }
        })

        binding.etTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if (!input.matches("[а-яА-Я]*".toRegex())) {
                    with(binding) {
                        etTo.setText(input.replace(Regex("[^а-яА-Я]"), EMPTY_TEXT))
                        etTo.setSelection(binding.etTo.text.length)
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    popularAdapter.submitList(list)
                    with(binding) {
                        titleText.visibility = View.GONE
                        rvFlightWithMusic.visibility = View.GONE
                        textOffers.visibility = View.GONE
                        rvPopular.visibility = View.VISIBLE
                        fastButtons.visibility = View.VISIBLE
                    }

                } else {
                    with(binding) {
                        titleText.visibility = View.VISIBLE
                        rvFlightWithMusic.visibility = View.VISIBLE
                        textOffers.visibility = View.VISIBLE
                        rvPopular.visibility = View.GONE
                        fastButtons.visibility = View.GONE
                    }

                }
            }
        })

        binding.etTo.setOnKeyListener(object : OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    (requireActivity() as ClickListenerFromMain).clickListenerToCountrySelected(binding.etTo.text.toString())
                    return true
                }
                return false
            }

        })

        binding.etFrom.setOnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.etFrom.right - binding.etFrom.compoundDrawables[drawableRight].bounds.width())) {
                    binding.etFrom.setText(EMPTY_TEXT)
                    preferencesManager.putString(Constants.SAVE_INPUT_FROM, EMPTY_TEXT)
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.etTo.setOnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.etTo.right - binding.etTo.compoundDrawables[drawableRight].bounds.width())) {
                    binding.etTo.setText(EMPTY_TEXT)
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.hard.setOnClickListener {
            (requireActivity() as ClickListenerFromMain).clickListenerToHard()
        }

        binding.weekend.setOnClickListener {
            (requireActivity() as ClickListenerFromMain).clickListenerToWeekend()
        }

        binding.hotTickets.setOnClickListener {
            (requireActivity() as ClickListenerFromMain).clickListenerToHotTickets()
        }

        binding.random.setOnClickListener {
            binding.etTo.setText(resources.getString(R.string.random))

        }

    }

    private fun setRecyclerView() {
        offersAdapter = OffersAdapter()
        binding.rvFlightWithMusic.adapter = offersAdapter
        binding.rvFlightWithMusic.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        popularAdapter = PopularAdapter()
        binding.rvPopular.adapter = popularAdapter
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext())

        popularAdapter.onItemClickListener = {
            binding.etTo.setText(it.town)
        }
    }

    interface ClickListenerFromMain {

        fun clickListenerToHard()

        fun clickListenerToHotTickets()

        fun clickListenerToWeekend()

        fun clickListenerToCountrySelected(value: String)
    }

    companion object {


        const val EMPTY_TEXT = ""


        fun newInstance() = MainFragment()
    }
}